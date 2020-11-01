package com.example.gamsung;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;

import java.util.ArrayList;
import java.util.List;

public class FragmentMypage extends Fragment {
    private ImageView profile;
    private TextView user_name;
    private RecyclerView recyclerView;
    private FavoriteAdapter favoriteAdapter;
    private List<Favorite> favoriteList;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup rootview = (ViewGroup)inflater.inflate(R.layout.fragment_mypage, container, false);

        profile = rootview.findViewById(R.id.profile);
        user_name = rootview.findViewById(R.id.user_name);
        recyclerView = (RecyclerView)rootview.findViewById(R.id.recyclerview);
        favoriteList = new ArrayList<>();
        favoriteAdapter = new FavoriteAdapter(getActivity(), favoriteList);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(favoriteAdapter);
        prepareFavorite();

        // 로그인 정보를 가지고 옴
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(getActivity());
        if(account != null) {
            Log.d("로그인 유지 중 닉네임", account.getDisplayName() + ", " + account.getEmail() + ", " + account.getPhotoUrl());
            System.out.println("ID토큰 : " + account.getIdToken());
            System.out.println("이건 뭐지 : " + account.getAccount().toString());
            System.out.println("이건 뭐지2 : " + account.getId());


            //            Glide.with(this).load(account.getPhotoUrl()).circleCrop().into(user_profile);
        }
        Glide.with(this).load(account.getPhotoUrl()).circleCrop().into(profile);
        user_name.setText(account.getDisplayName());

        for(int i = 0; i < FragmentMain.allFavoriteList.size(); i++) {
            System.out.println("즐겨찾기 한 카페 이름 : " + FragmentMain.allFavoriteList.get(i).getName());
        }
        return rootview;
    }

    private void prepareFavorite() {
        for(int i = 0; i < FragmentMain.allFavoriteList.size(); i++) {
            Favorite f = new Favorite(FragmentMain.allFavoriteList.get(i).getName());
            favoriteList.add(i, f);
        }
        favoriteAdapter.notifyDataSetChanged();
    }
}