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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CafeReviewList extends AppCompatActivity {
    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference databaseReference = firebaseDatabase.getReference();
    private RecyclerView recyclerView;
    private String name,title,pos,cafe,star,reviewcnt;
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
        reviewcnt = intent.getStringExtra("reviewcnt");
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
                extras.putString("title", title);
                extras.putString("pos", pos);
                extras.putString("reviewcnt", reviewcnt);
                intent.putExtras(extras);
                startActivity(intent);
            }
        });

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
                    System.out.println("여기는 리뷰리스트다 리뷰리스트!!!");
                    hashchange();
//                    databaseReference = FirebaseDatabase.getInstance().getReference(title+"/"+pos+"/review");
//                    Map<String, Object> hashValues = new HashMap<>();
//                    hashValues.put("review", new Review(reviewAdapter.hashtagarr[0],reviewAdapter.hashtagarr[1],reviewAdapter.hashtagarr[2]));
//                    Review review = new Review(reviewAdapter.hashtagarr[0],reviewAdapter.hashtagarr[1],reviewAdapter.hashtagarr[2]);
//                    Map<String, Object> hashValues = review.toMap();
//                    hashValues.putAll(hashValues);

                }
                else if(title.equals("익선동")) {
                    Review r = new Review(allReview.getProfile(), allReview.getImg(),allReview.getStar(),allReview.getLikecnt(),allReview.getUsername(),
                            allReview.getCafe(),allReview.getText(),allReview.getTag1(),allReview.getTag2(),allReview.getTag3());
                    reviewList.add(r);
                    System.out.println("여기는 리뷰리스트다 리뷰리스트!!!");
                    hashchange();
                }
                else if(title.equals("망원동")) {
                    Review r = new Review(allReview.getProfile(), allReview.getImg(), allReview.getStar(), allReview.getLikecnt(), allReview.getUsername(),
                            allReview.getCafe(), allReview.getText(), allReview.getTag1(), allReview.getTag2(), allReview.getTag3());
                    reviewList.add(r);
                    System.out.println("여기는 리뷰리스트다 리뷰리스트!!!");
                    hashchange();
                }
                else if(title.equals("연남동")) {
                    Review r = new Review(allReview.getProfile(), allReview.getImg(),allReview.getStar(),allReview.getLikecnt(),allReview.getUsername(),
                            allReview.getCafe(),allReview.getText(),allReview.getTag1(),allReview.getTag2(),allReview.getTag3());
                    reviewList.add(r);
                    System.out.println("여기는 리뷰리스트다 리뷰리스트!!!");
                    hashchange();
                }

            }
            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                AllReview allReview = dataSnapshot.getValue(AllReview.class);
                if(title.equals("혜화")) {
                    Review r = new Review(allReview.getProfile(), allReview.getImg(),allReview.getStar(),allReview.getLikecnt(),allReview.getUsername(),
                            allReview.getCafe(),allReview.getText(),allReview.getTag1(),allReview.getTag2(),allReview.getTag3());
                    reviewList.add(r);
                    hashchange();
                }
                else if(title.equals("익선동")) {
                    Review r = new Review(allReview.getProfile(), allReview.getImg(),allReview.getStar(),allReview.getLikecnt(),allReview.getUsername(),
                            allReview.getCafe(),allReview.getText(),allReview.getTag1(),allReview.getTag2(),allReview.getTag3());
                    reviewList.add(r);
                    hashchange();
                }
                else if(title.equals("망원동")) {
                    Review r = new Review(allReview.getProfile(), allReview.getImg(),allReview.getStar(),allReview.getLikecnt(),allReview.getUsername(),
                            allReview.getCafe(),allReview.getText(),allReview.getTag1(),allReview.getTag2(),allReview.getTag3());
                    reviewList.add(r);
                    hashchange();
                }
                else if(title.equals("연남동")) {
                    Review r = new Review(allReview.getProfile(), allReview.getImg(),allReview.getStar(),allReview.getLikecnt(),allReview.getUsername(),
                            allReview.getCafe(),allReview.getText(),allReview.getTag1(),allReview.getTag2(),allReview.getTag3());
                    reviewList.add(r);
                    hashchange();
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
    public void hashchange() { // ReviewAdapter에서 문자열에 해시태그를 넣어주고 CafeReviewList에서 배열을 조정함

        System.out.println("여기는 Hashchange함수!!!!!");
        for(int i=0;i<reviewList.size()*3;i++){
            if(reviewAdapter.hashtagarr[i] == reviewAdapter.hashtagarr[i+1]) {
                reviewAdapter.hashintarr[i] += 1; //#스콘 #스콘 같으면 처음#스콘의 옆 숫자 배열에 1을 증가
                for(int k=1;k<reviewList.size()*3;k++) {
                    reviewAdapter.hashtagarr[i+k] = reviewAdapter.hashtagarr[i+k+1]; // 두번째에 세번째 겹치게함
                    reviewAdapter.hashintarr[i+k] = reviewAdapter.hashintarr[i+k+1];
                }
            }
        }

        System.out.println(reviewAdapter.hashtagarr[0]+" " +reviewAdapter.hashintarr[0] +"," + reviewAdapter.hashintarr[1] +"," + reviewAdapter.hashintarr[2]+"," +
                reviewAdapter.hashintarr[3]+"," + reviewAdapter.hashintarr[4] +"," + reviewAdapter.hashintarr[5] +"," + reviewAdapter.hashintarr[6]+"," +
                reviewAdapter.hashintarr[7]+"," +reviewAdapter.hashintarr[8]);



    }

}