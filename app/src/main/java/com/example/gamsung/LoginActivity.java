package com.example.gamsung;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

public class LoginActivity extends AppCompatActivity {
    private GoogleSignInClient mGoogleSignInClient;
    private GoogleSignInAccount account;
    private final static int RC_SIGN_IN = 123;
    public FirebaseAuth mAuth;
//    private FirebaseAuth.AuthStateListener mAuthListener;
    FirebaseUser user;

    private SignInButton btn_google;    // 구글 로그인 버튼

    @Override
    protected void onStart() {
        super.onStart();

        createRequest();

        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();
        if(user != null) {  // user가 있을 때
            AuthCredential credential = GoogleAuthProvider.getCredential(user.getProviderId(),null);
            user.reauthenticate(credential)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            Log.d("재인증", "User re-authenticated.");
                            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                            startActivity(intent);
                            finish();
                        }
                    });
        }
        else {
            Log.d("재인증", "깜빡깜빡");
            return;
        }

//        mAuth.addAuthStateListener(mAuthListener);


//        mAuthListener = new FirebaseAuth.AuthStateListener() {
//            @Override
//            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
//                if(firebaseAuth.getCurrentUser() != null && user != null) {
//                    user.getIdToken(true)
//                            .addOnCompleteListener(new OnCompleteListener<GetTokenResult>() {
//                                @Override
//                                public void onComplete(@NonNull Task<GetTokenResult> task) {
//                                    if(task.isSuccessful()) {
//                                        String idToken = task.getResult().getToken();
//                                        Log.d("ID토큰", idToken);
//                                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
//                                        startActivity(intent);
//                                    }
//                                    else {
//                                        Log.d("로그인액티비티","시작");
//                                        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
//                                        startActivity(intent);
//                                    }
//                                }
//                            });
//                }
//                else {
//                    Log.d("아무것도", "거치지않았다는건데...");
//                }
//            }
//        };

    }

//    @Override
//    protected void onStop() {
//        super.onStop();
//        mAuth.removeAuthStateListener(mAuthListener);
//    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_main);

        Intent intent = new Intent(this, LoadingActivity.class);
        startActivity(intent);


        btn_google = findViewById(R.id.btn_google);
        setGoogleButtonText(btn_google, getString(R.string.sign_in));

        createRequest();
        mAuth = FirebaseAuth.getInstance();

        btn_google.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signIn();
//                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
//                startActivity(intent);
            }
        });

//        mAuthListener = new FirebaseAuth.AuthStateListener() {
//            @Override
//            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
//                user = firebaseAuth.getCurrentUser();
//                if(user != null) {  // user가 있을 때
//                    AuthCredential credential = GoogleAuthProvider.getCredential(user.getProviderId(),null);
//                    user.reauthenticate(credential)
//                            .addOnCompleteListener(new OnCompleteListener<Void>() {
//                                @Override
//                                public void onComplete(@NonNull Task<Void> task) {
//                                    Log.d("재인증", "User re-authenticated.");
//                                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
//                                    startActivity(intent);
//                                    finish();
//                                }
//                            });
//                }
//                else {
//                    Log.d("재인증", "깜빡깜빡");
//                    Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
//                    startActivity(intent);
//                    finish();
//                }
//            }
//        };
    }

    // TODO 구글 로그인버튼 텍스트 변경하기
    private void setGoogleButtonText(SignInButton btn_google, String buttonText) {
        for(int i = 0; i < btn_google.getChildCount(); i++) {
            View v = btn_google.getChildAt(i);
            if(v instanceof TextView) {
                ((TextView)v).setText((CharSequence)buttonText);
                ((TextView)v).setGravity(17);
                return;
            }
        }
    }

    private void createRequest() {
        // Configure Google Sign In
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        // Build a GoogleSignInClient with the options specified by gso.
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
    }

    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 1) {
            if(resultCode == Activity.RESULT_OK) {
                Log.e("LOG", "결과 받기 성공");
                Intent intent = getIntent();
            }
        }
        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                // Google Sign In was successful, authenticate with Firebase
                account = task.getResult(ApiException.class);
                Log.d("TAG", "firebaseAuthWithGoogle:" + account.getId());
                firebaseAuthWithGoogle(account.getIdToken());
            } catch (ApiException e) {
                // Google Sign In failed, update UI appropriately
                Log.w("TAG", "Google sign in failed", e);
                // ...
                Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void firebaseAuthWithGoogle(final String idToken) {
        AuthCredential credential = GoogleAuthProvider.getCredential(idToken, null);
        /* getCredential : idToken과 accessToken 정보를 받음 -> GoogleAuthProvider 객체로 바꿔줌? */
        /* signInWithCredential() : 부여된 서드파티 인증 정보를 이용해서 파이어베이스쪽으로 SignIn하는 것
           => 성공할 시 파이어베이스 앱으로 가입시킴 */
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {  // 로그인이 성공했으면
                            FirebaseUser user = mAuth.getCurrentUser();
                            Log.d("로그인", mAuth.getCurrentUser() + user.getDisplayName() + "로그인 성공!!! idToken은 : " + idToken);
                            Toast.makeText(LoginActivity.this, account.getDisplayName()+ " 님 반갑습니다", Toast.LENGTH_SHORT).show();

                            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                            startActivity(intent);
                            finish();
                        } else {    // 로그인이 실패했을 경우
                            Toast.makeText(LoginActivity.this, "로그인 실패", Toast.LENGTH_SHORT).show();
                            Log.w("로그인", "로그인 실패!!!!!!", task.getException());
                        }
                    }
                });
    }

}
