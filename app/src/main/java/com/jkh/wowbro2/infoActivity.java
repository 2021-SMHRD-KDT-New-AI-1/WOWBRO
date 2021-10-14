package com.jkh.wowbro2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class infoActivity extends AppCompatActivity {

    WebView webview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        webview = findViewById(R.id.webview);
        webview.setWebViewClient(new WebViewClient());

        webview.loadUrl("https://m.place.naver.com/place/1894268974/home?entry=pll");

        WebSettings webSettings = webview.getSettings();
        webSettings.setJavaScriptEnabled(true);




    }
}