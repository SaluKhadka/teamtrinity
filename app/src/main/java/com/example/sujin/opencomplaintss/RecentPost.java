package com.example.sujin.opencomplaintss;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class RecentPost extends AppCompatActivity {
    ListView listView;
    TrendAdapter trendAdapter;
    String json_string;
    String title, date, vote, tracker;
    JSONObject jsonObject;
    JSONArray jsonArray;
    String username;
    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recent_post);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        listView = (ListView) findViewById(R.id.listview);


        trendAdapter = new TrendAdapter(this, R.layout.top_trend);
        listView.setAdapter(trendAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(RecentPost.this, Description.class);
                //TrendComponent trend = (TrendComponent) view.getItem(position);
                //String date=trend.getTitle();
                //trendholder.vote.setText(trend.getVote());
                //trendholder.date.setText(trend.getDesc());
                //trendholder.tracker.setText(trend.getTracker());

                i.putExtra("desc", date);
                i.putExtra("vote",vote);
                i.putExtra("title",title);
                startActivity(i);
            }
        });
        json_string = getIntent().getExtras().getString("json_data");

        try {
            jsonObject = new JSONObject(json_string);
            jsonArray = jsonObject.getJSONArray("server_response"); //check here
            int count = 0;
            //String sender,message;
            while (count < jsonArray.length()) {
                JSONObject jo = jsonArray.getJSONObject(count);
                title = jo.getString("ptopic");
                date = jo.getString("pdes");
                vote = jo.getString("votes");
                tracker = jo.getString("tracker");

                TrendComponent trend = new TrendComponent(title, date, vote, tracker);

                trendAdapter.add(trend);
                count++;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

}


