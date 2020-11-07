package com.example.gamsung;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import android.app.AlertDialog;

import androidx.appcompat.app.ActionBarDrawerToggle;
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

import com.bumptech.glide.Glide;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity{
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;
    private Context context = this;

    private FragmentManager fragmentManager = getSupportFragmentManager();
    private FragmentMain fragmentMain = new FragmentMain();
    private FragmentToday fragmentToday = new FragmentToday();
    private FragmentCommunity fragmentCommunity = new FragmentCommunity();

    private FirebaseAuth.AuthStateListener mAuthListener;
    public FirebaseAuth mAuth;
    FirebaseUser user;

    private ImageView user_profile;
    private TextView user_name;
    private TextView user_email;

    @Override
    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); // ViewPager content view 메인화면인됨
        Intent intent = getIntent();

        TextView cupper = (TextView)findViewById(R.id.cupper);
        cupper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);

            }
        });
        final NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        final View headerView = navigationView.inflateHeaderView(R.layout.navi_header);
        user_profile = (ImageView)headerView.findViewById(R.id.user_profile);
        user_name = (TextView)headerView.findViewById(R.id.user_name);
        user_email = (TextView)headerView.findViewById(R.id.user_email);

        // 로그인 정보를 가지고 옴
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
        if(account != null) {
            Toast.makeText(MainActivity.this, account.getDisplayName()+ " 님 반갑습니다", Toast.LENGTH_SHORT).show();
            Log.d("로그인 성공 닉네임", account.getDisplayName() + account.getEmail() + account.getId() + account.getPhotoUrl());

            Glide.with(this).load(account.getPhotoUrl()).circleCrop().into(user_profile);
            user_name.setText(account.getDisplayName() + " 님");
            user_email.setText(account.getEmail());
        }

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(null);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowTitleEnabled(false); // 기존 title 지우기
        //actionBar.setDisplayHomeAsUpEnabled(true); // 뒤로가기 버튼 만들기
       // actionBar.setHomeAsUpIndicator(R.drawable.list); //뒤로가기 버튼 이미지 지정

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close){
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }
        };
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mDrawerLayout.addDrawerListener(mDrawerToggle);
        mDrawerToggle.syncState();

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
                } else if (id == R.id.logout) { // 로그아웃
                    android.app.AlertDialog.Builder dialog = new android.app.AlertDialog.Builder(MainActivity.this);
                    dialog.setMessage("로그아웃 하시겠습니까?").setCancelable(false)
                        .setPositiveButton("네",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    FirebaseAuth.getInstance().signOut();
                                    Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                                    startActivity(intent);
                                    Toast.makeText(MainActivity.this, "로그아웃 중입니다", Toast.LENGTH_SHORT).show();
                                    finish();
                                }
                            }).setNegativeButton("아니오",
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        dialogInterface.cancel();
                                    }
                                });

                    android.app.AlertDialog alert = dialog.create();
                    alert.setTitle("로그아웃");

                    alert.getWindow().setBackgroundDrawable(new ColorDrawable(Color.rgb(250,235,215)));
                    alert.show();
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

        Review review = new Review(text, mood, coffee,dessert, rest, price, star, waiting);
        CafeReview.ReviewList.add(CafeReview.ReviewList.size(), review);
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

    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        mDrawerToggle.onOptionsItemSelected(item);
        return super.onOptionsItemSelected(item);
    }

    /*
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

     */

}

