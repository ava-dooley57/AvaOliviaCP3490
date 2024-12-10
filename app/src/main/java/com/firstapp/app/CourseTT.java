package com.firstapp.app;

class CourseTT {
    private String courseName, courseID, date, time, semester;
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
        return date;
    }

    public String getTime() {
        return time;
    }

    public String getCourseName() {
        return courseName;
    }


    public String toString() {
        return courseID + " " + courseName + " " + date + " " + time + " " + semester;
    }
}