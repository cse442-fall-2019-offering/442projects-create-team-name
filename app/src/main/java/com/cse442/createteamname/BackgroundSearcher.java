package com.cse442.createteamname;

import android.content.Context;
import android.os.AsyncTask;

import java.io.IOException;
import java.net.MalformedURLException;

public class BackgroundSearcher extends AsyncTask<String,Void,String> {
    Context context;
    BackgroundSearcher (Context ctx) {
        context = ctx;
    }

    @Override
    protected String doInBackground(String... params) {
        // TODO: Finish Method or Create Stub. Insert into try-catch if finishing
        String type = params[0];
        if (type.equals("tags")) {
//            try{

//            } catch(MalformedURLException e) {
//                e.printStackTrace();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
        }
        else if (type.equals("food")) {
//            try{

//            } catch(MalformedURLException e) {
//                e.printStackTrace();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
        }
        return null;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(String aVoid) {
        super.onPostExecute(aVoid);
    }

    @Override
    protected void onProgressUpdate(Void... values){
        super.onProgressUpdate(values);
    }

}
