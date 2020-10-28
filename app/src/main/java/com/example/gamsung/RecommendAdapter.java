package com.example.gamsung;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
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

public class RecommendAdapter extends RecyclerView.Adapter<RecommendAdapter.MyViewHolder>{
    private Context context;
    private List<Cafe> cafeList;
    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference databaseReference = firebaseDatabase.getReference();

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView cafename;
        public ImageView cafeimg;
        public MyViewHolder(View view) {    // 뷰홀더가 만들어짐
            super(view);
            cafename = view.findViewById(R.id.cafe_name);
            cafeimg = view.findViewById(R.id.cafeimg);
        }
    }

    public RecommendAdapter(Context mContext, List<Cafe> data) {
        this.context = mContext;
        cafeList = data;
    }
    @NonNull
    @Override
    public RecommendAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.recommend_item, parent, false);

        return new RecommendAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecommendAdapter.MyViewHolder holder, final int position) {
        final Cafe cafe = cafeList.get(position);
        String url = cafe.getImagethr();
        Glide.with(holder.itemView.getContext()).load(url).into(holder.cafeimg);
        holder.cafename.setText(cafe.getName());
        holder.cafeimg.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
//                databaseReference = FirebaseDatabase.getInstance().getReference(cafeList.get(position).getTitle()+'/');
//                Map<String, Object> updateMap = new HashMap<>();
//                updateMap.put("views", String.valueOf(Integer.parseInt(cafe.getViews())+1));
//                databaseReference.child(cafe.getPos()).updateChildren(updateMap).addOnCompleteListener(new OnCompleteListener<Void>() {
//                    @Override
//                    public void onComplete(@NonNull Task<Void> task) {
//                        System.out.println("SuccessFul!!!!!!!!!!!!!!!!!!!11");
//                    }
//                }).addOnFailureListener(new OnFailureListener() {
//                    @Override
//
//                    public void onFailure(@NonNull Exception e) {
//                        System.out.println("Failure!!!!!!!!!!!!!!!!!!!!11");
//                    }
//                });

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
                String star = cafe.getStar();
                String reviewcnt = cafe.getReviewcnt();
                String pos = cafe.getPos();
                switch (cafe.getTitle()) {
                    case "혜화" :
                        FragmentToday.pos = 0 + Integer.parseInt(cafe.getPos());
                        break;
                    case "망원동" :
                        FragmentToday.pos = 10 + Integer.parseInt(cafe.getPos());
                        break;
                    case "익선동" :
                        FragmentToday.pos = 20 + Integer.parseInt(cafe.getPos());
                        break;
                    case "연남동" :
                        FragmentToday.pos = 30 + Integer.parseInt(cafe.getPos());
                        break;
                    default :
                        break;
                }
                String hashtag1 = FragmentToday.hashtag[FragmentToday.pos * 3];
                System.out.println("해시태그 1 : " + hashtag1);
                String hashtag2 = FragmentToday.hashtag[FragmentToday.pos * 3+1];
                System.out.println("해시태그 2 : " + hashtag2);
                String hashtag3 = FragmentToday.hashtag[FragmentToday.pos * 3+2];
                System.out.println("해시태그 3 : " + hashtag3);

                Bundle extras = new Bundle(); // 번들은 인텐트 속에 있는 데이터 꾸러미
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

                Intent intent = new Intent(view.getContext(), CafeDetail.class);
                intent.putExtras(extras);
                view.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return cafeList.size();
    }
}
