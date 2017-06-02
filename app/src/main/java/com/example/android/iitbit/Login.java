package com.example.android.iitbit;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Login extends AppCompatActivity {
        Button register;
        Intent intent;
        TextView tv1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_login);
        tv1 = (TextView)findViewById(R.id.tv_forgotPassword);
        tv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(Login.this,ForgotPassword.class);
                startActivity(intent);
            }
        });
        register = (Button)findViewById(R.id.btn_createNewWallet);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(Login.this,Register.class);
                startActivity(intent);
            }
        });
    }


}
