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
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListAdapter;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatEditText;

public class CafeReview extends AppCompatActivity {
    private RatingBar review_ratingBar;
    private ImageButton addButton, subButton;
    private TextView textView;
    private EditText reviewText;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cafe_review);

        textView = (TextView)findViewById(R.id.textView);
        reviewText = (EditText)findViewById(R.id.reviewText);
        addButton = (ImageButton)findViewById(R.id.add);
        subButton = (ImageButton)findViewById(R.id.sub);
        reviewText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

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

