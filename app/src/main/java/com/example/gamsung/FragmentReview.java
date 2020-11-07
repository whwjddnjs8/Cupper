package com.example.gamsung;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FragmentReview extends Fragment {
    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference databaseReference = firebaseDatabase.getReference();
    private RecyclerView recyclerView;
    private ReviewSortAdapter reviewSortAdapter;
    private ReviewAdapter reviewAdapter;
    private Spinner spinner;
    private ArrayList<String> arrayList; // 좋아요순, 최근 순 저장되는 리스트
    private ArrayAdapter<String> arrayAdapter;
    private EditText search;
    private String cafe,title,pos;
    private List<Review> reviewList;
    private List<Cafe> cafeList;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup rootview = (ViewGroup)inflater.inflate(R.layout.fragment_review, container, false);
        Intent intent = getActivity().getIntent();
        search = (EditText)rootview.findViewById(R.id.myFilter);
        ImageButton button = (ImageButton)rootview.findViewById(R.id.write);

        recyclerView = (RecyclerView)rootview.findViewById(R.id.recycler_view);
        cafeList = new ArrayList<>();
        reviewSortAdapter = new ReviewSortAdapter(getActivity(), cafeList);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(reviewSortAdapter);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(),CafeReview.class);
                startActivity(intent);
            }
        });
        arrayList = new ArrayList<>();
        arrayList.add("평점 높은 순");
        arrayList.add("조회수 높은 순");
        arrayList.add("리뷰 많은 순");

        arrayAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_dropdown_item, arrayList);

        spinner = (Spinner)rootview.findViewById(R.id.spinner);
        spinner.setAdapter(arrayAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//                Toast.makeText(getContext(),arrayList.get(i)+"가 선택되었습니다.", Toast.LENGTH_SHORT).show();
                String selectedClass = adapterView.getItemAtPosition(i).toString();
                switch (selectedClass) {
                    case "평점 높은 순" :{
                        System.out.println("평점 높은 순 정렬 시작!");
                        prepareCafe();
                        break;
                    }
                    case "조회수 높은 순" :{
                        System.out.println("조회수 높은 순 정렬 시작!");
                        prepareCafe2();
                        break;
                    }
                    case "리뷰 많은 순" :{
                        System.out.println("리뷰 많은 순 정렬 시작!");
                        prepareCafe3();
                        break;
                    }
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        search.setInputType(0);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle extras = new Bundle();
                extras.putInt("where", 2);
                Intent intent = new Intent(getContext(), SearchActivity.class);
                intent.putExtras(extras);
                startActivity(intent);
            }
        });

        return rootview;
    }
    public void prepareCafe() {
        if(cafeList.size() > 0) cafeList.clear();

        for(int i = 0; i < FragmentMain.cafeList.size(); i++) {
            Cafe cafe = new Cafe(FragmentMain.cafeList.get(i).getName(), FragmentMain.cafeList.get(i).getAddress(),
                    FragmentMain.cafeList.get(i).getDessert(), FragmentMain.cafeList.get(i).getTime(),
                    FragmentMain.cafeList.get(i).getTel(), FragmentMain.cafeList.get(i).getToilet(),
                    FragmentMain.cafeList.get(i).getViews(), FragmentMain.cafeList.get(i).getImageone(),
                    FragmentMain.cafeList.get(i).getImagetwo(), FragmentMain.cafeList.get(i).getImagethr(),
                    FragmentMain.cafeList.get(i).getTitle(), FragmentMain.cafeList.get(i).getAvgstar(),
                    FragmentMain.cafeList.get(i).getReviewcnt(), FragmentMain.cafeList.get(i).getPos());
            cafeList.add(cafe);
        }
        Collections.sort(cafeList, new Comparator<Cafe>() {
            @Override
            public int compare(Cafe cafe, Cafe cafe1) {
                return Float.valueOf(cafe.getAvgstar()).compareTo(Float.valueOf(cafe1.getAvgstar()));
            }
        });
        Collections.reverse(cafeList);
        reviewSortAdapter.notifyDataSetChanged();
    }
    public void prepareCafe2() {
        if(cafeList.size() > 0) cafeList.clear();

        for(int i = 0; i < FragmentMain.cafeList.size(); i++) {
            Cafe cafe = new Cafe(FragmentMain.cafeList.get(i).getName(), FragmentMain.cafeList.get(i).getAddress(),
                    FragmentMain.cafeList.get(i).getDessert(), FragmentMain.cafeList.get(i).getTime(),
                    FragmentMain.cafeList.get(i).getTel(), FragmentMain.cafeList.get(i).getToilet(),
                    FragmentMain.cafeList.get(i).getViews(), FragmentMain.cafeList.get(i).getImageone(),
                    FragmentMain.cafeList.get(i).getImagetwo(), FragmentMain.cafeList.get(i).getImagethr(),
                    FragmentMain.cafeList.get(i).getTitle(), FragmentMain.cafeList.get(i).getAvgstar(),
                    FragmentMain.cafeList.get(i).getReviewcnt(), FragmentMain.cafeList.get(i).getPos());
            cafeList.add(cafe);
        }
        Collections.sort(cafeList, new Comparator<Cafe>() {
            @Override
            public int compare(Cafe cafe, Cafe cafe1) {
                return Float.valueOf(cafe.getViews()).compareTo(Float.valueOf(cafe1.getViews()));
            }
        });
        Collections.reverse(cafeList);
        reviewSortAdapter.notifyDataSetChanged();
    }
    public void prepareCafe3() {
        if(cafeList.size() > 0) cafeList.clear();

        for(int i = 0; i < FragmentMain.cafeList.size(); i++) {
            Cafe cafe = new Cafe(FragmentMain.cafeList.get(i).getName(), FragmentMain.cafeList.get(i).getAddress(),
                    FragmentMain.cafeList.get(i).getDessert(), FragmentMain.cafeList.get(i).getTime(),
                    FragmentMain.cafeList.get(i).getTel(), FragmentMain.cafeList.get(i).getToilet(),
                    FragmentMain.cafeList.get(i).getViews(), FragmentMain.cafeList.get(i).getImageone(),
                    FragmentMain.cafeList.get(i).getImagetwo(), FragmentMain.cafeList.get(i).getImagethr(),
                    FragmentMain.cafeList.get(i).getTitle(), FragmentMain.cafeList.get(i).getAvgstar(),
                    FragmentMain.cafeList.get(i).getReviewcnt(), FragmentMain.cafeList.get(i).getPos());
            cafeList.add(cafe);
        }
        Collections.sort(cafeList, new Comparator<Cafe>() {
            @Override
            public int compare(Cafe cafe, Cafe cafe1) {
                return Float.valueOf(cafe.getReviewcnt()).compareTo(Float.valueOf(cafe1.getReviewcnt()));
            }
        });
        Collections.reverse(cafeList);
        reviewSortAdapter.notifyDataSetChanged();
    }


}