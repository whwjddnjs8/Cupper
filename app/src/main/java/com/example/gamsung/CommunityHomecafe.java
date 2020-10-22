package com.example.gamsung;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import java.util.ArrayList;
import java.util.List;

//커뮤니티 목록중에 3번째 홈카페 인테리어 누르면 동작되는 클래스

public class CommunityHomecafe extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.homecafe_main);

        ImageView homegif = (ImageView) findViewById(R.id.homegif);
        Glide.with(this).load(R.raw.homegif).into(homegif);

        Animation animation = AnimationUtils.loadAnimation(this,R.anim.translate);
        homegif.startAnimation(animation);
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }
}
