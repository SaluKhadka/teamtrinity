package com.example.sujin.opencomplaintss;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONObject;


/**
 * A simple {@link Fragment} subclass.
 */
public class UserIndexFragment extends Fragment {
    ListView listView;
    TrendAdapter trendAdapter;
    String json_string;
    String title, date, vote, tracker;
    JSONObject jsonObject;
    JSONArray jsonArray;

    public UserIndexFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_user_index, container, false);

        return view;
    }

}
