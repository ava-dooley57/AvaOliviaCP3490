package com.firstapp.app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    public String selectedProgram;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Retrieve selected program if available
        if (getIntent().hasExtra("selectedProgram")) {
            selectedProgram = getIntent().getStringExtra("selectedProgram");
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            selectedProgram = data.getStringExtra("selectedProgram");
            Toast.makeText(this, "Program Selected: " + selectedProgram, Toast.LENGTH_SHORT).show();
        }
    }

    public void openPrograms(View view) {
        Intent intent = new Intent(this, ProgramsActivity.class);
        startActivityForResult(intent, 1);
    }

    public void openPublicTransit(View view) {
        Intent intent = new Intent(this, PublicTransitActivity.class);
        intent.putExtra("selectedProgram", selectedProgram);
        startActivity(intent);
    }

    public void openOfferedCourses(View view) {
        Intent intent = new Intent(this, OfferedCoursesActivity.class);
        intent.putExtra("selectedProgram", selectedProgram);
        startActivity(intent);
    }

    public void openContactUs(View view) {
        Intent intent = new Intent(this, ContactUsActivity.class);
        intent.putExtra("selectedProgram", selectedProgram);
        startActivity(intent);
    }

    public void openNews(View view) {
        Intent intent = new Intent(this, NewsActivity.class);
        intent.putExtra("selectedProgram", selectedProgram);
        startActivity(intent);
    }

    public void openCalendar(View view) {
        Intent intent = new Intent(this, CalendarActivity.class);
        intent.putExtra("selectedProgram", selectedProgram);
        startActivity(intent);
    }

    public void openSchedule(View view) {
        Intent intent = new Intent(this, ScheduleActivity.class);
        intent.putExtra("selectedProgram", selectedProgram);
        startActivity(intent);
    }

}