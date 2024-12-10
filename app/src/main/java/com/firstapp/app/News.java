package com.firstapp.app;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class News {
    private String newsTitle, newsDate, newsDescription;

    public News(String newsTitle, String newsDate, String newsDescription) {
        this.newsTitle = newsTitle;
        this.newsDate = newsDate;
        this.newsDescription = newsDescription;
    }

    public String getNewsTitle() {

        return this.newsTitle;
    }

    public String getNewsDate() {

        return this.newsDate;
    }

    public String getNewsDescription() {

        return this.newsDescription;
    }
}
