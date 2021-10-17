package com.jkh.wowbro2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class info4Activity extends AppCompatActivity {

    WebView webview;
    String url = "https://m.place.naver.com/restaurant/1357740552/home";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        webview = findViewById(R.id.webview);
        webview.setWebViewClient(new WebViewClient());
        webview.loadUrl(url);
        WebSettings webSettings = webview.getSettings();
        webSettings.setJavaScriptEnabled(true);





    }
}
