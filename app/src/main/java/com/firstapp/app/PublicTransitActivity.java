package com.firstapp.app;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class PublicTransitActivity extends AppCompatActivity {
    private WebView webView;

    private String selectedProgram;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_public_transit);

        selectedProgram = getIntent().getStringExtra("selectedProgram");
        Log.d("PublicTransitActivity", "Received PT Program: " + selectedProgram);

        webView = findViewById(R.id.webView);
        configureWebViewSettings();
        loadMetrobusWebsite();
    }

    private void configureWebViewSettings() {
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
    }

    private void loadMetrobusWebsite() {
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl("https://www.metrobus.com");
    }
    public void openHome(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("selectedProgram", selectedProgram);
        startActivity(intent);
    }
}