package com.example.gamsung;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;

public class SlidePageFragment extends Fragment {
    private int page;
    private String img;

    public static SlidePageFragment newInstance(int page, String img) {
        SlidePageFragment fragment = new SlidePageFragment();
        Bundle args = new Bundle();
        args.putInt("int", page);
        args.putString("img", img);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        page = getArguments().getInt("int", 0);
        img = getArguments().getString("img");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = null;
        if(page == 0) {
            view = inflater.inflate(R.layout.fragment_first, container, false);
        }
        else if(page == 1) {
            view = inflater.inflate(R.layout.fragment_second, container, false);
        }
        else if(page == 2) {
            view = inflater.inflate(R.layout.fragment_third, container, false);
        }

        ImageView imageView = (ImageView)view.findViewById(R.id.imageView2);
        Glide.with(getContext()).load(img).into(imageView);

        return view;
    }
}
