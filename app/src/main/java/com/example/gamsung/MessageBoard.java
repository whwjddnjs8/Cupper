package com.example.gamsung;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.PopupMenu;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;

import java.util.ArrayList;
import java.util.List;

public class MessageBoard extends AppCompatActivity {
    private Button button;
    private TextView menu;
    private ImageButton write;
    private List<Post> postList = new ArrayList<>();
    private RecyclerView recyclerView;
    private BoardAdapter boardAdapter;
    private String username, profile;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.messageboard);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        // 로그인 정보를 가지고 옴
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
        if(account != null) {
            Log.d("로그인 유지 중 닉네임", account.getDisplayName() + ", " + account.getEmail() + ", " + account.getPhotoUrl());

//            Glide.with(this).load(account.getPhotoUrl()).circleCrop().into(user_profile);
        }
        username = account.getDisplayName();
        profile = account.getPhotoUrl().toString();

        recyclerView = findViewById(R.id.recyclerview);
        boardAdapter = new BoardAdapter(this, postList);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.setAdapter(boardAdapter);

        menu = (TextView)findViewById(R.id.menu);
        button = (Button)findViewById(R.id.menubtn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu popup = new PopupMenu(MessageBoard.this, button);

                MenuInflater inf = popup.getMenuInflater();
                inf.inflate(R.menu.boardmenu, popup.getMenu());
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.item00 :
                                menu.setText("HOME");
                                prepareBoardData(0);
                                break;
                            case R.id.item01 :
                                menu.setText("☕  카페 공유게시판");
                                prepareBoardData(1);
                                break;
                            case R.id.item02 :
                                menu.setText("📜  재료 공유게시판");
                                prepareBoardData(2);
                                break;
                            case R.id.item03 :
                                menu.setText("🍰  레시피 Q & A");
                                prepareBoardData(3);
                                break;
                        }
                        return false;
                    }
                });
                popup.show();
            }
        });

        write = (ImageButton)findViewById(R.id.write);
        write.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), BoardActivity.class);
                startActivity(intent);
            }
        });
    }
    private void prepareBoardData(int boardnum) {
        switch(boardnum) {
            case 0:

                break;
            case 1:
                Post post = new Post("", profile, "최진영", "카페", "제목", "이야아압");
                Post post1 = new Post("https://firebasestorage.googleapis.com/v0/b/gamsung-e3e5a.appspot.com/o/cafeimg%2Fmangwon%2Fmcafe4.jpg?alt=media&token=d8bf8064-9e20-4223-ac29-50a62aef17e5", profile, "최진영", "카페", "제목", "이야아압");
                Post post2 = new Post("", profile, "최진영", "카페", "제목", "이야아압");

                postList.add(post);
                postList.add(post1);
                postList.add(post2);

                break;
            case 2:
                break;
            case 3:
                break;
            default:
                break;
        }
        boardAdapter.notifyDataSetChanged();

    }
}
