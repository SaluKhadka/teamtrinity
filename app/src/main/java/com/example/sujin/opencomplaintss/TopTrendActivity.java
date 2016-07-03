package com.example.sujin.opencomplaintss;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class TopTrendActivity extends AppCompatActivity {
    ListView listView;
    TrendAdapter trendAdapter;
    String json_string;
    String title, date, vote, tracker;
    JSONObject jsonObject;
    JSONArray jsonArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top_trend);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        listView = (ListView) findViewById(R.id.listview);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(TopTrendActivity.this, Description.class);
                i.putExtra("desc", date);
                i.putExtra("vote",vote);
                i.putExtra("title",title);
                startActivity(i);
            }
        });

        trendAdapter = new TrendAdapter(this, R.layout.top_trend);
        listView.setAdapter(trendAdapter);
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
