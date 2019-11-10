package com.cse442.createteamname.util.query;

import android.net.Uri;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.concurrent.ExecutionException;

public class QueryTool {

    private final static String API_URL = "https://www-student.cse.buffalo.edu/CSE442-542/2019-Fall/cse-442v/api/";

    public static JSONArray getJSONArray(String url) throws ExecutionException, InterruptedException, JSONException {
        // Retrieve the JSON String
        String json = new DownloadJSON().execute(url).get();
        // Parse the JSON String
        return new JSONArray(json);
    }

    public static String getFullUrl(String location, String param_var, String[] query_params){
        Uri.Builder builder = Uri.parse(API_URL + location).buildUpon();

        for (String param : query_params) {
            builder.appendQueryParameter(param_var, param.replaceAll("\\s+", ""));
        }

        return builder.build().toString();
    }
}
