package com.example.android.moodindigo;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.app.FragmentTransaction;

import com.example.android.moodindigo.Fragments.FAQFragment;
import com.example.android.moodindigo.Fragments.NewzFragment;
import com.example.android.moodindigo.data.NewsResponse;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by owais on 05/07/17.
 */

public class PagerAdapter extends FragmentPagerAdapter {

    int mNumOfTabs; // number of tab variable for viewPager
    List<NewsResponse> responses=new ArrayList<>();


    //PagerAdapter Constructor
    public PagerAdapter(FragmentManager fm, int NumOfTabs,List<NewsResponse> responses) {
        super(fm);

        this.mNumOfTabs = NumOfTabs;
        this.responses=responses;


    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
    @Override
    public Fragment getItem(int position){

        switch (position){
            case 0:
                NewzFragment newzFragment = new NewzFragment();
                Bundle bundle=new Bundle();
                bundle.putInt("size",responses.size());
                for(int i=0;i<responses.size();i++){
                    String json=new Gson().toJson(responses.get(i));
                    bundle.putString("List"+i,json);
                }
                newzFragment.setArguments(bundle);

                return newzFragment;

            case 1:
                NewzFragment newzFragment1 = new NewzFragment();
                Bundle bundle1=new Bundle();
                bundle1.putInt("size",responses.size());
                for(int i=0;i<responses.size();i++){
                    String json=new Gson().toJson(responses.get(i));
                    bundle1.putString("List"+i,json);
                }
                newzFragment1.setArguments(bundle1);

                return newzFragment1;
            case 2:
                NewzFragment newzFragment2 = new NewzFragment();
                Bundle bundle2=new Bundle();
                bundle2.putInt("size",responses.size());
                for(int i=0;i<responses.size();i++){
                    String json=new Gson().toJson(responses.get(i));
                    bundle2.putString("List"+i,json);
                }
                newzFragment2.setArguments(bundle2);

                return newzFragment2;
            default:
                return null;
        }
    }


}
//package com.example.android.moodindigo;
//
//import android.os.Bundle;
//import android.support.v4.app.Fragment;
//import android.support.v4.app.FragmentActivity;
//import android.support.v4.app.FragmentManager;
//import android.support.v4.app.FragmentPagerAdapter;
//import android.support.v4.app.FragmentStatePagerAdapter;
//import android.support.v4.app.FragmentTransaction;
//
//import com.example.android.moodindigo.Fragments.EventsFragment;
//import com.example.android.moodindigo.Fragments.FAQFragment;
//import com.example.android.moodindigo.Fragments.NewzFragment;
//import com.example.android.moodindigo.data.NewsResponse;
//import com.google.gson.Gson;
//
//import java.util.ArrayList;
//import java.util.List;
//
///**
// * Created by owais on 05/07/17.
// */
//
//public class PagerAdapter extends FragmentPagerAdapter {
//
////    int mNumOfTabs; // number of tab variable for viewPager
////    List<NewsResponse> responses=new ArrayList<>();
////
////
////    //PagerAdapter Constructor
////    public PagerAdapter(FragmentManager fm, int NumOfTabs,List<NewsResponse> responses) {
////        super(fm);
////
////        this.mNumOfTabs = NumOfTabs;
////        this.responses=responses;
////
////
////    }
////
////    @Override
////    public int getCount() {
////        return mNumOfTabs;
////    }
////    @Override
////    public Fragment getItem(int position){
////
////        switch (position){
////            case 0:
////                NewzFragment newzFragment = new NewzFragment();
////                Bundle bundle=new Bundle();
////                bundle.putInt("size",responses.size());
////                for(int i=0;i<responses.size();i++){
////                    String json=new Gson().toJson(responses.get(i));
////                    bundle.putString("List"+i,json);
////                }
////                newzFragment.setArguments(bundle);
////
////                return newzFragment;
////
////            case 1:
////                CompiFragment compiFragment = new CompiFragment();
////                return compiFragment;
////            case 2:
////                EventsFragment eventsFragment=new EventsFragment();
////                return eventsFragment;
////            default:
////                return null;
////        }
////    }
//
//
//}
