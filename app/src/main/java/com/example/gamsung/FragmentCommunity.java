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
                "자신의 홈카페를 소개시켜주세요",imgs[4]); //여기서는 홈카페의 게시글을 다 보여주고 처음들어갈때는 홈카페 좋아요순으로 정렬
        communityList.add(c0);

        Community c = new Community("홈카페 재료 챙기기","운영자",
                "홈카페를 하기 위한 나만의 재료를 챙겨보아요",imgs[1]); //

        communityList.add(c);
        Community c1 = new Community("홈카페 인테리어 둘러보기","운영자",
                "커피, 원두, 음료 뿐만 아니라 예쁘 사진을 위한 홈카페 인테리어",imgs[0]);
        communityList.add(c1);

        Community c2 = new Community("홈카페 기본용품 준비하기","운영자",
                "홈카페 첫걸음을 위한 커피도구를 준비해보아요!",imgs[2]);
        communityList.add(c2);


    }
}
