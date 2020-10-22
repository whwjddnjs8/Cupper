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

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;
// Community_tool에서 배너를 클릭했을 때 동작

public class BannerCoffee extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.banner_coffee);

//        TextView t = (TextView)findViewById(R.id.textView);
//        Animation animation = AnimationUtils.loadAnimation(this,R.anim.translate);
//        t.startAnimation(animation);
        final TextView t1 = (TextView)findViewById(R.id.textView1);
        final TextView t2 = (TextView)findViewById(R.id.textView2);
        final TextView t3 = (TextView)findViewById(R.id.textView3);
        final TextView t4 = (TextView)findViewById(R.id.textView4);
        final TextView t5 = (TextView)findViewById(R.id.textView5);
        final TextView t6 = (TextView)findViewById(R.id.textView6);
        final TextView t7 = (TextView)findViewById(R.id.textView7);
        final TextView t8 = (TextView)findViewById(R.id.textView8);
        final TextView t9 = (TextView)findViewById(R.id.textView9);
        final TextView t10 = (TextView)findViewById(R.id.textView10);
        ImageView image = (ImageView)findViewById(R.id.image);
        ImageButton imageButton = (ImageButton)findViewById(R.id.buttonpoint) ;
        Animation animation = AnimationUtils.loadAnimation(this,R.anim.translate);
        image.startAnimation(animation);
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);

        t1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                t2.setVisibility(t2.getVisibility() == View.VISIBLE ? View.GONE : View.VISIBLE);
            }
        });
        t3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                t4.setVisibility(t4.getVisibility() == View.VISIBLE ? View.GONE : View.VISIBLE);
            }
        });
        t5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                t6.setVisibility(t6.getVisibility() == View.VISIBLE ? View.GONE : View.VISIBLE);
            }
        });
        t7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                t8.setVisibility(t8.getVisibility() == View.VISIBLE ? View.GONE : View.VISIBLE);
            }
        });
        t9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                t10.setVisibility(t10.getVisibility() == View.VISIBLE ? View.GONE : View.VISIBLE);
            }
        });
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),BeansActivity.class);
                startActivity(intent);
            }
        });




    }

}