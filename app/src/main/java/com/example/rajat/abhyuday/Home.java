package com.example.rajat.abhyuday;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import static com.example.rajat.abhyuday.R.id;
import static com.example.rajat.abhyuday.R.layout;
import static com.example.rajat.abhyuday.R.string;

//import android.app.Fragment;

public class Home extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, CallFragment.OnFragmentInteractionListener, RegisterFragment.OnFragmentInteractionListener, OnMapReadyCallback {


    SupportMapFragment smapfragment;

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     *
     */
    private GoogleApiClient client;
    //SupportMapFragment supportMapFragment;
    //MapFragment mapf;

    @Override
    public void onFragmentInteraction(Uri uri) {

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        smapfragment = SupportMapFragment.newInstance();
        setContentView(layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(id.toolbar);
        setSupportActionBar(toolbar);



        FloatingActionButton fab = (FloatingActionButton) findViewById(id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, string.navigation_drawer_open, string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
        smapfragment.getMapAsync(this);

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
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
        android.support.v4.app.FragmentManager fm=getSupportFragmentManager();
        android.support.v4.app.FragmentManager sfm = getSupportFragmentManager();




        int id = item.getItemId();

        if (id == R.id.nav_event) {

            Intent mHome = new Intent(Home.this, EventActivity.class);
            Home.this.startActivity(mHome);
            Home.this.finish();

        } else if (id == R.id.nav_schedule) {

        } else if (id == R.id.nav_register) {
            if (smapfragment.isAdded()) {
                sfm.beginTransaction().hide(smapfragment).commit();

            }


            android.support.v4.app.FragmentManager manager = getSupportFragmentManager();
            RegisterFragment registerFragment=new RegisterFragment();

            if(! registerFragment.isAdded()) {
                manager.beginTransaction().replace(R.id.content_home, registerFragment).commit();
            }
//            else
//                manager.beginTransaction().show(registerFragment).commit();

            Toast.makeText(this, "Register", Toast.LENGTH_LONG).show();


        } else if (id == R.id.nav_findus) {
//            Intent mHome = new Intent(Home.this, MapsActivity.class);
//            Home.this.startActivity(mHome);
//            Home.this.finish();


            if (!smapfragment.isAdded()) {
                sfm.beginTransaction().replace(R.id.map, smapfragment).commit();

            }
            else
                sfm.beginTransaction().show(smapfragment).commit();

        }else if (id == R.id.nav_conus) {
            if (smapfragment.isAdded()) {
                sfm.beginTransaction().hide(smapfragment).commit();

            }


            android.support.v4.app.FragmentManager manager = getSupportFragmentManager();
            CallFragment callFragment=new CallFragment();

            if(!callFragment.isAdded()) {
                manager.beginTransaction().replace(R.id.content_home, callFragment).commit();
            }
//            else
//                manager.beginTransaction().show(callFragment).commit();


        }
        else if (id == R.id.nav_abtus) {

        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void insert(View v) {

        EditText name = (EditText) findViewById(id.reg_name);
        EditText email = (EditText) findViewById(id.reg_id);
        EditText mobile = (EditText)findViewById(id.reg_mob);
        EditText address = (EditText) findViewById(id.reg_add);
        EditText password = (EditText) findViewById(id.reg_pass);
        String sname = name.getText().toString();
        String semail = email.getText().toString();
        String smob = mobile.getText().toString();
        String sadd = address.getText().toString();
        String spass = password.getText().toString();
        new RegisterActivity(this).execute(sname, semail, smob, sadd, spass);

    }


    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("Home Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        client.disconnect();
    }


    @Override
    public void onMapReady(GoogleMap mMap) {

//        LocationRequest mLocationRequest;
//
//        GoogleApiClient mGoogleApiClient;


        //private static final LatLng MSRIT = new LatLng(13.0311221, 77.5651647);
        LatLng MSRIT = new LatLng(12.9718915, 77.6411545);

        Marker mMsrit;


        // Add a marker in Sydney and move the camera


        mMsrit = mMap.addMarker(new MarkerOptions()
                .position(MSRIT)
                .title("MSRIT")
                .flat(true)
        );
        mMsrit.setTag(0);
        //mMap.setOnMarkerClickListener(this);

        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(MSRIT, 13));

        mMap.animateCamera(CameraUpdateFactory.zoomIn());

        mMap.animateCamera(CameraUpdateFactory.zoomTo(13), 2000, null);


    }

}
