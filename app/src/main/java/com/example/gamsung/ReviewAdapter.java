package com.example.gamsung;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ReviewAdapter extends RecyclerView.Adapter<ReviewAdapter.MyViewHolder> {
    private Context context;
    private List<Review> reviewList;

    public ReviewAdapter(Context mContext, List<Review> data) {
        context = mContext;
        reviewList = data;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.review_content, parent, false);

        return new ReviewAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return reviewList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView username,cafename,rating,text,tag1,tag2,tag3;
        public ImageView profile,revphoto;
        public MyViewHolder(View view) {    // 뷰홀더가 만들어짐
            super(view);
            profile = view.findViewById(R.id.profile);
            username = view.findViewById(R.id.username);
            cafename = view.findViewById(R.id.cafename);
            rating = view.findViewById(R.id.ratingbar);
            text = view.findViewById(R.id.text);
            revphoto = view.findViewById(R.id.reviewPhoto);
            tag1 = view.findViewById(R.id.tag1);
            tag2 = view.findViewById(R.id.tag2);
            tag3 = view.findViewById(R.id.tag3);
        }

    }
}



