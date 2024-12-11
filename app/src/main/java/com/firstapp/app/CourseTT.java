package com.firstapp.app;

import androidx.annotation.NonNull;

class CourseTT {
    private final String courseName;
    private final String courseID;
    private final String date;
    private final String time;
    private final String semester;
    public CourseTT(String courseName, String courseID, String date, String time, String semester) {
        this.courseName = courseName;
        this.courseID = courseID;
        this.semester = semester;
        this.date = date;
        this.time = time;
    }


    public String getSemester() {

        return semester;
    }

    public String getCourseID() {

        return courseID;
    }

    public String getDate() {
        if (this.date.equals("Variable")) {
            return " ";
        } else {
            return this.date;
        }
    }

    public String getTime() {
        if (this.time.equals("Variable")) {
            return " ";
        } else {
            return this.time;
        }
    }

    public String getCourseName() {
        return courseName;
    }


    @NonNull
    public String toString() {
        return courseID + " " + courseName + " " + date + " " + time + " " + semester;
    }
}