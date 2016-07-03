package com.example.sujin.opencomplaintss;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;


/**
 * A simple {@link Fragment} subclass.
 */
public class SignupFragment extends Fragment implements View.OnClickListener{


    public SignupFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View rootView=inflater.inflate(R.layout.fragment_signup, container, false);
        //TextView logintext=(TextView)rootView.findViewById(R.id.logintext);
        //logintext.setOnClickListener(this);
        Button signup =(Button)rootView.findViewById(R.id.registerButton);
        signup.setOnClickListener(this);
        return rootView;
    }

    public void onClick(View view){
        int id=view.getId();
        switch (id){
            /*case R.id.logintext:
                LoginFragment fragment = new LoginFragment();
                android.support.v4.app.FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.fragment_container, fragment);
                fragmentTransaction.commit();
                break;*/
            case R.id.registerButton:
                procesRegsiter();
                break;

        }
    }

    public void procesRegsiter(){
        EditText fname=(EditText)getActivity().findViewById(R.id.fname);
        EditText lname=(EditText)getActivity().findViewById(R.id.lname);
        EditText citizen=(EditText)getActivity().findViewById(R.id.citznum);
        EditText password=(EditText)getActivity().findViewById(R.id.password);
        Spinner location=(Spinner)getActivity().findViewById(R.id.spinner);
        EditText username=(EditText)getActivity().findViewById(R.id.username);
        RadioGroup genderGroup=(RadioGroup)getActivity().findViewById(R.id.radioGroup);
        RadioGroup regtypeGroup=(RadioGroup)getActivity().findViewById(R.id.radioGroup1);

        String str_fname=fname.getText().toString();
        String str_lname=lname.getText().toString();
        String str_passwrod=password.getText().toString();
        String str_location=String.valueOf(location.getSelectedItem());
        String str_username=username.getText().toString();
        String str_ctzn=citizen.getText().toString();

        int selectedId=genderGroup.getCheckedRadioButtonId();
        RadioButton butt=(RadioButton)getActivity().findViewById(selectedId);
        String gender=butt.getText().toString();
        selectedId=regtypeGroup.getCheckedRadioButtonId();
        butt=(RadioButton)getActivity().findViewById(selectedId);
        String regtype=butt.getText().toString();



        String type="register";

        BackgroundWorker bck=new BackgroundWorker(getActivity());
        bck.execute(type,str_fname,str_lname,str_location,str_ctzn,str_username,str_passwrod,gender,regtype);

        if(regtype.equals("gov")) {
            startActivity(new Intent(getContext(), GovRegisterActivity.class));
        }

            /*LoginFragment fragment = new LoginFragment();
        android.support.v4.app.FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, fragment);
        fragmentTransaction.commit();
*/
    }


}
