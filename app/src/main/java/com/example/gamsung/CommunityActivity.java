package com.example.gamsung;

import android.app.AlertDialog;
import android.content.Intent;
import android.icu.text.SimpleDateFormat;
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
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// 커뮤니티 글쓰는 액티비티
public class CommunityActivity extends AppCompatActivity {
    private Uri filePath;
    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference databaseReference = firebaseDatabase.getReference();
    private FirebaseStorage storage = FirebaseStorage.getInstance();
    private StorageReference storageReference;

    public static List<Community> communsitycardList = new ArrayList<>();
    private Button selectButton, writingButton;
    private EditText subjectText, materialText, writingText;
    private TextView count;
    private ImageView photoview;
    private String useremail, userDisplayname, profileurl, photo, subject, material, text, communitycnt,likecnt,pos;  // 사진, 작성자(카드에 보여지기위해), 작성자이메일(데이터베이스에 저장되기위해), 제목, 재료, 글내용)

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

        photoview = findViewById(R.id.photo);
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
        communitycnt = intent.getStringExtra("communitycnt");
        Log.d("로그인 정보를 잘 받아옴", "이름 : " + userDisplayname + "이메일 : " + useremail + "profileurl : " + profileurl ) ;



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
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType(MediaStore.Images.Media.CONTENT_TYPE);
                startActivityForResult(intent, 200);
            }
        });

//        recyclerView = findViewById(R.id.recycler_view);
//        cafeAdapter = new CafeAdapter(this, cafeList);
//        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
//        recyclerView.setAdapter(cafeAdapter);
//        prepareCafeData();
        if(likecnt == null) {
            likecnt = String.valueOf(0); //likecnt 0 으로 초기화
        }
        if(pos == null) {
            pos = String.valueOf(communitycnt);
            System.out.println("지금들어가는 커뮤니티 게시글의 번호는??" + pos);
        }

        writingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                uploadFile();
                //작성하기 버튼을 누르면 해당 버튼에 해당하는 단어가 DB에 들어감
                databaseReference = FirebaseDatabase.getInstance().getReference("커뮤니티게시판/" );
                // 사진, 작성자(카드에 보여지기위해), 작성자이메일(데이터베이스에 저장되기위해), 제목, 재료, 글내용)
                Community community = new Community(userDisplayname, photo, useremail, subject, material, text,likecnt,pos);
                Map<String, Object> communityValues = community.toMap();
                communityValues.putAll(communityValues);
                //커뮤니티 게시판경로를 위에서 결정해줬으니까 그 밑에 community밑에 0,1,2증가하여 게시글 추가(communitycnt는 숫자일뿐 그 목록아님)
                databaseReference.child("community/").child(communitycnt).updateChildren(communityValues).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        System.out.println("SuccessFul!!!!!!!!!!!!!!!!!!!11"); //여기까지는 됨
                        AlertDialog.Builder builder = new AlertDialog.Builder(CommunityActivity.this);
                        builder.setMessage("글이 작성되었습니다\uD83D\uDE0D");
                        AlertDialog alertDialog = builder.create();
                        alertDialog.show();

                        Map<String, Object> updateMap = new HashMap<>();
                        updateMap.put("communitycnt", String.valueOf(Integer.parseInt(communitycnt) + 1));
                        //communitycnt를 증가시키고 update시킴
                        databaseReference.updateChildren(updateMap).addOnCompleteListener(new OnCompleteListener<Void>() {
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

                        //System.out.println(communitycardList.size());
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        Bundle extras = new Bundle();
                        extras.putString("userDisplayname", userDisplayname);
                        extras.putString("useremail", useremail);
                        extras.putString("subject", subject);
                        extras.putString("material", material);
                        extras.putString("photo",photo);
                        extras.putString("text", text);
                        extras.putString("communitycnt", communitycnt);
                        extras.putString("pos",pos);
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
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 200 && resultCode == RESULT_OK && data != null && data.getData() != null) {
            filePath = data.getData();
            photoview.setImageURI(filePath); //이미지 url을 photoview에 보여지게함.
        }

    }
    private void uploadFile() {
        if (filePath != null) {
            //storage
            SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMHH_mmss");
            Date now = new Date();
            String filename = formatter.format(now) + ".png";

            //storage 주소와 폴더 파일명을 지정해 준다.
            storageReference = storage.getReferenceFromUrl("gs://gamsung-e3e5a.appspot.com").child("communityimg/" + filename);
            storageReference.putFile(filePath)
                    //성공시
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            // 업로드 완료 후 다운로드 경로 가져오기
                            storageReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri uri) {
                                    //System.out.println("잘 불러오는지 확인"+title + ", " + pos + ", " + reviewcnt);

                                    photo = uri.toString();
                                    databaseReference = FirebaseDatabase.getInstance().getReference("커뮤니티게시판/");
                                    Map<String, Object> updateMap = new HashMap<>();
                                    updateMap.put("photo", photo);
                                    databaseReference.child("community/").child(communitycnt).updateChildren(updateMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            System.out.println("다운로드 URI가 들어갔다!!! SuccessFul!!!!!!!!!!!!!!!!!!!11");
                                        }
                                    }).addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {
                                            System.out.println("다운로드 URI Failure!!!!!!!!!!!!!!!!!!!!11");
                                        }
                                    });
                                    System.out.println("여기는 onSuccess 부분입니다");
                                    System.out.println("다운로드 URL : " + uri.toString());
                                    System.out.println("업로드 완료~!!");
                                }
                            });

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
