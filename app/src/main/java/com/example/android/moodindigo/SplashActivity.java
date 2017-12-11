package com.example.android.moodindigo;

import android.app.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

import com.example.android.moodindigo.EventsDatabase.DatabaseHelper;
import com.example.android.moodindigo.EventsDatabase.Event;
import com.example.android.moodindigo.EventsDatabase.Tag;
import com.example.android.moodindigo.data.EventDetailResponse;

import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SplashActivity extends Activity {

    // Splash screen timer
    private static int SPLASH_TIME_OUT = 3000;
    private String userdetails="user details";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);



        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        Retrofit.Builder builder = new Retrofit.Builder();
        Retrofit retrofit;
        final DatabaseHelper db= new DatabaseHelper(getApplicationContext());;



        builder.baseUrl("http://themaskedman981.pythonanywhere.com/")
                .addConverterFactory(
                        GsonConverterFactory.create());
        retrofit = builder
                .client(
                        httpClient.build()
                )
                .build();

        Call<List<EventDetailResponse>> call=retrofit.create(SearchInterface.class).getEventDetails();

        call.enqueue(new Callback<List<EventDetailResponse>>() {
            @Override
            public void onResponse(Call<List<EventDetailResponse>> call, Response<List<EventDetailResponse>> response) {
                List<EventDetailResponse> events=response.body();


                for(EventDetailResponse eventDetailResponse:events){
                    Event event=new Event(eventDetailResponse.getName(),eventDetailResponse.getDescription(),eventDetailResponse.getVenue(),eventDetailResponse.getTime(),eventDetailResponse.getGoing());
                    Tag tag=new Tag(eventDetailResponse.getType(),eventDetailResponse.getDay());
                    db.updateEventifFound(event, new long[]{db.createTag(tag)});
                }

                Intent intent;
                if(getSharedPreferences(userdetails,MODE_PRIVATE).getBoolean("isLoggedIn",false)){

                    intent=new Intent(SplashActivity.this,MainActivity.class);
                }
                else
                {
                    intent=new Intent(SplashActivity.this, LoginActivity.class);
                }
                startActivity(intent);


            }

            @Override
            public void onFailure(Call<List<EventDetailResponse>> call, Throwable t) {
                new Handler().postDelayed(new Runnable() {

            /*
             * Showing splash screen with a timer. This will be useful when you
             * want to show case your app logo / company
             */

                    @Override
                    public void run() {
                        // This method will be executed once the timer is over
                        // Start your app main activity
                        Intent intent;
                        if(getSharedPreferences(userdetails,MODE_PRIVATE)==null){

                            intent=new Intent(SplashActivity.this, LoginActivity.class);
                        }
                        else
                        {
                            intent=new Intent(SplashActivity.this, MainActivity.class);
                        }
                        startActivity(intent);
                        // close this activity
                        finish();
                    }
                }, SPLASH_TIME_OUT);

            }
        });




    }

}