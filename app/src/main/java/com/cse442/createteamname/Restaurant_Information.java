package com.cse442.createteamname;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cse442.createteamname.restaurant.Restaurant;

import org.w3c.dom.Text;

public class Restaurant_Information extends Fragment {

    private static final String RESTAURANT_ARG = "restaurant";

    private Restaurant restaurant;

    public Restaurant_Information() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            restaurant = (Restaurant)getArguments().getSerializable(RESTAURANT_ARG);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment and store it in root to edit the text fields
        View root =  inflater.inflate(R.layout.fragment_restaurant__information, container, false);

        // Set the restaurant name in the fragment
        TextView nameTextView = (TextView) root.findViewById(R.id.textViewRestaurantName);
        nameTextView.setText(restaurant.getName());

        // Display the restaurant phone number in the fragment
        TextView phoneTextView = (TextView) root.findViewById(R.id.linkPhoneNumber);
        phoneTextView.setText(restaurant.getPhone());

        // Display the restaurant address in the fragment
        TextView addressTextView = (TextView) root.findViewById(R.id.linkAddress);
        addressTextView.setText(restaurant.getAddress());

        // Display the restaurant website in the fragment
        TextView websiteTextView = (TextView) root.findViewById(R.id.linkWebsite);
        websiteTextView.setText(restaurant.getWebsite());

        // Display the restaurant tags in the fragment
        TextView tagsTextView = (TextView) root.findViewById(R.id.textViewTags);
        tagsTextView.setText("Tags: " + restaurant.getTagsString());

        return root;
    }
}
