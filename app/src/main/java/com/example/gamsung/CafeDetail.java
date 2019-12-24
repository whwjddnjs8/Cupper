package com.example.gamsung;

import android.content.Intent;
import android.os.Bundle;
import android.text.Layout;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
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


public class CafeDetail extends AppCompatActivity{
    private ViewPager mPager;
    private PagerAdapter mPagerAdapter;
    private static final int NUM_PAGES = 3;
    private String name, address, dessert, time, tel, restroom , views, imgone, imgtwo, imgthr, title, price, star, reviewcnt, pos;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cafe_detail);

        TextView nametv = findViewById(R.id.name);
        TextView addresstv = findViewById(R.id.address);
        TextView pay = findViewById(R.id.price);
        TextView starr = findViewById(R.id.star);
        TextView timetv = findViewById(R.id.time);
        TextView teltv = findViewById(R.id.tel);
        TextView toilet = findViewById(R.id.restroom);
        TextView menutv = findViewById(R.id.menutv);
        TextView paytv = findViewById(R.id.pay);
        ImageButton info = (ImageButton)findViewById(R.id.info);
        ImageButton review =(ImageButton) findViewById(R.id.review);
        ImageButton web = (ImageButton)findViewById(R.id.web);

        Intent intent = getIntent();
        name = intent.getStringExtra("name");
        nametv.setText(name);
        address = intent.getStringExtra("address");
        addresstv.setText(address);
        dessert = intent.getStringExtra("dessert");
        menutv.setText(dessert);
        price = intent.getStringExtra("price");
        pay.setText(price);
        paytv.setText(price);
        star = intent.getStringExtra("star");
        starr.setText(star);
        time = intent.getStringExtra("time");
        timetv.setText(time);
        tel = intent.getStringExtra("tel");
        teltv.setText(tel);
        restroom = intent.getStringExtra("restroom");
        toilet.setText(restroom);
        views = intent.getStringExtra("views");
        title = intent.getStringExtra("title");
        reviewcnt = intent.getStringExtra("reviewcnt");
        imgone = intent.getStringExtra("imgone");
        imgtwo = intent.getStringExtra("imgtwo");
        imgthr = intent.getStringExtra("imgthr");
        pos = intent.getStringExtra("pos");

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
