package com.sample;

import android.os.Bundle;
import android.webkit.WebView;

import androidx.appcompat.app.AppCompatActivity;

public class WebViewActivity extends AppCompatActivity {
WebView webViewActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        webViewActivity = findViewById(R.id.web_view);
        webViewActivity.loadUrl("https://tpc.googlesyndication.com/pagead/imgad?id=CICAgKDbnKzm1AEQARgBMggptufdq5Gdzg");
        webViewActivity.setInitialScale(1);
        webViewActivity.getSettings().setLoadWithOverviewMode(true);
        webViewActivity.getSettings().setUseWideViewPort(true);

    }
}
