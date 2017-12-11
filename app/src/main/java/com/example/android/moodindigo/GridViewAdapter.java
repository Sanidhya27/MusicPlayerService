//package com.example.android.moodindigo;
//
//import android.app.Activity;
//import android.app.Fragment;
//import android.content.Context;
//import android.content.Intent;
//import android.os.Bundle;
//import android.os.Parcelable;
//import android.util.Log;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.BaseAdapter;
//import android.widget.ImageView;
//import android.widget.LinearLayout;
//import android.widget.TextView;
//
//
//import com.example.android.moodindigo.data.GenresResponse;
//import com.example.android.moodindigo.data.EventsResponse;
//import com.google.gson.Gson;
//import com.google.gson.reflect.TypeToken;
//
//import java.io.Serializable;
//import java.lang.reflect.Type;
//import java.util.ArrayList;
//import java.util.List;
//
//import retrofit2.Call;
//import retrofit2.Callback;
//import retrofit2.Response;
//
///**
// * Created by mrunz on 17/9/17.
// */
//
//public class GridViewAdapter extends BaseAdapter {
//
//    private List<Item> genres=new ArrayList<>();
//    private Context context;
//
//    public GridViewAdapter(Context context) {
//        genres.add(new Item("Competitions", R.drawable.ic_menu_compi));
//        genres.add(new Item("Concerts", R.drawable.ic_menu_compi));
//        genres.add(new Item("Informals", R.drawable.ic_menu_compi));
//        genres.add(new Item("Proshows", R.drawable.ic_menu_compi));
//        genres.add(new Item("Workshops", R.drawable.ic_menu_compi));
//        genres.add(new Item("Arts and Ideas", R.drawable.ic_menu_compi));
//        this.context = context;
//
//
//    }
//
//
//
//
//
//    @Override
//    public int getCount() {
//        return genres.size();
//    }
//
//    @Override
//    public Object getItem(int i) {
//        return genres.get(i);
//    }
//
//    @Override
//    public long getItemId(int i) {
//        return genres.get(i).drawableid;
//    }
//
//    @Override
//    public View getView(final int i, View view, ViewGroup viewGroup) {
//        final LayoutInflater inflater = LayoutInflater.from(context);
//        view = inflater.inflate(R.layout.grid_item, viewGroup, false);
//
//        LinearLayout linearLayout;
//        TextView name;
//        ImageView image;
//        linearLayout = view.findViewById(R.id.linearlayout);
//        name = view.findViewById(R.id.genre_name);
//        image = view.findViewById(R.id.genre_image);
//
//        name.setText(genres.get(i).name);
//        image.setImageResource(genres.get(i).drawableid);
//
//
//
//
//
//
//        linearLayout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                switch (i) {
//                    case 0:
//                        Intent intent = new Intent(context, GenresActivity.class);
//                        intent.putExtra("Type","competitions");
//                        context.startActivity(intent);
//                        break;
//                    case 1:
//                        intent = new Intent(context, GenresActivity.class);
//                        intent.putExtra("Type","concerts");
//                        context.startActivity(intent);
//                        break;
//                    case 2:
//                        intent = new Intent(context, GenresActivity.class);
//                        intent.putExtra("Type","informals");
//                        context.startActivity(intent);
//
//                        break;
//                    case 3:
//                        intent = new Intent(context, GenresActivity.class);
//                        intent.putExtra("Type","proshows");
//                        context.startActivity(intent);
//                        break;
//                    case 4:
//                        intent = new Intent(context, GenresActivity.class);
//                        intent.putExtra("Type","workshops");
//                        context.startActivity(intent);
//                        break;
//                    case 5:
//                        intent = new Intent(context, GenresActivity.class);
//                        intent.putExtra("Type","arts and ideas");
//                        context.startActivity(intent);
//                        break;
//
//
//                }
//
//
//
//
//            }
//
//
//        });
//        return view;
//    }
//
//    private static class Item{
//        public String name;
//        public int drawableid;
//
//        public Item(String name,int drawableid){
//            this.name=name;
//            this.drawableid=drawableid;
//        }
//
//    }
//
//
//}
