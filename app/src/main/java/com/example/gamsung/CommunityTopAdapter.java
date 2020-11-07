package com.example.gamsung;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CommunityTopAdapter extends RecyclerView.Adapter<CommunityTopAdapter.MyViewHolder> {
    private Context context;
    private List<Community> communityList;
    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference databaseReference = firebaseDatabase.getReference();

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView subjecttext,username,number;
        public ImageView roundedImageView;
        public String pos;
        public ImageButton btnLike;
        public  MyViewHolder(View itemView) {
            super(itemView);
            roundedImageView = (ImageView) itemView.findViewById(R.id.roundimage);   // 작성자가 올린 사진
            subjecttext = (TextView)itemView.findViewById(R.id.subjectText); // 작성자가 올린 제목
            username = (TextView)itemView.findViewById(R.id.user_name);
            number = (TextView)itemView.findViewById(R.id.number);
        }
    }

    public CommunityTopAdapter(Context mContext, List<Community> data) {
        this.context = mContext;
        communityList = data;
    }

    @Override
    public int getItemCount() { return communityList.size(); }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.community_top_content, parent, false);

        return new CommunityTopAdapter.MyViewHolder(itemView);    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, int position) {
        System.out.println("카드뷰"+position);
        final Community community = communityList.get(position);
        holder.number.setText(String.valueOf(position+1));
        holder.subjecttext.setText(community.getSubject()); // 제목 표시
        holder.username.setText(community.getUserDisplayname());
        String url = community.getPhoto();
        if(holder.pos == null) {
            holder.pos = String.valueOf(position);
            System.out.println("지금들어가는 커뮤니티 게시글의 번호는??" + holder.pos);
        }
        Glide.with(holder.itemView.getContext()).load(url).into(holder.roundedImageView); // 사진 표시

        //이미지 누르면 디테일 들어가는 부분
        holder.roundedImageView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                String text = community.getText();
                String material = community.getMaterial();
                String userDisplayname = community.getUserDisplayname();
                String photo = community.getPhoto();
                String subjecttext = community.getSubject();
                Bundle extras = new Bundle();
                extras.putString("subject",subjecttext);
                extras.putString("userDisplayname", userDisplayname);
                extras.putString("photo",photo);
                extras.putString("material",material);
                extras.putString("text",text);
                extras.putString("pos",holder.pos);
                Intent intent = new Intent(view.getContext(), CommunityDetail.class);

                intent.putExtras(extras);
                view.getContext().startActivity(intent);
            }
        });
    }
}