package com.example.gamsung;

import android.app.AlertDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// 커뮤니티 글쓰는 액티비티
public class CommunityActivity extends AppCompatActivity {
    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference databaseReference = firebaseDatabase.getReference();

    public static List<Community> communitycardList;
    private Button selectButton, writingButton;
    private EditText subjectText, materialText, writingText;
    private TextView count;

    private String useremail, userDisplayname, profileurl, photo, subject, material, text, communitycnt;  // 사진, 작성자(카드에 보여지기위해), 작성자이메일(데이터베이스에 저장되기위해), 제목, 재료, 글내용)
//    private Uri profileurl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.community_writing);

//        // 로그인 정보를 가지고 옴
//        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
//        if(account != null) {
////            Glide.with(this).load(account.getPhotoUrl()).circleCrop().into(user_profile);
//            useremail = account.getEmail();
//            profileurl = account.getPhotoUrl();
//            Log.d("로그인 정보를 잘 불러왔음", "이메일 : " + account.getEmail() + "photourl : " + account.getPhotoUrl()) ;
//        }

        communitycardList = new ArrayList<>();
        selectButton = findViewById(R.id.selectButton);
        writingButton = findViewById(R.id.writingButton);
        subjectText = findViewById(R.id.subjectText);
        materialText = findViewById(R.id.material);
        writingText = findViewById(R.id.writingText);
        count = findViewById(R.id.count);

        // CommunityMain에서 보내는 intent 받음
        // TODO 로그인한 사용자를 가져와야함 => getIntent로 받아와야함
        Intent intent = getIntent();
        userDisplayname = intent.getStringExtra("userDisplayname");
        useremail = intent.getStringExtra("useremail");
        profileurl = intent.getStringExtra("profileurl");
        Log.d("로그인 정보를 잘 받아옴", "이름 : " + userDisplayname + "이메일 : " + useremail + "profileurl : " + profileurl) ;

        // 제목 쓰는 text박스
        subjectText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String input = subjectText.getText().toString();
                System.out.println(input);
                subject = subjectText.getText().toString();
//                text = material.getText().toString();
//                System.out.println(text);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        // 재료 쓰는 text박스
        materialText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String input = materialText.getText().toString();
                System.out.println(input);
                material = materialText.getText().toString();
//                text = material.getText().toString();
//                System.out.println(text);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        // 커뮤니티 글쓰는 text박스
        writingText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String input = writingText.getText().toString();
                count.setText(input.length() +  "/ 800 자");
                System.out.println(input);
                text = writingText.getText().toString();
//                text = material.getText().toString();
//                System.out.println(text);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        // 사진 선택하는 버튼 클릭 시
        selectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
//        recyclerView = findViewById(R.id.recycler_view);
//        cafeAdapter = new CafeAdapter(this, cafeList);
//        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
//        recyclerView.setAdapter(cafeAdapter);
//        prepareCafeData();

        // TODO 작성하기 버튼을 누르면 동작하는 메소드
        writingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //작성하기 버튼을 누르면 해당 버튼에 해당하는 단어가 DB에 들어감

                databaseReference = FirebaseDatabase.getInstance().getReference("커뮤니티/");
                // 사진, 작성자(카드에 보여지기위해), 작성자이메일(데이터베이스에 저장되기위해), 제목, 재료, 글내용)
                Community community = new Community(userDisplayname, useremail, subject, material, text);
                Map<String, Object> communityValues = community.toMap();
                communityValues.putAll(communityValues);
                databaseReference.updateChildren(communityValues).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        System.out.println("SuccessFul!!!!!!!!!!!!!!!!!!!11");
                        AlertDialog.Builder builder = new AlertDialog.Builder(CommunityActivity.this);
                        builder.setMessage("글이 작성되었습니다\uD83D\uDE0D");
                        AlertDialog alertDialog = builder.create();
                        alertDialog.show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        System.out.println("Failure!!!!!!!!!!!!!!!!!!!!11");
                    }
                });
                //title밑에 pos밑에 review밑에 reviewcnt를 갱신함
//                databaseReference.child(pos).child("review").child(reviewcnt).updateChildren(reviewValues).addOnCompleteListener(new OnCompleteListener<Void>() {
//                    @Override
//                    public void onComplete(@NonNull Task<Void> task) {
//                        System.out.println("SuccessFul!!!!!!!!!!!!!!!!!!!11");
//                        AlertDialog.Builder builder = new AlertDialog.Builder(CommunityActivity.this);
//                        builder.setMessage("글이 작성되었습니다\uD83D\uDE0D");
//                        AlertDialog alertDialog = builder.create();
//                        alertDialog.show();
//
//                        System.out.println(ReviewList.size());
//                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
//                        Bundle extras = new Bundle(); // 번들은 인텐트 속에 있는 데이터 꾸러미
//                        extras.putString("name", name);
//                        extras.putString("address", address);
//                        extras.putString("dessert", dessert);
//                        extras.putString("time", time);
//                        extras.putString("tel", tel);
//                        extras.putString("restroom", restroom);
//                        extras.putString("views", views);
//                        extras.putString("imgone", imgone);
//                        extras.putString("imgtwo", imgtwo);
//                        extras.putString("imgthr", imgthr);
//                        extras.putString("title", title);
//                        extras.putString("price", price);
//                        extras.putString("star", star);
//                        extras.putString("reviewcnt", reviewcnt);
//                        extras.putString("pos", pos);
//                        extras.putInt("no",no);
//                        extras.putString("text", text);
//                        extras.putString("mood", mood);
//                        extras.putString("tag1",tag1);
//                        extras.putString("tag2",tag2);
//                        extras.putString("tag3",tag3);
//                        extras.putString("coffee", coffee);
//                        extras.putString("rdessert", rdessert);
//                        extras.putString("rest", rest);
//                        extras.putString("rest2", rest2);
//                        extras.putString("rest3", rest3);
//                        extras.putString("price", price);
//                        extras.putString("star", star);
//                        extras.putString("waiting", waiting);
//
//                        intent.putExtras(extras);
//                        startActivity(intent);
//
//                    }
//                }).addOnFailureListener(new OnFailureListener() {
//                    @Override
//                    public void onFailure(@NonNull Exception e) {
//                        System.out.println("Failure!!!!!!!!!!!!!!!!!!!!11");
//                    }
//                });
//
//                Map<String, Object> updateMap = new HashMap<>();
//                updateMap.put("communitycnt", String.valueOf(Integer.parseInt(communitycnt)+1));
//                databaseReference.child(pos).updateChildren(updateMap).addOnCompleteListener(new OnCompleteListener<Void>() {
//                    @Override
//                    public void onComplete(@NonNull Task<Void> task) {
//                        System.out.println("SuccessFul!!!!!!!!!!!!!!!!!!!11");
//                    }
//                }).addOnFailureListener(new OnFailureListener() {
//                    @Override
//                    public void onFailure(@NonNull Exception e) {
//                        System.out.println("Failure!!!!!!!!!!!!!!!!!!!!11");
//                    }
//                });
            }
        });
    }
}
