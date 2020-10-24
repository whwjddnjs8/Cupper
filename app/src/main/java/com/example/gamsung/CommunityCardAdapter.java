package com.example.gamsung;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.List;

public class CommunityCardAdapter extends RecyclerView.Adapter<CommunityCardAdapter.MyViewHolder> {
    private Context context;
    private List<Community> communitycardList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView subjecttext, writer;
        public ImageView roundedImageView;
        public  MyViewHolder(View itemView) {
            super(itemView);
            roundedImageView = (ImageView) itemView.findViewById(R.id.roundimage);   // 작성자가 올린 사진
            subjecttext = (TextView) itemView.findViewById(R.id.subjecttext);   // 제목
            writer = (TextView)itemView.findViewById(R.id.writer);  // 작성자 이름(이메일 X)
        }
    }

    public CommunityCardAdapter(Context mContext, List<Community> data) {
        this.context = mContext;
        communitycardList = data;
    }

    @Override
    public int getItemCount() { return communitycardList.size(); }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.community_card, parent, false);

        return new CommunityCardAdapter.MyViewHolder(itemView);    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        System.out.println("카드뷰"+position);
        final Community community = communitycardList.get(position);
        // 카드뷰의 이미지, 제목 가져오기
//        holder.roundedImageView.setImageResource(community.getRoundimg());
//        holder.subjecttext.setText(community.getSubjecttext());
//        holder.writer.setText(community.getWriter());
        holder.subjecttext.setText(community.getSubject());
        holder.writer.setText(community.getUserDisplayname());
        String url = community.getPhoto();
        Glide.with(holder.itemView.getContext()).load(url).into(holder.roundedImageView);
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
                   Intent intent = new Intent(view.getContext(), CommunityDetail.class);

                   intent.putExtras(extras);
                    view.getContext().startActivity(intent);
                }
            });
        }


}