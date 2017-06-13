package com.example.android.iitbit;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

public class SplashScreen extends AppCompatActivity {
    ImageView imageView ;
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        imageView = (ImageView)findViewById(R.id.iv_splashScreen);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                SharedPreferences sharedPreferences = getSharedPreferences("userInfo", Context.MODE_PRIVATE);
                Boolean isLocked = sharedPreferences.getBoolean("appLockStatus",false);

                if(isLocked)
                    intent = new Intent(SplashScreen.this,LockScreen.class);
                else
                    intent = new Intent(SplashScreen.this,Login.class);

                startActivity(intent);
            }
        },1500);
    }
}
