package com.example.gamsung;

import android.content.Intent;
import android.media.Image;
import android.media.Rating;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CafeReviewList extends AppCompatActivity {
    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference databaseReference = firebaseDatabase.getReference();
    private RecyclerView recyclerView;
    private String name,title,pos,cafe,star;
    private ReviewAdapter reviewAdapter;
    private List<Review> reviewList;
    RatingBar ratingBar;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.review_list);
        Intent intent = getIntent();
        name = intent.getStringExtra("name");
        title = intent.getStringExtra("title");
        pos = intent.getStringExtra("pos");
        star = intent.getStringExtra("star"); //별점을 가져옴.
        cafe = intent.getStringExtra("cafe");
        ImageButton button = findViewById(R.id.write);
        recyclerView = (RecyclerView)findViewById(R.id.recycler_view);
        reviewList = new ArrayList<>();
        reviewAdapter = new ReviewAdapter(this, reviewList);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.setAdapter(reviewAdapter);
        prepareReview();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), CafeReview.class);
                Bundle extras = new Bundle();
                extras.putString("name", name);
                intent.putExtras(extras);
                startActivity(intent);
            }
        });
        /*
        ratingBar = (RatingBar)findViewById(R.id.ratingbar);
        ratingBar.setRating(Float.valueOf(star)); //괄호안에 있는 별점을 float로 바꿔서 별점으로 나타내는 건데,,,,,, 리스트라서,,,, 어댑터를,,,

         */

    }

    public void prepareReview() {
        //카페이름을 검색하면 이름에 따른 리뷰를 불러옴
        databaseReference.child( title +"/" + pos + "/review" ).addChildEventListener(new ChildEventListener(){
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                AllReview allReview = dataSnapshot.getValue(AllReview.class);
                if(title.equals("혜화")) {
                    Review r = new Review(allReview.getProfile(), allReview.getImg(),allReview.getStar(),allReview.getLikecnt(),allReview.getUsername(),
                            allReview.getCafe(),allReview.getText(),allReview.getTag1(),allReview.getTag2(),allReview.getTag3());
                    reviewList.add(r);
                }
                else if(title.equals("익선동")) {
                    Review r = new Review(allReview.getProfile(), allReview.getImg(),allReview.getStar(),allReview.getLikecnt(),allReview.getUsername(),
                            allReview.getCafe(),allReview.getText(),allReview.getTag1(),allReview.getTag2(),allReview.getTag3());
                    reviewList.add(r);
                }
                else if(title.equals("망원동")) {
                    Review r = new Review(allReview.getProfile(), allReview.getImg(), allReview.getStar(), allReview.getLikecnt(), allReview.getUsername(),
                            allReview.getCafe(), allReview.getText(), allReview.getTag1(), allReview.getTag2(), allReview.getTag3());
                    reviewList.add(r);
                }
                else if(title.equals("연남동")) {
                    Review r = new Review(allReview.getProfile(), allReview.getImg(),allReview.getStar(),allReview.getLikecnt(),allReview.getUsername(),
                            allReview.getCafe(),allReview.getText(),allReview.getTag1(),allReview.getTag2(),allReview.getTag3());
                    reviewList.add(r);
                }

            }
            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                AllReview allReview = dataSnapshot.getValue(AllReview.class);
                if(title.equals("혜화")) {
                    Review r = new Review(allReview.getProfile(), allReview.getImg(),allReview.getStar(),allReview.getLikecnt(),allReview.getUsername(),
                            allReview.getCafe(),allReview.getText(),allReview.getTag1(),allReview.getTag2(),allReview.getTag3());
                    reviewList.add(r);
                }
                else if(title.equals("익선동")) {
                    Review r = new Review(allReview.getProfile(), allReview.getImg(),allReview.getStar(),allReview.getLikecnt(),allReview.getUsername(),
                            allReview.getCafe(),allReview.getText(),allReview.getTag1(),allReview.getTag2(),allReview.getTag3());
                    reviewList.add(r);
                }
                else if(title.equals("망원동")) {
                    Review r = new Review(allReview.getProfile(), allReview.getImg(),allReview.getStar(),allReview.getLikecnt(),allReview.getUsername(),
                            allReview.getCafe(),allReview.getText(),allReview.getTag1(),allReview.getTag2(),allReview.getTag3());
                    reviewList.add(r);
                }
                else if(title.equals("연남동")) {
                    Review r = new Review(allReview.getProfile(), allReview.getImg(),allReview.getStar(),allReview.getLikecnt(),allReview.getUsername(),
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
