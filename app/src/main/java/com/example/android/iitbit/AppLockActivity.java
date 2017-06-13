package com.example.android.iitbit;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.ToggleButton;

public class AppLockActivity extends AppCompatActivity {
    Switch aSwitch;
    TextView tvSetPassword;
    Intent intent;

    @Override
    public void onBackPressed() {
        intent = new Intent(AppLockActivity.this,NavigationDrawer.class);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_lock);

        tvSetPassword = (TextView)findViewById(R.id.tv_SetPasswordAppLock) ;

        aSwitch = (Switch)findViewById(R.id.switchAppLock);
        SharedPreferences sharedPreferences = getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        Boolean isLocked = sharedPreferences.getBoolean("appLockStatus",false);
        if(isLocked)
             aSwitch.setChecked(true);


        aSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                {
                    //set App lock
                    SharedPreferences sharedPreferences = getSharedPreferences("userInfo", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putBoolean("appLockStatus",true);
                    editor.apply();
                }
                else
                {
                    //remove app lock
                    SharedPreferences sharedPreferences = getSharedPreferences("userInfo", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putBoolean("appLockStatus",false);
                    editor.apply();
                }
            }
        });

        tvSetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences = getSharedPreferences("userInfo", Context.MODE_PRIVATE);
                Boolean isPINSet = sharedPreferences.getBoolean("LockPINStatus",false);
                if(isPINSet)
                    intent = new Intent(AppLockActivity.this,LockScreenTwo.class);
                else
                    intent = new Intent(AppLockActivity.this,LockScreenThree.class);
                startActivity(intent);
            }
        });
    }
}
