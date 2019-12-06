package com.example.gamsung;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class CafeActivity extends AppCompatActivity {
    private List<Cafe> cafeList = new ArrayList<>();
    private RecyclerView recyclerView;
    private CafeAdapter cafeAdapter;
    private String title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cafe_listview);

        Intent intent = getIntent();
        title = intent.getStringExtra("title");     // 메인 페이지에서 item을 클릭하면 title값 intent로 넘어옴

        recyclerView = findViewById(R.id.recycler_view);
        cafeAdapter = new CafeAdapter(this, cafeList);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.setAdapter(cafeAdapter);
        prepareCafeData();
    }

    private void prepareCafeData() {
        int[] covers = new int[] {
                R.drawable.hcafe1, R.drawable.hcafe4, R.drawable.hcafe7, R.drawable.hcafe10, R.drawable.hcafe13, R.drawable.hcafe16,
                R.drawable.hcafe19, R.drawable.hcafe22, R.drawable.hcafe25, R.drawable.hcafe28
        };

        if(title.equals("혜화")) {
            Cafe cafe = new Cafe(232, covers[0], "남녀공용", "학림다방", "아메리카노5000원", "4.2점");
            cafeList.add(cafe);

            cafe = new Cafe(232, covers[1], "남녀공용", "카페 데 코믹스 대학로점", "아메리카노5000원", "4.5점");
            cafeList.add(cafe);

            cafe = new Cafe(232, covers[2], "남녀공용", "학림다방", "아메리카노5000원", "4.5점");
            cafeList.add(cafe);

            cafe = new Cafe(232, covers[3], "남녀공용", "카페 데 코믹스 대학로점", "아메리카노5000원", "4.5점");
            cafeList.add(cafe);

            cafe = new Cafe(232, covers[4], "남녀공용", "학림다방", "아메리카노5000원", "4.5점");
            cafeList.add(cafe);

            cafe = new Cafe(232, covers[5], "남녀공용", "학림다방", "아메리카노5000원", "4.5점");
            cafeList.add(cafe);

            cafe = new Cafe(232, covers[6], "남녀공용", "학림다방", "아메리카노5000원", "4.5점");
            cafeList.add(cafe);

            cafe = new Cafe(232, covers[7], "남녀공용", "학림다방", "아메리카노5000원", "4.5점");
            cafeList.add(cafe);

            cafe = new Cafe(232, covers[8], "남녀공용", "학림다방", "아메리카노5000원", "4.5점");
            cafeList.add(cafe);

            cafe = new Cafe(232, covers[9], "남녀공용", "학림다방", "아메리카노5000원", "4.5점");
            cafeList.add(cafe);

        }
        else if(title.equals("망원동")) {
            Cafe cafe = new Cafe(232, covers[0], "남녀공용", "학림다방", "아메리카노5000원", "4.2점");
            cafeList.add(cafe);

            cafe = new Cafe(232, covers[1], "남녀공용", "카페 데 코믹스 대학로점", "아메리카노5000원", "4.5점");
            cafeList.add(cafe);

            cafe = new Cafe(232, covers[2], "남녀공용", "최진영", "아메리카노5000원", "4.5점");
            cafeList.add(cafe);
        }
        cafeAdapter.notifyDataSetChanged();
    }
}
