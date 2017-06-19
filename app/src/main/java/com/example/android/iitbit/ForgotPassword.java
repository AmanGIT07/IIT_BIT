package com.example.android.iitbit;

import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.os.Handler;

import java.util.concurrent.TimeUnit;

public class ForgotPassword extends AppCompatActivity {
    TextView tvShowTime;
    int time = 10000;//5*60*1000;//milli seconds
    Intent intent;
    Button goHome;
    CountDownTimer countDownTimer;

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        countDownTimer.cancel();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        goHome = (Button)findViewById(R.id.btn_goHomeFromForgotPass);
        tvShowTime =(TextView) findViewById(R.id.tv_showRemTime);

        countDownTimer  = new CountDownTimer(time, 1000) {

            public void onTick(long millisUntilFinished) {
                String ms = String.format("%02d:%02d", TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished) % TimeUnit.HOURS.toMinutes(1),
                            TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) % TimeUnit.MINUTES.toSeconds(1));
                    tvShowTime.setText(ms);
            }

            public void onFinish() {
                intent = new Intent(ForgotPassword.this,Login.class);
                startActivity(intent);
            }

        }.start();

        goHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                countDownTimer.cancel();
                intent = new Intent(ForgotPassword.this,Login.class);
                startActivity(intent);
            }
        });
    }
}
