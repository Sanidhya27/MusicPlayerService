package com.example.android.moodindigo.Fragments;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.moodindigo.DownloadImage;
import com.example.android.moodindigo.EventsActivity;
import com.example.android.moodindigo.MainActivity;
import com.example.android.moodindigo.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class ScheduleDaysFragment extends Fragment {
    int x = 0;

    public ScheduleDaysFragment() {
        // Required empty public constructor
    }
    TextView day1;
    TextView day2;
    TextView day3;
    TextView day4;

    CircleImageView profimage;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view=inflater.inflate(R.layout.fragment_schedule_days, container, false);


        ButterKnife.bind(view);

        day1=(TextView) view.findViewById(R.id.day1);
        day2=(TextView) view.findViewById(R.id.day2);
        day3=(TextView) view.findViewById(R.id.day3);
        day4=(TextView) view.findViewById(R.id.day4);

        day1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(), EventsActivity.class);
                intent.putExtra("day",1);
                startActivity(intent);
            }
        });
        day2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(), EventsActivity.class);
                intent.putExtra("day",2);
                startActivity(intent);
            }
        });
        day3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(), EventsActivity.class);
                intent.putExtra("day",3);
                startActivity(intent);
            }
        });
        day4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(), EventsActivity.class);
                intent.putExtra("day",4);
                startActivity(intent);
            }
        });

        //music float button
        final FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.schedule_music_button);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                x = (x + 1) % 2;
                if (x == 1) {
                    fab.setImageResource(R.drawable.ic_pause);
                    //fab.setBackgroundResource(R.drawable.play);
                }
//                    mediaPlayer.start();}
                if (x == 0) {   //fab.setBackgroundResource(R.drawable.ic_launcher_background);
                    fab.setImageResource((R.drawable.ic_action_name));
//                    mediaPlayer.pause();
                }
            }

        });

        return view;

    }
}
