package com.example.asus.myapplication;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Date;
import java.util.List;

/**
 * Created by asus on 23.07.2017.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    public List<Event> events;
    Activity activity;
    DbManager dbManager;
    AlertDialog alertDialog;
    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView text, date;
        public CardView cardView;
        public ViewHolder(View v) {
            super(v);
            text = (TextView) v.findViewById(R.id.text);
            date = (TextView) v.findViewById(R.id.date);

            cardView = (CardView) v.findViewById(R.id.cardView);

        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public MyAdapter(List<Event> events, DbManager dbManager, Activity activity) {
        this.events = events;
        this.dbManager = dbManager;
        this.activity = activity;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                   int viewType) {
        // create a new view
        View v =  LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card, parent, false);

        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        final Event currentEvent = events.get(events.size() - 1 - position);
        holder.text.setText(currentEvent.getText());
        String date = Util.getSmartTime(new Date(System.currentTimeMillis() - currentEvent.getDate()));
        holder.date.setText(date);

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(currentEvent);
            }
        });

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return events.size();
    }

    public void showDialog(final Event event){
        String[] items = {"Edit", "Delete"};
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which){
                    case 0 : edit(event);break;
                    case 1 : delete(event);break;
                }
            }
        });

        alertDialog = builder.create();
        alertDialog.show();

    }

    public void edit(Event event){
        alertDialog.dismiss();
    }

    public void delete(Event event){
        alertDialog.dismiss();
        dbManager.setTrash(event.getId(), true);
        events.remove(event);
        notifyDataSetChanged();


        View parent = activity.findViewById(android.R.id.content);
        Snackbar.make(parent, R.string.event_delete_message, Snackbar.LENGTH_SHORT)
                .setAction("Action", null).show();
    }
}
