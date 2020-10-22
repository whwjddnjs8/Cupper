package com.example.gamsung;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import java.util.ArrayList;
import java.util.List;

//커뮤니티 목록중에 4번째 홈카페 기본용품 준비하기를 누르면 나오는 페이지
//원두, 유리잔, 커피머신, 식기도구를 한번에 모아볼 수 있고 종류별로 모아볼 수 있음
public class Community_tool extends AppCompatActivity {
    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference databaseReference = firebaseDatabase.getReference();
    public static List<AllBeans> allBeansList = new ArrayList<>();
    private List<Beans> beansList = new ArrayList<>();
    private BeansAdapter beansAdapter;
    private String pos;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.community_tool);

        ImageButton beansbut =(ImageButton)findViewById(R.id.beansbut);
        ImageButton glassbut =(ImageButton)findViewById(R.id.glassbut);
        ImageButton machinebut =(ImageButton)findViewById(R.id.machinebut);
        ImageButton cutlerybut =(ImageButton)findViewById(R.id.cutlerybut);
        ImageView banner = (ImageView)findViewById(R.id.banner);


        banner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), BannerCoffee.class); //배너 클릭했을때 원두에 대한 설명 나오게 함
                startActivity(intent);


            }
        });
        beansbut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), BeansActivity.class); // 원두 스티커 누르면 다음 클래스로 넘어감
                startActivity(intent);
            }
        });
        glassbut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), CafeReview.class); //배너 클릭했을때 원두에 대한 설명 나오게 함
                startActivity(intent);
            }
        });
        machinebut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), CafeReview.class); //배너 클릭했을때 원두에 대한 설명 나오게 함
                startActivity(intent);
            }
        });
        cutlerybut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), CafeReview.class); //배너 클릭했을때 원두에 대한 설명 나오게 함
                startActivity(intent);
            }
        });

        databaseReference.child("커뮤니티/G블렌딩").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                AllBeans allBeans = dataSnapshot.getValue(AllBeans.class);
                if(allBeansList.size()<40) {
                    allBeansList.add(allBeans);
                    allBeansList.get(allBeansList.size() - 1).setGroup("G블렌딩");
                    System.out.println(allBeansList.size());
                }
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                if(allBeansList.size()<40) {
                    AllBeans allBeans = dataSnapshot.getValue(AllBeans.class);
                    allBeansList.add(allBeans);
                    allBeansList.get(allBeansList.size() - 1).setGroup("G블렌딩");
                }
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        databaseReference.child("커뮤니티/싱글오리진").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                AllBeans allBeans = dataSnapshot.getValue(AllBeans.class);
                if(allBeansList.size()<40) {
                    allBeansList.add(allBeans);
                    allBeansList.get(allBeansList.size() - 1).setGroup("싱글오리진");
                    System.out.println(allBeansList.size());
                }
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                if(allBeansList.size()<40) {
                    AllBeans allBeans = dataSnapshot.getValue(AllBeans.class);
                    allBeansList.add(allBeans);
                    allBeansList.get(allBeansList.size() - 1).setGroup("싱글오리진");
                }
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        databaseReference.child("커뮤니티/신맛높은원두").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                AllBeans allBeans = dataSnapshot.getValue(AllBeans.class);
                if(allBeansList.size()<40) {
                    allBeansList.add(allBeans);
                    allBeansList.get(allBeansList.size() - 1).setGroup("신맛높은원두");
                    System.out.println(allBeansList.size());
                }
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                if(allBeansList.size()<40) {
                    AllBeans allBeans = dataSnapshot.getValue(AllBeans.class);
                    allBeansList.add(allBeans);
                    allBeansList.get(allBeansList.size() - 1).setGroup("신맛높은원두");
                }
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        databaseReference.child("커뮤니티/신맛중간원두").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                AllBeans allBeans = dataSnapshot.getValue(AllBeans.class);
                if(allBeansList.size()<40) {
                    allBeansList.add(allBeans);
                    allBeansList.get(allBeansList.size() - 1).setGroup("신맛중간원두");
                    System.out.println(allBeansList.size());
                }
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                if(allBeansList.size()<40) {
                    AllBeans allBeans = dataSnapshot.getValue(AllBeans.class);
                    allBeansList.add(allBeans);
                    allBeansList.get(allBeansList.size() - 1).setGroup("신맛중간원두");
                }
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        databaseReference.child("커뮤니티/신맛적은원두").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                AllBeans allBeans = dataSnapshot.getValue(AllBeans.class);
                if(allBeansList.size()<40) {
                    allBeansList.add(allBeans);
                    allBeansList.get(allBeansList.size() - 1).setGroup("신맛적은원두");
                    System.out.println(allBeansList.size());
                }
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                if(allBeansList.size()<40) {
                    AllBeans allBeans = dataSnapshot.getValue(AllBeans.class);
                    allBeansList.add(allBeans);
                    allBeansList.get(allBeansList.size() - 1).setGroup("신맛적은원두");
                }
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



    }
}