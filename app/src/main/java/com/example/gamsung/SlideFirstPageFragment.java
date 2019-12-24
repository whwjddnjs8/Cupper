package com.example.gamsung;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;

public class SlideFirstPageFragment extends Fragment {

    private String title;
    private int page;


    public static SlideFirstPageFragment newInstance(int page, String title) {
        SlideFirstPageFragment fragmentFirst = new SlideFirstPageFragment();
        Bundle args = new Bundle();
        args.putInt("someInt", page);
        args.putString("someTitle", title);
        fragmentFirst.setArguments(args);
        return fragmentFirst;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        page = getArguments().getInt("someInt", 0);
        title = getArguments().getString("someTitle");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_first, container, false);
        ImageView imageView = (ImageView)view.findViewById(R.id.imageView2);
        imageView.setImageResource(R.drawable.cafe1);
        return view;
    }
}
