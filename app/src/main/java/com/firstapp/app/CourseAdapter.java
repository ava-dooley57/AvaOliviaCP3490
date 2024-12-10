package com.firstapp.app;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import java.util.List;

public class CourseAdapter extends BaseAdapter {
    private Context context;
    private List<Course> courses;

    // Constructor
    public CourseAdapter(Context context, List<Course> courses) {
        this.context = context;
        this.courses = courses;
    }

    @Override
    public int getCount() {

        return courses.size();
    }

    @Override
    public Object getItem(int position) {

        return courses.get(position);
    }

    @Override
    public long getItemId(int position) {

        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(R.layout.course, parent, false);
        }

        Course course = courses.get(position);

        // Set course name to TextView
        TextView courseName = convertView.findViewById(R.id.courseName);
        courseName.setText(course.getCourseName());

        // Set course ID to TextView
        TextView courseID = convertView.findViewById(R.id.courseID);
        courseID.setText(course.getCourseID());

        // Set semester to TextView
        TextView courseSemester = convertView.findViewById(R.id.semester);
        courseSemester.setText("Semester: " + course.getSemester());

        // You can also display additional info if needed, like credit, lecture, lab
        TextView courseCredit = convertView.findViewById(R.id.credit);
        courseCredit.setText(course.getCredit() + " credit course");

        TextView courseLecture = convertView.findViewById(R.id.lecture);
        courseLecture.setText(course.getLecture() + " lecture hours per week");

        TextView courseLab = convertView.findViewById(R.id.lab);
        courseLab.setText(course.getLab() + " lab hours per week");


        return convertView;
    }
}


