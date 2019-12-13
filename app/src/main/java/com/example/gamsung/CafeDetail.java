package com.example.gamsung;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;

public class CafeDetail extends AppCompatActivity{
    private ViewPager mPager;
    private PagerAdapter mPagerAdapter;
    private static final int NUM_PAGES = 3;
    private String title,price,star;
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cafe_detail);
        TextView name = findViewById(R.id.name);
        TextView pay = findViewById(R.id.price);
        TextView starr = findViewById(R.id.star);
        Intent intent = getIntent();
        title = intent.getStringExtra("title");
        name.setText(title);
        price = intent.getStringExtra("price");
        pay.setText(price);
        star = intent.getStringExtra("star");
        starr.setText(star);
        mPager = findViewById(R.id.pager);  // 페이저를가져옴
        mPagerAdapter = new SlidePagerAdapter(getSupportFragmentManager());     //어댑터 클래스를 오브젝트로 만들어서 어댑터로 가져옴.
        mPager.setAdapter(mPagerAdapter);   //어댑터를 등록
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
        BottomNavigationView bottomNavigationView = findViewById(R.id.navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                        switch (item.getItemId()) {
                            case R.id.navigation_info:
                                findViewById(R.id.email_item_layout);
                                return true;
                            case R.id.navigation_review:
                                return true;
                            case R.id.navigation_menu:
                                 return true;
                        }
                        return false;
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
                        return SlideFirstPageFragment.newInstance(0, "Page 1");
                    case 1:
                        return SlideSecondPageFragment.newInstance(1, "Page 2");
                    case 2:
                        return new SlidePageFragment();
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
