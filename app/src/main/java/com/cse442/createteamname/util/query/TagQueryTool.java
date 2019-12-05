package com.cse442.createteamname.util.query;

import android.util.Log;

import com.cse442.createteamname.restaurant.Restaurant;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.ExecutionException;

public class TagQueryTool extends QueryTool {

    private static final String LOCATION = API_URL + "tags/query.php";
    private static final String REST_ID = "rest_id[]";

    public static String[] query(String... ids){
        ArrayList<String> tags = new ArrayList<>();
        String url = getFullUrl(LOCATION, REST_ID, ids);

        try {
            // Get the JSON array from the query
            JSONArray jsonArray = getJSONArray(url);

            // Loop through the JSON objects and add them to the restaurant list
            for(int i = 0; i < jsonArray.length(); ++i){
                tags.add(jsonArray.getString(i));
            }
        } catch (ExecutionException e) {
            // Does not need to be handled. Empty JSON
        } catch (InterruptedException e) {
            // Does not need to be handled. Empty JSON
        } catch (JSONException e) {
            // Does not need to be handled
        }
        
        Object[] objArr = tags.toArray();
        String[] ret = Arrays.copyOf(objArr, objArr.length, String[].class);
        return ret;
    }
}
