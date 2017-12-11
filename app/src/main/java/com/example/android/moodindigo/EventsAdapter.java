package com.example.android.moodindigo;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.moodindigo.data.EventDetailResponse;
import com.example.android.moodindigo.data.GenresResponse;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by mrunz on 22/9/17.
 */

public class EventsAdapter extends RecyclerView.Adapter<EventsAdapter.myViewHolder>{



    public static class myViewHolder extends RecyclerView.ViewHolder{

        public myViewHolder(View itemView) {
            super(itemView);


        }




    }

    public EventsAdapter(){

    }

    @Override
    public  EventsAdapter.myViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View itemView = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.eventitem, parent,false);
        EventsAdapter.myViewHolder vh = new EventsAdapter.myViewHolder(itemView);



        return vh;
    }

    @Override
    public void onBindViewHolder(final EventsAdapter.myViewHolder holder, int position){

    }

    @Override
    public int getItemCount(){
        return 10;
    }
}
