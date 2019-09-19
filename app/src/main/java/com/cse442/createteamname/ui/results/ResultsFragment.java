package com.cse442.createteamname.ui.results;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.cse442.createteamname.R;

public class ResultsFragment extends Fragment {

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_results, container, false);

        RecyclerView recyclerView = (RecyclerView) root.findViewById(R.id.rv_results);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        String[] restaurants = {
                "Taco Bell",
                "Moe's",
                "Wegman's",
                "Zetti's",
                "Tim Hortons",
                "Tim Hortons",
                "Tim Hortons",
                "Tim Hortons",
                "Tim Hortons",
                "Tim Hortons",
                "Tim Hortons",
                "Tim Hortons",
                "Tim Hortons",
                "Tim Hortons",
                "Tim Hortons",
                "Tim Hortons",
                "Tim Hortons",
                "Starbucks",
        };
        RestaurantAdapter restaurantAdapter = new RestaurantAdapter(restaurants);

        recyclerView.setAdapter(restaurantAdapter);

        return root;
    }
}
