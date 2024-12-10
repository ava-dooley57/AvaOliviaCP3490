package com.firstapp.app;

class Course {
    private String courseName, courseID, semester, credit, lecture, lab;
    public Course(String courseName, String courseID, String semester, String credit, String lecture, String lab) {
        this.courseName = courseName;
        this.courseID = courseID;
        this.semester = semester;
        this.credit = credit;
        this.lecture = lecture;
        this.lab = lab;
    }


    public String getSemester() {

        return semester;
    }

    public String getCourseID() {

        return courseID;
    }

    public String getCourseName() {

        return courseName;
    }

    public String getCredit() {

        return credit;
    }

    public String getLecture() {

        return lecture;
    }

    public String getLab() {

        return lab;
    }

    public String toString() {
        return courseID + " " + courseName + " " + credit + " " + lecture + " " + lab;
    }
}