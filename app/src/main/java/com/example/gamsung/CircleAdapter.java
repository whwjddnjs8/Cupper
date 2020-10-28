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

import java.util.List;

public class CircleAdapter extends RecyclerView.Adapter<CircleAdapter.myViewHolder>{
    private Context context;
    private List<Circle> circleList;

    public class myViewHolder extends RecyclerView.ViewHolder {
        public TextView title;
        public ImageView circle_photo;
        public  myViewHolder(View itemView) {
            super(itemView);
            circle_photo = (ImageView) itemView.findViewById(R.id.image);
            title = (TextView) itemView.findViewById(R.id.title);
        }
    }

    public CircleAdapter(Context mContext, List<Circle> data) {
        context = mContext;
        circleList = data;
    }

    @NonNull
    @Override
    public CircleAdapter.myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.cafe_card, parent, false);

        return new CircleAdapter.myViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull myViewHolder holder, int position) {      // 틀에 값을 하나씩 채워넣는 작업
        final Circle circle = circleList.get(position);
        holder.circle_photo.setImageResource(circle.getCircle_photo());
        holder.title.setText(circle.getTitle());
        holder.circle_photo.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                String title = circle.getTitle();
                Bundle extras = new Bundle();
                extras.putString("title", title);
                if(title.equals("마카롱") || title.equals("티라미수") || title.equals("브런치") || title.equals("케이크")) {
                    Intent intent = new Intent(view.getContext(), DessertActivity.class);
                    intent.putExtras(extras);
                    view.getContext().startActivity(intent);
                }
                else {
                    Intent intent = new Intent(view.getContext(), CafeActivity.class);
                    intent.putExtras(extras);
                    view.getContext().startActivity(intent);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return circleList.size();
    }

}
