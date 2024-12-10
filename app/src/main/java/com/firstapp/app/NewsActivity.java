package com.firstapp.app;

import android.app.Activity;
import android.content.Intent;
import android.content.res.XmlResourceParser;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import org.xmlpull.v1.XmlPullParser;

import java.util.ArrayList;

public class NewsActivity extends Activity {

    private ListView newsListView;
    private ArrayList<News> newsItems;
    private String selectedProgram;
    final int xmlResourceId = R.xml.newsposts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);

        newsListView = findViewById(R.id.newsListView);
        newsItems = new ArrayList<>();

        selectedProgram = getIntent().getStringExtra("selectedProgram");

        loadNewsFromXml(xmlResourceId);

        NewsAdapter adapter = new NewsAdapter(this, newsItems);
        newsListView.setAdapter(adapter);
    }

    private void loadNewsFromXml(int xmlFileID) {
        try {
            XmlResourceParser parser = getResources().getXml(xmlFileID);
            int eventType = parser.getEventType();
            String currentTag = "";
            String date = "";
            String title = "";
            String description = "";

            while (eventType != XmlPullParser.END_DOCUMENT) {
                switch (eventType) {
                    case XmlPullParser.START_TAG:
                        currentTag = parser.getName();
                        break;

                    case XmlPullParser.TEXT:
                        String text = parser.getText().trim();
                        if (!text.isEmpty()) {
                            switch (currentTag) {
                                case "date":
                                    date = text;
                                    break;
                                case "title":
                                    title = text;
                                    break;
                                case "description":
                                    description = text;
                                    break;
                                default:
                                    Log.d("XML Parsing", "Unknown tag: " + currentTag);
                            }
                        }
                        break;

                    case XmlPullParser.END_TAG:
                        if (parser.getName().equals("newsItem")) {
                            newsItems.add(new News(date, title, description));
                            date = "";
                            title = "";
                            description = "";
                        }
                        break;
                }
                eventType = parser.next();
            }

            Log.d("XML Parsing", "Finished parsing. Total courses: " + newsItems.size());
            parser.close();
        } catch (Exception e) {
            Log.e("XML Parsing", "Error parsing XML: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void openHome(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("selectedProgram", selectedProgram);
        startActivity(intent);
    }
}

