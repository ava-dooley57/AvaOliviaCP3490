package com.firstapp.app;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class ContactUsActivity extends AppCompatActivity  {

    private String selectedProgram;

    private ImageButton buttonCall;

    private WebView webViewCNA;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_us);

        selectedProgram = getIntent().getStringExtra("selectedProgram");

        webViewCNA = findViewById(R.id.webViewCNA);
        configureWebViewSettings();
        loadCNAWebsite();

        buttonCall = findViewById(R.id.buttonCall);
        buttonCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:1234567890"));
                startActivity(intent);
            }
        });
    }
    public void openGoogleMaps(View view) {
        double latitude = 47.58724661437915;
        double longitude = -52.73460470296534;
        Uri location = Uri.parse("geo:" + latitude + "," + longitude + "?z=15");
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, location);
        mapIntent.setPackage("com.google.android.apps.maps");
        startActivity(mapIntent);
    }

    private void configureWebViewSettings() {
        WebSettings webSettings = webViewCNA.getSettings();
        webSettings.setJavaScriptEnabled(true);
    }

    private void loadCNAWebsite() {
        webViewCNA.setWebViewClient(new WebViewClient());
        webViewCNA.loadUrl("https://www.cna.nl.ca/explore-our-campuses/Ridge-Road");
    }

    public void openHome(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("selectedProgram", selectedProgram);
        startActivity(intent);
    }

    public void openEmail(View view) {
        Intent intent = new Intent(this, EmailActivity.class);
        intent.putExtra("selectedProgram", selectedProgram);
        startActivity(intent);
    }
}