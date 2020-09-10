package com.example.gamsung;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

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

public class CafeReviewList extends AppCompatActivity {
    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference databaseReference = firebaseDatabase.getReference();
    private RecyclerView recyclerView;
    private String name,title,pos,cafe;
    private ReviewAdapter reviewAdapter;
    private List<Review> reviewList;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.review_list);
        Intent intent = getIntent();
        name = intent.getStringExtra("name");
        title = intent.getStringExtra("title");
        pos = intent.getStringExtra("pos");
        cafe = intent.getStringExtra("cafe");
        recyclerView = (RecyclerView)findViewById(R.id.recycler_view);
        reviewList = new ArrayList<>();
        reviewAdapter = new ReviewAdapter(this, reviewList);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.setAdapter(reviewAdapter);
        prepareReview();
    }
    public void prepareReview() {
        //카페이름을 검색하면 이름에 따른 리뷰를 불러옴
        databaseReference.child( title +"/" + pos + "/review" ).addChildEventListener(new ChildEventListener(){
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                AllReview allReview = dataSnapshot.getValue(AllReview.class);
                if(title.equals("혜화")) {
                    Review r = new Review(allReview.getProfile(), allReview.getImg(),allReview.getLikecnt(),allReview.getUsername(),
                            allReview.getCafe(),allReview.getText(),allReview.getTag1(),allReview.getTag2(),allReview.getTag3());
                    reviewList.add(r);
                }
                else if(title.equals("익선동")) {
                    Review r = new Review(allReview.getProfile(), allReview.getImg(),allReview.getLikecnt(),allReview.getUsername(),
                            allReview.getCafe(),allReview.getText(),allReview.getTag1(),allReview.getTag2(),allReview.getTag3());
                    reviewList.add(r);
                }
                else if(title.equals("망원동")) {
                    Review r = new Review(allReview.getProfile(), allReview.getImg(),allReview.getLikecnt(),allReview.getUsername(),
                            allReview.getCafe(),allReview.getText(),allReview.getTag1(),allReview.getTag2(),allReview.getTag3());
                    reviewList.add(r);
                }
                else if(title.equals("연남동")) {
                    Review r = new Review(allReview.getProfile(), allReview.getImg(),allReview.getLikecnt(),allReview.getUsername(),
                            allReview.getCafe(),allReview.getText(),allReview.getTag1(),allReview.getTag2(),allReview.getTag3());
                    reviewList.add(r);
                }

            }
            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                AllReview allReview = dataSnapshot.getValue(AllReview.class);
                if(title.equals("혜화")) {
                    Review r = new Review(allReview.getProfile(), allReview.getImg(),allReview.getLikecnt(),allReview.getUsername(),
                            allReview.getCafe(),allReview.getText(),allReview.getTag1(),allReview.getTag2(),allReview.getTag3());
                    reviewList.add(r);
                }
                else if(title.equals("익선동")) {
                    Review r = new Review(allReview.getProfile(), allReview.getImg(),allReview.getLikecnt(),allReview.getUsername(),
                            allReview.getCafe(),allReview.getText(),allReview.getTag1(),allReview.getTag2(),allReview.getTag3());
                    reviewList.add(r);
                }
                else if(title.equals("망원동")) {
                    Review r = new Review(allReview.getProfile(), allReview.getImg(),allReview.getLikecnt(),allReview.getUsername(),
                            allReview.getCafe(),allReview.getText(),allReview.getTag1(),allReview.getTag2(),allReview.getTag3());
                    reviewList.add(r);
                }
                else if(title.equals("연남동")) {
                    Review r = new Review(allReview.getProfile(), allReview.getImg(),allReview.getLikecnt(),allReview.getUsername(),
                            allReview.getCafe(),allReview.getText(),allReview.getTag1(),allReview.getTag2(),allReview.getTag3());
                    reviewList.add(r);
                }
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {}

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String a) {}

            @Override
            public void onCancelled(DatabaseError databaseError) {}

        });
        reviewAdapter.notifyDataSetChanged();

    }
}
