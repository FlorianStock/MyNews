package com.mynews.flooo.mynews.Controllers;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;

import com.mynews.flooo.mynews.R;

public class WebViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {

        //Show the article with WebView in a new activity, with parameter URL for show.

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);

        String url = getIntent().getStringExtra("URL");
        WebView myWebView = findViewById(R.id.webview);
        myWebView.loadUrl(url);
    }
}
