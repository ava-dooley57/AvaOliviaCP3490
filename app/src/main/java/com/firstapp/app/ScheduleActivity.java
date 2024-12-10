package com.firstapp.app;

import android.app.Activity;
import android.content.Intent;
import android.content.res.XmlResourceParser;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import org.xmlpull.v1.XmlPullParser;

import java.util.ArrayList;
import java.util.Objects;

public class ScheduleActivity extends Activity {

    private ListView courseTTListView;
    private ArrayList<CourseTT> courses;

    private String selectedProgram;
    private ArrayList<CourseTT> filteredCourses;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);

        courseTTListView = findViewById(R.id.courseTTListView);
        Spinner yearSpinner = findViewById(R.id.yearSpinner);
        filteredCourses = new ArrayList<>();
        courses = new ArrayList<>();

        selectedProgram = getIntent().getStringExtra("selectedProgram");

        if (selectedProgram != null) {
            int xmlResourceId = mapProgramToXml(selectedProgram);
            loadCoursesFromXml(xmlResourceId);
        } else {
            TextView title = findViewById(R.id.title);
            title.setText("Please select a program to view courses");
        }

        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this, R.array.years_array, android.R.layout.simple_spinner_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        yearSpinner.setAdapter(adapter1);

        yearSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                filterCoursesByYear(position + 1);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                filteredCourses.clear();
                filteredCourses.addAll(courses);
                updateListView();
            }

        });
        CourseTTAdapter adapter2 = new CourseTTAdapter(this, courses);
        courseTTListView.setAdapter(adapter2);
    }

    private void filterCoursesByYear(int year) {
        filteredCourses.clear();
        for (CourseTT course : courses) {
            if (year == 1) {
                if (Integer.parseInt(course.getSemester()) <= 3) {
                    filteredCourses.add(course);
                }
            } else if (year == 2) {
                if (Integer.parseInt(course.getSemester()) >= 4 && Integer.parseInt(course.getSemester()) <= 6) {
                    filteredCourses.add(course);
                }
            } else if (year == 3) {
                if (Integer.parseInt(course.getSemester()) >= 7) {
                    filteredCourses.add(course);
                }
            }
        }
        updateListView();
    }

    private void updateListView() {
        CourseTTAdapter adapter = new CourseTTAdapter(this, filteredCourses);
        courseTTListView.setAdapter(adapter);
    }

    private int mapProgramToXml(String programName) {
        if (Objects.equals(programName, "Architectural Engineering Technology")) {
            return R.xml.aett;
        } else if (Objects.equals(programName, "Chemical Process Engineering Technology (Co-op)")) {
            return R.xml.cptt;
        } else if (Objects.equals(programName, "Civil Engineering Technology (Co-op)")) {
            return R.xml.cett;
        } else if (Objects.equals(programName, "Computing Systems Engineering Technology (Co-op)")) {
            return R.xml.cstt;
        } else if (Objects.equals(programName, "Electrical Engineering Technology (Power and Controls) Co-op")) {
            return R.xml.eptt;
        } else if (Objects.equals(programName, "Electronics Engineering Technology (Biomedical)")) {
            return R.xml.ebtt;
        } else if (Objects.equals(programName, "Geomatics/Surveying Engineering Technology (Co-op)")) {
            return R.xml.gett;
        } else if (Objects.equals(programName, "Instrumentation and Controls Engineering Technology")) {
            return R.xml.eitt;
        } else if (Objects.equals(programName, "Mechanical Engineering Technology")) {
            return R.xml.mett;
        } else if (Objects.equals(programName, "Mechanical Engineering Technology (Manufacturing) Co-op")) {
            return R.xml.mmtt;
        } else if (Objects.equals(programName, "Petroleum Engineering Technology (Co-op)")) {
            return R.xml.pett;
        } else {
            return 0;
        }
    }

    private void loadCoursesFromXml(int xmlFileID) {
        try {
            XmlResourceParser parser = getResources().getXml(xmlFileID);

            int eventType = parser.getEventType();
            String currentTag = "";
            String courseName = "";
            String courseID = "";
            String date = "";
            String time = "";
            String semester = "";
            String currentSemester = "";

            while (eventType != XmlPullParser.END_DOCUMENT) {
                switch (eventType) {
                    case XmlPullParser.START_TAG:
                        currentTag = parser.getName();
                        break;

                    case XmlPullParser.TEXT:
                        String text = parser.getText().trim();
                        if (!text.isEmpty()) {
                            switch (currentTag) {
                                case "courseName":
                                    courseName = text;
                                    break;
                                case "courseID":
                                    courseID = text;
                                    break;
                                case "day":
                                    date = text;
                                    break;
                                case "time":
                                    time = text;
                                    break;
                                case "number":
                                    semester = text;
                                    currentSemester = text;
                                    break;
                                default:
                                    Log.d("XML Parsing", "Unknown tag: " + currentTag);
                            }
                        }
                        Log.d("Course Info", "Course Date: " + date + " | Course Time: " + time);

                        break;

                    case XmlPullParser.END_TAG:
                        if (parser.getName().equals("course")) {
                            if (semester.equals("")) {
                                semester = currentSemester;
                            }
                            courses.add(new CourseTT(courseName, courseID, date, time, semester));
                            courseName = "";
                            courseID = "";
                            date = "";
                            time = "";
                            semester = "";
                        }
                        break;
                }
                eventType = parser.next();
            }

            Log.d("XML Parsing", "Finished parsing. Total courses: " + courses.size());
            parser.close();
        } catch (Exception e) {
            Log.e("XML Parsing", "Error parsing XML: " + e.getMessage());
            e.printStackTrace();
        }
    }
    public void openHome(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("selectedProgram", selectedProgram);  // Pass the selected program
        Log.d("ContactUsActivity", "Sending Courses Program: " + selectedProgram);
        startActivity(intent);
    }
}
