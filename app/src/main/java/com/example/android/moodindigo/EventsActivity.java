package com.example.android.moodindigo;

import android.provider.CalendarContract;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.android.moodindigo.EventsDatabase.DatabaseHelper;
import com.example.android.moodindigo.EventsDatabase.Event;
import com.example.android.moodindigo.ServerConnection.GsonModels;

import java.util.List;

public class EventsActivity extends AppCompatActivity {

    List<Event> events;
    DatabaseHelper db;
    int day;
    Bundle bundle;
    TabLayout tabLayout;
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events);

        bundle=getIntent().getExtras();
        day=bundle.getInt("day");


        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.addTab(tabLayout.newTab().setText("Competitions"));
        tabLayout.addTab(tabLayout.newTab().setText("Concerts"));
        tabLayout.addTab(tabLayout.newTab().setText("Proshows"));
        tabLayout.addTab(tabLayout.newTab().setText("Informals"));
        tabLayout.addTab(tabLayout.newTab().setText("Workshops"));
        tabLayout.addTab(tabLayout.newTab().setText("Arts and Ideas"));

        viewPager = (ViewPager) findViewById(R.id.viewpager);

        viewPager.setAdapter(new TypeTabPagerAdapter(getSupportFragmentManager(),day,EventsActivity.this));
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());

            }
        });

    }





}
