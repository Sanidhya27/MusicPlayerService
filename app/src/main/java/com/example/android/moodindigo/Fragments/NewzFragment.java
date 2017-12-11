package com.example.android.moodindigo.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.example.android.moodindigo.MyAdapter;
import com.example.android.moodindigo.NewsCardAdapter;
import com.example.android.moodindigo.R;
import com.example.android.moodindigo.RetrofitClass;
import com.example.android.moodindigo.SearchInterface;
import com.example.android.moodindigo.data.NewsResponse;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by owais on 19/08/17.
 */

public class NewzFragment extends Fragment {
    public View view;
    RetrofitClass rcinitiate;
    SearchInterface client;
    int size;
    ProgressBar progressBar;
    List<NewsResponse> responses=new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //container.removeAllViews();

        // Inflate the layout for this fragment
        final View rootView = inflater.inflate(R.layout.fragment_faq, container, false);

        Bundle bundle=getArguments();
//        size=bundle.getInt("size");
//        for(int j=0;j<size;j++){
//            responses.add(new Gson().fromJson(bundle.getString("List"+j),NewsResponse.class));
//        }

//
//                RecyclerView rv = (RecyclerView) rootView.findViewById(R.id.recycler_view_news);
//                rv.setHasFixedSize(true);
//                NewsCardAdapter adapter=new NewsCardAdapter(responses);
//                rv.setAdapter(adapter);
//
//                LinearLayoutManager llm = new LinearLayoutManager(getActivity());
//                rv.setLayoutManager(llm);

        return rootView;

    }

}
