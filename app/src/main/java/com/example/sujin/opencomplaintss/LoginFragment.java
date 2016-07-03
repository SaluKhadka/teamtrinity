package com.example.sujin.opencomplaintss;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
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


/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends Fragment implements View.OnClickListener {
    public static String res = null;
    public static final String MyPref = "MyPref";
    public static final String Username = "usernameKey";
    SharedPreferences sharedPreferences;
    String val;
    String json_string1;

    String str_username, str_password;

    public LoginFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View root = inflater.inflate(R.layout.fragment_login, container, false);
        Button login = (Button) root.findViewById(R.id.loginButton);
        sharedPreferences = getActivity().getSharedPreferences(MyPref, Context.MODE_PRIVATE);
        login.setOnClickListener(this);
        TextView tv = (TextView) root.findViewById(R.id.signuptext);
        tv.setOnClickListener(this);
        return root;
    }


    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.loginButton:

                processLogin();
                //startActivity(new Intent(getContext(),AfterLoginActivity.class));
                break;
            case R.id.signuptext:
                SignupFragment fragment = new SignupFragment();
                android.support.v4.app.FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.fragment_container, fragment);
                fragmentTransaction.commit();
                break;
        }
    }

    public void processLogin() {
       /* EditText username = (EditText) getActivity().findViewById(R.id.username);
        EditText password = (EditText) getActivity().findViewById(R.id.lpassword);

        str_username = username.getText().toString();
        str_password = password.getText().toString();
        String type = "login";*/

        BackgroundWorker bck = new BackgroundWorker();
        bck.execute();

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("Username", str_username);
        editor.commit();


    }

    public class BackgroundWorker extends AsyncTask<String, Void, String> {
        //String type;
        //String res;
        // Context context;
        AlertDialog alertDialog;
        String result;

        //type = params[0];
        String login_url = "http://192.168.56.1/deerthon/login.php";


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            EditText username = (EditText) getActivity().findViewById(R.id.username1);
            EditText password = (EditText) getActivity().findViewById(R.id.lpassword);
            RadioGroup loginGroup=(RadioGroup)getActivity().findViewById(R.id.radioGroup);
            str_username = username.getText().toString();
            str_password = password.getText().toString();
            int selectedId=loginGroup.getCheckedRadioButtonId();
            RadioButton rb=(RadioButton)getActivity().findViewById(selectedId);
            val=rb.getText().toString();

        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            if (s.equals("success")) {
                if(val.equals("public")) {
                   // getTrends();
                    startActivity(new Intent(getContext(), UserIndexActivity.class));
                    //Toast.makeText(getContext(),"success",Toast.LENGTH_LONG).show();
                }else{
                   startActivity(new Intent(getContext(),GovIndexActivity.class));
                    //Toast.makeText(getContext(),"failed",Toast.LENGTH_LONG).show();
                }
            } else {
                AlertDialog alert = new AlertDialog.Builder(getContext()).create();
                alert.setTitle("Message");
                //alert.setIcon(R.drawable.logo);
                alert.setMessage(s);
                alert.show();
            }
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
                String postdata = URLEncoder.encode("username", "UTF-8") + "=" + URLEncoder.encode(str_username, "UTF-8") + "&" + URLEncoder.encode("password", "UTF-8") + "=" + URLEncoder.encode(str_password, "UTF-8")+"&"+URLEncoder.encode("logintype", "UTF-8") + "=" + URLEncoder.encode(val, "UTF-8");
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
