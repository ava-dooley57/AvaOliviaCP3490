package com.firstapp.app;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import java.util.List;

public class NewsAdapter extends BaseAdapter {
    private Context context;
    private List<News> newsItems;

    public NewsAdapter(Context context, List<News> newsItems) {
        this.context = context;
        this.newsItems = newsItems;
    }

    @Override
    public int getCount() {

        return newsItems.size();
    }

    @Override
    public Object getItem(int position) {

        return newsItems.get(position);
    }

    @Override
    public long getItemId(int position) {

        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(R.layout.news, parent, false);
        }

        News newsPost = newsItems.get(position);

        TextView newsDate = convertView.findViewById(R.id.date);
        newsDate.setText(newsPost.getNewsDate());

        TextView newsTitle = convertView.findViewById(R.id.title);
        newsTitle.setText(newsPost.getNewsTitle());

        TextView newsDescription = convertView.findViewById(R.id.description);
        newsDescription.setText(newsPost.getNewsDescription());

        return convertView;
    }
}
