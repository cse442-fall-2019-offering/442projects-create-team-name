package com.cse442.createteamname.ui.results;

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

public class RestaurantAdapter extends Adapter<RestaurantAdapter.ViewHolder> {

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView restaurantName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            // Get the textview from the xml layout
            restaurantName = (TextView) itemView.findViewById(R.id.restaurant_name);
        }
    }

    private Context context;
    private Restaurant[] restaurantList;

    public RestaurantAdapter(Context context, Restaurant[]restaurantList) {
        this.context = context;
        this.restaurantList = restaurantList;
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
        textView.setText(restaurantList[position].getName());

        // When the item is selected, send the restaurant object to the restaurant info screen
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create bundle and add the restaurant to be displayed on the info screen
                Bundle bundle = new Bundle();
                bundle.putSerializable(context.getString(R.string.restaurant_key), restaurantList[position]);
                // Change fragments
                Navigation.findNavController(v).navigate(R.id.action_results_to_info, bundle);
            }
        });
    }

    @Override
    public int getItemCount() {
        return restaurantList.length;
    }
}
