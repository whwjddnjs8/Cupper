package com.example.gamsung;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
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
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FragmentReview extends Fragment {
    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference databaseReference = firebaseDatabase.getReference();
    private RecyclerView recyclerView;
    private ReviewAdapter reviewAdapter;
    private String name = null;
    private EditText search;
    private String cafe;
    private List<Review> reviewList;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup rootview = (ViewGroup)inflater.inflate(R.layout.fragment_review, container, false);
        Intent intent = getActivity().getIntent();
        search = (EditText)rootview.findViewById(R.id.myFilter);
        recyclerView = (RecyclerView)rootview.findViewById(R.id.recycler_view);
        reviewList = new ArrayList<>();
        reviewAdapter = new ReviewAdapter(getActivity(), reviewList);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(reviewAdapter);
        prepareReview();
        ImageButton button = (ImageButton)rootview.findViewById(R.id.write);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(),CafeReview.class);
                startActivity(intent);
            }
        });

        search.setInputType(0);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle extras = new Bundle();
                extras.putInt("where", 2);
                Intent intent = new Intent(getContext(), SearchActivity.class);
                intent.putExtras(extras);
                startActivity(intent);
            }
        });
        return rootview;
    }

    public void prepareReview(){
        final int[] imgs = new int[] {
                R.drawable.jwjw,R.drawable.hcafe8,R.drawable.jwjw,R.drawable.cafe1, R.drawable.jyjy, R.drawable.hcafe25
        };
        //firebase에 리뷰 root에 해당하는 모든리스트 가져옴
        databaseReference.child("리뷰").addChildEventListener(new ChildEventListener(){
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                AllReview allReview = dataSnapshot.getValue(AllReview.class);
                Review r = new Review(imgs[2], imgs[3],allReview.getLikecnt(),allReview.getUsername(),
                        allReview.getCafe(),allReview.getText(),allReview.getTag1(),allReview.getTag2(),allReview.getTag3());
                reviewList.add(r);
            }
            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                AllReview allReview = dataSnapshot.getValue(AllReview.class);
                Review r = new Review(imgs[2], imgs[3],allReview.getLikecnt(),allReview.getUsername(),
                        allReview.getCafe(),allReview.getText(),allReview.getTag1(),allReview.getTag2(),allReview.getTag3());
                reviewList.add(r);
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {}

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String a) {}

            @Override
            public void onCancelled(DatabaseError databaseError) {}

        });
        reviewAdapter.notifyDataSetChanged();


        String img = "https://firebasestorage.googleapis.com/v0/b/gamsung-e3e5a.appspot.com/o/cafeimg%2Fyeonnam%2Fycafe21.JPG?alt=media&token=d5c3bc9d-6bb1-49ce-87f0-036f9683e6a5";
/*
        for(int i = 1; i < CafeReview.allReviewList.size(); i++) {
            if((i % 2) != 0) {
                Review r = new Review(imgs[4], CafeReview.allReviewList.get(i).getImgtwo(), "지녕이",
                        CafeReview.allReviewList.get(i).getCafename(),
                        CafeReview.allReviewList.get(i).getText(), "#맛있다", "#혜화", "#히히");
                reviewList.add(r);
            }
            else if ((i % 2) == 0) {
                Review r = new Review(imgs[0], CafeReview.allReviewList.get(i).getImgtwo(), "정원이",
                        CafeReview.allReviewList.get(i).getCafename(),
                        CafeReview.allReviewList.get(i).getText(), "#룰루", "#망원동", "#좋아요");
                reviewList.add(r);
            }
        }

 */
/*
        Review r = new Review(imgs[2], ,"조정원",
                "광합성카페","처음으로 간 카페였는데 되게 괜찮았던 카페~~~!!","#연남동","#컵케익","#맛이또");
        reviewList.add(r);
        */



    }

}