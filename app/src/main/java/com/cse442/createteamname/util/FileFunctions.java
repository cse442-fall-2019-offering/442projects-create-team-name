package com.cse442.createteamname.util;

import android.content.Context;
import android.view.View;
import android.widget.Toast;

import com.cse442.createteamname.ui.settings.SettingsFragment;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public class FileFunctions {

    public static ArrayList<String> getDislikes(String path){
        return loadFile(path);
    }

    public static int getMaxDistance(String path){
        ArrayList<String> vals = loadFile(path);
        if(vals.size() == 0){
            return 10;
        }
        return Integer.parseInt(vals.get(0));
    }

    private static ArrayList<String> loadFile(String path){
        ArrayList<String> arr = new ArrayList<String>();
        String line;

//        FileInputStream fis = null;
        File file = new File(path);
        try{
//            fis = openFileInput(file);
            BufferedReader br = new BufferedReader(new FileReader(file));
            while((line = br.readLine()) != null){
                arr.add(line);
            }
            br.close();
//            fis.close();
//            Toast.makeText(getActivity(), "" + arr, Toast.LENGTH_SHORT).show();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return arr;
    }

    public static void saveFile(ArrayList<String> disList, String path) {
        try {
            File file = new File(path);
//            FileOutputStream fos = sf.getContext().openFileOutput("dislikes.txt", Context.MODE_PRIVATE);
            BufferedWriter bw = new BufferedWriter(new FileWriter(file));
            for(String str: disList){
                bw.append(str + System.lineSeparator());
            }
            bw.close();
//            fos.close();
            //Toast.makeText(getActivity(), "Saved to " + getContext().getFilesDir() + "/dislikes.txt", Toast.LENGTH_LONG).show();
        } catch(Exception e){
            e.printStackTrace();
        }
    }
}
