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
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.android.moodindigo.EventsDatabase.DatabaseHelper;
import com.example.android.moodindigo.EventsDatabase.Event;

import java.util.ArrayList;
import java.util.Date;
import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by mrunz on 8/12/17.
 */

public class UpcomingEventsAdapter extends RecyclerView.Adapter<UpcomingEventsAdapter.myViewHolder> {
    DatabaseHelper db;
    List<Event> events;

    List<Event> raw_events;


    public static class myViewHolder extends RecyclerView.ViewHolder {

        TextView name;
        TextView description;
        TextView time;
        TextView venue;
        ImageView going;
        LinearLayout layout;


        public myViewHolder(View itemView) {
            super(itemView);
            going = (ImageView) itemView.findViewById(R.id.going);
            name = (TextView) itemView.findViewById(R.id.name);
            description = (TextView) itemView.findViewById(R.id.description);
            venue = (TextView) itemView.findViewById(R.id.venue);
            time = (TextView) itemView.findViewById(R.id.time);
            layout = (LinearLayout) itemView.findViewById(R.id.layout);


        }
    }

    public UpcomingEventsAdapter(int day, Context context) {
        db = new DatabaseHelper(context);
        events = new ArrayList<>();
        Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int min = c.get(Calendar.MINUTE);
        int current_time_string, time_after_hour;
        if (min < 10) {
            current_time_string = Integer.parseInt(String.valueOf(hour) + "0" + String.valueOf(min));
            if(hour!=23){
                time_after_hour = Integer.parseInt(String.valueOf(hour + 1) + "0" + String.valueOf(min));
            }
            else {
                time_after_hour = Integer.parseInt("2400");
            }
        } else {
            current_time_string = Integer.parseInt(String.valueOf(hour) + String.valueOf(min));
            if(hour!=23){
                time_after_hour = Integer.parseInt(String.valueOf(hour + 1)  + String.valueOf(min));
            }
            else {
                time_after_hour = Integer.parseInt("2400");
            }
        }

        raw_events = db.getOrderedEventsByDay(day);
        for (Event event : raw_events) {
            String time = event.getTime_start();
            Log.i("current", String.valueOf(current_time_string));
            Log.i("+1", String.valueOf(time_after_hour));
            Log.i("time", time);
            if (current_time_string <= Integer.parseInt(time) && Integer.parseInt(time) <= time_after_hour) {
                events.add(event);
            }
        }
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
        if ((position == 0)) {
            holder.layout.setVisibility(View.GONE);
        } else {
            String time = events.get(position - 1).getTime_start();

            holder.layout.setVisibility(View.VISIBLE);
            holder.name.setText(events.get(position - 1).getName());
            holder.description.setText(events.get(position - 1).getDescription());
            holder.venue.setText("Venue: " + events.get(position - 1).getVenue());

            if (time.length() == 4) {
                holder.time.setText("Start: " + time.substring(0, 2) + ":" + time.substring(2));
            } else {
                holder.time.setText("Start: " + time.charAt(0) + ":" + time.substring(1));
            }
            final int ifGoing = events.get(position - 1).getGoing();
            if (ifGoing == 1) {
                holder.going.setColorFilter(Color.RED, PorterDuff.Mode.SRC_IN);
            } else {
                holder.going.setColorFilter(null);
            }
            holder.going.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (events.get(position - 1).getGoing() == 1) {
                        events.get(position - 1).setGoing(0);
                        db.updateEvent(events.get(position - 1));

                        holder.going.setColorFilter(null);

                    } else {
                        events.get(position - 1).setGoing(1);
                        holder.going.setColorFilter(Color.RED, PorterDuff.Mode.SRC_IN);
                        db.updateEvent(events.get(position - 1));
                    }
                }
            });
        }
    }


    @Override
    public int getItemCount() {
        try {
            Log.i("size", Integer.toString(events.size()));
            return events.size() + 1;
        } catch (Exception e) {
            Log.i("size", Integer.toString(0));
            return 0;
        }
    }
}


