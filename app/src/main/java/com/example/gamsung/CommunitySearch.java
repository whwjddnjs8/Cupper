package com.example.gamsung;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class CommunitySearch extends AppCompatActivity {
    private List<Community> communityList = new ArrayList<>();
    public List<AllCommunity> allCommunityList = new ArrayList<>();
    private RecyclerView recyclerView;
    private CommunitySearchAdapter csearchAdapter;

    private EditText search;
    private String searchword;
    private int where;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.community_search);

        Intent intent = getIntent();
        where = intent.getIntExtra("where", 0);

        recyclerView = findViewById(R.id.search_recycler);
        if(communityList.size() > 0) communityList.clear();

        if(where == 0) {
            csearchAdapter = new CommunitySearchAdapter(this, communityList, where);
            recyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(),2));
            recyclerView.setAdapter(csearchAdapter);
            prepareData(0);
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
                if(communityList.size() > 0) communityList.clear();
                if(where == 0) {
                    if(communityList.size() > 0) communityList.clear();
                    prepareData(0);
                }

            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });
    }

    private void prepareData(int n) {
        if(communityList.size() > 0) communityList.clear();

        for(int i = 0; i <CommunityMain.allCommunityList.size(); i++) {
            System.out.println(CommunityMain.allCommunityList.size()+"사이즈");
            if(CommunityMain.allCommunityList.get(i).getSubject().contains(""+searchword+"")) {
                System.out.println(CommunityMain.allCommunityList.get(i).getSubject());
                Community c = new Community(CommunityMain.allCommunityList.get(i).getUserDisplayname(),CommunityMain.allCommunityList.get(i).getPhoto(), CommunityMain.allCommunityList.get(i).getUseremail(),
                        CommunityMain.allCommunityList.get(i).getSubject(), CommunityMain.allCommunityList.get(i).getMaterial(),
                        CommunityMain.allCommunityList.get(i).getText(),CommunityMain.allCommunityList.get(i).getLikecnt(),CommunityMain.allCommunityList.get(i).getPos());
                communityList.add(c);
            }
        }
        if(where == 0) {
            csearchAdapter.notifyDataSetChanged();
        }
    }
}
