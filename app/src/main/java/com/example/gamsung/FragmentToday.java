package com.example.gamsung;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

// Navigation에서 추천을 눌렀을 때 동작하는 Fragment
public class FragmentToday extends Fragment {
    private List<Cafe> cafeList = new ArrayList<>();
    public static String[] hashtag = new String[150];
    private RecommendAdapter recommendAdapter;
    private RecyclerView recyclerView;
    private Button btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8;
    private TextView cupper;
    public static int pos;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup rootview = (ViewGroup)inflater.inflate(R.layout.recommend_main, container, false);
        recyclerView = (RecyclerView)rootview.findViewById(R.id.recycler_view);
        cafeList = new ArrayList<>();
        recommendAdapter = new RecommendAdapter(getActivity(), cafeList);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(),2));
        recyclerView.setAdapter(recommendAdapter);


        btn1 = rootview.findViewById(R.id.btn1);
        btn1.setBackground(getResources().getDrawable(R.color.btn_pressed));
        prepareRecommend(btn1.getText().toString());
        btn2 = rootview.findViewById(R.id.btn2);
        btn3 = rootview.findViewById(R.id.btn3);
        btn4 = rootview.findViewById(R.id.btn4);
        btn5 = rootview.findViewById(R.id.btn5);
        btn6 = rootview.findViewById(R.id.btn6);
        btn7 = rootview.findViewById(R.id.btn7);
        btn8 = rootview.findViewById(R.id.btn8);
        cupper = rootview.findViewById(R.id.cupper);
        cupper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), MainActivity.class);
                startActivity(intent);

            }
        });

        View.OnClickListener btnClick = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.btn1 : {
                        btn1.setBackground(getResources().getDrawable(R.color.btn_pressed));
                        btn2.setBackground(getResources().getDrawable(R.color.btn_nopressed));
                        btn3.setBackground(getResources().getDrawable(R.color.btn_nopressed));
                        btn4.setBackground(getResources().getDrawable(R.color.btn_nopressed));
                        btn5.setBackground(getResources().getDrawable(R.color.btn_nopressed));
                        btn6.setBackground(getResources().getDrawable(R.color.btn_nopressed));
                        btn7.setBackground(getResources().getDrawable(R.color.btn_nopressed));
                        btn8.setBackground(getResources().getDrawable(R.color.btn_nopressed));
                        recyclerView.smoothScrollToPosition(0);
                        prepareRecommend(btn1.getText().toString());
                        break ;
                    }
                    case R.id.btn2 : {
                        btn2.setBackground(getResources().getDrawable(R.color.btn_pressed));
                        btn1.setBackground(getResources().getDrawable(R.color.btn_nopressed));
                        btn3.setBackground(getResources().getDrawable(R.color.btn_nopressed));
                        btn4.setBackground(getResources().getDrawable(R.color.btn_nopressed));
                        btn5.setBackground(getResources().getDrawable(R.color.btn_nopressed));
                        btn6.setBackground(getResources().getDrawable(R.color.btn_nopressed));
                        btn7.setBackground(getResources().getDrawable(R.color.btn_nopressed));
                        btn8.setBackground(getResources().getDrawable(R.color.btn_nopressed));
                        recyclerView.smoothScrollToPosition(0);
                        prepareRecommend(btn2.getText().toString());
                        break ;
                    }
                    case R.id.btn3 : {
                        btn3.setBackground(getResources().getDrawable(R.color.btn_pressed));
                        btn1.setBackground(getResources().getDrawable(R.color.btn_nopressed));
                        btn2.setBackground(getResources().getDrawable(R.color.btn_nopressed));
                        btn4.setBackground(getResources().getDrawable(R.color.btn_nopressed));
                        btn5.setBackground(getResources().getDrawable(R.color.btn_nopressed));
                        btn6.setBackground(getResources().getDrawable(R.color.btn_nopressed));
                        btn7.setBackground(getResources().getDrawable(R.color.btn_nopressed));
                        btn8.setBackground(getResources().getDrawable(R.color.btn_nopressed));
                        recyclerView.smoothScrollToPosition(0);
                        prepareRecommend(btn3.getText().toString());
                        break ;
                    }
                    case R.id.btn4 : {
                        btn4.setBackground(getResources().getDrawable(R.color.btn_pressed));
                        btn1.setBackground(getResources().getDrawable(R.color.btn_nopressed));
                        btn2.setBackground(getResources().getDrawable(R.color.btn_nopressed));
                        btn3.setBackground(getResources().getDrawable(R.color.btn_nopressed));
                        btn5.setBackground(getResources().getDrawable(R.color.btn_nopressed));
                        btn6.setBackground(getResources().getDrawable(R.color.btn_nopressed));
                        btn7.setBackground(getResources().getDrawable(R.color.btn_nopressed));
                        btn8.setBackground(getResources().getDrawable(R.color.btn_nopressed));
                        recyclerView.smoothScrollToPosition(0);
                        prepareRecommend(btn4.getText().toString());
                        break ;
                    }
                    case R.id.btn5 : {
                        btn5.setBackground(getResources().getDrawable(R.color.btn_pressed));
                        btn1.setBackground(getResources().getDrawable(R.color.btn_nopressed));
                        btn2.setBackground(getResources().getDrawable(R.color.btn_nopressed));
                        btn3.setBackground(getResources().getDrawable(R.color.btn_nopressed));
                        btn4.setBackground(getResources().getDrawable(R.color.btn_nopressed));
                        btn6.setBackground(getResources().getDrawable(R.color.btn_nopressed));
                        btn7.setBackground(getResources().getDrawable(R.color.btn_nopressed));
                        btn8.setBackground(getResources().getDrawable(R.color.btn_nopressed));
                        recyclerView.smoothScrollToPosition(0);
                        prepareRecommend(btn5.getText().toString());
                        break ;
                    }
                    case R.id.btn6 : {
                        btn6.setBackground(getResources().getDrawable(R.color.btn_pressed));
                        btn1.setBackground(getResources().getDrawable(R.color.btn_nopressed));
                        btn2.setBackground(getResources().getDrawable(R.color.btn_nopressed));
                        btn3.setBackground(getResources().getDrawable(R.color.btn_nopressed));
                        btn4.setBackground(getResources().getDrawable(R.color.btn_nopressed));
                        btn5.setBackground(getResources().getDrawable(R.color.btn_nopressed));
                        btn7.setBackground(getResources().getDrawable(R.color.btn_nopressed));
                        btn8.setBackground(getResources().getDrawable(R.color.btn_nopressed));
                        recyclerView.smoothScrollToPosition(0);
                        prepareRecommend(btn6.getText().toString());
                        break ;
                    }
                    case R.id.btn7 : {
                        btn7.setBackground(getResources().getDrawable(R.color.btn_pressed));
                        btn1.setBackground(getResources().getDrawable(R.color.btn_nopressed));
                        btn2.setBackground(getResources().getDrawable(R.color.btn_nopressed));
                        btn3.setBackground(getResources().getDrawable(R.color.btn_nopressed));
                        btn4.setBackground(getResources().getDrawable(R.color.btn_nopressed));
                        btn5.setBackground(getResources().getDrawable(R.color.btn_nopressed));
                        btn6.setBackground(getResources().getDrawable(R.color.btn_nopressed));
                        btn8.setBackground(getResources().getDrawable(R.color.btn_nopressed));
                        recyclerView.smoothScrollToPosition(0);
                        prepareRecommend(btn7.getText().toString());
                        break ;
                    }
                    case R.id.btn8 : {
                        btn8.setBackground(getResources().getDrawable(R.color.btn_pressed));
                        btn1.setBackground(getResources().getDrawable(R.color.btn_nopressed));
                        btn2.setBackground(getResources().getDrawable(R.color.btn_nopressed));
                        btn3.setBackground(getResources().getDrawable(R.color.btn_nopressed));
                        btn4.setBackground(getResources().getDrawable(R.color.btn_nopressed));
                        btn5.setBackground(getResources().getDrawable(R.color.btn_nopressed));
                        btn6.setBackground(getResources().getDrawable(R.color.btn_nopressed));
                        btn7.setBackground(getResources().getDrawable(R.color.btn_nopressed));
                        recyclerView.smoothScrollToPosition(0);
                        prepareRecommend(btn8.getText().toString());
                        break ;
                    }
                }
            }
        };
        btn1.setOnClickListener(btnClick);
        btn2.setOnClickListener(btnClick);
        btn3.setOnClickListener(btnClick);
        btn4.setOnClickListener(btnClick);
        btn5.setOnClickListener(btnClick);
        btn6.setOnClickListener(btnClick);
        btn7.setOnClickListener(btnClick);
        btn8.setOnClickListener(btnClick);

        return rootview;

    }

    private void prepareRecommend(String btnstr) {
        // TODO 해시태그랑 버튼을 비교해서 거기에 해당하는 카페를 가져와서 넣어야함
        if(cafeList.size() > 0) cafeList.clear();

        System.out.println(btnstr);
        for(int i = 0; i < FragmentMain.cafeList.size(); i++) {
            if(FragmentMain.hashtag[i*3].contains(""+btnstr+"") || FragmentMain.hashtag[i*3+1].contains(""+btnstr+"") || FragmentMain.hashtag[i*3+2].contains(""+btnstr+"")) {
                System.out.println(FragmentMain.hashtag[i*3] + FragmentMain.hashtag[i*3+1] + FragmentMain.hashtag[i*3+2]);
                hashtag[i*3] = FragmentMain.hashtag[i*3];
                hashtag[i*3+1] = FragmentMain.hashtag[i*3+1];
                hashtag[i*3+2] = FragmentMain.hashtag[i*3+2];

                Cafe cafe = new Cafe(FragmentMain.cafeList.get(i).getName(), FragmentMain.cafeList.get(i).getAddress(),
                        FragmentMain.cafeList.get(i).getDessert(), FragmentMain.cafeList.get(i).getTime(),
                        FragmentMain.cafeList.get(i).getTel(), FragmentMain.cafeList.get(i).getToilet(),
                        FragmentMain.cafeList.get(i).getViews(), FragmentMain.cafeList.get(i).getImageone(),
                        FragmentMain.cafeList.get(i).getImagetwo(), FragmentMain.cafeList.get(i).getImagethr(),
                        FragmentMain.cafeList.get(i).getTitle(),  FragmentMain.cafeList.get(i).getAvgstar(),
                        FragmentMain.cafeList.get(i).getReviewcnt(), FragmentMain.cafeList.get(i).getPos());
                cafeList.add(cafe);
            }
        }
        recommendAdapter.notifyDataSetChanged();
    }
}