package com.example.gamsung;

import android.app.AlertDialog;
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
    private String name,cafe,star,reviewcnt;
    public String title, pos;
    private ReviewAdapter reviewAdapter;
    public int[] hashintarr = new int[100];
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
                    System.out.println(title + pos);
                    reviewAdapter.utitle = title;
                    reviewAdapter.upos = pos;
//                    hashchange();
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
                    System.out.println(title + pos);
                    reviewAdapter.utitle = title;
                    reviewAdapter.upos = pos;
//                    hashchange();
                }
                else if(title.equals("망원동")) {
                    Review r = new Review(allReview.getProfile(), allReview.getImg(), allReview.getStar(), allReview.getLikecnt(), allReview.getUsername(),
                            allReview.getCafe(), allReview.getText(), allReview.getTag1(), allReview.getTag2(), allReview.getTag3());
                    reviewList.add(r);
                    System.out.println("여기는 리뷰리스트다 리뷰리스트!!!");
                    System.out.println(title + pos);
                    reviewAdapter.utitle = title;
                    reviewAdapter.upos = pos;
//                    hashchange();
                }
                else if(title.equals("연남동")) {
                    Review r = new Review(allReview.getProfile(), allReview.getImg(),allReview.getStar(),allReview.getLikecnt(),allReview.getUsername(),
                            allReview.getCafe(),allReview.getText(),allReview.getTag1(),allReview.getTag2(),allReview.getTag3());
                    reviewList.add(r);
                    System.out.println("여기는 리뷰리스트다 리뷰리스트!!!");
                    System.out.println(title + pos);
                    reviewAdapter.utitle = title;
                    reviewAdapter.upos = pos;
//                    hashchange();
                }

            }
            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                AllReview allReview = dataSnapshot.getValue(AllReview.class);
                if(title.equals("혜화")) {
                    Review r = new Review(allReview.getProfile(), allReview.getImg(),allReview.getStar(),allReview.getLikecnt(),allReview.getUsername(),
                            allReview.getCafe(),allReview.getText(),allReview.getTag1(),allReview.getTag2(),allReview.getTag3());
                    reviewList.add(r);
//                    hashchange();
                }
                else if(title.equals("익선동")) {
                    Review r = new Review(allReview.getProfile(), allReview.getImg(),allReview.getStar(),allReview.getLikecnt(),allReview.getUsername(),
                            allReview.getCafe(),allReview.getText(),allReview.getTag1(),allReview.getTag2(),allReview.getTag3());
                    reviewList.add(r);
//                    hashchange();
                }
                else if(title.equals("망원동")) {
                    Review r = new Review(allReview.getProfile(), allReview.getImg(),allReview.getStar(),allReview.getLikecnt(),allReview.getUsername(),
                            allReview.getCafe(),allReview.getText(),allReview.getTag1(),allReview.getTag2(),allReview.getTag3());
                    reviewList.add(r);
//                    hashchange();
                }
                else if(title.equals("연남동")) {
                    Review r = new Review(allReview.getProfile(), allReview.getImg(),allReview.getStar(),allReview.getLikecnt(),allReview.getUsername(),
                            allReview.getCafe(),allReview.getText(),allReview.getTag1(),allReview.getTag2(),allReview.getTag3());
                    reviewList.add(r);
//                    hashchange();
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
    public void hashchange(List<Review> list, String[] stag) { // ReviewAdapter에서 문자열에 해시태그를 넣어주고 CafeReviewList에서 배열을 조정함
        String s;
        System.out.println("여기는 Hashchange함수!!!!!");
        System.out.println(stag[0]+stag[1]+stag[2]+stag[3]
                +stag[4]+stag[5]+stag[6]+stag[7]+stag[8]
                +stag[9]+stag[10]+stag[11]+stag[12]+stag[13]+stag[14]);
        System.out.println("리스트사이즈는?!!?!?!?!?" +list.size());

        for(int i=0;i<list.size()*3;i++){ // 숫자 들어가는 배열 1로 초기화
            hashintarr[i] = 1;
        }

        // 비교해서 같은 태그 구분?하기
        for(int i = 0; i < list.size()*3; i++){
            for(int j = i + 1; j<list.size()*3-1; j++) {
                System.out.println(hashintarr[0]);
                System.out.println(stag[i] + stag[j]);
                s = stag[i];
                if(stag[j] == null) {
                    break;
                }
                if(s.equalsIgnoreCase(stag[j])) {
                    if(stag[j].equals("")) {
                        continue;
                    }
                    System.out.println("비교는 되는거니?");
                    hashintarr[i] += 1;
                    for(int k = j; k < list.size()*3;k++) {
                        stag[k] = stag[k+1];
                        hashintarr[k] = hashintarr[k+1];
                    }
                    j--;

                }
            }
        }
//        for(int i=0;i<reviewList.size()*3;i++){
//            if(reviewAdapter.hashtagarr[i] == reviewAdapter.hashtagarr[i+1]) {
//                reviewAdapter.hashintarr[i] += 1; //#스콘 #스콘 같으면 처음#스콘의 옆 숫자 배열에 1을 증가
//                for(int k=1;k<reviewList.size()*3;k++) {
//                    reviewAdapter.hashtagarr[i+k] = reviewAdapter.hashtagarr[i+k+1]; // 두번째에 세번째 겹치게함
//                    reviewAdapter.hashintarr[i+k] = reviewAdapter.hashintarr[i+k+1];
//                }
//            }
//        }

        System.out.println(stag[0]+" " +hashintarr[0] +"," + stag[1]+" " +hashintarr[1] +"," + stag[2]+" " +hashintarr[2]+"," +
                stag[3]+" " +hashintarr[3]+"," + stag[4]+" " + hashintarr[4] +"," + stag[5]+" " +hashintarr[5] +"," + stag[6]+" " + hashintarr[6]+"," +
                stag[7]+" " +hashintarr[7]+"," + stag[8]+" " +hashintarr[8]+"," + stag[9]+" " +hashintarr[9]+"," + stag[10]+" " +hashintarr[10]+"," +
                stag[11]+" " +hashintarr[11] +"," + stag[12]+" " +hashintarr[12]+"," +stag[13]+" " +hashintarr[13]+"," +stag[14]+" " +hashintarr[14]);

        String tempstr;
        int temp = 0;

        // 내림차순 정렬
        for(int i = 0; i < hashintarr.length-1; i++) {
//            if(hashintarr[i] == 0) {
//                break;
//            }
            for(int j = i+1; j < hashintarr.length-1; j++) {
                if(hashintarr[i] < hashintarr[j]) {
                    temp = hashintarr[i];
                    tempstr = stag[i];
                    hashintarr[i] = hashintarr[j];
                    stag[i] = stag[j];
                    hashintarr[j] = temp;
                    stag[j] = tempstr;
                }
            }
        }

        // 출력
        for(int i = 0; i < hashintarr.length-1; i++) {
            System.out.print(stag[i] + " : ");
            System.out.println(hashintarr[i]);

        }


//        databaseReference.child(pos).child("hashtag").updateChildren(hashtagValues).addOnCompleteListener(new OnCompleteListener<Void>() {
//            @Override
//            public void onComplete(@NonNull Task<Void> task) {
//                System.out.println("SuccessFul!!!!!!!!!!!!!!!!!!!11");
//                AlertDialog.Builder builder = new AlertDialog.Builder(CafeReviewList.this);
//                builder.setMessage("데이터베이스에 들어갔습니당\uD83D\uDE0D");
//                AlertDialog alertDialog = builder.create();
//                alertDialog.show();
//            }
//        }).addOnFailureListener(new OnFailureListener() {
//            @Override
//            public void onFailure(@NonNull Exception e) {
//                System.out.println("Failure!!!!!!!!!!!!!!!!!!!!11");
//            }
//        });
    }

}