package com.example.gamsung;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;

public class SlideThirdPageFragment  extends Fragment {
    // Store instance variables
    private String title;
    private int page;

    // newInstance constructor for creating fragment with arguments
    public static SlideThirdPageFragment newInstance(int page, String title) {
        SlideThirdPageFragment fragmentThird = new SlideThirdPageFragment();
        Bundle args = new Bundle();
        args.putInt("someInt", page);
        args.putString("someTitle", title);
        fragmentThird.setArguments(args);
        return fragmentThird;
    }
    /* Not allowed
    public SlideFirstPageFragment(int p, String t) {
        page = p; title = t;
    }*/

    // Store instance variables based on arguments passed
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        page = getArguments().getInt("someInt", 0);
        title = getArguments().getString("someTitle");
    }

    // Inflate the view for the fragment based on layout XML
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) { //첫번째 화면 디자인을 불러와서 첫번째 화면(뷰)을 만들어주는 곳.
        View view = inflater.inflate(R.layout.fragment_third, container, false);
        //TextView tvLabel = (TextView) view.findViewById(R.id.message);
        ImageView imageView = (ImageView)view.findViewById(R.id.imageView2);
        imageView.setImageResource(R.drawable.cafe3);
        //tvLabel.setText(page + " -- " + title);
        return view;
    }
}

