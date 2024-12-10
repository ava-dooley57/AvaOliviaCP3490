package com.firstapp.app;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class StoreEventActivity extends AppCompatActivity {
    private EditText eventTitleEditText;
    private EditText eventDescriptionEditText;
    private Button selectDateButton;
    private Button saveEventButton;
    private String selectedDate;

    private String selectedProgram;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_event);

        if (getIntent().hasExtra("selectedProgram")) {
            selectedProgram = getIntent().getStringExtra("selectedProgram");
            Log.d("CalendarActivity", "Received program: " + selectedProgram);
        }

        eventTitleEditText = findViewById(R.id.eventTitle);
        eventDescriptionEditText = findViewById(R.id.eventDescription);
        selectDateButton = findViewById(R.id.selectDateButton);
        saveEventButton = findViewById(R.id.saveEventButton);

        selectDateButton.setOnClickListener(v -> showDatePickerDialog());

        saveEventButton.setOnClickListener(v -> saveEvent());
    }

    private void showDatePickerDialog() {
        Calendar calendar = Calendar.getInstance();
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                this,
                (thisview, year, month, dayOfMonth) -> {
                    selectedDate = year + "-" + (month + 1) + "-" + dayOfMonth;
                    Toast.makeText(StoreEventActivity.this, "Selected date: " + selectedDate, Toast.LENGTH_SHORT).show();
                },
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
        );
        datePickerDialog.show();
    }

    private void saveEvent() {
        String title = eventTitleEditText.getText().toString();
        String description = eventDescriptionEditText.getText().toString();

        if (!title.isEmpty() && !description.isEmpty() && selectedDate != null) {
            CalendarEvent newEvent = new CalendarEvent(title, description, selectedDate);
            storeEventInDatabase(newEvent);  // You will define this method later
            Toast.makeText(this, "Event Saved!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Please fill out all fields.", Toast.LENGTH_SHORT).show();
        }
    }

    private void storeEventInDatabase(CalendarEvent event) {
        StoreEventOperations dbOperations = new StoreEventOperations(this);
        dbOperations.open();

        long rowId = dbOperations.saveEvent(event);
        if (rowId != -1) {
            Toast.makeText(this, "Event was successfully stored.", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Failed to store event.", Toast.LENGTH_SHORT).show();
        }
        dbOperations.close();
    }
    public void openHome(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("selectedProgram", selectedProgram);
        Log.d("NewsActivity", "Sending news Program: " + selectedProgram);
        startActivity(intent);
    }
}
