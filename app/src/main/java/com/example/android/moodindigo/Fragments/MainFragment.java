package com.example.android.moodindigo.Fragments;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.support.v7.widget.Toolbar;
import android.view.ViewParent;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.moodindigo.AboutMIActivity;
import com.example.android.moodindigo.ContactsActivity;
import com.example.android.moodindigo.DownloadImage;
import com.example.android.moodindigo.LoginActivity;
import com.example.android.moodindigo.MainActivity;
import com.example.android.moodindigo.R;
import com.example.android.moodindigo.RetrofitClass;
import com.example.android.moodindigo.SearchInterface;
import com.example.android.moodindigo.ViewPagerAdapter;
import com.example.android.moodindigo.data.NewsResponse;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginManager;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by owais on 05/07/17.
 */


public class MainFragment extends Fragment {

    View view;
    ViewPager viewPager;
    private FragmentActivity myContext;
    //Bottom Navigation Bar
    BottomNavigationView bottomNavigationView;
    UpcomingEventsFragment upcomingEventsFragment, upcomingEventsFragment2;
    MenuItem prevMenuItem;
    MapFragment mapFragment;
    DrawerLayout mDrawerLayout;
    int size;
    ScheduleDaysFragment scheduleDaysFragment;
    List<NewsResponse> responses = new ArrayList<>();

    public MainFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        container.removeAllViews();

        view = inflater.inflate(R.layout.fragment_main, container, false);


        //
        // Bottom Navigation Bar
        //


        //Initializing viewPager
        viewPager = (ViewPager) view.findViewById(R.id.viewpager);





        //Initializing the bottomNavigationView
        bottomNavigationView = (BottomNavigationView) view.findViewById(R.id.bottom_navigation);


        bottomNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {

                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.action_map:
                                viewPager.setCurrentItem(0);
                                break;
                            case R.id.action_events:
                                viewPager.setCurrentItem(1);
                                break;
                            case R.id.action_developers:
                                viewPager.setCurrentItem(2);
                                break;
                        }
                        return false;
                    }
                });

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (prevMenuItem != null) {
                    prevMenuItem.setChecked(false);
                    if(position ==2) {
                        FloatingActionButton fab1 = (FloatingActionButton) view.findViewById(R.id.schedule_music_button);
                        fab1.show();
                    }
                    if(position == 1) {
                        FloatingActionButton fab2 = (FloatingActionButton) view.findViewById(R.id.upcoming_music_button);
                        fab2.show();
                    }
                    if(position == 0) {
                        FloatingActionButton fab3 = (FloatingActionButton) view.findViewById(R.id.music_map_button);
                        fab3.show();
                    }
                } else {
                    bottomNavigationView.getMenu().getItem(1).setChecked(false);
                }
                Log.d("page", "onPageSelected: " + position);
                bottomNavigationView.getMenu().getItem(position).setChecked(true);
                prevMenuItem = bottomNavigationView.getMenu().getItem(position);

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

       /*  //Disable ViewPager Swipe
       viewPager.setOnTouchListener(new View.OnTouchListener()
        {
            @Override
            public boolean onTouch(View v, MotionEvent event)
            {
                return true;
            }
        });
        */

        ViewPagerAdapter adapter = new ViewPagerAdapter(getActivity().getSupportFragmentManager());
        mapFragment = new MapFragment();
        upcomingEventsFragment = new UpcomingEventsFragment();
        scheduleDaysFragment=new ScheduleDaysFragment();


        adapter.addFragment(mapFragment);
        adapter.addFragment(upcomingEventsFragment);
        adapter.addFragment(scheduleDaysFragment);
        viewPager.setAdapter(adapter);
        viewPager.setCurrentItem(1);
//    @Override
//    public void onBackPressed() {
//        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
//        if (drawer.isDrawerOpen(GravityCompat.START)) {
//            drawer.closeDrawer(GravityCompat.START);
//        } else {
//            super.onBackPressed();
//        }
//    }


//        Bundle bundle=getArguments();
//        size=bundle.getInt("size");
//        Log.d("size223", String.valueOf(size));
//        for(int j=0;j<size;j++){
//            NewsResponse newsResponse=new Gson().fromJson(bundle.getString("List" + j),NewsResponse.class);
//            Log.d("name",newsResponse.getName());
//            responses.add(newsResponse);
//        }
//
//        TabLayout tabLayout = (TabLayout) view.findViewById(R.id.tabs);
//        tabLayout.addTab(tabLayout.newTab().setText("News"));
//        tabLayout.addTab(tabLayout.newTab().setText("Multicity"));
//        tabLayout.addTab(tabLayout.newTab().setText("Events"));
//
//        viewPager = (ViewPager) view.findViewById(R.id.pager);
//        Log.d("size223", String.valueOf(responses.size()));
//        viewPager.setAdapter(new PagerAdapter(getFragmentManager(), tabLayout.getTabCount(),responses));
//        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
//        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
//            @Override
//            public void onTabSelected(TabLayout.Tab tab) {
//                viewPager.setCurrentItem(tab.getPosition());
//            }
//
//            @Override
//            public void onTabUnselected(TabLayout.Tab tab) {
//
//            }
//
//            @Override
//            public void onTabReselected(TabLayout.Tab tab) {
//                viewPager.setCurrentItem(tab.getPosition());
//
//            }
//        });
        return view;
    }

    @Override
    public void onAttach(Context context) {
        myContext = (FragmentActivity) context;
        super.onAttach(context);

    }
}
