package com.example.gamsung;

import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class FragmentCommunity extends Fragment {
    private List<Community> communityList = new ArrayList<>();
    private CommunityAdapter communityAdapter;
    private RecyclerView recyclerView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup rootview = (ViewGroup)inflater.inflate(R.layout.community_main, container, false);
        recyclerView = (RecyclerView)rootview.findViewById(R.id.recycler_view);
        communityList = new ArrayList<>();
        communityAdapter = new CommunityAdapter(getActivity(), communityList);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(communityAdapter);
        prepareCommunity();
        return rootview;
    }
    private void prepareCommunity() {
        final int[] imgs = new int[] {
                R.drawable.home1,R.drawable.home2,R.drawable.home3,R.drawable.home4, R.drawable.home5
        };
        Community c0 = new Community("CUPPER 커뮤니티 바로가기",
                "자신의 홈카페를 소개시켜주세요",imgs[4]);
        communityList.add(c0);

        Community c = new Community("이번주 홈카페 BEST10","운영자",
                "이번주 좋아요를 많이 받은 홈카페 게시글!",imgs[1],100);

        communityList.add(c);
        Community c1 = new Community("홈카페 레시피","운영자",
                "홈카페 레시피를 참고하여 나만의 음료를 만들어보아요",imgs[0],120);
        communityList.add(c1);

        Community c2 = new Community("홈카페 기본용품 준비하기","운영자",
                "홈카페 첫걸음을 위한 커피도구",imgs[2],130);
        communityList.add(c2);


    }
}
