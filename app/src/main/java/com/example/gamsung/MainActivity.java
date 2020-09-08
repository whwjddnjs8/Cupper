package com.example.gamsung;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentHostCallback;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity{
    private DrawerLayout mDrawerLayout;
    private Context context = this;

    private FragmentManager fragmentManager = getSupportFragmentManager();
    private FragmentMain fragmentMain = new FragmentMain();
    private FragmentToday fragmentToday = new FragmentToday();
    private FragmentCommunity fragmentCommunity = new FragmentCommunity();

    @Override
    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); // ViewPager content view 메인화면인됨

        Intent intent = new Intent(this, LoadingActivity.class);
        startActivity(intent);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowTitleEnabled(false); // 기존 title 지우기
        actionBar.setDisplayHomeAsUpEnabled(true); // 뒤로가기 버튼 만들기
        actionBar.setHomeAsUpIndicator(R.drawable.list); //뒤로가기 버튼 이미지 지정

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);



        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                menuItem.setChecked(true);
                mDrawerLayout.closeDrawers();

                int id = menuItem.getItemId();
                String title = menuItem.getTitle().toString();
                //추후 if문 수정
                if (id == R.id.recommend) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout,
                            new FragmentToday()).commit();
                } else if (id == R.id.review) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout,
                            new FragmentReview()).commit();
                }
                else if (id == R.id.community) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout,
                            new FragmentCommunity()).commit();
                }
                else if (id == R.id.setting) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout,
                            new FragmentMypage()).commit();
                } else if (id == R.id.logout) {
                    Toast.makeText(context, title + ": 로그아웃 시도중", Toast.LENGTH_SHORT).show();
                }
                return true;
            }
        });

        Intent intent1 = getIntent();
        String name = intent1.getStringExtra("name");
        String text = intent1.getStringExtra("text");
        String mood = intent1.getStringExtra("mood");
        String coffee = intent1.getStringExtra("coffee");
        String dessert = intent1.getStringExtra("dessert");
        String rest = intent1.getStringExtra("rest");
        String price = intent1.getStringExtra("price");
        String star = intent1.getStringExtra("star");
        String waiting = intent1.getStringExtra("waiting");
        String imgtwo = intent1.getStringExtra("imgtwo");

        AllReview review = new AllReview(text, text, mood, coffee, rest, price, star, waiting, name, imgtwo);
        CafeReview.allReviewList.add(CafeReview.allReviewList.size(), review);
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.frameLayout, fragmentMain).commitAllowingStateLoss();
        BottomNavigationView bottomNavigationView = findViewById(R.id.navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new ItemSelectedListener());
    }


class ItemSelectedListener implements BottomNavigationView.OnNavigationItemSelectedListener, BottomNavigationView.OnNavigationItemReselectedListener {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            switch (menuItem.getItemId()) {
                case R.id.navigation_home:
                    transaction.replace(R.id.frameLayout, fragmentMain).commitAllowingStateLoss();
                    break;
                case R.id.navigation_community:
                    transaction.replace(R.id.frameLayout, fragmentCommunity).commitAllowingStateLoss();
                    break;

            }
            return true;
        }

        @Override
        public void onNavigationItemReselected(@NonNull MenuItem menuItem) {
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            switch (menuItem.getItemId()) {
                case R.id.navigation_home:
                    transaction.replace(R.id.frameLayout, fragmentMain).commitAllowingStateLoss();
                    break;
                case R.id.navigation_community:
                    transaction.replace(R.id.frameLayout, fragmentCommunity).commitAllowingStateLoss();
                    break;
            }
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.list: {// 왼쪽 상단 버튼 눌렀을 l때
                mDrawerLayout.openDrawer(GravityCompat.START);
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }

}

