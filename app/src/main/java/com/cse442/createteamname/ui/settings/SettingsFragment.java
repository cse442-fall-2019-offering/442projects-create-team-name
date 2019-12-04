package com.cse442.createteamname.ui.settings;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.cse442.createteamname.R;

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
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.nio.Buffer;
import java.util.ArrayList;

public class SettingsFragment extends Fragment {

    private SettingsViewModel settingsViewModel;
    private static ArrayList<String> dislikes;
    private DislikesAdapter dislikesAdapter;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             final ViewGroup container, Bundle savedInstanceState) {
        settingsViewModel =
                ViewModelProviders.of(this).get(SettingsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_settings, container, false);
        final TextView textView = root.findViewById(R.id.text_dashboard);
        settingsViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });

        dislikes = new ArrayList<String>();
        dislikes = loadFIle(getView());


        super.onCreate(savedInstanceState);
        //setContentView(R.layout.fragment_settings);
        recyclerView = (RecyclerView) root.findViewById(R.id.ds_results);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        recyclerView.setHasFixedSize(true);

        // use a linear layout manager
        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        // specify an adapter (see also next example)
        mAdapter = new DislikesAdapter(getContext(), dislikes);
        recyclerView.setAdapter(mAdapter);

        final TextView dislike = (TextView) root.findViewById(R.id.adddislike);
        Button add_dislike = (Button) root.findViewById(R.id.button);


        add_dislike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tag = dislike.getText().toString().toLowerCase();
                if (tag != "") {
                    if (dislikes.size() < 5) {
                        dislikes.add(tag);


                        saveFile(view, dislikes);
                        dislikes = loadFIle(view);

                        mAdapter = new DislikesAdapter(getContext(), dislikes);
                        recyclerView.setAdapter(mAdapter);
                    } else {
                        Toast.makeText(getActivity(), "Remove a tag to add a new one.", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getActivity(), "Try adding some text.", Toast.LENGTH_SHORT).show();
                }
            }
        });
        return root;
    }

    public ArrayList<String> loadFIle(View v){
        ArrayList<String> arr = new ArrayList<String>();
        String line;

        FileInputStream fis = null;
        try{
            fis = getContext().openFileInput("dislikes.txt");
            BufferedReader br = new BufferedReader(new InputStreamReader(fis));
            while((line = br.readLine()) != null){
                arr.add(line);
            }
            br.close();
            fis.close();
            Toast.makeText(getActivity(), "" + arr, Toast.LENGTH_SHORT).show();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return arr;
    }

    public void saveFile(View v, ArrayList<String> disList) {
        try {
            FileOutputStream fos = getContext().openFileOutput("dislikes.txt", Context.MODE_PRIVATE);
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));
            for(String str: disList){
                bw.append(str + System.lineSeparator());
            }
            bw.close();
            fos.close();
            //Toast.makeText(getActivity(), "Saved to " + getContext().getFilesDir() + "/dislikes.txt", Toast.LENGTH_LONG).show();
        } catch(Exception e){
            e.printStackTrace();
        }
    }
}