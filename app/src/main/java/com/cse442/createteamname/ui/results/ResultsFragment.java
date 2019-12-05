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
import com.cse442.createteamname.util.adapters.RestaurantAdapter;
import com.cse442.createteamname.util.gps.DistanceFilter;
import com.cse442.createteamname.util.gps.LocationUtil;
import com.cse442.createteamname.util.query.RestaurantQueryTool;

import java.util.ArrayList;

public class ResultsFragment extends Fragment {

    private ArrayList<Restaurant> restaurants;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        String search_q = "";

        // Try to get the args that have been sent to this fragment
        if(getArguments() != null) {
            search_q = getArguments().getString(getString(R.string.search_q_key));
            restaurants = RestaurantQueryTool.query(search_q.split(","));
        }
        else {
            restaurants = RestaurantQueryTool.query(new String[]{});
        }

        // TODO: TANISHA! UNCOMMENT THIS WHEN DONE!
        // restaurants = DistanceFilter.filterDistance(new LocationUtil(getContext()), restaurants);

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
}
