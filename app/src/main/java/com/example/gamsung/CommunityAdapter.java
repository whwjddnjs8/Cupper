package com.example.gamsung;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
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

public class CommunityAdapter extends RecyclerView.Adapter<CommunityAdapter.MyViewHolder> {
    private Context context;
    private List<Community> communityList;
    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView username,title,text,views;
        public ImageView image;
        public MyViewHolder(View view) {    // 뷰홀더가 만들어짐
            super(view);
            title = view.findViewById(R.id.title);
            username = view.findViewById(R.id.username);
            text = view.findViewById(R.id.text);
            image = view.findViewById(R.id.image);
            views = view.findViewById(R.id.views);
        }
    }
    public CommunityAdapter(Context mContext, List<Community> data) {
        this.context = mContext;
        communityList = data;
    }
    @Override
    public int getItemCount() {
        return communityList.size();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.community_item, parent, false);

        return new CommunityAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        final Community community = communityList.get(position);
        holder.image.setImageResource(community.getImg());
        holder.title.setText(community.getTitle());
        holder.username.setText(community.getUsername());
        holder.text.setText(community.getText());
        holder.views.setText(String.valueOf(community.getViews()));
    }
}
