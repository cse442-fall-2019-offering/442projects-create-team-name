package com.cse442.createteamname.ui.home;

import android.database.MatrixCursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.cse442.createteamname.R;
import com.cse442.createteamname.util.adapters.TagAdapter;
import com.cse442.createteamname.util.query.TagQueryTool;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;

    private ArrayList<String> tags;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             final ViewGroup container, Bundle savedInstanceState) {
        final View root = inflater.inflate(R.layout.fragment_home, container, false);

        root.findViewById(R.id.dont_know_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(root).navigate(R.id.action_home_to_results);
            }
        });

        tags = TagQueryTool.query();

        final SearchView iKnowBar = (SearchView)(root.findViewById(R.id.i_know_q));
        iKnowBar.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Bundle bundle = new Bundle();
                bundle.putString(getString(R.string.search_q_key), query);
                Navigation.findNavController(root).navigate(R.id.action_home_to_results, bundle);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        iKnowBar.setOnSuggestionListener(new SearchView.OnSuggestionListener() {
            @Override
            public boolean onSuggestionSelect(int i) {
                return false;
            }

            @Override
            public boolean onSuggestionClick(int i) {
                // Get the item suggestion selected
                MatrixCursor cursor = (MatrixCursor)iKnowBar.getSuggestionsAdapter().getItem(i);
                int tagIndex = cursor.getColumnIndex("tag");
                String tag = cursor.getString(tagIndex);
                // Append it to the end of the query string
                String query = iKnowBar.getQuery().toString();
                query = query.substring(0, query.lastIndexOf(',') + 1) + tag;
                iKnowBar.setQuery(query, false);
                // Keep the keyboard up
                return true;
            }
        });

        // Convert the Tag array to a cursor and set the suggestion adapter
        MatrixCursor matrixCursor = new MatrixCursor(new String[]{"_id", "tag"});
        String[] temp = new String[2];
        int id = 0;
        for (String tag : tags){
            temp[0] = Integer.toString(id++);
            temp[1] = tag;
            matrixCursor.addRow(temp);
        }
        iKnowBar.setSuggestionsAdapter(new TagAdapter(getContext(), R.layout.tag_item, matrixCursor, new String[]{"tag"}, new int[]{R.id.tag_suggestion}));

        return root;
    }
}