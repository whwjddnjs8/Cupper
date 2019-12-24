package com.example.gamsung;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerView recyclerView2;

    private CircleAdapter circleadapter;
    private List<Circle> circleList;
    private List<Circle> circleList2;

    private FragmentManager fragmentManager = getSupportFragmentManager();
    private FragmentMain fragmentMain = new FragmentMain();
    private FragmentReview fragmentReview = new FragmentReview();
    private FragmentToday fragmentToday = new FragmentToday();
    private FragmentMypage fragmentMypage = new FragmentMypage();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); // ViewPager content view 메인화면인됨

        Intent intent = new Intent(this, LoadingActivity.class);
        startActivity(intent);
        /** ActionBar 없애기 **/
        ActionBar ab = getSupportActionBar();
        ab.hide();

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

        AllReview review = new AllReview(text,text, mood, coffee, rest, price, star, waiting, name,imgtwo);
        CafeReview.allReviewList.add(CafeReview.allReviewList.size(), review);
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.frameLayout, fragmentMain).commitAllowingStateLoss();

        BottomNavigationView bottomNavigationView = findViewById(R.id.navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new ItemSelectedListener());
    }


    class ItemSelectedListener implements BottomNavigationView.OnNavigationItemSelectedListener, BottomNavigationView.OnNavigationItemReselectedListener{

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            switch (menuItem.getItemId()) {
                case R.id.navigation_home:
                    transaction.replace(R.id.frameLayout, fragmentMain).commitAllowingStateLoss();
                    break;
                case R.id.navigation_today:
                    transaction.replace(R.id.frameLayout, fragmentToday).commitAllowingStateLoss();
                    break;
                case R.id.navigation_review:
                    transaction.replace(R.id.frameLayout, fragmentReview).commitAllowingStateLoss();
                    break;
                case R.id.navigation_mypage:
                    transaction.replace(R.id.frameLayout, fragmentMypage).commitAllowingStateLoss();
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
                case R.id.navigation_today:
                    transaction.replace(R.id.frameLayout, fragmentToday).commitAllowingStateLoss();
                    break;
                case R.id.navigation_review:
                    transaction.replace(R.id.frameLayout, fragmentReview).commitAllowingStateLoss();
                    break;
                case R.id.navigation_mypage:
                    transaction.replace(R.id.frameLayout, fragmentMypage).commitAllowingStateLoss();
                    break;
            }

        }
    }
}
