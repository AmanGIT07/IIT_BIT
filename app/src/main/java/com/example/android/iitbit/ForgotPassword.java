package com.example.android.iitbit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.os.Handler;

import java.util.concurrent.TimeUnit;

public class ForgotPassword extends AppCompatActivity {
    TextView tvShowTime;
  private Handler handler;
    int remTime;
    Intent intent;

    @Override
    public void onBackPressed() {
        remTime = 0;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        remTime =5*60*1000;//milli seconds
        tvShowTime =(TextView) findViewById(R.id.tv_showRemTime);
        handler = new Handler();


        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                if(remTime>0)
                {
                    String ms = String.format("%02d:%02d", TimeUnit.MILLISECONDS.toMinutes(remTime) % TimeUnit.HOURS.toMinutes(1),
                            TimeUnit.MILLISECONDS.toSeconds(remTime) % TimeUnit.MINUTES.toSeconds(1));
                    tvShowTime.setText(ms);
                    remTime -= 1000;
                    handler.postDelayed(this,1000);
                }
                else
                {
                    intent = new Intent(ForgotPassword.this,Login.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                    finish();
                }
            }
        };

        handler.postDelayed(runnable,1000);
    }
}
