package com.example.sujin.opencomplaintss;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class TrendAdapter extends ArrayAdapter {
    List list = new ArrayList();

    public TrendAdapter(Context context, int resource) {
        super(context, resource);
    }

    @Override
    public void add(Object object) {
        super.add(object);
        list.add(object);
    }

    @Override
    public Object getItem(int position) {

        return list.get(position);
    }

    @Override
    public int getCount() {

        return list.size();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View row;
        row = convertView;
        Trendholder trendholder;

        if (row == null) {
            LayoutInflater layoutInflater = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = layoutInflater.inflate(R.layout.top_trend, parent, false);
            trendholder = new Trendholder();
            trendholder.title = (TextView) row.findViewById(R.id.topicText);
            trendholder.desc = (TextView) row.findViewById(R.id.dectext);
            trendholder.vote=(TextView)row.findViewById(R.id.voteText);
            trendholder.tracker=(TextView)row.findViewById(R.id.trackerText);


            row.setTag(trendholder);

        } else {
            trendholder = (Trendholder) row.getTag();
        }

        TrendComponent trend = (TrendComponent) this.getItem(position);
        trendholder.title.setText(trend.getTitle());
        trendholder.vote.setText(trend.getVote());
        trendholder.desc.setText(trend.getDesc());
        trendholder.tracker.setText(trend.getTracker());


        return row;
    }

    static class Trendholder {
        TextView title, desc,vote,tracker;

    }
}
