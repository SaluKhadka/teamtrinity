package com.example.sujin.opencomplaintss;

import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;

public class Description extends AppCompatActivity {
    TextView desc;
    String title,strvote;
    int count=0;
    int vote;
    String result;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_description);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        desc=(TextView)findViewById(R.id.descText);
        Intent inte=getIntent();
        String str_desc=inte.getStringExtra("desc");
        desc.setText(str_desc);

        strvote=inte.getStringExtra("vote");
        vote=Integer.parseInt(strvote);

        //title=inte.getStringExtra("title");
        //TextView titletext=(TextView)findViewById(R.id.descText);
        //titletext.setText(title);



        FloatingActionButton fab=(FloatingActionButton)findViewById(R.id.fab);
        assert fab != null;
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Description.this,UserIndexActivity.class));
            }
        });


        final Button voteButton=(Button)findViewById(R.id.voteButton);
        assert voteButton != null;
        voteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(count%2==0) {
                    vote++;
                    count++;
                    voteButton.setBackgroundColor(Color.BLUE);
                }else{
                    vote--;
                    count++;
                    voteButton.setBackgroundColor(Color.GRAY);
                }
                processVoting();
            }
        });


    }

    public void processVoting(){
        new BackgroundWorker().execute();
    }

    public class BackgroundWorker extends AsyncTask<String, Void, String> {
        //String type;
        //String res;
        // Context context;
      //  AlertDialog alertDialog;
        String result;

        //type = params[0];
        String login_url = "http://192.168.56.1/deerthon/vote.php";


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
                    }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);


        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected String doInBackground(String... params) {
            try {
                URL url = new URL(login_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoInput(true);
                httpURLConnection.setDoOutput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String postdata = URLEncoder.encode("title", "UTF-8") + "=" + URLEncoder.encode(title, "UTF-8") + "&" + URLEncoder.encode("count", "UTF-8") + "=" + URLEncoder.encode(String.valueOf(count), "UTF-8");
                bufferedWriter.write(postdata);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();

                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
                result = "";
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    result += line;
                }
                //res = result;
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (ProtocolException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return result;
        }
    }
}
