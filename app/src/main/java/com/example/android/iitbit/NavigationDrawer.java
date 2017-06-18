package com.example.android.iitbit;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

public class NavigationDrawer extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private static final String TAG_HOME = "home",  TAG_EARN = "earn",
            TAG_TRANSFER = "transfer", TAG_SHOP = "shop", TAG_TRANSACTION = "transaction";
    public static String CURRENT_TAG = TAG_HOME;
    public static int navItemIndex = 0;
    Handler handler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_drawer);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        handler = new Handler();


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        setTitle("My Wallet");
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.frame, new HomeScreenFragment());
        transaction.commit();

        if(!checkConnected())
        {
            new AlertDialog.Builder(NavigationDrawer.this)
                    .setTitle("Error!")
                    .setMessage("Device not connected to network..")
                    .setPositiveButton("Retry", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            finish();
                            startActivity(getIntent());

                        }
                    })
                    .setNegativeButton("Exit", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            finish();
                            Intent intent = new  Intent(Intent.ACTION_MAIN);
                            intent.addCategory(Intent.CATEGORY_HOME);
                            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(intent);
                            finish();
                            System.exit(0);
                        }
                    })
                    .show();
        }
    }


    public boolean checkConnected(){
        ConnectivityManager connectivityManager = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        return connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED;
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
            return;
        }
        if(navItemIndex!=0)
        {
            navItemIndex = 0;
            CURRENT_TAG = TAG_HOME;
            loadHomeFragment();
            setTitle("My Wallet");
            return;
        }
           // super.onBackPressed();
        
    }

    private void loadHomeFragment() {
        Runnable mRunnable = new Runnable() {
            @Override
            public void run() {
                Fragment fragment = getFragmentIndex();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.frame,fragment);
                transaction.commit();
            }
        };
        if(mRunnable!=null)
        {
            handler.post(mRunnable);
        }
    }
    private Fragment getFragmentIndex()
    {
        switch(navItemIndex)
        {
            case 0:
                HomeScreenFragment homefragment = new HomeScreenFragment();
                return homefragment;
            case 1:
                EarnScreenFragment earnfrag = new EarnScreenFragment();
                return earnfrag;
            case 2:
                TransferScreenFragment transferFrag = new TransferScreenFragment();
                return transferFrag;
            case 3:
                return new ShopScreenFragment();
            case 4:
                return new TransactionsScreenFragment();
        }
        return new HomeScreenFragment();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        switch(id)
        {
            case R.id.menu_settings:
                Intent intentSettings = new Intent(NavigationDrawer.this,SettingActivity.class);
                startActivity(intentSettings);
                return  true;
            case R.id.menu_appLock:
                Intent intentLock = new Intent(NavigationDrawer.this,AppLockActivity.class);
                startActivity(intentLock);
                return  true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        Fragment selectedFragment = null;
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            // Handle the home action
            setTitle("My Wallet");
            selectedFragment = new HomeScreenFragment();
            CURRENT_TAG = TAG_HOME;
            navItemIndex = 0;
        } else if (id == R.id.nav_earn) {
            setTitle("Earn");
            selectedFragment = new EarnScreenFragment();
            CURRENT_TAG = TAG_EARN;
            navItemIndex = 1;
        } else if (id == R.id.nav_transfer) {
            setTitle("Transfer");
            selectedFragment = new TransferScreenFragment();
            CURRENT_TAG =TAG_TRANSFER;
            navItemIndex = 2;
        } else if (id == R.id.nav_shop) {
            setTitle("Shop");
            selectedFragment = new ShopScreenFragment();
            CURRENT_TAG = TAG_SHOP;
            navItemIndex = 3;
        } else if (id == R.id.nav_transactions) {
            setTitle("Transactions");
            selectedFragment = new TransactionsScreenFragment();
            CURRENT_TAG = TAG_TRANSACTION;
            navItemIndex = 4;
        }

        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.frame,selectedFragment);
        transaction.commit();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
