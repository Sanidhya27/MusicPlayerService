package com.example.android.moodindigo.Fragments;

import android.content.Context;
import android.content.SharedPreferences;

import android.content.Intent;

import android.icu.lang.UProperty;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.moodindigo.DownloadImage;
import com.example.android.moodindigo.EventsAdapter;
import com.example.android.moodindigo.MainActivity;
import com.example.android.moodindigo.MediaPlayerService;
import com.example.android.moodindigo.R;
import com.example.android.moodindigo.ScheduleEventsAdapter;
import com.example.android.moodindigo.UpcomingEventsAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by owais on 05/07/17.
 */

public class UpcomingEventsFragment extends Fragment {

    CircleImageView profimage;
    RecyclerView events;
    UpcomingEventsAdapter eventsAdapter;

    SharedPreferences prefs;
    String image,name,mi_number;
    SharedPreferences.Editor editor;
    String userdetails="user details";

    int x = 0;
    public UpcomingEventsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        // Inflate the layout for this fragment
//        container.removeAllViews();
//        return inflater.inflate(R.layout.fragment_upcoming_events, container, false);
//    }
@Override
public View onCreateView(LayoutInflater inflater, ViewGroup container,
                         Bundle savedInstanceState) {
    // Inflate the layout for this fragment
    //container.removeAllViews();

    // Inflate the layout for this fragment
    View rootView = inflater.inflate(R.layout.fragment_upcoming_events, container, false);
    profimage=(CircleImageView) rootView.findViewById(R.id.imageView);

    prefs=getContext().getSharedPreferences(userdetails, Context.MODE_PRIVATE);
    editor=prefs.edit();

    if(prefs!=null)
    {
        name=prefs.getString("name",null);
        image=prefs.getString("image",null);
        mi_number=prefs.getString("mi number",null);
    }
    else
    {
        name="User Name";
        image=null;
        mi_number="MI number";
    }

if(image!=null){

    new DownloadImage(profimage,getContext()).execute(image);

}
    TextView profname=(TextView) rootView.findViewById(R.id.name);
    profname.setText(name);

    TextView mi_no=(TextView) rootView.findViewById(R.id.mi_number);
    mi_no.setText(mi_number);

    eventsAdapter=new UpcomingEventsAdapter(1,getContext());
    events=(RecyclerView) rootView.findViewById(R.id.events);
    events.setAdapter(eventsAdapter);
    events.setLayoutManager(new LinearLayoutManager(getContext()));


    //music float button
    final FloatingActionButton fab = (FloatingActionButton) rootView.findViewById(R.id.upcoming_music_button);

    fab.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            x = (x + 1) % 2;
            MediaPlayerService obj=new MediaPlayerService();
            MainActivity obj2=new MainActivity();
            obj2.playAudio("hello");
            if (x == 1) {
                fab.setImageResource(R.drawable.ic_pause);
                //fab.setBackgroundResource(R.drawable.play);
            }
//                    mediaPlayer.start();}
            if (x == 0) {   //fab.setBackgroundResource(R.drawable.ic_launcher_background);
                obj.pauseMedia();
                fab.setImageResource((R.drawable.ic_action_name));
//                    mediaPlayer.pause();
            }
        }

    });
    return rootView;

}

}
