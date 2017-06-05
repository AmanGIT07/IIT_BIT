package com.example.android.iitbit;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class NavigationActivity extends AppCompatActivity {
        BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);
        bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_nav_bar);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment selectedFragment = null;
               switch (item.getItemId())
               {
                   case R.id.bottom_nav_home:
                       selectedFragment = new HomeScreenFragment();
                       break;
                   case R.id.bottom_nav_earn:
                       selectedFragment = new EarnScreenFragment();
                       break;
                   case R.id.bottom_nav_transfer:
                       selectedFragment = new TransferScreenFragment();
                       break;
                   case R.id.bottom_nav_shop:
                       selectedFragment = new ShopScreenFragment();
                       break;
                   case R.id.bottom_nav_transactions:
                       selectedFragment = new TransactionsScreenFragment();
                       break;
               }
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.frame_layout,selectedFragment);
                transaction.commit();
                return true;
            }
        });
        //initial default fragment for one time use
//        FragmentTransaction transaction = getFragmentManager().beginTransaction();
//        transaction.replace(R.id.frame_layout,HomeScreenFragment);
//        transaction.commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_list,menu);
        return true;
    }
}
