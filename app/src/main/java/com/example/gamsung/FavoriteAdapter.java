package com.example.gamsung;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class FavoriteAdapter extends RecyclerView.Adapter<FavoriteAdapter.MyViewHolder> {
    private Context context;
    private List<Favorite> favoriteList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView favorite_cafe;
        public ImageButton favorite;
        public MyViewHolder(View view) {    // 뷰홀더가 만들어짐
            super(view);
            favorite_cafe = view.findViewById(R.id.favorite_cafe);
            favorite = view.findViewById(R.id.favorite);
        }
    }

    public FavoriteAdapter(Context mContext, List<Favorite> data) {
        this.context = mContext;
        favoriteList = data;
    }

    @Override
    public int getItemCount() {
        return favoriteList.size();
    }

    @NonNull
    @Override
    public FavoriteAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.favorite_list, parent, false);

        return new FavoriteAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {
        final Favorite favorite = favoriteList.get(position);
        holder.favorite_cafe.setText(favorite.getName());
    }
}