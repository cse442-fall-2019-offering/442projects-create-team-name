package com.cse442.createteamname.util.gps;

import com.cse442.createteamname.restaurant.Restaurant;

import java.util.ArrayList;

public class DistanceFilter {
    public static ArrayList<Restaurant> filterDistance(LocationUtil locationUtil, ArrayList<Restaurant> restaurants, double maxDist){
        ArrayList<Restaurant> filtered = new ArrayList<>();

        for(Restaurant restaurant : restaurants){
            restaurant.calculateDistance(locationUtil);
            if(restaurant.getDistance() <= maxDist){
                int i = 0;
                while(i < filtered.size() && filtered.get(i).getDistance() < restaurant.getDistance()){
                    ++i;
                }
                filtered.add(i, restaurant);
            }
        }

        return filtered;
    }
}
