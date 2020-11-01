package com.example.gamsung;

import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import java.util.ArrayList;
import java.util.List;
// 누른 원두 종류에 따라 recyclerview 이용하여 보여지는 곳
public class BeansDetail extends AppCompatActivity {
    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference databaseReference = firebaseDatabase.getReference();
    private List<Beans> beansList = new ArrayList<>();
    private RecyclerView recyclerView;
    private BeansAdapter beansAdapter;
    private String group;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.beans_detail);

        TextView cupper = (TextView)findViewById(R.id.cupper);
        cupper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);

            }
        });
        Intent intent = getIntent();
        group = intent.getStringExtra("group");
        System.out.println(group);
        TextView groupname = (TextView)findViewById(R.id.group);
        ImageView image = (ImageView)findViewById(R.id.image);
        groupname.setText(">" +group);
        recyclerView = findViewById(R.id.recycler_view);
        beansAdapter = new BeansAdapter(this, beansList);
        recyclerView.setLayoutManager(new GridLayoutManager(this,2));
        recyclerView.setAdapter(beansAdapter);
        prepareCafeData();

    }

    private void prepareCafeData() {
        databaseReference.child("커뮤니티/"+ group).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                AllBeans allBeans = dataSnapshot.getValue(AllBeans.class);
                if(group.equals("G블렌딩")) {
                    Beans b = new Beans(allBeans.getName(), allBeans.getNote(), allBeans.getPrice()
                            , allBeans.getText(),allBeans.getImage(),String.valueOf(beansList.size()));
                    beansList.add(b);
                }
                else if(group.equals("싱글오리진")) {
                    Beans b = new Beans(allBeans.getName(), allBeans.getNote(), allBeans.getPrice()
                            , allBeans.getText(),allBeans.getImage(),String.valueOf(beansList.size()));
                    beansList.add(b);
                }
                else if(group.equals("신맛높은원두")) {
                    Beans b = new Beans(allBeans.getName(), allBeans.getNote(), allBeans.getPrice()
                            , allBeans.getText(),allBeans.getImage(),String.valueOf(beansList.size()));
                    beansList.add(b);
                }
                else if(group.equals("신맛중간원두")) {
                    Beans b = new Beans(allBeans.getName(), allBeans.getNote(), allBeans.getPrice()
                            , allBeans.getText(),allBeans.getImage(),String.valueOf(beansList.size()));
                    beansList.add(b);
                }
                else if(group.equals("신맛적은원두")) {
                    Beans b = new Beans(allBeans.getName(), allBeans.getNote(), allBeans.getPrice()
                            , allBeans.getText(),allBeans.getImage(),String.valueOf(beansList.size()));
                    beansList.add(b);
                }

            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                AllBeans allBeans = dataSnapshot.getValue(AllBeans.class);
                if(group.equals("G블렌딩")) {
                    Beans b = new Beans(allBeans.getName(), allBeans.getNote(), allBeans.getPrice()
                            , allBeans.getText(),allBeans.getImage(),String.valueOf(beansList.size()));
                    beansList.add(b);
                }
                else if(group.equals("싱글오리진")) {
                    Beans b = new Beans(allBeans.getName(), allBeans.getNote(), allBeans.getPrice()
                            , allBeans.getText(),allBeans.getImage(),String.valueOf(beansList.size()));
                    beansList.add(b);
                }
                else if(group.equals("신맛높은원두")) {
                    Beans b = new Beans(allBeans.getName(), allBeans.getNote(), allBeans.getPrice()
                            , allBeans.getText(),allBeans.getImage(),String.valueOf(beansList.size()));
                    beansList.add(b);
                }
                else if(group.equals("신맛중간원두")) {
                    Beans b = new Beans(allBeans.getName(), allBeans.getNote(), allBeans.getPrice()
                            , allBeans.getText(),allBeans.getImage(),String.valueOf(beansList.size()));
                    beansList.add(b);
                }
                else if(group.equals("신맛적은원두")) {
                    Beans b = new Beans(allBeans.getName(), allBeans.getNote(), allBeans.getPrice()
                            , allBeans.getText(),allBeans.getImage(),String.valueOf(beansList.size()));
                    beansList.add(b);
                }
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }


}