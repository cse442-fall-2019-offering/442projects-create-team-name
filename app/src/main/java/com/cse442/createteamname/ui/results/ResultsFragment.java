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

public class ResultsFragment extends Fragment {

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_results, container, false);

        RecyclerView recyclerView = (RecyclerView) root.findViewById(R.id.rv_results);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        Restaurant[] restaurants = {
                new Restaurant("Taco Bell", ""),
                new Restaurant("Moe's", ""),
                new Restaurant("Wegman's", ""),
                new Restaurant("Zetti's", ""),
                new Restaurant("Tim Hortons", ""),
                new Restaurant("Tim Hortons", ""),
                new Restaurant("Tim Hortons", ""),
                new Restaurant("Tim Hortons", ""),
                new Restaurant("Tim Hortons", ""),
                new Restaurant("Tim Hortons", ""),
                new Restaurant("Tim Hortons", ""),
                new Restaurant("Tim Hortons", ""),
                new Restaurant("Tim Hortons", ""),
                new Restaurant("Tim Hortons", ""),
                new Restaurant("Tim Hortons", ""),
                new Restaurant("Tim Hortons", ""),
                new Restaurant("Tim Hortons", ""),
                new Restaurant("Tim Hortons", ""),
                new Restaurant("Tim Hortons", ""),
                new Restaurant("Tim Hortons", "")
        };
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
                layoutManager.getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);
        RestaurantAdapter restaurantAdapter = new RestaurantAdapter(restaurants);
        recyclerView.setAdapter(restaurantAdapter);

        return root;
    }
}
