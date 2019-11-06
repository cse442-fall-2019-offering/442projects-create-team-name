package com.cse442.createteamname.ui.settings;

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

import com.cse442.createteamname.R;

public class SettingsFragment extends Fragment {

    private SettingsViewModel settingsViewModel;

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

        final TextView dislike = (TextView) root.findViewById(R.id.adddislike);
        Button add_dislike = (Button) root.findViewById(R.id.button);
        add_dislike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tag = dislike.getText().toString().toLowerCase();
                //Toast.makeText(getActivity(), tag, Toast.LENGTH_SHORT).show();
            }
        });

        return root;
    }
}