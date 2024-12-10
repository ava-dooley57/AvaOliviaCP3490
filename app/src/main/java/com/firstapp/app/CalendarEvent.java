package com.firstapp.app;

public class CalendarEvent {
    String date;
    String title;
    String description;

    public CalendarEvent(String date, String title, String description) {
        this.date = date;
        this.title = title;
        this.description = description;
    }

    public String getCalendarDate() {

        return date;
    }

    public String getCalendarTitle() {

        return title;
    }

    public String getCalendarDescription() {

        return description;
    }
}


