package com.example.gamsung;

import android.content.Intent;
import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;


public class CafeDetail extends AppCompatActivity{
    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference databaseReference = firebaseDatabase.getReference();

    private ViewPager mPager;
    private PagerAdapter mPagerAdapter;
    private static final int NUM_PAGES = 3;
    private String name, address, dessert, time, tel, restroom , views, imgone, imgtwo, imgthr, title, avgstar, reviewcnt, pos;
    private String hashtag1, hashtag2, hashtag3;
    public static String cnt = String.valueOf(0);
    private boolean check = false;
    private String position;
    private RatingBar ratingBar;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cafe_detail);

        TextView nametv = findViewById(R.id.name);
        TextView addresstv = findViewById(R.id.address);
        TextView starr = findViewById(R.id.star);
        TextView timetv = findViewById(R.id.time);
        TextView teltv = findViewById(R.id.tel);
        TextView toilet = findViewById(R.id.restroom);
        TextView menutv = findViewById(R.id.menutv);
        TextView hashtag1tv = findViewById(R.id.tag1);
        TextView hashtag2tv = findViewById(R.id.tag2);
        TextView hashtag3tv = findViewById(R.id.tag3);
        RatingBar ratingBar = findViewById(R.id.ratingbar);

        final ImageButton favorite = (ImageButton)findViewById(R.id.favorite);
        ImageButton info = (ImageButton)findViewById(R.id.info);
        final ImageButton review =(ImageButton) findViewById(R.id.review);
        ImageButton web = (ImageButton)findViewById(R.id.web);

        Intent intent = getIntent();
        name = intent.getStringExtra("name");
        nametv.setText(name);
        address = intent.getStringExtra("address");
        addresstv.setText(address);
        dessert = intent.getStringExtra("dessert");
        menutv.setText(dessert);
        avgstar = intent.getStringExtra("star");
        starr.setText(avgstar);
        time = intent.getStringExtra("time");
        timetv.setText(time);
        tel = intent.getStringExtra("tel");
        teltv.setText(tel);
        restroom = intent.getStringExtra("restroom");
        toilet.setText(restroom);
        hashtag1 = intent.getStringExtra("hashtag1");
        hashtag1tv.setText(hashtag1);
        hashtag2 = intent.getStringExtra("hashtag2");
        hashtag2tv.setText(hashtag2);
        hashtag3 = intent.getStringExtra("hashtag3");
        hashtag3tv.setText(hashtag3);
        views = intent.getStringExtra("views");
        title = intent.getStringExtra("title");
        reviewcnt = intent.getStringExtra("reviewcnt");
        imgone = intent.getStringExtra("imgone");
        imgtwo = intent.getStringExtra("imgtwo");
        imgthr = intent.getStringExtra("imgthr");
        pos = intent.getStringExtra("pos");
        ratingBar.setRating(Float.valueOf(avgstar));

        mPager = findViewById(R.id.pager);
        mPagerAdapter = new SlidePagerAdapter(getSupportFragmentManager());
        mPager.setAdapter(mPagerAdapter);
        mPager.setPageTransformer(true, new ZoomOutTransformer());

        mPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
            }
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {  //현재 페이지가 사라져서 스크롤 됐을 때
            }
            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
        // 로그인 정보를 가지고 옴
        final GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
        if(account != null) {
            Log.d("로그인 유지 중 닉네임", account.getDisplayName() + ", " + account.getEmail() + ", " + account.getPhotoUrl());
            System.out.println("ID토큰 : " + account.getIdToken());
            System.out.println("이건 뭐지 : " + account.getAccount().toString());
            System.out.println("이건 뭐지2 : " + account.getId());


            //            Glide.with(this).load(account.getPhotoUrl()).circleCrop().into(user_profile);
        }
        System.out.println("체크!!!!! + " + check);
        for(int i = 0; i < FragmentMain.allFavoriteList.size(); i++) {
            // 즐겨찾기에 추가되어있는 이름과 상세보기 하는 이름이 같으면(즐겨찾기 목록에 있으면 true)
            if(FragmentMain.allFavoriteList.get(i).getName().equals(name)) {
                check = true;
                position = String.valueOf(i);
                favorite.setImageResource(R.drawable.star_yellow);

            }
        }
        // 즐겨찾기 누르면 색이 바뀜
        favorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id = account.getId().toString();
                databaseReference = FirebaseDatabase.getInstance().getReference().child("사용자/");

                Map<String, Object> updateMap = new HashMap<>();

                if(favorite.getTag() != null && favorite.getTag().toString().equals("yellow")) {
                    favorite.setImageResource(R.drawable.star_grey);
                    favorite.setTag("grey");
                    if(check == true) {
                        System.out.println("제대로 된 거 불렀나? " + position);
                        check = false;
                    }
                }
                else {
                    favorite.setImageResource(R.drawable.star_yellow);
                    favorite.setTag("yellow");
                    updateMap.put("name", name);
                    cnt = String.valueOf(FragmentMain.allFavoriteList.size());

                    if(check == false) {
                        // 즐겨찾기 한 카페를 추가하는 것
                        databaseReference.child(id).child(cnt).updateChildren(updateMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                System.out.println("SuccessFul!!!!!!!!!!!!!!!!!!!11");
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                System.out.println("Failure!!!!!!!!!!!!!!!!!!!!11");

                            }
                        });
                        // 즐겨찾기 한 카페의 수를 갱신
                        Map<String, Object> updateMap2 = new HashMap<>();
                        updateMap2.put("cnt", String.valueOf(Integer.parseInt(cnt) + 1));
                        //cnt를 증가시키고 update시킴
                        databaseReference.updateChildren(updateMap2).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                System.out.println("SuccessFul!!!!!!!!!!!!!!!!!!!11");
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                System.out.println("Failure!!!!!!!!!!!!!!!!!!!!11");
                            }
                        });

                        Toast.makeText(getApplicationContext(), "즐겨찾기에 추가되었습니다!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        review.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), CafeReview.class);
                Bundle extras = new Bundle();
                extras.putString("name", name);
                intent.putExtras(extras);
                startActivity(intent);
            }
        });
        web.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), CafeWebView.class);
                Bundle extras = new Bundle();
                extras.putString("name", name);
                intent.putExtras(extras);
                startActivity(intent);
            }
        });
    }


    @Override
    public void onBackPressed() {
        if (mPager.getCurrentItem() == 0) {
            super.onBackPressed();
        } else {
            mPager.setCurrentItem(mPager.getCurrentItem() - 1);
        }
    }

    // FragmentAdapter class 를 정의
    private class SlidePagerAdapter extends FragmentStatePagerAdapter {     // 어댑터 두개의 메소드 가져와야함
        private String tabTitiles[] = {"Tab1", "Tab2", "Tab3"};

        public SlidePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return SlidePageFragment.newInstance(0, imgone);
                case 1:
                    return SlidePageFragment.newInstance(1, imgtwo);
                case 2:
                    return SlidePageFragment.newInstance(2, imgthr);
                default:
                    return null;
            }
        }
        @Override
        public int getCount() {
            return NUM_PAGES;
        }
        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return tabTitiles[position];
        }
    }
}