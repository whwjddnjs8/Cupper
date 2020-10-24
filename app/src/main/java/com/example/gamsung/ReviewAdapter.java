package com.example.gamsung;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReviewAdapter extends RecyclerView.Adapter<ReviewAdapter.MyViewHolder> {
    private Context context;
    public String[] hashtagarr = new String[100];
    //    public int[] hashintarr = new int[50];
    private String htag1, htag2, htag3;
    public String utitle, upos;
    private int i = 0;
    public List<Review> reviewList;
    private CafeReviewList cafeReviewList = new CafeReviewList();
    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference databaseReference = firebaseDatabase.getReference();
    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView username,cafe,text,tag1,tag2,tag3,Likecnt,star;
        public ImageButton btnLike;
        public ImageView profile,photo;
        RatingBar ratingBar;

        public MyViewHolder(View view) {    // 뷰홀더가 만들어짐
            super(view);
            ratingBar = view.findViewById(R.id.ratingbar);
            profile = view.findViewById(R.id.profile);
            username = view.findViewById(R.id.username);
            cafe = view.findViewById(R.id.cafename);
            star = view.findViewById(R.id.star);
            text = view.findViewById(R.id.reviewText);
            photo = view.findViewById(R.id.reviewPhoto);
            tag1 = view.findViewById(R.id.tag1);
            tag2 = view.findViewById(R.id.tag2);
            tag3 = view.findViewById(R.id.tag3);
            btnLike=view.findViewById(R.id.btnLike);
            Likecnt = view.findViewById(R.id.likecnt);
            context = view.getContext();
        }
    }

    public ReviewAdapter(Context mContext, List<Review> data) {
        this.context = mContext;
        reviewList = data;
    }
    @Override
    public int getItemCount() {
        return reviewList.size();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.review_content, parent, false);

        return new ReviewAdapter.MyViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        final Review review = reviewList.get(position);
        Glide.with(context).load(review.getProfile()).circleCrop().into(holder.profile);
//        holder.profile.setImageResource(review.getProfile());
        String url = review.getImg();
        Glide.with(holder.itemView.getContext()).load(url).into(holder.photo);
        holder.username.setText(review.getUsername());
        holder.cafe.setText(review.getCafe());
        holder.star.setText(String.valueOf(review.getStar()));
        holder.text.setText(review.getText());
        holder.tag1.setText(review.getTag1());
        holder.tag2.setText(review.getTag2());
        holder.tag3.setText(review.getTag3());
        holder.Likecnt.setText(String.valueOf(review.getLikecnt()));
        holder.ratingBar.setRating(Float.valueOf(review.getStar()));

//        for(int i=0;i<reviewList.size()*3;i++){ // 숫자 들어가는 배열 1로 초기화
//            hashintarr[i] = 1;
//        }

        htag1 = review.getTag1(); // 각각의 태그가 들어감
        htag2 = review.getTag2();
        htag3 = review.getTag3();

        System.out.println("여기는 어댑터 갱신해주는곳인가????/");
        arrayadd(htag1, htag2, htag3, position);

        /*
        holder.btnLike.setOnClickListener(new View.OnClickListener() { // 리뷰 리스트에 있는 리뷰 좋아요 눌렀을 때
            @Override
            public void onClick(View view) {
                databaseReference = FirebaseDatabase.getInstance().getReference().child(""); //child안에있는곳으로 likecnt 증가
                Map<String, Object> updateMap = new HashMap<>();
                updateMap.put("likecnt", String.valueOf(review.getLikecnt())+1);
                databaseReference.child(String.valueOf(position)).updateChildren(updateMap).addOnCompleteListener(new OnCompleteListener<Void>() {
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

         */


    }

    public void arrayadd(String htag1, String htag2, String htag3, int j) {
        System.out.println("여기는 arrayadd 함수다!!!!");
        if(j == 0) {
            hashtagarr[j] = htag1;
            hashtagarr[j+1] = htag2;
            hashtagarr[j+2]= htag3;

//            cafeReviewList.hashchange(reviewList, hashtagarr, hashintarr);
        }
        else {
            hashtagarr[j+(j*2)]= htag1;
            hashtagarr[j+(j*2)+1] = htag2;
            hashtagarr[j+(j*2)+2] = htag3;
//            cafeReviewList.hashchange(reviewList, hashtagarr, hashintarr);
        }

        if(j == reviewList.size()-1) {
            System.out.println("4번째인데...");
            System.out.println(utitle+upos);
            cafeReviewList.hashchange(reviewList, hashtagarr);
            puthashtag();
        }

        System.out.println(hashtagarr[i]+hashtagarr[i+1]+hashtagarr[i+2]+hashtagarr[i+3]
                +hashtagarr[i+4]+hashtagarr[i+5]+hashtagarr[i+6]+hashtagarr[i+7]+hashtagarr[i+8]
                +hashtagarr[i+9]+hashtagarr[i+10]+hashtagarr[i+11]+hashtagarr[i+12]+hashtagarr[i+13]+hashtagarr[i+14]); // #아메리카노#스콘 등이 들어감, 테스트용


//        System.out.println(hashtagarr[0]+" " +hashintarr[0] +"," + hashintarr[1] +"," + hashintarr[2]+"," +
//                hashintarr[3]+"," + hashintarr[4] +"," + hashintarr[5] +"," + hashintarr[6]+"," +
//                hashintarr[7]+"," + hashintarr[8]);

    }

    // 대표 해시태그 3개 넣어주는 함수 puthashtag
    public void puthashtag() {
        System.out.println("데이터베이스에 들어가는곳인데 title은?" + utitle + "pos는?" + upos);
        databaseReference = FirebaseDatabase.getInstance().getReference(utitle + "/");
        Hashtag hashtag = new Hashtag(hashtagarr[0], hashtagarr[1], hashtagarr[2]);
        Map<String, Object> hashtagValues = hashtag.toMap();
        hashtagValues.putAll(hashtagValues);

        Map<String, Object> childUpdates = new HashMap<>();
        childUpdates.put(upos+"/hashtag", hashtagValues);

        databaseReference.updateChildren(childUpdates);
    }
}