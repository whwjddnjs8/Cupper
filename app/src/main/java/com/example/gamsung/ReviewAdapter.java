package com.example.gamsung;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
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
    private List<Review> reviewList;
    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference databaseReference = firebaseDatabase.getReference();
    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView username,cafe,text,tag1,tag2,tag3,Likecnt,star;
        public ImageButton btnLike;
        public ImageView profile,photo;

        public MyViewHolder(View view) {    // 뷰홀더가 만들어짐
            super(view);
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
        holder.profile.setImageResource(review.getProfile());
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
}


