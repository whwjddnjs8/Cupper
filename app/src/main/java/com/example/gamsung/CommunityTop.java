package com.example.gamsung;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.util.AbstractCollection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

public class CommunityTop extends AppCompatActivity {
    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference databaseReference = firebaseDatabase.getReference();
    private RecyclerView recyclerView;
    public static List<AllCommunity> allCommunityList = new ArrayList<>();
    private List<Community> communityList = new ArrayList<>();
    private List<Community> likecntList = new ArrayList<>();
    private CommunityTopAdapter communityTopAdapter;
    public String[] likearr = new String[50];
    public int[] intlikearr = new int[50]; //최종 pos가 들어갈자리
    public String pos;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.community_top);

//        Intent intent = getIntent();
//        pos = intent.getStringExtra("pos");
//        System.out.println("pos 잘 불러왓니??" + pos);


        //intlikearr 초기화
        for(int k=0;k<50;k++) {
            intlikearr[k] = k;
            //pos가 들어가서 배열 정리가 되면 그 pos 5개만 보내줌
            System.out.println( " 인트배열 " + intlikearr[0] + intlikearr[1] + intlikearr[2]);
        }

        recyclerView = (RecyclerView)findViewById(R.id.recycler_view);
        communityTopAdapter = new CommunityTopAdapter(this, communityList);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.setAdapter(communityTopAdapter);
        prepareCommunityTop();
    }

    public void prepareCommunityTop() {
        //데이터베이스에서 커뮤니티의 글을 모두 불러오는 작업
        //TODO likecnt를 불러와서 그 중 제일 높은 (일단)5개만 추출하여 리스트화 하기
        databaseReference.child("커뮤니티게시판/community").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                AllCommunity allCommunity = dataSnapshot.getValue(AllCommunity.class);

                Community c = new Community(allCommunity.getUserDisplayname(),allCommunity.getPhoto(), allCommunity.getUseremail(),
                        allCommunity.getSubject(), allCommunity.getMaterial(), allCommunity.getText(),String.valueOf(communityList.size()),allCommunity.getLikecnt(),allCommunity.getPos());
                if(allCommunityList.size() < communityList.size()+1) {
                    allCommunityList.add(allCommunity);
                }
                if(communityList.size() < 10) {
                    communityList.add(c);
                }

                System.out.println(String.valueOf(communityList.size()));

                Collections.sort(communityList, new Comparator<Community>() {
                    @Override
                    public int compare(Community community, Community community2) {
                        return Integer.valueOf(community.getLikecnt()).compareTo(Integer.valueOf(community2.getLikecnt()));
                    }
                });
                Collections.reverse(communityList);

                }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                AllCommunity allCommunity = dataSnapshot.getValue(AllCommunity.class);
                Community c = new Community(allCommunity.getUserDisplayname(),allCommunity.getPhoto(), allCommunity.getUseremail(),
                        allCommunity.getSubject(), allCommunity.getMaterial(), allCommunity.getText(),String.valueOf(communityList.size()),allCommunity.getLikecnt(),allCommunity.getPos());
                allCommunityList.add(allCommunity);
                communityList.add(c);
                System.out.println(String.valueOf(communityList.size()));
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        communityTopAdapter.notifyDataSetChanged();
    }

}

