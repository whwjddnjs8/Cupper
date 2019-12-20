package com.example.gamsung;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class FragmentReview extends Fragment {
    private RecyclerView recyclerView;
    private ReviewAdapter reviewAdapter;
    private List<Review> reviewList;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup rootview = (ViewGroup)inflater.inflate(R.layout.fragment_review, container, false);
        recyclerView = (RecyclerView)rootview.findViewById(R.id.recycler_view);
        reviewList = new ArrayList<>();
        reviewAdapter = new ReviewAdapter(getActivity(), reviewList);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(reviewAdapter);
        prepareReview();
        return rootview;
    }
    public void prepareReview(){
        final int[] imgs = new int[] {
          R.drawable.cafe1,R.drawable.cafe1,R.drawable.cafe1,R.drawable.cafe1
        };
        Review r = new Review(imgs[0],imgs[1],"조정원",
                "갬성","맛있어요!","sdfsdf","sdf","sdf");
        reviewList.add(r);
        r = new Review(imgs[2],imgs[3],"조정원",
                "갬성","dsg","sdfsdf","sdf","sdf");
        reviewList.add(r);
    }

}
