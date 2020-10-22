package com.example.gamsung;

import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.icu.text.SimpleDateFormat;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import android.app.AlertDialog;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;
import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CafeReview extends AppCompatActivity {
    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference databaseReference = firebaseDatabase.getReference();

    public static List<Review> ReviewList = new ArrayList<>();
    private RatingBar review_ratingBar;
    private ImageButton addButton, subButton;
    private Uri filePath;
    private TextView textView,search;
    private ImageView reviewimage;
    private Button imagebutton;
    private EditText reviewText;
    private EditText hash1,hash2,hash3; //xml에서 가져온 edittext 해시태그
    private String name = null, title;
    private String tag1, tag2, tag3; //해시태그 String버전
    private String mood, coffee, dessert,rdessert, rest,rest2,rest3, price, rprice,waiting, star, text, reviewcnt,img;
    //dessert:케이크,마카롱 이런거 rdessert:리뷰로 남긴 디저트의 맛
    private Button mood1, mood2, mood3, mood4, mood5, mood6, mood7, mood8; //분위기 버튼 8개
    private Button coffee1, coffee2, coffee3, dessert1, dessert2, dessert3; //커피와 디저트 버튼 각각 3개씩
    private Button rgood, rbad; //화장실 좋은지 나쁜지
    private Button rpeople, rnpeople; //화장실 공용 구분
    private Button in, out; //화장실 내부, 외부
    private Button price1,price2,waiting1,waiting2; //가격과 웨이팅
    private Button reviewbtn;
    private String button;
    private String pos;
    private String address, time, tel, restroom, views, imgone, imgtwo, imgthr;
    private int no = 0;
    private File tempFile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cafe_review);

        textView = (TextView)findViewById(R.id.textView);
        search = (TextView)findViewById(R.id.myFilter);
        reviewimage = (ImageView)findViewById(R.id.reviewimage);
        imagebutton = (Button)findViewById(R.id.imagebutton);
        reviewText = (EditText)findViewById(R.id.reviewText);
        hash1 = (EditText)findViewById(R.id.tag1);
        hash2 = (EditText)findViewById(R.id.tag2);
        hash3 = (EditText)findViewById(R.id.tag3);
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
        rgood = (Button)findViewById(R.id.rgood);
        rbad = (Button)findViewById(R.id.rbad);
        rpeople = (Button)findViewById(R.id.rest1);
        rnpeople = (Button)findViewById(R.id.rest2);
        in = (Button)findViewById(R.id.rest3);
        out = (Button)findViewById(R.id.rest4);
        price1 = (Button)findViewById(R.id.price1);
        price2 = (Button)findViewById(R.id.price2);
        waiting1 = (Button)findViewById(R.id.waiting1);
        waiting2 = (Button)findViewById(R.id.waiting2);
        reviewbtn = (Button)findViewById(R.id.review);

        Intent intent = getIntent();
        name = intent.getStringExtra("name");
        if(name != null) {
            search.setText(""+name);
        }
        price = intent.getStringExtra("price");
        dessert = intent.getStringExtra("dessert");
        address = intent.getStringExtra("address");
        time = intent.getStringExtra("time");
        star = intent.getStringExtra("star");
        tel = intent.getStringExtra("tel");
        restroom = intent.getStringExtra("restroom");
        views = intent.getStringExtra("views");
        title = intent.getStringExtra("title");
        pos = intent.getStringExtra("pos");
        reviewcnt = intent.getStringExtra("reviewcnt");
        imgone = intent.getStringExtra("imgone");
        imgtwo = intent.getStringExtra("imgtwo");
        imgthr = intent.getStringExtra("imgthr");

        imagebutton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType(MediaStore.Images.Media.CONTENT_TYPE);
                startActivityForResult(intent, 200);

            }
        });


        //리뷰에 글을 남기기
        reviewText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            // 글자 수가 변동되면 동작하는 메소드
            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
                String input = reviewText.getText().toString();
                textView.setText(input.length() + "/ 800 자");
                text = reviewText.getText().toString();
                System.out.println(text);


            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        //해시태그1번
        hash1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            // 글자 수가 변동되면 동작하는 메소드
            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
                tag1 = hash1.getText().toString();
                System.out.println(hash1);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        hash2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            // 글자 수가 변동되면 동작하는 메소드
            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
                tag2 = hash2.getText().toString();
                System.out.println(hash2);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        hash3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            // 글자 수가 변동되면 동작하는 메소드
            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
                tag3 = hash3.getText().toString();
                System.out.println(hash3);
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
                System.out.println(""+review_ratingBar.getRating());
                star = String.valueOf(review_ratingBar.getRating());
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
                review_ratingBar.setRating((float)(review_ratingBar.getRating() - 0.5));
            }
        });

        star = String.valueOf(review_ratingBar.getRating());

        // 검색창을 클릭하면 검색하는 Activity로 넘어감
        search.setInputType(0);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle extras = new Bundle();
                extras.putInt("where", 1);
                Intent intent = new Intent(getApplicationContext(), SearchActivity.class);
                intent.putExtras(extras);
                startActivity(intent);
            }
        });

        final GestureDetector gd = new GestureDetector(this, new GestureDetector.SimpleOnGestureListener() {
            @Override
            public boolean onDoubleTap(MotionEvent e) {
                switch (button) {
                    case "mood1" :
                        mood1.setBackground(getDrawable(R.drawable.buttonborder));
                        mood = null;
                        break;
                    case "mood2" :
                        mood2.setBackground(getDrawable(R.drawable.buttonborder));
                        mood = null;
                        break;
                    case "mood3" :
                        mood3.setBackground(getDrawable(R.drawable.buttonborder));
                        mood = null;
                        break;
                    case "mood4" :
                        mood4.setBackground(getDrawable(R.drawable.buttonborder));
                        mood = null;
                        break;
                    case "mood5" :
                        mood5.setBackground(getDrawable(R.drawable.buttonborder));
                        mood = null;
                        break;
                    case "mood6" :
                        mood6.setBackground(getDrawable(R.drawable.buttonborder));
                        mood = null;
                        break;
                    case "mood7" :
                        mood7.setBackground(getDrawable(R.drawable.buttonborder));
                        mood = null;
                        break;
                    case "mood8" :
                        mood8.setBackground(getDrawable(R.drawable.buttonborder));
                        mood = null;
                        break;
                    case "coffee1" :
                        coffee1.setBackground(getDrawable(R.drawable.buttonborder));
                        coffee = null;
                        break;
                    case "coffee2" :
                        coffee2.setBackground(getDrawable(R.drawable.buttonborder));
                        coffee = null;
                        break;
                    case "coffee3" :
                        coffee3.setBackground(getDrawable(R.drawable.buttonborder));
                        coffee = null;
                        break;
                    case "dessert1" :
                        dessert1.setBackground(getDrawable(R.drawable.buttonborder));
                        rdessert = null;
                        break;
                    case "dessert2" :
                        dessert2.setBackground(getDrawable(R.drawable.buttonborder));
                        rdessert = null;
                        break;
                    case "dessert3" :
                        dessert3.setBackground(getDrawable(R.drawable.buttonborder));
                        rdessert = null;
                        break;
                    case "rgood" :
                        rgood.setBackground(getDrawable(R.drawable.buttonborder));
                        rest = null;
                        break;
                    case "rbad" :
                        rbad.setBackground(getDrawable(R.drawable.buttonborder));
                        rest = null;
                        break;
                    case "rpeople" :
                        rpeople.setBackground(getDrawable(R.drawable.buttonborder));
                        rest2 = null;
                        break;
                    case "rnpeople" :
                        rnpeople.setBackground(getDrawable(R.drawable.buttonborder));
                        rest2 = null;
                        break;
                    case "in" :
                        in.setBackground(getDrawable(R.drawable.buttonborder));
                        rest3 = null;
                        break;
                    case "out" :
                        out.setBackground(getDrawable(R.drawable.buttonborder));
                        rest3 = null;
                        break;
                    case "price1" :
                        price1.setBackground(getDrawable(R.drawable.buttonborder));
                        rprice = null;
                        break;
                    case "price2" :
                        price2.setBackground(getDrawable(R.drawable.buttonborder));
                        rprice = null;
                        break;
                    case "waiting1" :
                        waiting1.setBackground(getDrawable(R.drawable.buttonborder));
                        waiting = null;
                        break;
                    case "waiting2" :
                        waiting2.setBackground(getDrawable(R.drawable.buttonborder));
                        waiting = null;
                        break;
                }
                return true;
            }
            @Override
            public boolean onDoubleTapEvent(MotionEvent e) {
                return super.onDoubleTapEvent(e);
            }
        });
        mood1.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if(mood == null && motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                    mood1.setBackground(getDrawable(R.drawable.buttonfillboarder));
                    mood = mood1.getText().toString();
                    System.out.println(mood);
                }
                button = "mood1";
                return gd.onTouchEvent(motionEvent);
            }
        });
        mood2.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if(mood == null && motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                    mood2.setBackground(getDrawable(R.drawable.buttonfillboarder));
                    mood = mood2.getText().toString();
                    System.out.println(mood);
                }
                button = "mood2";
                return gd.onTouchEvent(motionEvent);
            }
        });
        mood3.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if(mood == null && motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                    mood3.setBackground(getDrawable(R.drawable.buttonfillboarder));
                    mood = mood3.getText().toString();
                    System.out.println(mood);
                }
                button = "mood3";
                return gd.onTouchEvent(motionEvent);
            }
        });
        mood4.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if(mood == null && motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                    mood4.setBackground(getDrawable(R.drawable.buttonfillboarder));
                    mood = mood4.getText().toString();
                    System.out.println(mood);
                }
                button = "mood4";
                return gd.onTouchEvent(motionEvent);
            }
        });
        mood5.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if(mood == null && motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                    mood5.setBackground(getDrawable(R.drawable.buttonfillboarder));
                    mood = mood5.getText().toString();
                    System.out.println(mood);
                }
                button = "mood5";
                return gd.onTouchEvent(motionEvent);
            }
        });
        mood6.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if(mood == null && motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                    mood6.setBackground(getDrawable(R.drawable.buttonfillboarder));
                    mood = mood6.getText().toString();
                    System.out.println(mood);
                }
                button = "mood6";
                return gd.onTouchEvent(motionEvent);
            }
        });
        mood7.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if(mood == null && motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                    mood7.setBackground(getDrawable(R.drawable.buttonfillboarder));
                    mood = mood7.getText().toString();
                    System.out.println(mood);
                }
                button = "mood7";
                return gd.onTouchEvent(motionEvent);
            }
        });
        mood8.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if(mood == null && motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                    mood8.setBackground(getDrawable(R.drawable.buttonfillboarder));
                    mood = mood8.getText().toString();
                    System.out.println(mood);
                }
                button = "mood8";
                return gd.onTouchEvent(motionEvent);
            }
        });

        coffee1.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if(coffee == null && motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                    coffee1.setBackground(getDrawable(R.drawable.buttonfillboarder));
                    coffee = coffee1.getText().toString();
                    System.out.println(coffee1);
                }
                button = "coffee1";
                return gd.onTouchEvent(motionEvent);
            }
        });
        coffee2.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if(coffee == null && motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                    coffee2.setBackground(getDrawable(R.drawable.buttonfillboarder));
                    coffee = coffee2.getText().toString();
                    System.out.println(coffee2);
                }
                button = "coffee2";
                return gd.onTouchEvent(motionEvent);
            }
        });
        coffee3.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if(coffee == null && motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                    coffee3.setBackground(getDrawable(R.drawable.buttonfillboarder));
                    coffee = coffee3.getText().toString();
                    System.out.println(coffee3);
                }
                button = "coffee3";
                return gd.onTouchEvent(motionEvent);
            }
        });

        dessert1.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if(rdessert == null && motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                    dessert1.setBackground(getDrawable(R.drawable.buttonfillboarder));
                    rdessert = dessert1.getText().toString();
                    System.out.println(rdessert);
                }
                button = "dessert1";
                return gd.onTouchEvent(motionEvent);
            }
        });
        dessert2.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if(rdessert == null && motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                    dessert2.setBackground(getDrawable(R.drawable.buttonfillboarder));
                    rdessert = dessert2.getText().toString();
                    System.out.println(rdessert);
                }
                button = "dessert2";
                return gd.onTouchEvent(motionEvent);
            }
        });
        dessert3.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if(rdessert == null && motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                    dessert3.setBackground(getDrawable(R.drawable.buttonfillboarder));
                    rdessert = dessert3.getText().toString();
                    System.out.println(rdessert);
                }
                button = "dessert3";
                return gd.onTouchEvent(motionEvent);
            }
        });

        rgood.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if(rest == null && motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                    rgood.setBackground(getDrawable(R.drawable.buttonfillboarder));
                    rest = rgood.getText().toString();
                    System.out.println(rest);
                }
                button = "rgood";
                return gd.onTouchEvent(motionEvent);
            }
        });
        rbad.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if(rest == null && motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                    rbad.setBackground(getDrawable(R.drawable.buttonfillboarder));
                    rest = rbad.getText().toString();
                    System.out.println(rest);
                }
                button = "rbad";
                return gd.onTouchEvent(motionEvent);
            }
        });
        rpeople.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if(rest2 == null && motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                    rpeople.setBackground(getDrawable(R.drawable.buttonfillboarder));
                    rest2 = rpeople.getText().toString();
                    System.out.println(rest2);
                }
                button = "rpeople";
                return gd.onTouchEvent(motionEvent);
            }
        });
        rnpeople.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if(rest2 == null && motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                    rnpeople.setBackground(getDrawable(R.drawable.buttonfillboarder));
                    rest2 = rnpeople.getText().toString();
                    System.out.println(rest2);
                }
                button = "rnpeople";
                return gd.onTouchEvent(motionEvent);
            }
        });
        in.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if(rest3 == null && motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                    in.setBackground(getDrawable(R.drawable.buttonfillboarder));
                    rest3 = in.getText().toString();
                    System.out.println(rest3);
                }
                button = "in";
                return gd.onTouchEvent(motionEvent);
            }
        });
        out.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if(rest3 == null && motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                    out.setBackground(getDrawable(R.drawable.buttonfillboarder));
                    rest3 = out.getText().toString();
                    System.out.println(rest3);
                }
                button = "out";
                return gd.onTouchEvent(motionEvent);
            }
        });
        price1.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if(rprice == null && motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                    price1.setBackground(getDrawable(R.drawable.buttonfillboarder));
                    rprice = price1.getText().toString();
                    System.out.println(rprice);
                }
                button = "price1";
                return gd.onTouchEvent(motionEvent);
            }
        });
        price2.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if(rprice == null && motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                    price2.setBackground(getDrawable(R.drawable.buttonfillboarder));
                    rprice = price2.getText().toString();
                    System.out.println(rprice);
                }
                button = "price2";
                return gd.onTouchEvent(motionEvent);
            }
        });

        waiting1.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if(waiting == null && motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                    waiting1.setBackground(getDrawable(R.drawable.buttonfillboarder));
                    waiting = waiting1.getText().toString();
                    System.out.println(waiting);
                }
                button = "waiting1";
                return gd.onTouchEvent(motionEvent);
            }
        });
        waiting2.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if(waiting == null && motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                    waiting2.setBackground(getDrawable(R.drawable.buttonfillboarder));
                    waiting = waiting2.getText().toString();
                    System.out.println(waiting);
                }
                button = "waiting2";
                return gd.onTouchEvent(motionEvent);
            }
        });

        // TODO REVIEW 제출하기 버튼을 누르면 동작하는 메소드
        reviewbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                uploadFile(); //사진이 firebase에 들어감
                //제출하기 버튼을 누르면 해당 버튼에 해당하는 단어가 DB에 들어감
                databaseReference = FirebaseDatabase.getInstance().getReference(title+"/");
                Review review = new Review(text,img,tag1,tag2,tag3,mood, coffee, rdessert, rest,rest2,rest3, rprice, star, waiting);
                Map<String, Object> reviewValues = review.toMap();
                reviewValues.putAll(reviewValues);
                //title밑에 pos밑에 review밑에 reviewcnt를 갱신함
                databaseReference.child(pos).child("review").child(reviewcnt).updateChildren(reviewValues).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        System.out.println("SuccessFul!!!!!!!!!!!!!!!!!!!11");
                        AlertDialog.Builder builder = new AlertDialog.Builder(CafeReview.this);
                        builder.setMessage("리뷰가 작성되었습니다\uD83D\uDE0D");
                        AlertDialog alertDialog = builder.create();
                        alertDialog.show();

                        Map<String, Object> updateMap = new HashMap<>();
                        updateMap.put("reviewcnt", String.valueOf(Integer.parseInt(reviewcnt)+1));
                        databaseReference.child(pos).updateChildren(updateMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                System.out.println("SuccessFul!!!!!!!!!!!!!!!!!!!11");
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                System.out.println("Failure!!!!!!!!!!!!!!!!!!!!11");
                            }
                        });

                        System.out.println(ReviewList.size());
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        Bundle extras = new Bundle(); // 번들은 인텐트 속에 있는 데이터 꾸러미
                        extras.putString("name", name);
                        extras.putString("address", address);
                        extras.putString("dessert", dessert);
                        extras.putString("time", time);
                        extras.putString("tel", tel);
                        extras.putString("restroom", restroom);
                        extras.putString("views", views);
                        extras.putString("imgone", imgone);
                        extras.putString("imgtwo", imgtwo);
                        extras.putString("imgthr", imgthr);
                        extras.putString("title", title);
                        extras.putString("price", price);
                        extras.putString("star", star);
                        extras.putString("reviewcnt", reviewcnt);
                        extras.putString("pos", pos);
                        extras.putInt("no",no);
                        extras.putString("text", text);
                        extras.putString("mood", mood);
                        extras.putString("tag1",tag1);
                        extras.putString("tag2",tag2);
                        extras.putString("tag3",tag3);
                        extras.putString("coffee", coffee);
                        extras.putString("rdessert", rdessert);
                        extras.putString("rest", rest);
                        extras.putString("rest2", rest2);
                        extras.putString("rest3", rest3);
                        extras.putString("price", price);
                        extras.putString("star", star);
                        extras.putString("waiting", waiting);
                        extras.putString("img",img);

                        intent.putExtras(extras);
                        startActivity(intent);

                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        System.out.println("Failure!!!!!!!!!!!!!!!!!!!!11");
                    }
                });


            }
        });
    }
    //이미지를 갤러리에서 선택하면 이미지뷰에 보이게함.
    private void setImage() {
        ImageView imageView = findViewById(R.id.reviewimage);
        BitmapFactory.Options options = new BitmapFactory.Options();
        Bitmap originalBm = BitmapFactory.decodeFile(tempFile.getAbsolutePath(), options);
        imageView.setImageBitmap(originalBm);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 200 && resultCode == RESULT_OK && data != null && data.getData() != null) {
            filePath = data.getData();
            reviewimage.setImageURI(filePath); //이미지 url을 reviewimage라는 이미지뷰에 보여지게함.
            img = filePath.toString(); //데이터베이스에 이미지 uri를 저장하기 위해 text로 바꿔서 img라는 변수에 이미지 uri를 집어넣음
            img = getRealPathFromgURI(filePath);

        }

    }

    public String getRealPathFromgURI(Uri uri) {
        Cursor cursor = getContentResolver().query(uri, null, null, null, null );
        cursor.moveToNext();
        String path = cursor.getString( cursor.getColumnIndex( "_data" ) );
        cursor.close();
        return path;

    }

    private void uploadFile() {
        if (filePath != null) {
            //storage
            FirebaseStorage storage = FirebaseStorage.getInstance();
            SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMHH_mmss");
            Date now = new Date();
            String filename = formatter.format(now) + ".png";
            //storage 주소와 폴더 파일명을 지정해 준다.
            final StorageReference storageRef = storage.getReferenceFromUrl("gs://gamsung-e3e5a.appspot.com").child("images/" + filename);
            storageRef.putFile(filePath)
                    //성공시
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot> () {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            Task<Uri> downloadUri = taskSnapshot.getMetadata().getReference().getDownloadUrl();
                            img = downloadUri.toString();
                            Toast.makeText(getApplicationContext(), "업로드 완료!", Toast.LENGTH_SHORT).show();

                        }
                    })
                    //실패시
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(getApplicationContext(), "업로드 실패!", Toast.LENGTH_SHORT).show();
                        }
                    })
                    //진행중
                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                            @SuppressWarnings("VisibleForTests")
                            double progress = (100 * taskSnapshot.getBytesTransferred()) /  taskSnapshot.getTotalByteCount();
                        }
                    });




        }
    }


}