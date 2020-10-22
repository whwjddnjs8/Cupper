package com.example.gamsung;

import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.content.res.AppCompatResources;
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

public class BeansAdapter extends RecyclerView.Adapter<BeansAdapter.MyViewHolder> {
    private Context context;
    private List<Beans> beansList;
    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference databaseReference = firebaseDatabase.getReference();

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView name,group,note,price,text;
        public ImageView image;

        public MyViewHolder(View view) {    // 뷰홀더가 만들어짐
            super(view);
            image = view.findViewById(R.id.image);
            name = view.findViewById(R.id.name);
            note = view.findViewById(R.id.note);
            text = view.findViewById(R.id.text);
            price = view.findViewById(R.id.price);

        }
    }
    public BeansAdapter(Context context, List<Beans> list) {       // 생성자
        this.context = context;
        beansList = list;
    }

    @Override
    public int getItemCount() {     // 데이터가 총 몇개인지 알려주는 함수
        return beansList.size();
    }

    @NonNull
    @Override
    public BeansAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.beans_item, parent, false);
        return new BeansAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        final Beans beans = beansList.get(position);

        /** 여기가 데이터베이스에서 이미지 받아와서 세팅하는 곳 **/
        String url = beans.getImage();
        Glide.with(holder.itemView.getContext()).load(url).into(holder.image);

        holder.name.setText(beans.getName());
        holder.price.setText(beans.getPrice());
        holder.text.setText(beans.getText());
        holder.note.setText(beans.getNote());
        holder.image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), BeansWebView.class);
                context.startActivity(intent);
            }
        });

    }
}
