package com.example.gamsung;

import android.os.Bundle;
import android.view.KeyEvent;
import android.webkit.WebSettings;
import android.webkit.WebView;

import androidx.appcompat.app.AppCompatActivity;

public class CafeWebView extends AppCompatActivity {
    private WebView webView= null;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.web_view);

        this.webView = (WebView)findViewById(R.id.webview);
        WebSettings webSettings = webView.getSettings(); //웹뷰가 여러가지 동작을 설정
        webSettings.setJavaScriptEnabled(true);//자바스크립트 프로그램이 실행되게 할것이다.

        MyWebViewClient webViewClient = new MyWebViewClient(this);
        webView.setWebViewClient(webViewClient);
        webView.loadUrl("https://www.naver.com/");
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if((keyCode == KeyEvent.KEYCODE_BACK) && this.webView.canGoBack()) {
            this.webView.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
