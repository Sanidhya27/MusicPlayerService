package com.example.android.moodindigo;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.content.pm.PackageInstaller;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.moodindigo.Fragments.MainFragment;
import com.example.android.moodindigo.Fragments.MapFragment;
import com.example.android.moodindigo.R;
import com.example.android.moodindigo.data.NewsResponse;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginManager;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import android.support.v4.view.ViewPager;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    List<NewsResponse> responses = new ArrayList<>();
    RetrofitClass rcinitiate;
    SearchInterface client;


    // Navigation Bar
    BottomNavigationView bottomNavigationView;
    ViewPager viewPager;
     MenuItem prevMenuItem;
    DrawerLayout mDrawerLayout;
    SharedPreferences prefs;
    SharedPreferences.Editor editor;
    String userdetails="user details";
    String image,name,mi_number;


    private MediaPlayerService player;
    boolean serviceBound = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        prefs=getSharedPreferences(userdetails,MODE_PRIVATE);
        editor=prefs.edit();


        FacebookSdk.sdkInitialize(getApplicationContext());
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setVisibility(View.VISIBLE);
        setSupportActionBar(toolbar);



        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, mDrawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawerLayout.addDrawerListener(toggle);
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        toggle.syncState();



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

        View hview = navigationView.getHeaderView(0);
        TextView userName = (TextView) hview.findViewById(R.id.textView1);
        userName.setText(name);

        CircleImageView profileImage = (CircleImageView) hview.findViewById(R.id.imageView);
        if(image!=null){

            new DownloadImage(profileImage,MainActivity.this).execute(image);}
//
//        rcinitiate = new RetrofitClass(this);
//        client = rcinitiate.createBuilder().create(SearchInterface.class);
//        rcinitiate.startLogging();
//
//        Call<List<NewsResponse>> call = client.getNews();
//        call.enqueue(new Callback<List<NewsResponse>>() {
//            @Override
//            public void onResponse(Call<List<NewsResponse>> call, Response<List<NewsResponse>> response) {
//
//                pb.setVisibility(View.GONE);
//                responses = response.body();
//                Bundle bundle = new Bundle();
//                bundle.putInt("size", responses.size());
//                for (int i = 0; i < responses.size(); i++) {
//                    String json = new Gson().toJson(responses.get(i));
//                    bundle.putString("List" + i, json);
//                }
                MainFragment mainFragment = new MainFragment();
                        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.relative_layout_for_main_fragment, mainFragment, mainFragment.getTag());
                //ft.addToBackStack("main");
                ft.commit();
//
//            }
//
//            @Override
//            public void onFailure(Call<List<NewsResponse>> call, Throwable t) {
//
//            }
//        });
//        Log.i("Size112", String.valueOf(responses.size()));




        Button logout = (Button) hview.findViewById(R.id.logout);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginManager.getInstance().logOut();
                editor.clear();
                editor.putBoolean("isLoggedIn",false);
                editor.commit();
                startActivity(new Intent(MainActivity.this, LoginActivity.class));


            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
    }

//    @Override
//    public void onBackPressed() {
//        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
//        if (drawer.isDrawerOpen(GravityCompat.START)) {
//            drawer.closeDrawer(GravityCompat.START);
//        } else {
//            super.onBackPressed();
//        }
//    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_events) {



        } else if (id == R.id.nav_about) {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent intent = new Intent(getApplicationContext(), AboutMIActivity.class);
                    startActivity(intent);
                }
            }, 120);

        }
        else if (id == R.id.nav_contacts) {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent intent = new Intent(getApplicationContext(),ContactsActivity.class);
                    startActivity(intent);
                }
            }, 120);

        }
        else if (id == R.id.nav_fnb){
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent intent = new Intent(getApplicationContext(),fnbActivity.class);
                    startActivity(intent);
                }
            }, 120);
        }
//        else if (id == R.id.nav_maps) {
//            new Handler().postDelayed(new Runnable() {
//                @Override
//                public void run() {
////                    Intent intent = new Intent(getApplicationContext(),MapFragment.class);
////                    startActivity(intent);
//                    Class fragmentClass = MapFragment.class;
//                    MapFragment mapFragment = null;
//
//                    try {
//                        mapFragment = (MapFragment) fragmentClass.newInstance();
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
//                    FragmentManager fragmentManager = getSupportFragmentManager();
//                    fragmentManager.beginTransaction().replace(R.id.relative_layout_for_main_fragment, mapFragment).commit();
//                }
//            }, 120);
//
//        }


        mDrawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    private boolean backPressedToExitOnce = false;
    private Toast toast = null;

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);

        } else if (backPressedToExitOnce) {
            super.onBackPressed();
        } else {
            this.backPressedToExitOnce = true;
            toast = Toast.makeText(getApplicationContext(), "Press again to exit", Toast.LENGTH_SHORT);
            toast.show();//showToast("Press again to exit");
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {

                @Override
                public void run() {
                    backPressedToExitOnce = false;
                }
            }, 2000);
        }

    }

    /**
     * Created to make sure that you toast doesn't show miltiple times, if user pressed back
     * button more than once.
     *
     * @param message Message to show on toast.
     */
    private void showToast(String message) {
        if (this.toast == null) {
            // Create toast if found null, it would he the case of first call only
            this.toast = Toast.makeText(this, message, Toast.LENGTH_SHORT);

        } else if (this.toast.getView() == null) {
            // Toast not showing, so create new one
            this.toast = Toast.makeText(this, message, Toast.LENGTH_SHORT);

        } else {
            // Updating toast message is showing
            this.toast.setText(message);
        }

        // Showing toast finally
        this.toast.show();
    }

    /**
     * Kill the toast if showing. Supposed to call from onPause() of activity.
     * So that toast also get removed as activity goes to background, to improve
     * better app experiance for user
     */
    private void killToast() {
        if (this.toast != null) {
            this.toast.cancel();
        }
    }

    @Override
    protected void onPause() {
        killToast();
        super.onPause();
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        return false;
    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        switch (item.getItemId()) {
//            case android.R.id.home:
//                mDrawerLayout = findViewById(R.id.drawer_layout);
//                mDrawerLayout.openDrawer(GravityCompat.START);  // OPEN DRAWER
//                return true;
//        }
//        return super.onOptionsItemSelected(item);
//    }

    //Binding this Client to the AudioPlayer Service
    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            // We've bound to LocalService, cast the IBinder and get LocalService instance
            MediaPlayerService.LocalBinder binder = (MediaPlayerService.LocalBinder) service;
            player = binder.getService();
            serviceBound = true;

            Toast.makeText(MainActivity.this, "Service Bound", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            serviceBound = false;
        }
    };
    public  void playAudio(String media) {
        //Check is service is active
        if (!serviceBound) {
            Intent playerIntent = new Intent(this, MediaPlayerService.class);
            playerIntent.putExtra("media", media);
            startService(playerIntent);
            bindService(playerIntent, serviceConnection, Context.BIND_AUTO_CREATE);
        } else {
            //Service is active
            //Send media with BroadcastReceiver
        }
    }
    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putBoolean("ServiceState", serviceBound);
        super.onSaveInstanceState(savedInstanceState);
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        serviceBound = savedInstanceState.getBoolean("ServiceState");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (serviceBound) {
            unbindService(serviceConnection);
            //service is active
            player.stopSelf();
        }
    }
}





