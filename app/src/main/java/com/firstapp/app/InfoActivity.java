package com.firstapp.app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;

public class InfoActivity extends AppCompatActivity {

    private String selectedProgram;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        selectedProgram = getIntent().getStringExtra("selectedProgram");

    }

    public void openHome(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("selectedProgram", selectedProgram);
        startActivity(intent);
    }
}
