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

public class DessertActivity extends AppCompatActivity {
    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference databaseReference = firebaseDatabase.getReference();
    private List<Cafe> dessertcafelist = new ArrayList<>();
    public List<Hashtag> hashtagList = new ArrayList<>();
    public static String[] hashtag = new String[1000];
    private DessertAdapter dessertAdapter;
    private RecyclerView recyclerView;
    private String title;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cafe_listview);

        Intent intent = getIntent();
        title = intent.getStringExtra("title");     // 메인 페이지에서 item을 클릭하면 title값 intent로 넘어옴

        recyclerView = findViewById(R.id.recycler_view);
        dessertAdapter = new DessertAdapter(this, dessertcafelist);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.setAdapter(dessertAdapter);
        hashtag = new String[1000];
        prepareDessertData();
    }

    private void prepareDessertData() {
        // title값이 마카롱, 티라미수, 브런치, 케이크라면 dessert값이 같아야 불러옴........
        databaseReference.child("혜화").orderByChild("dessert").equalTo(title).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                System.out.println("가져오긴 하나? " + dataSnapshot.getValue().toString());
                for(DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    if(snapshot.getKey().toString().equals("hashtag")) {
                        Hashtag gettag = snapshot.getValue(Hashtag.class);
                        Hashtag addtag = new Hashtag(gettag.getHashtag1(), gettag.getHashtag2(), gettag.getHashtag3());
                        hashtagList.add(addtag);
                        System.out.println("해시태그 size 테스트 : " + hashtagList.size());
                        System.out.println("해시태그 잘 들어갔나요? : " + hashtagList.get(hashtagList.size()-1).getHashtag1() + hashtagList.get(hashtagList.size()-1).getHashtag2() +
                                hashtagList.get(hashtagList.size()-1).getHashtag3());
                        hashtag[(hashtagList.size()-1)*3] = hashtagList.get(hashtagList.size()-1).getHashtag1();
                        hashtag[(hashtagList.size()-1)*3+1] = hashtagList.get(hashtagList.size()-1).getHashtag2();
                        hashtag[(hashtagList.size()-1)*3+2] = hashtagList.get(hashtagList.size()-1).getHashtag3();
                        System.out.println("해시태그 배열에도 들어갔는지 확인 : " + hashtag[(hashtagList.size()-1)*3] + hashtag[(hashtagList.size()-1)*3+1] +
                                hashtag[(hashtagList.size()-1)*3+2]);
                        System.out.println("해시태그!!!!!??!?!?!? : " + snapshot.getValue().toString());
                    }
                }
                if (title.equals("마카롱")) {
                    AllCafe allcafe = dataSnapshot.getValue(AllCafe.class);
                    System.out.println("name = " + allcafe.getName());
                    System.out.println("이건 pos(위치) : " + dataSnapshot.getKey());

                    Cafe cafe = new Cafe(allcafe.getName(), allcafe.getAddress(), allcafe.getDessert(), allcafe.getTime(), allcafe.getTel(),
                            allcafe.getRestroom(), allcafe.getViews(), allcafe.getImageone(), allcafe.getImagetwo(),
                            allcafe.getImagethr(), "혜화", allcafe.getAvgstar(), allcafe.getReviewcnt(), dataSnapshot.getKey());
                    dessertcafelist.add(cafe);
                } else if (title.equals("티라미수")) {
                    AllCafe allcafe = dataSnapshot.getValue(AllCafe.class);
                    System.out.println("name = " + allcafe.getName());

                    Cafe cafe = new Cafe(allcafe.getName(), allcafe.getAddress(), allcafe.getDessert(), allcafe.getTime(), allcafe.getTel(),
                            allcafe.getRestroom(), allcafe.getViews(), allcafe.getImageone(), allcafe.getImagetwo(),
                            allcafe.getImagethr(), "혜화", allcafe.getAvgstar(), allcafe.getReviewcnt(), dataSnapshot.getKey());
                    dessertcafelist.add(cafe);
                } else if (title.equals("브런치")) {
                    AllCafe allcafe = dataSnapshot.getValue(AllCafe.class);
                    System.out.println("name = " + allcafe.getName());

                    Cafe cafe = new Cafe(allcafe.getName(), allcafe.getAddress(), allcafe.getDessert(), allcafe.getTime(), allcafe.getTel(),
                            allcafe.getRestroom(), allcafe.getViews(), allcafe.getImageone(), allcafe.getImagetwo(),
                            allcafe.getImagethr(), "혜화", allcafe.getAvgstar(), allcafe.getReviewcnt(), dataSnapshot.getKey());
                    dessertcafelist.add(cafe);
                } else if (title.equals("케이크")) {
                    AllCafe allcafe = dataSnapshot.getValue(AllCafe.class);
                    System.out.println("name = " + allcafe.getName());

                    Cafe cafe = new Cafe(allcafe.getName(), allcafe.getAddress(), allcafe.getDessert(), allcafe.getTime(), allcafe.getTel(),
                            allcafe.getRestroom(), allcafe.getViews(), allcafe.getImageone(), allcafe.getImagetwo(),
                            allcafe.getImagethr(), "혜화", allcafe.getAvgstar(), allcafe.getReviewcnt(), dataSnapshot.getKey());
                    dessertcafelist.add(cafe);
                }
            }
            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

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
        databaseReference.child("망원동").orderByChild("dessert").equalTo(title).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                System.out.println("가져오긴 하나? " + dataSnapshot.getValue().toString());
                for(DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    if(snapshot.getKey().toString().equals("hashtag")) {
                        Hashtag gettag = snapshot.getValue(Hashtag.class);
                        Hashtag addtag = new Hashtag(gettag.getHashtag1(), gettag.getHashtag2(), gettag.getHashtag3());
                        hashtagList.add(addtag);
                        System.out.println("해시태그 size 테스트 : " + hashtagList.size());
                        System.out.println("해시태그 잘 들어갔나요? : " + hashtagList.get(hashtagList.size()-1).getHashtag1() + hashtagList.get(hashtagList.size()-1).getHashtag2() +
                                hashtagList.get(hashtagList.size()-1).getHashtag3());
                        hashtag[(hashtagList.size()-1)*3] = hashtagList.get(hashtagList.size()-1).getHashtag1();
                        hashtag[(hashtagList.size()-1)*3+1] = hashtagList.get(hashtagList.size()-1).getHashtag2();
                        hashtag[(hashtagList.size()-1)*3+2] = hashtagList.get(hashtagList.size()-1).getHashtag3();
                        System.out.println("해시태그 배열에도 들어갔는지 확인 : " + hashtag[(hashtagList.size()-1)*3] + hashtag[(hashtagList.size()-1)*3+1] +
                                hashtag[(hashtagList.size()-1)*3+2]);
                        System.out.println("해시태그!!!!!??!?!?!? : " + snapshot.getValue().toString());
                    }
                }
                if (title.equals("마카롱")) {
                    AllCafe allcafe = dataSnapshot.getValue(AllCafe.class);
                    System.out.println("name = " + allcafe.getName());
                    System.out.println("이건 pos(위치) : " + dataSnapshot.getKey());

                    Cafe cafe = new Cafe(allcafe.getName(), allcafe.getAddress(), allcafe.getDessert(), allcafe.getTime(), allcafe.getTel(),
                            allcafe.getRestroom(), allcafe.getViews(), allcafe.getImageone(), allcafe.getImagetwo(),
                            allcafe.getImagethr(), "망원동", allcafe.getAvgstar(), allcafe.getReviewcnt(), dataSnapshot.getKey());
                    dessertcafelist.add(cafe);
                } else if (title.equals("티라미수")) {
                    AllCafe allcafe = dataSnapshot.getValue(AllCafe.class);
                    System.out.println("name = " + allcafe.getName());

                    Cafe cafe = new Cafe(allcafe.getName(), allcafe.getAddress(), allcafe.getDessert(), allcafe.getTime(), allcafe.getTel(),
                            allcafe.getRestroom(), allcafe.getViews(), allcafe.getImageone(), allcafe.getImagetwo(),
                            allcafe.getImagethr(), "망원동",  allcafe.getAvgstar(), allcafe.getReviewcnt(), dataSnapshot.getKey());
                    dessertcafelist.add(cafe);
                } else if (title.equals("브런치")) {
                    AllCafe allcafe = dataSnapshot.getValue(AllCafe.class);
                    System.out.println("name = " + allcafe.getName());

                    Cafe cafe = new Cafe(allcafe.getName(), allcafe.getAddress(), allcafe.getDessert(), allcafe.getTime(), allcafe.getTel(),
                            allcafe.getRestroom(), allcafe.getViews(), allcafe.getImageone(), allcafe.getImagetwo(),
                            allcafe.getImagethr(), "망원동", allcafe.getAvgstar(), allcafe.getReviewcnt(), dataSnapshot.getKey());
                    dessertcafelist.add(cafe);
                } else if (title.equals("케이크")) {
                    AllCafe allcafe = dataSnapshot.getValue(AllCafe.class);
                    System.out.println("name = " + allcafe.getName());

                    Cafe cafe = new Cafe(allcafe.getName(), allcafe.getAddress(), allcafe.getDessert(), allcafe.getTime(), allcafe.getTel(),
                            allcafe.getRestroom(), allcafe.getViews(), allcafe.getImageone(), allcafe.getImagetwo(),
                            allcafe.getImagethr(), "망원동",  allcafe.getAvgstar(), allcafe.getReviewcnt(), dataSnapshot.getKey());
                    dessertcafelist.add(cafe);
                }
            }
            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

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
        databaseReference.child("익선동").orderByChild("dessert").equalTo(title).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                System.out.println("가져오긴 하나? " + dataSnapshot.getValue().toString());
                for(DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    if(snapshot.getKey().toString().equals("hashtag")) {
                        Hashtag gettag = snapshot.getValue(Hashtag.class);
                        Hashtag addtag = new Hashtag(gettag.getHashtag1(), gettag.getHashtag2(), gettag.getHashtag3());
                        hashtagList.add(addtag);
                        System.out.println("해시태그 size 테스트 : " + hashtagList.size());
                        System.out.println("해시태그 잘 들어갔나요? : " + hashtagList.get(hashtagList.size()-1).getHashtag1() + hashtagList.get(hashtagList.size()-1).getHashtag2() +
                                hashtagList.get(hashtagList.size()-1).getHashtag3());
                        hashtag[(hashtagList.size()-1)*3] = hashtagList.get(hashtagList.size()-1).getHashtag1();
                        hashtag[(hashtagList.size()-1)*3+1] = hashtagList.get(hashtagList.size()-1).getHashtag2();
                        hashtag[(hashtagList.size()-1)*3+2] = hashtagList.get(hashtagList.size()-1).getHashtag3();
                        System.out.println("해시태그 배열에도 들어갔는지 확인 : " + hashtag[(hashtagList.size()-1)*3] + hashtag[(hashtagList.size()-1)*3+1] +
                                hashtag[(hashtagList.size()-1)*3+2]);
                        System.out.println("해시태그!!!!!??!?!?!? : " + snapshot.getValue().toString());
                    }
                }
                if (title.equals("마카롱")) {
                    AllCafe allcafe = dataSnapshot.getValue(AllCafe.class);
                    System.out.println("name = " + allcafe.getName());
                    System.out.println("이건 pos(위치) : " + dataSnapshot.getKey());

                    Cafe cafe = new Cafe(allcafe.getName(), allcafe.getAddress(), allcafe.getDessert(), allcafe.getTime(), allcafe.getTel(),
                            allcafe.getRestroom(), allcafe.getViews(), allcafe.getImageone(), allcafe.getImagetwo(),
                            allcafe.getImagethr(), "익선동",  allcafe.getAvgstar(), allcafe.getReviewcnt(), dataSnapshot.getKey());
                    dessertcafelist.add(cafe);
                } else if (title.equals("티라미수")) {
                    AllCafe allcafe = dataSnapshot.getValue(AllCafe.class);
                    System.out.println("name = " + allcafe.getName());

                    Cafe cafe = new Cafe(allcafe.getName(), allcafe.getAddress(), allcafe.getDessert(), allcafe.getTime(), allcafe.getTel(),
                            allcafe.getRestroom(), allcafe.getViews(), allcafe.getImageone(), allcafe.getImagetwo(),
                            allcafe.getImagethr(), "익선동", allcafe.getAvgstar(), allcafe.getReviewcnt(), dataSnapshot.getKey());
                    dessertcafelist.add(cafe);
                } else if (title.equals("브런치")) {
                    AllCafe allcafe = dataSnapshot.getValue(AllCafe.class);
                    System.out.println("name = " + allcafe.getName());

                    Cafe cafe = new Cafe(allcafe.getName(), allcafe.getAddress(), allcafe.getDessert(), allcafe.getTime(), allcafe.getTel(),
                            allcafe.getRestroom(), allcafe.getViews(), allcafe.getImageone(), allcafe.getImagetwo(),
                            allcafe.getImagethr(), "익선동", allcafe.getAvgstar(), allcafe.getReviewcnt(), dataSnapshot.getKey());
                    dessertcafelist.add(cafe);
                } else if (title.equals("케이크")) {
                    AllCafe allcafe = dataSnapshot.getValue(AllCafe.class);
                    System.out.println("name = " + allcafe.getName());

                    Cafe cafe = new Cafe(allcafe.getName(), allcafe.getAddress(), allcafe.getDessert(), allcafe.getTime(), allcafe.getTel(),
                            allcafe.getRestroom(), allcafe.getViews(), allcafe.getImageone(), allcafe.getImagetwo(),
                            allcafe.getImagethr(), "익선동",  allcafe.getAvgstar(), allcafe.getReviewcnt(), dataSnapshot.getKey());
                    dessertcafelist.add(cafe);
                }
            }
            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

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
        databaseReference.child("연남동").orderByChild("dessert").equalTo(title).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                System.out.println("가져오긴 하나? " + dataSnapshot.getValue().toString());
                for(DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    if(snapshot.getKey().toString().equals("hashtag")) {
                        Hashtag gettag = snapshot.getValue(Hashtag.class);
                        Hashtag addtag = new Hashtag(gettag.getHashtag1(), gettag.getHashtag2(), gettag.getHashtag3());
                        hashtagList.add(addtag);
                        System.out.println("해시태그 size 테스트 : " + hashtagList.size());
                        System.out.println("해시태그 잘 들어갔나요? : " + hashtagList.get(hashtagList.size()-1).getHashtag1() + hashtagList.get(hashtagList.size()-1).getHashtag2() +
                                hashtagList.get(hashtagList.size()-1).getHashtag3());
                        hashtag[(hashtagList.size()-1)*3] = hashtagList.get(hashtagList.size()-1).getHashtag1();
                        hashtag[(hashtagList.size()-1)*3+1] = hashtagList.get(hashtagList.size()-1).getHashtag2();
                        hashtag[(hashtagList.size()-1)*3+2] = hashtagList.get(hashtagList.size()-1).getHashtag3();
                        System.out.println("해시태그 배열에도 들어갔는지 확인 : " + hashtag[(hashtagList.size()-1)*3] + hashtag[(hashtagList.size()-1)*3+1] +
                                hashtag[(hashtagList.size()-1)*3+2]);
                        System.out.println("해시태그!!!!!??!?!?!? : " + snapshot.getValue().toString());
                    }
                }
                if (title.equals("마카롱")) {
                    AllCafe allcafe = dataSnapshot.getValue(AllCafe.class);
                    System.out.println("name = " + allcafe.getName());
                    System.out.println("이건 pos(위치) : " + dataSnapshot.getKey());

                    Cafe cafe = new Cafe(allcafe.getName(), allcafe.getAddress(), allcafe.getDessert(), allcafe.getTime(), allcafe.getTel(),
                            allcafe.getRestroom(), allcafe.getViews(), allcafe.getImageone(), allcafe.getImagetwo(),
                            allcafe.getImagethr(), "연남동",  allcafe.getAvgstar(), allcafe.getReviewcnt(), dataSnapshot.getKey());
                    dessertcafelist.add(cafe);
                } else if (title.equals("티라미수")) {
                    AllCafe allcafe = dataSnapshot.getValue(AllCafe.class);
                    System.out.println("name = " + allcafe.getName());

                    Cafe cafe = new Cafe(allcafe.getName(), allcafe.getAddress(), allcafe.getDessert(), allcafe.getTime(), allcafe.getTel(),
                            allcafe.getRestroom(), allcafe.getViews(), allcafe.getImageone(), allcafe.getImagetwo(),
                            allcafe.getImagethr(), "연남동", allcafe.getAvgstar(), allcafe.getReviewcnt(), dataSnapshot.getKey());
                    dessertcafelist.add(cafe);
                } else if (title.equals("브런치")) {
                    AllCafe allcafe = dataSnapshot.getValue(AllCafe.class);
                    System.out.println("name = " + allcafe.getName());

                    Cafe cafe = new Cafe(allcafe.getName(), allcafe.getAddress(), allcafe.getDessert(), allcafe.getTime(), allcafe.getTel(),
                            allcafe.getRestroom(), allcafe.getViews(), allcafe.getImageone(), allcafe.getImagetwo(),
                            allcafe.getImagethr(), "연남동",  allcafe.getAvgstar(), allcafe.getReviewcnt(), dataSnapshot.getKey());
                    dessertcafelist.add(cafe);
                } else if (title.equals("케이크")) {
                    AllCafe allcafe = dataSnapshot.getValue(AllCafe.class);
                    System.out.println("name = " + allcafe.getName());

                    Cafe cafe = new Cafe(allcafe.getName(), allcafe.getAddress(), allcafe.getDessert(), allcafe.getTime(), allcafe.getTel(),
                            allcafe.getRestroom(), allcafe.getViews(), allcafe.getImageone(), allcafe.getImagetwo(),
                            allcafe.getImagethr(), "연남동",  allcafe.getAvgstar(), allcafe.getReviewcnt(), dataSnapshot.getKey());
                    dessertcafelist.add(cafe);
                }
            }
            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

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

        dessertAdapter.notifyDataSetChanged();
    }
}