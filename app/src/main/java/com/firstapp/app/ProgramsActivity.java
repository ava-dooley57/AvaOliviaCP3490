package com.firstapp.app;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class ProgramsActivity extends ListActivity {

    private String loc = new String();
    public static final String[] programs = new String[]{
            "Architectural Engineering Technology",
            "Chemical Process Engineering Technology (Co-op)",
            "Civil Engineering Technology (Co-op)",
            "Computing Systems Engineering Technology (Co-op)",
            "Electrical Engineering Technology (Power and Controls) Co-op",
            "Electronics Engineering Technology (Biomedical)",
            "Geomatics/Surveying Engineering Technology (Co-op)",
            "Instrumentation and Controls Engineering Technology",
            "Mechanical Engineering Technology",
            "Mechanical Engineering Technology (Manufacturing) Co-op",
            "Petroleum Engineering Technology (Co-op)"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_programs);

        ListView programListView = getListView();

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, programs);
        programListView.setAdapter(adapter);

        programListView.setOnItemClickListener((parent, view, position, id) -> {
            String selectedValue = (String) parent.getItemAtPosition(position);

            Intent resultIntent = new Intent();
            resultIntent.putExtra("selectedProgram", selectedValue);
            setResult(RESULT_OK, resultIntent);

            // Finish this activity to return to MainActivity
            finish();
        });
    }

}