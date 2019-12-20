package com.example.gamsung;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class CafeActivity extends AppCompatActivity {
    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference databaseReference = firebaseDatabase.getReference();
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
        final int[] covers = new int[] {
                R.drawable.hcafe1, R.drawable.hcafe4, R.drawable.hcafe7, R.drawable.hcafe10, R.drawable.hcafe13, R.drawable.hcafe16,
                R.drawable.hcafe19, R.drawable.hcafe22, R.drawable.hcafe25, R.drawable.hcafe28
        };

        /** 파이어베이스에서 title값이랑 똑같은 값을 가진 데이터를 가져옴 **/
        databaseReference.child(title).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                AllCafe allcafe = dataSnapshot.getValue(AllCafe.class);
                System.out.println("name = " + allcafe.getName());

                if(title.equals("혜화")) {
                    Cafe cafe = new Cafe(allcafe.getViews(), covers[0], allcafe.getRestroom(), allcafe.getName(), "아메리카노5000원", "4.2점");
                    cafeList.add(cafe);
                }
                else if(title.equals("익선동")) {
                    Cafe cafe = new Cafe(232, covers[0], allcafe.getRestroom(), allcafe.getName(), "아메리카노5000원", "4.2점");
                    cafeList.add(cafe);
                }
                else if(title.equals("망원동")) {
                    Cafe cafe = new Cafe(232, covers[0], allcafe.getRestroom(), allcafe.getName(), "아메리카노5000원", "4.2점");
                    cafeList.add(cafe);
                }
                else if(title.equals("연남동")) {
                    Cafe cafe = new Cafe(232, covers[0], allcafe.getRestroom(), allcafe.getName(), "아메리카노5000원", "4.2점");
                    cafeList.add(cafe);
                }
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        cafeAdapter.notifyDataSetChanged();
    }
}
