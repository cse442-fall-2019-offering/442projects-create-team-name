package com.cse442.createteamname.ui.results;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.cse442.createteamname.R;
import com.cse442.createteamname.restaurant.Restaurant;
import com.cse442.createteamname.util.QueryTool;

import java.util.ArrayList;

public class ResultsFragment extends Fragment {

    private ArrayList<Restaurant> restaurants;

    private String tag1;
    private String tag2;
    private String tag3;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        String search_q = "";

        // Try to get the args that have been sent to this fragment
        if(getArguments() != null) {
            search_q = getArguments().getString(getString(R.string.search_q_key));
            set_Search(search_q);
            restaurants = QueryTool.queryTags(search_q.split(","));
        }
        else {
            restaurants = QueryTool.queryTags(new String[]{});
        }

    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_results, container, false);

        RecyclerView recyclerView = (RecyclerView) root.findViewById(R.id.rv_results);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        // Set up the recycler view to contain the restaurant list
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
                layoutManager.getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);
        RestaurantAdapter restaurantAdapter = new RestaurantAdapter(getContext(), restaurants);
        recyclerView.setAdapter(restaurantAdapter);

        return root;
    }

    //gets the search query so it can be passed on to other functions for the DB
    public void set_Search(String s){
        //The toast below is the test
        //Toast.makeText(getApplicationContext(), s.toLowerCase(), Toast.LENGTH_SHORT).show();

        //sSeparates string input by comma
        String[] input_List = s.split(",");

        //set the different input tags
        if (input_List.length == 1) {
            String str1 = input_List[0];
            tag1 = str1.replace(" ", "").toLowerCase();
        }
        if (input_List.length == 2) {
            String str1 = input_List[0];
            tag1 = str1.replace(" ", "").toLowerCase();
            String str2 = input_List[1];
            tag2 = str2.replace(" ", "").toLowerCase();
        }
        if (input_List.length >= 3) {
            String str1 = input_List[0];
            tag1 = str1.replace(" ", "").toLowerCase();
            String str2 = input_List[1];
            tag2 = str2.replace(" ", "").toLowerCase();
            String str3 = input_List[2];
            tag3 = str3.replace(" ", "").toLowerCase();
        }
    }

    public String get_tag1(){
        return tag1;
    }

    public String get_tag2(){
        return tag2;
    }

    public String get_tag3(){
        return tag3;
    }
}
