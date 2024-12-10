package com.firstapp.app;

import android.app.Activity;
import android.content.Intent;
import android.content.res.XmlResourceParser;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import org.xmlpull.v1.XmlPullParser;

import java.util.ArrayList;
import java.util.Objects;

public class OfferedCoursesActivity extends Activity {

    private ListView courseListView;
    private ArrayList<Course> courses;

    private String selectedProgram;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offered_courses);

        courseListView = findViewById(R.id.courseListView);
        courses = new ArrayList<>();

        selectedProgram = getIntent().getStringExtra("selectedProgram");

        if (selectedProgram != null) {
            int xmlResourceId = mapProgramToXml(selectedProgram);
            loadCoursesFromXml(xmlResourceId);
        } else {
            TextView title = findViewById(R.id.title);
            title.setText("Please select a program to view courses");
        }

        CourseAdapter adapter = new CourseAdapter(this, courses);
        courseListView.setAdapter(adapter);
    }

    private int mapProgramToXml(String programName) {
        if (Objects.equals(programName, "Architectural Engineering Technology")) {
            return R.xml.ae;
        } else if (Objects.equals(programName, "Chemical Process Engineering Technology (Co-op)")) {
            return R.xml.cp;
        } else if (Objects.equals(programName, "Civil Engineering Technology (Co-op)")) {
            return R.xml.ce;
        } else if (Objects.equals(programName, "Computing Systems Engineering Technology (Co-op)")) {
            return R.xml.cs;
        } else if (Objects.equals(programName, "Electrical Engineering Technology (Power and Controls) Co-op")) {
            return R.xml.ep;
        } else if (Objects.equals(programName, "Electronics Engineering Technology (Biomedical)")) {
            return R.xml.eb;
        } else if (Objects.equals(programName, "Geomatics/Surveying Engineering Technology (Co-op)")) {
            return R.xml.ge;
        } else if (Objects.equals(programName, "Instrumentation and Controls Engineering Technology")) {
            return R.xml.ei;
        } else if (Objects.equals(programName, "Mechanical Engineering Technology")) {
            return R.xml.me;
        } else if (Objects.equals(programName, "Mechanical Engineering Technology (Manufacturing) Co-op")) {
            return R.xml.mm;
        } else if (Objects.equals(programName, "Petroleum Engineering Technology (Co-op)")) {
            return R.xml.pe;
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
            String semester = "";
            String currentSemester = "";
            String credit = "";
            String lecture = "";
            String lab = "";

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
                                case "credit":
                                    credit = text;
                                    break;
                                case "lecture":
                                    lecture = text;
                                    break;
                                case "lab":
                                    lab = text;
                                    break;
                                case "number":
                                    semester = text;
                                    currentSemester = text;
                                    break;
                                default:
                                    Log.d("XML Parsing", "Unknown tag: " + currentTag);
                            }
                        }
                        break;

                    case XmlPullParser.END_TAG:
                        if (parser.getName().equals("course")) {
                            if (semester.equals("")) {
                                semester = currentSemester;
                            }
                            courses.add(new Course(courseName, courseID, semester, credit, lecture, lab));
                            courseName = "";
                            courseID = "";
                            semester = "";
                            credit = "";
                            lecture = "";
                            lab = "";
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
        intent.putExtra("selectedProgram", selectedProgram);
        Log.d("ContactUsActivity", "Sending Courses Program: " + selectedProgram);
        startActivity(intent);
    }
}
