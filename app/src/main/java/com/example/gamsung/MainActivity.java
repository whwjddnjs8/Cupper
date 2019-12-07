package com.example.gamsung;

import android.os.Bundle;
import android.widget.ImageView;


import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerView recyclerView2;

    private CircleAdapter circleadapter;
    private List<Circle> circleList;
    private List<Circle> circleList2;

    private ViewPager mPager;
    private PagerAdapter mPagerAdapter;
    private static final int NUM_PAGES = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); // ViewPager content view 메인화면인됨

        /** ActionBar 없애기 **/
        ActionBar ab = getSupportActionBar();
        ab.hide();

        /** recyclerView 설정 **/
        recyclerView = findViewById(R.id.recycler_view);
        recyclerView2 = findViewById(R.id.recycler_view2);

        circleList = new ArrayList<>();
        circleadapter = new CircleAdapter(this, circleList);
        RecyclerView.LayoutManager manager = new GridLayoutManager(this, 4);
        recyclerView.setLayoutManager(manager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(circleadapter);
        prepareCircles(1);

        circleList2 = new ArrayList<>();
        circleadapter = new CircleAdapter(this, circleList2);
        RecyclerView.LayoutManager manager1 = new GridLayoutManager(this, 4);
        recyclerView2.setLayoutManager(manager1);
        recyclerView2.setItemAnimator(new DefaultItemAnimator());
        recyclerView2.setAdapter(circleadapter);
        prepareCircles(2);

        mPager = (ViewPager) findViewById(R.id.pager);  // 페이저를가져옴
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
        public Fragment getItem(int position) {     // 페이지를 만드는 역할 페이지를 페이저에게 전달해주는 역할
            switch (position) {
                case 0:
                    return SlideFirstPageFragment.newInstance(0, "Page 1");     // 첫번째 페이지를 하나 만듬 SlideFirstPageFragment는 fragment로 상속되어잇음 그래야 액티비티의 화면에 포함되어잇음.
                case 1:
                    return SlideSecondPageFragment.newInstance(1, "Page 2");    //
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
                R.drawable.circle, R.drawable.circle2, R.drawable.circle3, R.drawable.circle4,
                R.drawable.circle8, R.drawable.circle5, R.drawable.circle6, R.drawable.circle7
        };

        if (i == 1) {
            Circle c = new Circle("혜화", covers[0]);
            circleList.add(c);
            c = new Circle("망원동", covers[1]);
            circleList.add(c);
            c = new Circle("익선동", covers[2]);
            circleList.add(c);
            c = new Circle("성수동", covers[3]);
            circleList.add(c);
        }
        else if (i == 2) {
            Circle c = new Circle("마카롱", covers[4]);
            circleList2.add(c);
            c = new Circle("티라미수", covers[5]);
            circleList2.add(c);
            c = new Circle("브런치", covers[6]);
            circleList2.add(c);
            c = new Circle("타르트", covers[7]);
            circleList2.add(c);
        }
    }
}
