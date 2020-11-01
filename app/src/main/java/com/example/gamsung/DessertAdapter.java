package com.example.gamsung;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
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

public class DessertAdapter extends RecyclerView.Adapter<DessertAdapter.MyViewHolder>{
    private Context context;
    private DessertActivity dessertActivity;
    private List<Cafe> dessertcafelist;
    Bundle extras = new Bundle();
    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference databaseReference = firebaseDatabase.getReference();

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView views, toilet, name, price, star;
        public ImageView image;
        public RatingBar ratingBar;

        public MyViewHolder(View view) {    // 뷰홀더가 만들어짐
            super(view);
            image = view.findViewById(R.id.image);
            views = view.findViewById(R.id.views);
            toilet = view.findViewById(R.id.toilet);
            name = view.findViewById(R.id.name);
            price = view.findViewById(R.id.price);
            star = view.findViewById(R.id.star);
            ratingBar = view.findViewById(R.id.ratingbar);
        }
    }
    public DessertAdapter(Context context, List<Cafe> list) {       // 생성자
        this.context = context;
        dessertcafelist = list;
    }

    @Override
    public int getItemCount() {
        return dessertcafelist.size();
    }

    @NonNull
    @Override
    public DessertAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.cafe_item, parent, false);
        return new DessertAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        final Cafe cafe = dessertcafelist.get(position);
        /** 여기가 데이터베이스에서 이미지 받아와서 세팅하는 곳 **/
        String url = cafe.getImageone();
        Glide.with(holder.itemView.getContext()).load(url).into(holder.image);
        holder.image.setColorFilter(Color.parseColor("#6F000000"), PorterDuff.Mode.SRC_ATOP);
        holder.views.setText(cafe.getViews());
        holder.toilet.setText(cafe.getToilet());
        holder.name.setText(cafe.getName());
        holder.price.setText(cafe.getPrice());
        holder.star.setText(cafe.getAvgstar());
        holder.ratingBar.setRating(Float.valueOf(cafe.getAvgstar()));
        holder.image.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(final View view) {
                databaseReference = FirebaseDatabase.getInstance().getReference(dessertcafelist.get(position).getTitle()+'/');
                Map<String, Object> updateMap = new HashMap<>();
                updateMap.put("views", String.valueOf(Integer.parseInt(cafe.getViews())+1));
                databaseReference.child(cafe.getPos()).updateChildren(updateMap).addOnCompleteListener(new OnCompleteListener<Void>() {
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
                String price = cafe.getPrice();
                String star = cafe.getAvgstar();
                String reviewcnt = cafe.getReviewcnt();
                String pos = cafe.getPos();
                System.out.println("포지션이 뭔가요? " + String.valueOf(position));
                String hashtag1 = dessertActivity.hashtag[position*3];
                System.out.println("해시태그 1 : " + hashtag1);
                String hashtag2 = dessertActivity.hashtag[position*3+1];
                System.out.println("해시태그 2 : " + hashtag2);
                String hashtag3 = dessertActivity.hashtag[position*3+2];
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
                extras.putString("price", price);
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
