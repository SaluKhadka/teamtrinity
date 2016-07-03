package com.example.sujin.opencomplaintss;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class GovRegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gov_register);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        Button regB2 =(Button)findViewById(R.id.regButton2);
        regB2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                processRegister();
            }
        });
    }


    public void processRegister(){
        EditText username=(EditText)findViewById(R.id.username2);
        EditText empid=(EditText)findViewById(R.id.empID2);
        Spinner depart=(Spinner)findViewById(R.id.spinner2);
        Spinner wloc=(Spinner)findViewById(R.id.spinner3);

        String str_uname=username.getText().toString();
        String str_empid=empid.getText().toString();
        String str_depart=String.valueOf(depart.getSelectedItem());
        String str_loc=String.valueOf(wloc.getSelectedItem());
        if(str_uname.equals("") || str_empid.equals("") || str_depart.equals("") || str_loc.equals("")){
            AlertDialog alert=new AlertDialog.Builder(getApplicationContext()).create();
            alert.setTitle("Error");
            alert.setMessage("Regsitration failed.");
            alert.show();
        }

        String type="register";
        Toast.makeText(GovRegisterActivity.this, "Successfully registered", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(GovRegisterActivity.this,UserIndexActivity.class));
        //BackgroundWorker2 bck=new BackgroundWorker2(this);
        //bck.execute(type,str_uname,str_empid,str_depart,str_loc);
        /*LoginFragment fragment = new LoginFragment();
        android.support.v4.app.FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, fragment);
        fragmentTransaction.commit();*/
    }

}
