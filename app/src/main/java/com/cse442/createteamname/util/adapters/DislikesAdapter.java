package com.cse442.createteamname.util.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cse442.createteamname.R;
import com.cse442.createteamname.util.FileFunctions;

import java.util.ArrayList;

public class DislikesAdapter extends RecyclerView.Adapter<DislikesAdapter.MyViewHolder> {

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView textView;
        public MyViewHolder(@NonNull View v) {
            super(v);
            textView = (TextView) v.findViewById(R.id.dislikeText);
        }
    }

    private Context context;
    private ArrayList<String> dislikes;
    private FileFunctions ff;
    private String filePath;

    // Provide a suitable constructor (depends on the kind of dataset)
    public DislikesAdapter(Context context, ArrayList<String> dislike_list, String p) {
        this.context = context;
        this.dislikes = dislike_list;
        this.filePath = p;
        this.ff = new FileFunctions();
    }

    // Create new views (invoked by the layout manager)
    @NonNull
    @Override
    public DislikesAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // create a new view
        View dis_view = inflater.inflate(R.layout.dislikes_item, parent, false);

        MyViewHolder vh = new MyViewHolder(dis_view);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(@NonNull final DislikesAdapter.MyViewHolder holder, final int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        TextView textView = holder.textView;
        textView.setText(dislikes.get(position));

        holder.itemView.findViewById(R.id.deleteButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int pos = holder.getAdapterPosition();
                dislikes.remove(pos);
                notifyItemRemoved(pos);
                notifyItemRangeChanged(pos, dislikes.size());
                ff.saveFile(dislikes, filePath);
            }
        });

        holder.textView.setText(dislikes.get(position));

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        if(dislikes != null){
            return dislikes.size();
        }
        return 0;
    }
}

