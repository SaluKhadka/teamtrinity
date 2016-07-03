package com.example.sujin.opencomplaintss;


import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by Sujin on 5/1/2016.
 */
public class BackgroundWorker2 extends AsyncTask<String, Void, String> {
    String type;
    String res,result="";
    Context context;
    AlertDialog alertDialog;

    BackgroundWorker2(Context ctx) {
        context = ctx;
    }

    @Override
    protected String doInBackground(String... params) {
        type = params[0];

        String register_url = "http://192.168.56.1/deerthon/gov_register.php";
        if (type.equals("register")) {
            try {
                String uname = params[1];
                String empid = params[2];
                String depart = params[3];
                String location = params[4];
                URL url = new URL(register_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String postdata = URLEncoder.encode("username", "UTF-8") + "=" + URLEncoder.encode(uname, "UTF-8") + "&" + URLEncoder.encode("department", "UTF-8") + "=" + URLEncoder.encode(depart, "UTF-8") + "&" + URLEncoder.encode("emp_id", "UTF-8") + "=" + URLEncoder.encode(empid, "UTF-8") + "&" + URLEncoder.encode("work_loc", "UTF-8") + "=" + URLEncoder.encode(location, "UTF-8");
                bufferedWriter.write(postdata);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();

                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new
                        BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
                result = "";
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    result += line;
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                //return result;

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return result;
    }


    @Override
    protected void onPreExecute() {
        //super.onPreExecute();

        alertDialog = new AlertDialog.Builder(context).create();
        alertDialog.setTitle("Status");
        //alertDialog.setIcon(R.drawable.logo);

    }

    @Override
    protected void onPostExecute(String result) {

        alertDialog.setMessage(result);
        alertDialog.show();

    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }
}
