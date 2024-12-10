package com.firstapp.app;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class EmailActivity extends AppCompatActivity {

    ImageButton button;
    EditText subject, body;

    String sendTo = "ava.dooley57@ed.cna.nl.ca";

    private String selectedProgram;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email);

        selectedProgram = getIntent().getStringExtra("selectedProgram");
        Log.d("EmailActivity", "Received Email Program: " + selectedProgram);

        subject = findViewById(R.id.editSubject);
        body = findViewById(R.id.editBody);
        button = findViewById(R.id.button);

        button.setOnClickListener(view -> {
            String emailsubject = subject.getText().toString();
            String emailbody = body.getText().toString();

            Intent intent = new Intent(Intent.ACTION_SEND);

            intent.putExtra(Intent.EXTRA_EMAIL, new String[]{sendTo});
            intent.putExtra(Intent.EXTRA_SUBJECT, emailsubject);
            intent.putExtra(Intent.EXTRA_TEXT, emailbody);

            intent.setType("message/rfc822");

            startActivity(Intent.createChooser(intent, "Choose an Email account:"));
        });
    }
    public void openHome(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("selectedProgram", selectedProgram);
        Log.d("ContactUsActivity", "Sending Email Program: " + selectedProgram);
        startActivity(intent);
    }
}
