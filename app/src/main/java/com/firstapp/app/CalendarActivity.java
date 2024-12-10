package com.firstapp.app;

import android.content.Intent;
import android.content.res.XmlResourceParser;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CalendarView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import org.xmlpull.v1.XmlPullParser;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

public class CalendarActivity extends AppCompatActivity {

    private String selectedProgram;
    private ListView calendarListView;
    private ArrayList<CalendarEvent> eventItems;
    private final int xmlResourceId = R.xml.events;
    private CalendarView calendarView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        calendarListView = findViewById(R.id.calendarListView);
        calendarView = findViewById(R.id.calendarView);
        eventItems = new ArrayList<>();
        loadCalendarEventsFromDatabase();

        if (getIntent().hasExtra("selectedProgram")) {
            selectedProgram = getIntent().getStringExtra("selectedProgram");
        }

        loadCalendarEventsFromXML(xmlResourceId);
        CalendarAdapter adapter = new CalendarAdapter(this, eventItems);
        calendarListView.setAdapter(adapter);
        calendarView.setDate(System.currentTimeMillis(), true, true);
        calendarView.setFirstDayOfWeek(GregorianCalendar.SUNDAY);
        calendarView.setShowWeekNumber(true);
        calendarView.setOnDateChangeListener((view, year, month, dayOfMonth) -> {
        });
    }

    public void loadCalendarEventsFromDatabase() {
        StoreEventOperations dbOperations = new StoreEventOperations(this);
        dbOperations.open();
        List<CalendarEvent> savedEvents = dbOperations.getAllEvents();
        eventItems.addAll(savedEvents);
        dbOperations.close();
    }

    public void loadCalendarEventsFromXML(int xmlFileID) {
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
                        if (parser.getName().equals("event")) {
                            eventItems.add(new CalendarEvent(date, title, description));
                            date = "";
                            title = "";
                            description = "";
                        }
                        break;
                }
                eventType = parser.next();
            }

            Log.d("XML Parsing", "Finished parsing. Total events: " + eventItems.size());
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

    public void openStoreEvent(View view) {
        Intent intent = new Intent(this, StoreEventActivity.class);
        intent.putExtra("selectedProgram", selectedProgram);
        startActivity(intent);
    }
}
