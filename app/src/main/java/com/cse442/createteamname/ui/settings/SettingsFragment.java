package com.cse442.createteamname.ui.settings;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.cse442.createteamname.R;
import com.cse442.createteamname.util.FileFunctions;
import com.cse442.createteamname.util.adapters.DislikesAdapter;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public class SettingsFragment extends Fragment {

    private SettingsViewModel settingsViewModel;
    private ArrayList<String> dislikes;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private String dislikesFile, maxDistanceFile;
    private int maxDistance;

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

        dislikesFile = getContext().getFilesDir() + "/dislikes.txt";
        maxDistanceFile = getContext().getFilesDir() + "/maxDistance.txt";

        dislikes = FileFunctions.getDislikes(dislikesFile);
        maxDistance = FileFunctions.getMaxDistance(maxDistanceFile);

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
        mAdapter = new DislikesAdapter(getContext(), dislikes, dislikesFile);
        recyclerView.setAdapter(mAdapter);

        final TextView dislike = (TextView) root.findViewById(R.id.adddislike);
        Button add_dislike = (Button) root.findViewById(R.id.button);


        add_dislike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tag = dislike.getText().toString().toLowerCase();
                if (tag != "") {
                    dislikes.add(tag);

                    FileFunctions.saveFile(dislikes, dislikesFile);
                    dislikes = FileFunctions.getDislikes(dislikesFile);

                    mAdapter = new DislikesAdapter(getContext(), dislikes, dislikesFile);
                    recyclerView.setAdapter(mAdapter);
                } else {
                    Toast.makeText(getActivity(), "Try adding some text.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Get the max distance field and restore the value
        final TextView maxDistanceView = (TextView) root.findViewById(R.id.max_distance);
        maxDistanceView.setText(FileFunctions.getMaxDistance(maxDistanceFile) + "");

        // Set the listener for the button to save the max distance
        Button saveMaxDistance = (Button) root.findViewById(R.id.save_max_distance);
        saveMaxDistance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String dist = maxDistanceView.getText().toString();
                if(dist != "") {
                    ArrayList<String> distance = new ArrayList<>();
                    distance.add(dist);
                    FileFunctions.saveFile(distance, maxDistanceFile);
                } else {
                    ArrayList<String> distance = new ArrayList<>();
                    distance.add("10");
                    FileFunctions.saveFile(distance, maxDistanceFile);
                }
            }
        });

        return root;
    }


}