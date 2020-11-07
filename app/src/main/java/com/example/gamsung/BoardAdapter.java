package com.example.gamsung;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class BoardAdapter extends RecyclerView.Adapter<BoardAdapter.MyViewHolder> {
    private Context context;
    private List<Post> postList;
    private ImageView photo;
    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView username, board, title, text;
        public ImageView profile;

        public MyViewHolder(View view) {    // 뷰홀더가 만들어짐
            super(view);
            profile = view.findViewById(R.id.profile);
            username = view.findViewById(R.id.username);
            board = view.findViewById(R.id.board);
            title = view.findViewById(R.id.title);
            text = view.findViewById(R.id.text);
            photo = view.findViewById(R.id.photo);
        }
    }
    public BoardAdapter(Context context, List<Post> list) {       // 생성자
        this.context = context;
        postList = list;
    }
    @Override
    public int getItemCount() {     // 데이터가 총 몇개인지 알려주는 함수
        return postList.size();
    }

    @NonNull
    @Override
    public BoardAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.board_post, parent, false);
        return new BoardAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        final Post post = postList.get(position);

        Glide.with(context).load(post.getProfile()).circleCrop().into(holder.profile);
        String url = post.getPhoto();
        if(url.equals("")) {
            photo.setVisibility(View.GONE);
        }
        else {
            Glide.with(holder.itemView.getContext()).load(url).into(photo);
        }
        holder.username.setText(post.getUsername());
        holder.board.setText(post.getBoard());
        holder.title.setText(post.getTitle());
        holder.text.setText(post.getText());
    }
}
