package com.cse442.createteamname.util.query;

import com.cse442.createteamname.restaurant.Restaurant;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class RestaurantQueryTool extends QueryTool {

    private static final String LOCATION = API_URL + "restaurants/query.php";
    private static final String INC = "tags[]", EXC = "exc[]";
    private static final String ID = "id", NAME = "name", ADDRESS = "address", PHONE = "phone", HOURS = "hours",
            DESCRIPTION = "description", WEB = "website", LAT = "lat", LON = "lon";

    public static ArrayList<Restaurant> query(String[] inc, String[] exc){
        ArrayList<Restaurant> restaurants = new ArrayList<>();
        String url = getFullUrl(LOCATION, INC, inc);
        url = getFullUrl(url, EXC, exc);

        try {
            // Get the JSON array from the query
            JSONArray jsonArray = getJSONArray(url);

            // Loop through the JSON objects and add them to the restaurant list
            for (int i = 0; i < jsonArray.length(); ++i){
                JSONObject obj = jsonArray.getJSONObject(i);
                int id = obj.getInt(ID);
                restaurants.add(new Restaurant(id, obj.getString(NAME), obj.getString(ADDRESS), obj.getString(PHONE),
                        obj.getString(WEB), obj.getString(DESCRIPTION), obj.getString(HOURS), obj.getDouble(LAT), obj.getDouble(LON), TagQueryTool.query(id+"")));

            }
        } catch (ExecutionException e) {
            // Does not need to be handled. Empty JSON
        } catch (InterruptedException e) {
            // Does not need to be handled. Empty JSON
        } catch (JSONException e) {
            // Does not need to be handled
        }

        return restaurants;
    }
}
