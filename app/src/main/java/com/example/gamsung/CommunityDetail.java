package com.example.gamsung;;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.util.AbstractCollection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
//커뮤니티에서 하나의 항목(레몬에이드만들기) 클릭했을 때 실행되는 자바라옹~

public class CommunityDetail extends AppCompatActivity {
    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference databaseReference = firebaseDatabase.getReference();
    private String photo,subject,userDisplayname,material,text,pos;
    public List<Community> communityList;
    public List<AllCommunity> allCommunityList;
    private ImageButton btnLike;
    private TextView likecntview; //좋아요 개수 화면에 보이기 위한 TextView
    public String likecnt; //진짜 좋아요 개수 숫자

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.community_detail);

        TextView subjectview = (TextView)findViewById(R.id.subject);
        TextView userDisplaynamev = (TextView)findViewById(R.id.userDisplayname);
        TextView materialv = (TextView)findViewById(R.id.material);
        TextView textv = (TextView)findViewById(R.id.text);
        ImageView photov = (ImageView)findViewById(R.id.photo);
        TextView cupper = (TextView)findViewById(R.id.cupper);
        btnLike = findViewById(R.id.btnLike);
        likecntview = findViewById(R.id.likecnt);
        cupper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);

            }
        });

        Intent intent = getIntent();
        subject = intent.getStringExtra("subject");
        subjectview.setText(subject);
        userDisplayname = intent.getStringExtra("userDisplayname");
        userDisplaynamev.setText(userDisplayname);
        material = intent.getStringExtra("material");
        materialv.setText(material);
        text = intent.getStringExtra("text");
        textv.setText(text);
        photo = intent.getStringExtra("photo");
        pos = intent.getStringExtra("pos");
        System.out.println("pos 잘 불러왓니??" + pos);
        Glide.with(this).load(photo).into(photov);

        //디비에서 likecnt개수를 불러오는 작업
        firebaseDatabase.getInstance().getReference().child("커뮤니티게시판/community/" + pos).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    AllCommunity community = dataSnapshot.getValue(AllCommunity.class);
                    likecnt = community.getLikecnt();
                    likecntview.setText(likecnt);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        btnLike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                databaseReference = FirebaseDatabase.getInstance().getReference().child("커뮤니티게시판/"); //child안에있는곳으로 likecnt 증가
                Map<String, Object> updateMap = new HashMap<>();


                if(btnLike.getTag() != null && btnLike.getTag().toString().equals("red")) {
                    btnLike.setImageResource(R.drawable.ic_heart_outline_grey);
                    btnLike.setTag("greyheart");
                    //likecnt 1 감소
                    String cnt = String.valueOf(Integer.parseInt(likecnt));
                    updateMap.put("likecnt", cnt);
                    likecntview.setText(cnt);
                    Toast.makeText(getApplicationContext(),"좋아요를 취소했습니다!",Toast.LENGTH_SHORT).show();
                } else {
                    btnLike.setImageResource(R.drawable.ic_heart_red);
                    btnLike.setTag("red");
                    //likecnt 1 증가
                    String cnt2 = String.valueOf(Integer.parseInt(likecnt)+1);
                    updateMap.put("likecnt", cnt2);
                    likecntview.setText(cnt2);
                    Toast.makeText(getApplicationContext(), "좋아요를 눌렀습니다!", Toast.LENGTH_SHORT).show();

                }
                databaseReference.child("community").child(pos).updateChildren(updateMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        System.out.println("SuccessFul!!!!!!!!!!!!!!!!!!!11");
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override

                    public void onFailure(@NonNull Exception e) {
                        System.out.println("Failure!!!!!!!!!!!!!!!!!!!!11");
                    }
                });
            }
        });
    }

}