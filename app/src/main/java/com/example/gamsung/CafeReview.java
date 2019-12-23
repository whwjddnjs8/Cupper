package com.example.gamsung;

import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RectShape;
import android.media.Image;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListAdapter;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;

public class
CafeReview extends AppCompatActivity {
    private RatingBar review_ratingBar;
    private ImageButton addButton, subButton;
    private TextView textView;
    private EditText reviewText;
    private Button mood1,mood2,mood3,mood4,mood5,mood6,mood7,mood8; //분위기 버튼 8개
    private Button coffee1,coffee2,coffee3,dessert1,dessert2,dessert3; //커피와 디저트 버튼 각각 3개씩
    private Button rgood,rbac; //화장실 좋은지 나쁜지
    private Button rest1,rest2,rest3,rest4;// 남녀구분,남녀공용,내부,외부
    private Button price1,price2,waiting1,waiting2; //가격과 웨이팅


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cafe_review);

        textView = (TextView)findViewById(R.id.textView);
        reviewText = (EditText)findViewById(R.id.reviewText);
        addButton = (ImageButton)findViewById(R.id.add);
        subButton = (ImageButton)findViewById(R.id.sub);
        mood1 = (Button)findViewById(R.id.mood1);
        mood2 = (Button)findViewById(R.id.mood2);
        mood3 = (Button)findViewById(R.id.mood3);
        mood4 = (Button)findViewById(R.id.mood4);
        mood5 = (Button)findViewById(R.id.mood5);
        mood6 = (Button)findViewById(R.id.mood6);
        mood7 = (Button)findViewById(R.id.mood7);
        mood8 = (Button)findViewById(R.id.mood8);
        coffee1 = (Button)findViewById(R.id.coffe1);
        coffee2 = (Button)findViewById(R.id.coffe2);
        coffee3 = (Button)findViewById(R.id.coffe3);
        dessert1 = (Button)findViewById(R.id.dessert1);
        dessert2 = (Button)findViewById(R.id.dessert2);
        dessert3 = (Button)findViewById(R.id.dessert3);
        //아직 더남았음



        reviewText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            // 글자 수가 변동되면 동작하는 메소드
            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
                String input = reviewText.getText().toString();
                textView.setText(input.length() + "/ 800 자");
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        review_ratingBar = (RatingBar)findViewById(R.id.review_ratingbar);
        review_ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
            // RatingBar의 별점 값이 변하면 동작하는 메소드
            }
        });

        // +버튼을 누르면 동작하는 메소드(0.5점씩 올려줌)
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                review_ratingBar.setRating((float)(review_ratingBar.getRating() + 0.5));
            }
        });
        // -버튼을 누르면 동작하는 메소드(0.5점씩 내려줌)
        subButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                review_ratingBar.setRating((float)(review_ratingBar.getRating() + 0.5));
            }
        });
    }
}

