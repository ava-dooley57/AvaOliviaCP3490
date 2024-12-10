package com.firstapp.app;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class StoreEventOperations {
    private SQLiteEvents dbHelper;
    private SQLiteDatabase database;

    public StoreEventOperations(Context context) {
        dbHelper = new SQLiteEvents(context);
    }

    public void open() {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public long saveEvent(CalendarEvent event) {
        ContentValues values = new ContentValues();
        values.put(SQLiteEvents.COLUMN_TITLE, event.getCalendarTitle());
        values.put(SQLiteEvents.COLUMN_DESCRIPTION, event.getCalendarDescription());
        values.put(SQLiteEvents.COLUMN_DATE, event.getCalendarDate());

        return database.insert(SQLiteEvents.TABLE_EVENTS, null, values);
    }

    public List<CalendarEvent> getAllEvents() {
        List<CalendarEvent> events = new ArrayList<>();
        Cursor cursor = database.query(
                SQLiteEvents.TABLE_EVENTS, // Use SQLiteEvents for table name
                null, null, null, null, null, null);

        while (cursor.moveToNext()) {
            String title = cursor.getString(cursor.getColumnIndexOrThrow(SQLiteEvents.COLUMN_TITLE)); // Use SQLiteEvents constants
            String description = cursor.getString(cursor.getColumnIndexOrThrow(SQLiteEvents.COLUMN_DESCRIPTION));
            String date = cursor.getString(cursor.getColumnIndexOrThrow(SQLiteEvents.COLUMN_DATE));

            events.add(new CalendarEvent(title, description, date));
        }

        cursor.close();
        return events;
    }
}
