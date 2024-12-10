package com.firstapp.app;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import java.util.List;

public class CourseTTAdapter extends BaseAdapter {
    private Context context;
    private List<CourseTT> courses;

    public CourseTTAdapter(Context context, List<CourseTT> courses) {
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
            convertView = inflater.inflate(R.layout.coursett, parent, false);
        }

        CourseTT course = courses.get(position);

        TextView courseName = convertView.findViewById(R.id.courseName);
        courseName.setText(course.getCourseName());

        TextView courseID = convertView.findViewById(R.id.courseID);
        courseID.setText(course.getCourseID());

        TextView date = convertView.findViewById(R.id.date);
        date.setText(course.getDate());

        TextView time = convertView.findViewById(R.id.time);
        time.setText(course.getTime());

        TextView courseSemester = convertView.findViewById(R.id.semester);
        courseSemester.setText("Semester: " + course.getSemester());

        return convertView;
    }
}


