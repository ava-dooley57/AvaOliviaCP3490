package com.firstapp.app;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class CalendarAdapter extends ArrayAdapter<CalendarEvent> {

    private Context context;
    private List<CalendarEvent> eventItems;

    public CalendarAdapter(Context context, List<CalendarEvent> eventItems) {
        super(context, android.R.layout.simple_list_item_1, eventItems);
        this.context = context;
        this.eventItems = eventItems;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            view = View.inflate(context, R.layout.event, null);
        }

        CalendarEvent event = eventItems.get(position);

        TextView calendarDate = view.findViewById(R.id.date);
        calendarDate.setText(event.getCalendarDate());

        TextView calendarTitle = view.findViewById(R.id.title);
        calendarTitle.setText(event.getCalendarTitle());

        TextView calendarDescription = view.findViewById(R.id.description);
        calendarDescription.setText(event.getCalendarDescription());
        return view;
    }
}


