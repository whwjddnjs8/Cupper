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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.List;

public class CommunitySearchAdapter extends RecyclerView.Adapter<CommunitySearchAdapter.MyViewHolder> {
    private int where;
    private Context context;
    private List<Community> communitycardList;
    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference databaseReference = firebaseDatabase.getReference();

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView subjecttext, writer;
        public RoundedImageView roundedImageView;

        public  MyViewHolder(View itemView) {
            super(itemView);
            roundedImageView = (RoundedImageView) itemView.findViewById(R.id.roundimage);
            subjecttext = (TextView) itemView.findViewById(R.id.subjecttext);
            writer = (TextView)itemView.findViewById(R.id.writer);
        }
    }

    public CommunitySearchAdapter(Context mContext, List<Community> data) {
        this.context = mContext;
        communitycardList = data;
    }
    public CommunitySearchAdapter(Context context, List<Community> list, int where) {       // 생성자
        this.context = context;
        communitycardList = list;
        this.where = where;
    }



    @NonNull
    @Override
    public CommunitySearchAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.community_card, parent, false);
        return new CommunitySearchAdapter.MyViewHolder(itemView);    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        final Community community = communitycardList.get(position);

        holder.subjecttext.setText(community.getSubject());
        holder.writer.setText(community.getUserDisplayname());
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
                if(where == 0) {
                    Intent intent1 = new Intent(view.getContext(), CommunityDetail.class);
                    intent1.putExtras(extras);
                    view.getContext().startActivity(intent1);
                }
            }
        });
   }

    @Override
    public int getItemCount() { return communitycardList.size(); }

}