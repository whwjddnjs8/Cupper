package com.example.gamsung;

import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.service.autofill.Dataset;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
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

public class CafeAdapter extends RecyclerView.Adapter<CafeAdapter.MyViewHolder> {
    private Context context;
    private CafeActivity cafeActivity;
    private List<Cafe> cafeList;
    Bundle extras = new Bundle();
    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference databaseReference = firebaseDatabase.getReference();

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView views, toilet, name, star;
        public ImageView image;
        public RatingBar ratingbar;

        public MyViewHolder(View view) {    // 뷰홀더가 만들어짐
            super(view);
            image = view.findViewById(R.id.image);
            views = view.findViewById(R.id.views);
            toilet = view.findViewById(R.id.toilet);
            name = view.findViewById(R.id.name);
           star = view.findViewById(R.id.star);
           ratingbar = view.findViewById(R.id.ratingbar);
        }
    }
    public CafeAdapter(Context context, List<Cafe> list) {       // 생성자
        this.context = context;
        cafeList = list;
    }

    @Override
    public int getItemCount() {     // 데이터가 총 몇개인지 알려주는 함수
        return cafeList.size();
    }

    @NonNull
    @Override
    public CafeAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.cafe_item, parent, false);
        return new CafeAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        final Cafe cafe = cafeList.get(position);
        /** 여기가 데이터베이스에서 이미지 받아와서 세팅하는 곳 **/
        String url = cafe.getImageone();
        Glide.with(holder.itemView.getContext()).load(url).into(holder.image);
        holder.image.setColorFilter(Color.parseColor("#6F000000"), PorterDuff.Mode.SRC_ATOP);
        holder.views.setText(cafe.getViews());
        holder.toilet.setText(cafe.getToilet());
        holder.name.setText(cafe.getName());
        holder.star.setText(cafe.getAvgstar());
        holder.ratingbar.setRating(Float.valueOf(cafe.getAvgstar()));
        holder.image.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(final View view) {
                databaseReference = FirebaseDatabase.getInstance().getReference(cafeList.get(position).getTitle()+'/');
                Map<String, Object> updateMap = new HashMap<>();
                updateMap.put("views", String.valueOf(Integer.parseInt(cafe.getViews())+1));
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

                String name = cafe.getName();
                String address = cafe.getAddress();
                String dessert = cafe.getDessert();
                String time = cafe.getTime();
                String tel = cafe.getTel();
                String restroom = cafe.getToilet();
                String views = cafe.getViews();
                String imgone = cafe.getImageone();
                String imgtwo = cafe.getImagetwo();
                String imgthr = cafe.getImagethr();
                String title = cafe.getTitle();
                String star = cafe.getAvgstar();
                String reviewcnt = cafe.getReviewcnt();
                String pos = cafe.getPos();
                System.out.println("포지션이 뭔가요? " + String.valueOf(position));
                String hashtag1 = cafeActivity.hashtag[position*3];
                System.out.println("해시태그 1 : " + hashtag1);
                String hashtag2 = cafeActivity.hashtag[position*3+1];
                System.out.println("해시태그 2 : " + hashtag2);
                String hashtag3 = cafeActivity.hashtag[position*3+2];
                System.out.println("해시태그 3 : " + hashtag3);

                extras.putString("name", name);
                extras.putString("address", address);
                extras.putString("dessert", dessert);
                extras.putString("time", time);
                extras.putString("tel", tel);
                extras.putString("restroom", restroom);
                extras.putString("views", views);
                extras.putString("imgone", imgone);
                extras.putString("imgtwo", imgtwo);
                extras.putString("imgthr", imgthr);
                extras.putString("title", title);
                extras.putString("star", star);
                extras.putString("reviewcnt", reviewcnt);
                extras.putString("pos", pos);
                extras.putString("hashtag1", hashtag1);
                extras.putString("hashtag2", hashtag2);
                extras.putString("hashtag3", hashtag3);
                Intent intent = new Intent(view.getContext(), CafeDetail.class); // 예를들어 혜화카페페이지로 넘어감
                intent.putExtras(extras);
                view.getContext().startActivity(intent);
            }
        });
    }
}