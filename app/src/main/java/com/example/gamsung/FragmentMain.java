package com.example.gamsung;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class FragmentMain extends Fragment {
    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference databaseReference = firebaseDatabase.getReference();
    private DatabaseReference databaseReference2 = firebaseDatabase.getReference();

    public static List<AllCafe> allCafeList = new ArrayList<>();
    public static List<Cafe> cafeList = new ArrayList<>();
    public static List<Favorite> allFavoriteList = new ArrayList<>();
    public List<Favorite> favoriteList = new ArrayList<>();
    public List<Hashtag> hashtagList = new ArrayList<>();
    public static String[] hashtag = new String[1000];
    public static String users;
    public static String myposition;
    private RecyclerView recyclerView;
    private RecyclerView recyclerView2;

    private CircleAdapter circleadapter;
    private List<Circle> circleList;
    private List<Circle> circleList2;

    private EditText search;
    private ViewPager mPager;
    private PagerAdapter mPagerAdapter;
    private static final int NUM_PAGES = 3;
    private String pos = "0";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup rootview = (ViewGroup)inflater.inflate(R.layout.fragment_main, container, false);
        search = (EditText)rootview.findViewById(R.id.myFilter);
        recyclerView = (RecyclerView)rootview.findViewById(R.id.recycler_view);
        recyclerView2 = (RecyclerView)rootview.findViewById(R.id.recycler_view2);
        circleList = new ArrayList<>();
        circleadapter = new CircleAdapter(getActivity(), circleList);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 4));
        recyclerView.setAdapter(circleadapter);
        hashtag = new String[1000];
        hashtagList.clear();
        favoriteList = new ArrayList<>();
        allFavoriteList = new ArrayList<>();
        prepareCircles(1);

        circleList2 = new ArrayList<>();
        circleadapter = new CircleAdapter(getActivity(), circleList2);
        RecyclerView.LayoutManager manager1 = new GridLayoutManager(getActivity(), 4);
        recyclerView2.setLayoutManager(manager1);
        recyclerView2.setItemAnimator(new DefaultItemAnimator());
        recyclerView2.setAdapter(circleadapter);
        prepareCircles(2);

        mPager = (ViewPager)rootview.findViewById(R.id.pager);
        mPagerAdapter = new ViewPagerAdapter(getFragmentManager());
        mPager.setAdapter(mPagerAdapter);
        mPager.setPageTransformer(true, new ZoomOutTransformer());
        mPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {

            }
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }
            @Override
            public void onPageScrollStateChanged(int state) {

            }

        });

        search.setInputType(0);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle extras = new Bundle();
                extras.putInt("where", 0);
                Intent intent = new Intent(getContext(), SearchActivity.class);
                intent.putExtras(extras);
                startActivity(intent);
            }
        });

        databaseReference.child("혜화").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                AllCafe allcafe = dataSnapshot.getValue(AllCafe.class);
                for(DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    if(snapshot.getKey().toString().equals("hashtag")) {
                        if(hashtagList.size() < 100 ) {
                            Hashtag gettag = snapshot.getValue(Hashtag.class);
                            Hashtag addtag = new Hashtag(gettag.getHashtag1(), gettag.getHashtag2(), gettag.getHashtag3());
                            hashtagList.add(addtag);
                            System.out.println("해시태그 size 테스트 : " + hashtagList.size());
                            System.out.println("해시태그 잘 들어갔나요? : " + hashtagList.get(hashtagList.size() - 1).getHashtag1() + hashtagList.get(hashtagList.size() - 1).getHashtag2() +
                                    hashtagList.get(hashtagList.size() - 1).getHashtag3());
                            hashtag[(hashtagList.size() - 1) * 3] = hashtagList.get(hashtagList.size() - 1).getHashtag1();
                            hashtag[(hashtagList.size() - 1) * 3 + 1] = hashtagList.get(hashtagList.size() - 1).getHashtag2();
                            hashtag[(hashtagList.size() - 1) * 3 + 2] = hashtagList.get(hashtagList.size() - 1).getHashtag3();
                            System.out.println("해시태그 배열에도 들어갔는지 확인 : " + hashtag[(hashtagList.size() - 1) * 3] + hashtag[(hashtagList.size() - 1) * 3 + 1] +
                                    hashtag[(hashtagList.size() - 1) * 3 + 2]);
                            System.out.println("해시태그!!!!!??!?!?!? : " + snapshot.getValue().toString());
                        }
                    }
                }
                if(cafeList.size() < 68) {
                    Cafe cafe = new Cafe(allcafe.getName(), allcafe.getAddress(), allcafe.getDessert(), allcafe.getTime(), allcafe.getTel(),
                            allcafe.getRestroom(), allcafe.getViews(), allcafe.getImageone(), allcafe.getImagetwo(),
                            allcafe.getImagethr(), "혜화", allcafe.getAvgstar(), allcafe.getReviewcnt(), allcafe.getPos());
                    cafeList.add(cafe);
//                    allCafeList.add(allCafe);
//                    allCafeList.get(allCafeList.size() - 1).setTitle("혜화");
//                    System.out.println(allCafeList.size());
                }
            }
            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                AllCafe allcafe = dataSnapshot.getValue(AllCafe.class);
                for(DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    if(snapshot.getKey().toString().equals("hashtag")) {
                        if(hashtagList.size() < 100 ) {
                            Hashtag gettag = snapshot.getValue(Hashtag.class);
                            Hashtag addtag = new Hashtag(gettag.getHashtag1(), gettag.getHashtag2(), gettag.getHashtag3());
                            hashtagList.add(addtag);
                            System.out.println("해시태그 size 테스트 : " + hashtagList.size());
                            System.out.println("해시태그 잘 들어갔나요? : " + hashtagList.get(hashtagList.size() - 1).getHashtag1() + hashtagList.get(hashtagList.size() - 1).getHashtag2() +
                                    hashtagList.get(hashtagList.size() - 1).getHashtag3());
                            hashtag[(hashtagList.size() - 1) * 3] = hashtagList.get(hashtagList.size() - 1).getHashtag1();
                            hashtag[(hashtagList.size() - 1) * 3 + 1] = hashtagList.get(hashtagList.size() - 1).getHashtag2();
                            hashtag[(hashtagList.size() - 1) * 3 + 2] = hashtagList.get(hashtagList.size() - 1).getHashtag3();
                            System.out.println("해시태그 배열에도 들어갔는지 확인 : " + hashtag[(hashtagList.size() - 1) * 3] + hashtag[(hashtagList.size() - 1) * 3 + 1] +
                                    hashtag[(hashtagList.size() - 1) * 3 + 2]);
                            System.out.println("해시태그!!!!!??!?!?!? : " + snapshot.getValue().toString());
                        }
                    }
                }
                if(cafeList.size() < 68) {
                    Cafe cafe = new Cafe(allcafe.getName(), allcafe.getAddress(), allcafe.getDessert(), allcafe.getTime(), allcafe.getTel(),
                            allcafe.getRestroom(), allcafe.getViews(), allcafe.getImageone(), allcafe.getImagetwo(),
                            allcafe.getImagethr(), "혜화", allcafe.getAvgstar(), allcafe.getReviewcnt(), allcafe.getPos());
                    cafeList.add(cafe);
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

        databaseReference.child("망원동").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                AllCafe allcafe = dataSnapshot.getValue(AllCafe.class);
                for(DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    if(snapshot.getKey().toString().equals("hashtag")) {
                        if(hashtagList.size() < 100 ) {
                            Hashtag gettag = snapshot.getValue(Hashtag.class);
                            Hashtag addtag = new Hashtag(gettag.getHashtag1(), gettag.getHashtag2(), gettag.getHashtag3());
                            hashtagList.add(addtag);
                            System.out.println("해시태그 size 테스트 : " + hashtagList.size());
                            System.out.println("해시태그 잘 들어갔나요? : " + hashtagList.get(hashtagList.size() - 1).getHashtag1() + hashtagList.get(hashtagList.size() - 1).getHashtag2() +
                                    hashtagList.get(hashtagList.size() - 1).getHashtag3());
                            hashtag[(hashtagList.size() - 1) * 3] = hashtagList.get(hashtagList.size() - 1).getHashtag1();
                            hashtag[(hashtagList.size() - 1) * 3 + 1] = hashtagList.get(hashtagList.size() - 1).getHashtag2();
                            hashtag[(hashtagList.size() - 1) * 3 + 2] = hashtagList.get(hashtagList.size() - 1).getHashtag3();
                            System.out.println("해시태그 배열에도 들어갔는지 확인 : " + hashtag[(hashtagList.size() - 1) * 3] + hashtag[(hashtagList.size() - 1) * 3 + 1] +
                                    hashtag[(hashtagList.size() - 1) * 3 + 2]);
                            System.out.println("해시태그!!!!!??!?!?!? : " + snapshot.getValue().toString());
                        }
                    }
                }
                if(cafeList.size() < 68) {
                    Cafe cafe = new Cafe(allcafe.getName(), allcafe.getAddress(), allcafe.getDessert(), allcafe.getTime(), allcafe.getTel(),
                            allcafe.getRestroom(), allcafe.getViews(), allcafe.getImageone(), allcafe.getImagetwo(),
                            allcafe.getImagethr(), "망원동", allcafe.getAvgstar(), allcafe.getReviewcnt(), allcafe.getPos());
                    cafeList.add(cafe);
                }
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                AllCafe allcafe = dataSnapshot.getValue(AllCafe.class);
                for(DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    if(snapshot.getKey().toString().equals("hashtag")) {
                        if(hashtagList.size() < 100 ) {
                            Hashtag gettag = snapshot.getValue(Hashtag.class);
                            Hashtag addtag = new Hashtag(gettag.getHashtag1(), gettag.getHashtag2(), gettag.getHashtag3());
                            hashtagList.add(addtag);
                            System.out.println("해시태그 size 테스트 : " + hashtagList.size());
                            System.out.println("해시태그 잘 들어갔나요? : " + hashtagList.get(hashtagList.size() - 1).getHashtag1() + hashtagList.get(hashtagList.size() - 1).getHashtag2() +
                                    hashtagList.get(hashtagList.size() - 1).getHashtag3());
                            hashtag[(hashtagList.size() - 1) * 3] = hashtagList.get(hashtagList.size() - 1).getHashtag1();
                            hashtag[(hashtagList.size() - 1) * 3 + 1] = hashtagList.get(hashtagList.size() - 1).getHashtag2();
                            hashtag[(hashtagList.size() - 1) * 3 + 2] = hashtagList.get(hashtagList.size() - 1).getHashtag3();
                            System.out.println("해시태그 배열에도 들어갔는지 확인 : " + hashtag[(hashtagList.size() - 1) * 3] + hashtag[(hashtagList.size() - 1) * 3 + 1] +
                                    hashtag[(hashtagList.size() - 1) * 3 + 2]);
                            System.out.println("해시태그!!!!!??!?!?!? : " + snapshot.getValue().toString());
                        }
                    }
                }
                if(cafeList.size() < 68) {
                    Cafe cafe = new Cafe(allcafe.getName(), allcafe.getAddress(), allcafe.getDessert(), allcafe.getTime(), allcafe.getTel(),
                            allcafe.getRestroom(), allcafe.getViews(), allcafe.getImageone(), allcafe.getImagetwo(),
                            allcafe.getImagethr(), "망원동",  allcafe.getAvgstar(), allcafe.getReviewcnt(), allcafe.getPos());
                    cafeList.add(cafe);
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

        databaseReference.child("익선동").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                AllCafe allcafe = dataSnapshot.getValue(AllCafe.class);
                for(DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    if(snapshot.getKey().toString().equals("hashtag")) {
                        if(hashtagList.size() < 100 ) {
                            Hashtag gettag = snapshot.getValue(Hashtag.class);
                            Hashtag addtag = new Hashtag(gettag.getHashtag1(), gettag.getHashtag2(), gettag.getHashtag3());
                            hashtagList.add(addtag);
                            System.out.println("해시태그 size 테스트 : " + hashtagList.size());
                            System.out.println("해시태그 잘 들어갔나요? : " + hashtagList.get(hashtagList.size() - 1).getHashtag1() + hashtagList.get(hashtagList.size() - 1).getHashtag2() +
                                    hashtagList.get(hashtagList.size() - 1).getHashtag3());
                            hashtag[(hashtagList.size() - 1) * 3] = hashtagList.get(hashtagList.size() - 1).getHashtag1();
                            hashtag[(hashtagList.size() - 1) * 3 + 1] = hashtagList.get(hashtagList.size() - 1).getHashtag2();
                            hashtag[(hashtagList.size() - 1) * 3 + 2] = hashtagList.get(hashtagList.size() - 1).getHashtag3();
                            System.out.println("해시태그 배열에도 들어갔는지 확인 : " + hashtag[(hashtagList.size() - 1) * 3] + hashtag[(hashtagList.size() - 1) * 3 + 1] +
                                    hashtag[(hashtagList.size() - 1) * 3 + 2]);
                            System.out.println("해시태그!!!!!??!?!?!? : " + snapshot.getValue().toString());
                        }
                    }
                }
                if(cafeList.size() < 68) {
                    Cafe cafe = new Cafe(allcafe.getName(), allcafe.getAddress(), allcafe.getDessert(), allcafe.getTime(), allcafe.getTel(),
                            allcafe.getRestroom(), allcafe.getViews(), allcafe.getImageone(), allcafe.getImagetwo(),
                            allcafe.getImagethr(), "익선동", allcafe.getAvgstar(), allcafe.getReviewcnt(), allcafe.getPos());
                    cafeList.add(cafe);
//                    allCafeList.add(allCafe);
//                    allCafeList.get(allCafeList.size() - 1).setTitle("익선동");
                }
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                AllCafe allcafe = dataSnapshot.getValue(AllCafe.class);
                for(DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    if(snapshot.getKey().toString().equals("hashtag")) {
                        if(hashtagList.size() < 100) {
                            Hashtag gettag = snapshot.getValue(Hashtag.class);
                            Hashtag addtag = new Hashtag(gettag.getHashtag1(), gettag.getHashtag2(), gettag.getHashtag3());
                            hashtagList.add(addtag);
                            System.out.println("해시태그 size 테스트 : " + hashtagList.size());
                            System.out.println("해시태그 잘 들어갔나요? : " + hashtagList.get(hashtagList.size() - 1).getHashtag1() + hashtagList.get(hashtagList.size() - 1).getHashtag2() +
                                    hashtagList.get(hashtagList.size() - 1).getHashtag3());
                            hashtag[(hashtagList.size() - 1) * 3] = hashtagList.get(hashtagList.size() - 1).getHashtag1();
                            hashtag[(hashtagList.size() - 1) * 3 + 1] = hashtagList.get(hashtagList.size() - 1).getHashtag2();
                            hashtag[(hashtagList.size() - 1) * 3 + 2] = hashtagList.get(hashtagList.size() - 1).getHashtag3();
                            System.out.println("해시태그 배열에도 들어갔는지 확인 : " + hashtag[(hashtagList.size() - 1) * 3] + hashtag[(hashtagList.size() - 1) * 3 + 1] +
                                    hashtag[(hashtagList.size() - 1) * 3 + 2]);
                            System.out.println("해시태그!!!!!??!?!?!? : " + snapshot.getValue().toString());
                        }
                    }
                }
                if(cafeList.size() < 68) {
                    Cafe cafe = new Cafe(allcafe.getName(), allcafe.getAddress(), allcafe.getDessert(), allcafe.getTime(), allcafe.getTel(),
                            allcafe.getRestroom(), allcafe.getViews(), allcafe.getImageone(), allcafe.getImagetwo(),
                            allcafe.getImagethr(), "익선동",  allcafe.getAvgstar(), allcafe.getReviewcnt(), allcafe.getPos());
                    cafeList.add(cafe);
//                    allCafeList.add(allCafe);
//                    allCafeList.get(allCafeList.size() - 1).setTitle("익선동");
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
        databaseReference.child("연남동").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                AllCafe allcafe = dataSnapshot.getValue(AllCafe.class);
                for(DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    if(snapshot.getKey().toString().equals("hashtag")) {
                        if(hashtagList.size() < 100 ) {
                            Hashtag gettag = snapshot.getValue(Hashtag.class);
                            Hashtag addtag = new Hashtag(gettag.getHashtag1(), gettag.getHashtag2(), gettag.getHashtag3());
                            hashtagList.add(addtag);
                            System.out.println("해시태그 size 테스트 : " + hashtagList.size());
                            System.out.println("해시태그 잘 들어갔나요? : " + hashtagList.get(hashtagList.size() - 1).getHashtag1() + hashtagList.get(hashtagList.size() - 1).getHashtag2() +
                                    hashtagList.get(hashtagList.size() - 1).getHashtag3());
                            hashtag[(hashtagList.size() - 1) * 3] = hashtagList.get(hashtagList.size() - 1).getHashtag1();
                            hashtag[(hashtagList.size() - 1) * 3 + 1] = hashtagList.get(hashtagList.size() - 1).getHashtag2();
                            hashtag[(hashtagList.size() - 1) * 3 + 2] = hashtagList.get(hashtagList.size() - 1).getHashtag3();
                            System.out.println("해시태그 배열에도 들어갔는지 확인 : " + hashtag[(hashtagList.size() - 1) * 3] + hashtag[(hashtagList.size() - 1) * 3 + 1] +
                                    hashtag[(hashtagList.size() - 1) * 3 + 2]);
                            System.out.println("해시태그!!!!!??!?!?!? : " + snapshot.getValue().toString());
                        }
                    }
                }
                if(cafeList.size() < 68) {
                    Cafe cafe = new Cafe(allcafe.getName(), allcafe.getAddress(), allcafe.getDessert(), allcafe.getTime(), allcafe.getTel(),
                            allcafe.getRestroom(), allcafe.getViews(), allcafe.getImageone(), allcafe.getImagetwo(),
                            allcafe.getImagethr(), "연남동", allcafe.getAvgstar(), allcafe.getReviewcnt(), allcafe.getPos());
                    cafeList.add(cafe);
//                    allCafeList.add(allCafe);
//                    allCafeList.get(allCafeList.size() - 1).setTitle("연남동");
                }
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                AllCafe allcafe = dataSnapshot.getValue(AllCafe.class);
                for(DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    if(snapshot.getKey().toString().equals("hashtag")) {
                        if(hashtagList.size() < 100 ) {
                            Hashtag gettag = snapshot.getValue(Hashtag.class);
                            Hashtag addtag = new Hashtag(gettag.getHashtag1(), gettag.getHashtag2(), gettag.getHashtag3());
                            hashtagList.add(addtag);
                            System.out.println("해시태그 size 테스트 : " + hashtagList.size());
                            System.out.println("해시태그 잘 들어갔나요? : " + hashtagList.get(hashtagList.size() - 1).getHashtag1() + hashtagList.get(hashtagList.size() - 1).getHashtag2() +
                                    hashtagList.get(hashtagList.size() - 1).getHashtag3());
                            hashtag[(hashtagList.size() - 1) * 3] = hashtagList.get(hashtagList.size() - 1).getHashtag1();
                            hashtag[(hashtagList.size() - 1) * 3 + 1] = hashtagList.get(hashtagList.size() - 1).getHashtag2();
                            hashtag[(hashtagList.size() - 1) * 3 + 2] = hashtagList.get(hashtagList.size() - 1).getHashtag3();
                            System.out.println("해시태그 배열에도 들어갔는지 확인 : " + hashtag[(hashtagList.size() - 1) * 3] + hashtag[(hashtagList.size() - 1) * 3 + 1] +
                                    hashtag[(hashtagList.size() - 1) * 3 + 2]);
                            System.out.println("해시태그!!!!!??!?!?!? : " + snapshot.getValue().toString());
                        }
                    }
                }
                if(cafeList.size() < 68) {
                    Cafe cafe = new Cafe(allcafe.getName(), allcafe.getAddress(), allcafe.getDessert(), allcafe.getTime(), allcafe.getTel(),
                            allcafe.getRestroom(), allcafe.getViews(), allcafe.getImageone(), allcafe.getImagetwo(),
                            allcafe.getImagethr(), "연남동", allcafe.getAvgstar(), allcafe.getReviewcnt(), allcafe.getPos());
                    cafeList.add(cafe);
//                    allCafeList.add(allCafe);
//                    allCafeList.get(allCafeList.size() - 1).setTitle("연남동");
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

        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(getActivity());
        if(account != null) {
            Log.d("로그인 유지 중 닉네임", account.getDisplayName() + ", " + account.getEmail() + ", " + account.getPhotoUrl());
            System.out.println("ID토큰 : " + account.getIdToken());
            System.out.println("이건 뭐지 : " + account.getAccount().toString());
            System.out.println("이건 뭐지2 : " + account.getId());

            //            Glide.with(this).load(account.getPhotoUrl()).circleCrop().into(user_profile);
        }
        final String id = account.getId().toString();
        System.out.println("초기 Myposition : " + myposition);
        databaseReference.child("사용자").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    System.out.println("어떻게 나오나여" + snapshot.getValue());
                    System.out.println("키가 나오나" + snapshot.getKey().toString());
                    for(DataSnapshot snap : snapshot.getChildren()) {
                        System.out.println("뭐가나오냐 " + snap.getKey().toString());
                        if(snap.getKey().toString().equals(id)) {   // id값에 있는 즐겨찾기 목록을 들고오기
                            myposition = snap.getRef().getParent().getKey().toString();
                            System.out.println("부모값이 나오나? " + snap.getRef().getParent().getKey().toString());
                            System.out.println("값이 나와라! " + snap.getValue().toString());
                            for(DataSnapshot snapshot1 : snap.getChildren()) {
                                Favorite allFavorite = snapshot1.getValue(Favorite.class);
                                Favorite favorite = new Favorite(allFavorite.getName());
                                if(allFavoriteList.size() < favoriteList.size()+1) {
                                    allFavoriteList.add(favorite);
                                }
                                favoriteList.add(favorite);
                                System.out.println("즐겨찾기 갯수 " + String.valueOf(favoriteList.size()));
                                System.out.println("모든 즐겨찾기 갯수 " + String.valueOf(allFavoriteList.size()));
                                System.out.println("Myposition : " + myposition);
                            }
                        }
                    }
                    users = String.valueOf(Integer.parseInt(snapshot.getKey().toString())+1);
                    System.out.println("사용자 수 : " + users);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        return rootview;
    }

    private class ViewPagerAdapter extends FragmentPagerAdapter {
        private String tabTitiles[] = {"Tab1", "Tab2", "Tab3"};

        public ViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public long getItemId(int position) {
            return System.currentTimeMillis();
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return SlideFirstPageFragment.newInstance(0, "Page 1");
                case 1:
                    return SlideSecondPageFragment.newInstance(1, "Page 2");
                case 2:
                    return SlideThirdPageFragment.newInstance(2, "Page 3");
                default:
                    return null;
            }
        }

        @Override
        public int getCount() {     // 페이지의 숫자
            return NUM_PAGES; //위에 상수로 정의 페이지 숫자를 상수로 정의함 다섯개로 정의되있음.
        }   //화면에서 사라지면 메모리에 유지해야되는게 효율적으로 유지시켜주는 어댑터

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return tabTitiles[position];
        }
    }

    private void prepareCircles(int i) {
        int[] covers = new int[]{
                R.drawable.circle11, R.drawable.circle22, R.drawable.circle33, R.drawable.circle44,
                R.drawable.circle55, R.drawable.circle5, R.drawable.circle77, R.drawable.circle88
        };

        if (i == 1) {
            Circle c = new Circle("혜화", covers[0]);
            circleList.add(c);
            c = new Circle("망원동", covers[1]);
            circleList.add(c);
            c = new Circle("익선동", covers[2]);
            circleList.add(c);
            c = new Circle("연남동", covers[3]);
            circleList.add(c);
        }
        else if (i == 2) {
            Circle c = new Circle("마카롱", covers[4]);
            circleList2.add(c);
            c = new Circle("티라미수", covers[5]);
            circleList2.add(c);
            c = new Circle("브런치", covers[6]);
            circleList2.add(c);
            c = new Circle("케이크", covers[7]);
            circleList2.add(c);
        }
    }
}