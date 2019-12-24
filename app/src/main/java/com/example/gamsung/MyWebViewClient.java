package com.example.gamsung;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.util.Log;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MyWebViewClient extends WebViewClient {
    private Activity activity = null;

    public MyWebViewClient(Activity activity) {
        this.activity = activity;
    }

    @Override
    public void onPageStarted(WebView view, String url, Bitmap favicon) { //이 URL을 가지고 보이려고 할때 웹뷰에 보이기 전에
        super.onPageStarted(view, url, favicon);
    }

    @Override
    public void onPageFinished(WebView view, String url) {//그 페이지를 웹뷰에 다 보였을때 동작
        super.onPageFinished(view, url);
    }


    public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {

        String url;
        url = request.getUrl().toString();

        view.loadUrl(url);
        return super.shouldOverrideUrlLoading(view, request);
    }
    //    public boolean shouldOverrideUrlLoading(WebView view, String url) {
//        return false;
//    }

//        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
//        view.getContext().startActivity(intent); //묵시적인 인텐트 커널한테 이것을 보여달라 의도를 전달
//        return  true; //새로운 링크처리를 액티비티한테 넘긴다



    }

