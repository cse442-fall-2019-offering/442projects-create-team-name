package com.cse442.createteamname.ui.settings;

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

import java.util.ArrayList;

public class SettingsFragment extends Fragment {

    private SettingsViewModel settingsViewModel;
    private static ArrayList<String> dislikes;
    private DislikesAdapter dislikesAdapter;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
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


//        dislikes = new ArrayList<String>();
//
//        RecyclerView list = (RecyclerView) root.findViewById(R.id.ds_results);
//
//
        final TextView dislike = (TextView) root.findViewById(R.id.adddislike);
        Button add_dislike = (Button) root.findViewById(R.id.button);
        dislikes = new ArrayList<String>();
//        DislikesAdapter dislikesAdapter = new DislikesAdapter(getContext(), dislikes);
//        recyclerView.setAdapter(dislikesAdapter);
//
//        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
//        list.setLayoutManager(layoutManager);
//        dislikesAdapter = new DislikesAdapter(getContext(), dislikes);
//        list.addItemDecoration(new DividerItemDecoration(list.getContext(), layoutManager.getOrientation()));
//        list.setAdapter(dislikesAdapter);

        add_dislike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tag = dislike.getText().toString().toLowerCase();
                if(dislikes.size() < 5){
                    dislikes.add(tag);
                    mAdapter = new DislikesAdapter(getContext(), dislikes);
                    recyclerView.setAdapter(mAdapter);
                }
                else{
                    Toast.makeText(getActivity(), "Remove a tag to add a new one.", Toast.LENGTH_SHORT).show();
                }
            }
        });
        return root;
    }
}