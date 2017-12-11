package com.example.android.moodindigo;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.provider.CalendarContract;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.moodindigo.EventsDatabase.DatabaseHelper;
import com.example.android.moodindigo.EventsDatabase.Event;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by mrunz on 8/12/17.
 */

public class ScheduleEventsAdapter extends RecyclerView.Adapter<ScheduleEventsAdapter.myViewHolder> {
    DatabaseHelper db;
    List<Event> events;

    public static class myViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.name)
        TextView name;
        @BindView(R.id.description)
        TextView description;
        @BindView(R.id.time)
        TextView time;
        @BindView(R.id.venue)
        TextView venue;
        @BindView(R.id.going)
        ImageView going;


        public myViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(itemView);
            going = (ImageView) itemView.findViewById(R.id.going);
            name = (TextView) itemView.findViewById(R.id.name);
            description = (TextView) itemView.findViewById(R.id.description);
            venue = (TextView) itemView.findViewById(R.id.venue);
            time = (TextView) itemView.findViewById(R.id.time);


        }
    }

    public ScheduleEventsAdapter(String type, int day, Context context) {
        db = new DatabaseHelper(context);
        events = db.getOrderedEventsByTag(type, day);

    }

    @Override
    public myViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.eventitem, parent, false);
        myViewHolder vh = new myViewHolder(itemView);


        return vh;
    }

    @Override
    public void onBindViewHolder(final myViewHolder holder, final int position) {


        holder.name.setText(events.get(position).getName());
        holder.description.setText(events.get(position).getDescription());
        holder.venue.setText("Venue: "+events.get(position).getVenue());
        String time = events.get(position).getTime_start();
        if (time.length() == 4) {
            holder.time.setText("Start: "+time.substring(0, 2) + ":" + time.substring(2));
        } else {
            holder.time.setText("Start: "+time.charAt(0) + ":" + time.substring(1));
        }
        final int ifGoing = events.get(position).getGoing();
        if (ifGoing == 1) {
            holder.going.setColorFilter(Color.RED, PorterDuff.Mode.SRC_IN);
        } else {
            holder.going.setColorFilter(null);
        }
        holder.going.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (events.get(position).getGoing() == 1) {
                    events.get(position).setGoing(0);
                    db.updateEvent(events.get(position));

                    holder.going.setColorFilter(null);

                } else {
                    events.get(position).setGoing(1);
                    holder.going.setColorFilter(Color.RED, PorterDuff.Mode.SRC_IN);
                    db.updateEvent(events.get(position));
                }
            }
        });
    }


    @Override
    public int getItemCount() {
        try{
            Log.i("size",Integer.toString(events.size()));
            return events.size();
        }
        catch(Exception e){
            Log.i("size",Integer.toString(0));
            return 0;
        }
    }
}


