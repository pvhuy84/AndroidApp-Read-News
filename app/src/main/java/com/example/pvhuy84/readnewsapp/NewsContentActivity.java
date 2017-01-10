package com.example.pvhuy84.readnewsapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class NewsContentActivity extends AppCompatActivity {
    private WebView wvNewsContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_content);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        String urlNews = "";
        if (intent.getExtras() != null) {
            News news = intent.getExtras().getParcelable("news");
            getSupportActionBar().setTitle(news.getTitle());
            urlNews = news.getUrl();
        }

        wvNewsContent = (WebView) findViewById(R.id.wv_news_content);
        wvNewsContent.getSettings().setJavaScriptEnabled(true);
        wvNewsContent.setWebViewClient(new WebViewClient());
        wvNewsContent.loadUrl(urlNews);
    }
}

