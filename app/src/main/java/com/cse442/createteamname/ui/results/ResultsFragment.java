package com.cse442.createteamname.ui.results;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.cse442.createteamname.R;
import com.cse442.createteamname.restaurant.Restaurant;

public class ResultsFragment extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        String search_q = "";

        // Try to get the args that have been sent to this fragment
        if(getArguments() != null){
            search_q = getArguments().getString(getString(R.string.search_q_key));

            // TODO: Remove this line and query the database with the text
            Toast.makeText(getContext(), search_q, Toast.LENGTH_SHORT).show();
        }
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_results, container, false);

        RecyclerView recyclerView = (RecyclerView) root.findViewById(R.id.rv_results);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        // TODO: Remove this and replace it with actual queried results
        Restaurant[] restaurants = new Restaurant[15];
        for (int i = 0; i < 15; ++i){
            restaurants[i] = new Restaurant("Tim Hortons", "1950 Sweet Home Rd, Amherst, NY 14228","(716) 689-0187",
                    "https://timhortons.com/us/en/index.php", new String[]{"coffee", "breakfast", "fast"});
        }

        // Set up the recycler view to contain the restaurant list
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
                layoutManager.getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);
        RestaurantAdapter restaurantAdapter = new RestaurantAdapter(getContext(), restaurants);
        recyclerView.setAdapter(restaurantAdapter);

        return root;
    }
}
