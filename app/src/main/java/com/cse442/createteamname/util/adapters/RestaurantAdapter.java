package com.cse442.createteamname.util.adapters;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.Adapter;

import com.cse442.createteamname.R;
import com.cse442.createteamname.restaurant.Restaurant;

import java.util.ArrayList;

public class RestaurantAdapter extends Adapter<RestaurantAdapter.ViewHolder> {

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView restaurantName, distanceLabel;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            // Get the textview for the restaurant name
            restaurantName = (TextView) itemView.findViewById(R.id.restaurant_name);
            // Get the textview for the distance
            distanceLabel = (TextView) itemView.findViewById(R.id.distance_view);
        }
    }

    private Context context;
    private ArrayList<Restaurant> restaurants;

    public RestaurantAdapter(Context context, ArrayList<Restaurant> restaurants) {
        this.context = context;
        this.restaurants = restaurants;
    }

    @NonNull
    @Override
    public RestaurantAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View contactView = inflater.inflate(R.layout.results_item, parent, false);

        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(contactView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RestaurantAdapter.ViewHolder holder, final int position) {
        // Set the label of this list item to the restaurant name
        TextView textView = holder.restaurantName;
        textView.setText(restaurants.get(position).getName());

        // Set the distance label
        TextView distance = holder.distanceLabel;
        distance.setText(String.format("%.1f mi", restaurants.get(position).getDistance()));

        // When the item is selected, send the restaurant object to the restaurant info screen
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create bundle and add the restaurant to be displayed on the info screen
                Bundle bundle = new Bundle();
                bundle.putSerializable(context.getString(R.string.restaurant_key), restaurants.get(position));
                // Change fragments
                Navigation.findNavController(v).navigate(R.id.action_results_to_info, bundle);
            }
        });
    }

    @Override
    public int getItemCount() {
        return restaurants.size();
    }

    public void setRestaurants(ArrayList<Restaurant> restaurants){
        this.restaurants = restaurants;
    }
}
