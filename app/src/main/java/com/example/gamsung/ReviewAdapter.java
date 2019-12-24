package com.example.gamsung;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class ReviewAdapter extends RecyclerView.Adapter<ReviewAdapter.MyViewHolder> {
    private Context context;
    private List<Review> reviewList;
    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView username,cafe,text,tag1,tag2,tag3,Likecnt;
        public ImageButton btnLike;
        public ImageView profile,photo;
        public MyViewHolder(View view) {    // 뷰홀더가 만들어짐
            super(view);
            profile = view.findViewById(R.id.profile);
            username = view.findViewById(R.id.username);
            cafe = view.findViewById(R.id.cafename);
            text = view.findViewById(R.id.reviewText);
            photo = view.findViewById(R.id.reviewPhoto);
            tag1 = view.findViewById(R.id.tag1);
            tag2 = view.findViewById(R.id.tag2);
            tag3 = view.findViewById(R.id.tag3);
            btnLike=view.findViewById(R.id.btnLike);
            Likecnt = view.findViewById(R.id.likecnt);

        }

    }

    public ReviewAdapter(Context mContext, List<Review> data) {
        this.context = mContext;
        reviewList = data;
    }
    @Override
    public int getItemCount() {
        return reviewList.size();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.review_content, parent, false);

        return new ReviewAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        final Review review = reviewList.get(position);
        holder.profile.setImageResource(review.getProfile());
        String url = review.getImg();
        Glide.with(holder.itemView.getContext()).load(url).into(holder.photo);
//        holder.photo.setImageResource(review.getPhoto());
        holder.username.setText(review.getUsername());
        holder.cafe.setText(review.getCafe());
        holder.text.setText(review.getText());
        holder.tag1.setText(review.getTag1());
        holder.tag2.setText(review.getTag2());
        holder.tag3.setText(review.getTag3());
    }
}



