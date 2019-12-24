package com.example.gamsung;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends AppCompatActivity {
    private List<Cafe> cafeList = new ArrayList<>();
    private RecyclerView recyclerView;
    private SearchAdapter searchAdapter, searchAdapter1;

    private ImageView imageView;
    private EditText search;
    private String searchword;
    private int where;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cafe_search);

        Intent intent = getIntent();
        where = intent.getIntExtra("where", 0);

        recyclerView = findViewById(R.id.search_recycler);
        if(cafeList.size() > 0) cafeList.clear();

        if(where == 1) {
            searchAdapter1 = new SearchAdapter(this, cafeList, where);
            recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
            recyclerView.setAdapter(searchAdapter1);
            prepareData(1);
        }
        else if(where == 0) {
            searchAdapter = new SearchAdapter(this, cafeList);
            recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
            recyclerView.setAdapter(searchAdapter);
            prepareData(0);
        }
//        imageView = (ImageView)findViewById(R.id.imageView);
        search = (EditText)findViewById(R.id.myFilter);

        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                searchword = search.getText().toString();
                System.out.println(searchword);
                if(cafeList.size() > 0) cafeList.clear();
                if(where == 1) {
                    if(cafeList.size() > 0) cafeList.clear();
                    prepareData(1);
                }
                else if(where == 0) {
                    if(cafeList.size() > 0) cafeList.clear();
                    prepareData(0);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });
//        imageView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                cafeList.clear();
//                searchword = search.getText().toString();
//                prepareData();
//
//            }
//        });
    }

    private void prepareData(int n) {
        if(cafeList.size() > 0) cafeList.clear();

        for(int i = 0; i < FragmentMain.allCafeList.size(); i++) {
            if(FragmentMain.allCafeList.get(i).getName().contains(""+searchword+"")) {
                System.out.println(FragmentMain.allCafeList.get(i).getName());
                System.out.println(FragmentMain.allCafeList.get(i).getPos());
                Cafe cafe = new Cafe(FragmentMain.allCafeList.get(i).getName(), FragmentMain.allCafeList.get(i).getAddress(),
                        FragmentMain.allCafeList.get(i).getDessert(), FragmentMain.allCafeList.get(i).getTime(),
                        FragmentMain.allCafeList.get(i).getTel(), FragmentMain.allCafeList.get(i).getRestroom(),
                        FragmentMain.allCafeList.get(i).getViews(), FragmentMain.allCafeList.get(i).getImageone(),
                        FragmentMain.allCafeList.get(i).getImagetwo(), FragmentMain.allCafeList.get(i).getImagethr(),
                        FragmentMain.allCafeList.get(i).getTitle(), "아메리카노5000원", "4.2점",
                        FragmentMain.allCafeList.get(i).getReviewcnt(), FragmentMain.allCafeList.get(i).getPos());
                cafeList.add(cafe);
            }
        }
        if(where == 1) {
            searchAdapter1.notifyDataSetChanged();
        }
        else if(where == 0) {
            searchAdapter.notifyDataSetChanged();
        }
    }
}
