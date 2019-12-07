package com.example.gamsung;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CafeAdapter extends RecyclerView.Adapter<CafeAdapter.MyViewHolder> {
    private Context context;
    private List<Cafe> cafeList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView views, toilet, name, price, star;
        public ImageView image;

        public MyViewHolder(View view) {    // 뷰홀더가 만들어짐
            super(view);
            image = view.findViewById(R.id.image);
            views = view.findViewById(R.id.views);
            toilet = view.findViewById(R.id.toilet);
            name = view.findViewById(R.id.name);
            price = view.findViewById(R.id.price);
            star = view.findViewById(R.id.star);
        }
    }
    public CafeAdapter(Context context, List<Cafe> list) {       // 생성자
        this.context = context;
        cafeList = list;
    }

    @Override
    public int getItemCount() {     // 데이터가 총 몇개인지 알려주는 함수
        return cafeList.size();
    }

    @NonNull
    @Override
    public CafeAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.cafe_item, parent, false);
        //LayoutInflater->xml을 컴파일해서 실시간적으로 읽어들이는 작업.  inflater부풀리는것. 소스에있는것을 메모리에 로딩시켜라. movie_item레이아웃을 읽어들여서 객체로 만들어주는것
        return new CafeAdapter.MyViewHolder(itemView); //myViewHolder 아이템뷰 하나를 만들어주는것. 뷰홀더객체 하나 생성. 뷰홀더란 아이템뷰를 가리킨다.
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        final Cafe cafe = cafeList.get(position); // 배열에 있는것중에 무비하나를 꺼내옴.
        // 위에서는 껍데기를 만들고 여기서는 실제 데이터를 생성하는함수.
        // 뷰 홀더의 각 자리에 데이터 셋팅(binding)
        holder.image.setImageResource(cafe.getImage());
        holder.views.setText(Integer.toString(cafe.getViews()));
        holder.toilet.setText(cafe.getToilet());
        holder.name.setText(cafe.getName());
        holder.price.setText(cafe.getPrice());
        holder.star.setText(cafe.getStar());
        holder.image.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                String title = cafe.getName();
                String price = cafe.getPrice();
                String star = cafe.getStar();
                Bundle extras = new Bundle(); // 번들은 인텐트 속에 있는 데이터 꾸러미
                extras.putString("title", title);
                extras.putString("price", price);
                extras.putString("star", star);
                Intent intent = new Intent(view.getContext(), CafeDetail.class); // 예를들어 혜화카페페이지로 넘어감
                intent.putExtras(extras); //인텐트 안에 번들을 집어 넣음
                view.getContext().startActivity(intent); //화면을 띄움
            }
        });
    }
}
