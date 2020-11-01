package com.example.gamsung;;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.util.AbstractCollection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
//커뮤니티에서 하나의 항목(레몬에이드만들기) 클릭했을 때 실행되는 자바라옹~

public class CommunityDetail extends AppCompatActivity {
    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference databaseReference = firebaseDatabase.getReference();
    private DatabaseReference databaseReference2 = firebaseDatabase.getReference();
    private String photo,subject,userDisplayname,material,text;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.community_detail);

        TextView subjectview = (TextView)findViewById(R.id.subject);
        TextView userDisplaynamev = (TextView)findViewById(R.id.userDisplayname);
        TextView materialv = (TextView)findViewById(R.id.material);
        TextView textv = (TextView)findViewById(R.id.text);
        ImageView photov = (ImageView)findViewById(R.id.photo);
        TextView cupper = (TextView)findViewById(R.id.cupper);
        cupper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);

            }
        });

        Intent intent = getIntent();
        subject = intent.getStringExtra("subject");
        subjectview.setText(subject);
        userDisplayname = intent.getStringExtra("userDisplayname");
        userDisplaynamev.setText(userDisplayname);
        material = intent.getStringExtra("material");
        materialv.setText(material);
        text = intent.getStringExtra("text");
        textv.setText(text);
        photo = intent.getStringExtra("photo");
        Glide.with(this).load(photo).into(photov);


    }
}