package com.cse442.createteamname.util;

import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;

import com.cse442.createteamname.restaurant.Restaurant;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import javax.net.ssl.HttpsURLConnection;

public class QueryTool {

    private static final String API_URL = "https://www-student.cse.buffalo.edu/CSE442-542/2019-Fall/cse-442v/api/query.php";
    private static final String NAME = "name", ADDRESS = "address", PHONE = "phonenumber", HOURS = "hours",
                                DESCRIPTION = "description", WEB = "website", TAG1 = "tag1", TAG2 = "tag2", TAG3 = "tag3";

    public static ArrayList<Restaurant> queryTags(String... tags){
        class DownloadJSON extends AsyncTask<String, Void, String> {
            @Override
            protected String doInBackground(String... strings) {
                try {
                    String path = getUrlQuery(strings);
                    Log.d("TAG", path);
                    URL url = new URL(path);
                    HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
                    StringBuffer stringBuffer = new StringBuffer();
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                    String json;
                    while((json = bufferedReader.readLine()) != null) {
                        stringBuffer.append(json + '\n');
                    }
                    return stringBuffer.toString().trim();
                }
                catch (Exception e){
                    return "";
                }
            }

            private String getUrlQuery(String[] tags){
                Uri.Builder builder = Uri.parse(API_URL).buildUpon();

                for (int i = 0; i < tags.length; ++i) {
                    builder.appendQueryParameter("tags[]", tags[i].replaceAll("\\s+", ""));
                }

                return builder.build().toString();
            }
        }

        ArrayList<Restaurant> restaurants = new ArrayList<>();
        try {
            // Retrieve the JSON String
            String json = new DownloadJSON().execute(tags).get();
            // Parse the JSON String
            JSONArray jsonArray = new JSONArray(json);

            // Loop through the JSON objects and add them to the restaurant list
            for (int i = 0; i < jsonArray.length(); ++i){
                JSONObject obj = jsonArray.getJSONObject(i);
                restaurants.add(new Restaurant(obj.getString(NAME), obj.getString(ADDRESS), obj.getString(PHONE),
                        obj.getString(WEB), new String[]{obj.getString(TAG1), obj.getString(TAG2), obj.getString(TAG3)}));
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
