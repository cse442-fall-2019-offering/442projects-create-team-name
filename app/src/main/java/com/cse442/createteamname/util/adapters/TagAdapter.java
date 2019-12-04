package com.cse442.createteamname.util.adapters;

import android.content.Context;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.widget.Filter;
import android.widget.SimpleCursorAdapter;

import java.util.ArrayList;


public class TagAdapter extends SimpleCursorAdapter {

    private Cursor cursor;

    public TagAdapter(Context context, int layout, Cursor c, String[] from, int[] to) {
        super(context, layout, c, from, to);
        this.cursor = c;
    }

    @Override
    public Filter getFilter() {
        return tagFilter;
    }

    private Filter tagFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            FilterResults results = new FilterResults();

            if (charSequence != null){
                // Initialize filtered tags array
                ArrayList<String> resultValues = new ArrayList<>();
                // Get the column the tags are in
                int tagIndex = cursor.getColumnIndex("tag");
                // Get the string after the last column
                String qString = charSequence.toString();
                String filter = qString.substring(qString.lastIndexOf(',')+1).replaceAll("\\s+", "");

                // If any of the tags start with the filter, add it to the filter results
                cursor.moveToFirst();
                while(!cursor.isAfterLast()){
                    String tag = cursor.getString(tagIndex);

                    if(tag.startsWith(filter)) {
                        resultValues.add(tag);
                    }
                    cursor.moveToNext();
                }
                results.values = resultValues;
                results.count = resultValues.size();
            }
            return results;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            ArrayList<String> tags = (ArrayList<String>)filterResults.values;
            if(filterResults != null && filterResults.count > 0) {
                // Update the cursor with the filtered results
                MatrixCursor matrixCursor = new MatrixCursor(new String[]{"_id", "tag"});
                int id = 0;
                for (String tag : tags) {
                    matrixCursor.addRow(new String[]{Integer.toString(id++), tag});
                }
                changeCursor(matrixCursor);
                notifyDataSetChanged();
            }
        }
    };
}
