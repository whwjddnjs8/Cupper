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
import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends AppCompatActivity {
    private List<Cafe> cafeList = new ArrayList<>();
    private List<AllCafe> allCafeList = new ArrayList<>();
    public static String[] hashtag = new String[150];
    public static int pos;
    private RecyclerView recyclerView;
    private SearchAdapter searchAdapter;

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
            searchAdapter = new SearchAdapter(this, cafeList, where);
            recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
            recyclerView.setAdapter(searchAdapter);
            prepareData(1);
        }
        else if(where == 0) {
            searchAdapter = new SearchAdapter(this, cafeList);
            recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
            recyclerView.setAdapter(searchAdapter);
            prepareData(0);
        }
        else if(where == 2) {
            searchAdapter = new SearchAdapter(this, cafeList,where);
            recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
            recyclerView.setAdapter(searchAdapter);
            prepareData(2);
        }
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
                else if(where == 2) {
                    if(cafeList.size() > 0) cafeList.clear();
                    prepareData(2);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });
    }

    private void prepareData(int n) {
        if(cafeList.size() > 0) cafeList.clear();

        for(int i = 0; i < FragmentMain.cafeList.size(); i++) {
            if(FragmentMain.cafeList.get(i).getName().contains(""+searchword+"")) {
                System.out.println(FragmentMain.cafeList.get(i).getName());
                System.out.println(FragmentMain.cafeList.get(i).getPos());
                System.out.println(FragmentMain.hashtag[i*3] + FragmentMain.hashtag[i*3+1] + FragmentMain.hashtag[i*3+2]);
                hashtag[i*3] = FragmentMain.hashtag[i*3];
                hashtag[i*3+1] = FragmentMain.hashtag[i*3+1];
                hashtag[i*3+2] = FragmentMain.hashtag[i*3+2];
                Cafe cafe = new Cafe(FragmentMain.cafeList.get(i).getName(), FragmentMain.cafeList.get(i).getAddress(),
                        FragmentMain.cafeList.get(i).getDessert(), FragmentMain.cafeList.get(i).getTime(),
                        FragmentMain.cafeList.get(i).getTel(), FragmentMain.cafeList.get(i).getToilet(),
                        FragmentMain.cafeList.get(i).getViews(), FragmentMain.cafeList.get(i).getImageone(),
                        FragmentMain.cafeList.get(i).getImagetwo(), FragmentMain.cafeList.get(i).getImagethr(),
                        FragmentMain.cafeList.get(i).getTitle(), "아메리카노5000원", "4.2점",
                        FragmentMain.cafeList.get(i).getReviewcnt(), FragmentMain.cafeList.get(i).getPos());
                cafeList.add(cafe);
            }
        }
        if(where == 1) {
            searchAdapter.notifyDataSetChanged();
        }
        else if(where == 0) {
            searchAdapter.notifyDataSetChanged();
        }
        else if(where == 2) {
            searchAdapter.notifyDataSetChanged();
        }
    }
}
