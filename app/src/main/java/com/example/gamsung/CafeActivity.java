package com.example.gamsung;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class CafeActivity extends AppCompatActivity {
    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference databaseReference = firebaseDatabase.getReference();
    private List<Cafe> cafeList = new ArrayList<>();
    public List<Hashtag> hashtagList = new ArrayList<>();
    public static String[] hashtag = new String[50];
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
        hashtag = new String[50];
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
                //해시태그 불러오는 코드
                for(DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    // key값이 hashtag라면 hashtag리스트에 추가함
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
                if(title.equals("혜화")) {
                    Cafe cafe = new Cafe(allcafe.getName(), allcafe.getAddress(), allcafe.getDessert(), allcafe.getTime(), allcafe.getTel(),
                            allcafe.getRestroom(), allcafe.getViews(), allcafe.getImageone(), allcafe.getImagetwo(),
                            allcafe.getImagethr(), title, allcafe.getAvgstar(), allcafe.getReviewcnt(), String.valueOf(cafeList.size()));
                    cafeList.add(cafe);
                    System.out.println("카페 리스트 사이즈는? : " + cafeList.size());
                }
                else if(title.equals("익선동")) {
                    Cafe cafe = new Cafe(allcafe.getName(), allcafe.getAddress(), allcafe.getDessert(), allcafe.getTime(), allcafe.getTel(),
                            allcafe.getRestroom(), allcafe.getViews(), allcafe.getImageone(), allcafe.getImagetwo(),
                            allcafe.getImagethr(), title, allcafe.getAvgstar(), allcafe.getReviewcnt(), String.valueOf(cafeList.size()));
                    cafeList.add(cafe);
                    System.out.println("카페 리스트 사이즈는? : " + cafeList.size());
                }
                else if(title.equals("망원동")) {
                    Cafe cafe = new Cafe(allcafe.getName(), allcafe.getAddress(), allcafe.getDessert(), allcafe.getTime(), allcafe.getTel(),
                            allcafe.getRestroom(), allcafe.getViews(), allcafe.getImageone(), allcafe.getImagetwo(),
                            allcafe.getImagethr(), title, allcafe.getAvgstar(), allcafe.getReviewcnt(), String.valueOf(cafeList.size()));
                    cafeList.add(cafe);
                    System.out.println("카페 리스트 사이즈는? : " + cafeList.size());
                }
                else if(title.equals("연남동")) {
                    Cafe cafe = new Cafe(allcafe.getName(), allcafe.getAddress(), allcafe.getDessert(), allcafe.getTime(), allcafe.getTel(),
                            allcafe.getRestroom(), allcafe.getViews(), allcafe.getImageone(), allcafe.getImagetwo(),
                            allcafe.getImagethr(), title, allcafe.getAvgstar(), allcafe.getReviewcnt(), String.valueOf(cafeList.size()));
                    cafeList.add(cafe);
                    System.out.println("카페 리스트 사이즈는? : " + cafeList.size());
                }
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                AllCafe allcafe = dataSnapshot.getValue(AllCafe.class);
                System.out.println("name = " + allcafe.getName());

                if(title.equals("혜화")) {
                    Cafe cafe = new Cafe(allcafe.getName(), allcafe.getAddress(), allcafe.getDessert(), allcafe.getTime(), allcafe.getTel(),
                            allcafe.getRestroom(), allcafe.getViews(), allcafe.getImageone(), allcafe.getImagetwo(),
                            allcafe.getImagethr(), title,  allcafe.getAvgstar(), allcafe.getReviewcnt(), String.valueOf(cafeList.size()));
                    cafeList.add(cafe);
                }
                else if(title.equals("익선동")) {
                    Cafe cafe = new Cafe(allcafe.getName(), allcafe.getAddress(), allcafe.getDessert(), allcafe.getTime(), allcafe.getTel(),
                            allcafe.getRestroom(), allcafe.getViews(), allcafe.getImageone(), allcafe.getImagetwo(),
                            allcafe.getImagethr(), title, allcafe.getAvgstar(), allcafe.getReviewcnt(), String.valueOf(cafeList.size()));
                    cafeList.add(cafe);
                }
                else if(title.equals("망원동")) {
                    Cafe cafe = new Cafe(allcafe.getName(), allcafe.getAddress(), allcafe.getDessert(), allcafe.getTime(), allcafe.getTel(),
                            allcafe.getRestroom(), allcafe.getViews(), allcafe.getImageone(), allcafe.getImagetwo(),
                            allcafe.getImagethr(), title,  allcafe.getAvgstar(), allcafe.getReviewcnt(), String.valueOf(cafeList.size()));
                    cafeList.add(cafe);
                }
                else if(title.equals("연남동")) {
                    Cafe cafe = new Cafe(allcafe.getName(), allcafe.getAddress(), allcafe.getDessert(), allcafe.getTime(), allcafe.getTel(),
                            allcafe.getRestroom(), allcafe.getViews(), allcafe.getImageone(), allcafe.getImagetwo(),
                            allcafe.getImagethr(), title,  allcafe.getAvgstar(), allcafe.getReviewcnt(), String.valueOf(cafeList.size()));
                    cafeList.add(cafe);
                }
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