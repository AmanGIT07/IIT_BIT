package com.example.android.iitbit;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Login extends AppCompatActivity {
        Button register,signIn;
        Intent intent;
        TextView tv1;
    EditText et_userId,et_pass;
    String userID,password;

    @Override
    public void onBackPressed() {
        //Do nothing
    }

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

        et_userId = (EditText) findViewById(R.id.et_LoginID);
        et_pass = (EditText)findViewById(R.id.et_LoginPassword);

        signIn = (Button)findViewById(R.id.btn_signIn);
        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userID = et_userId.getText().toString().trim();
                password = et_pass.getText().toString().trim();
                if (userID.isEmpty())
                        et_userId.setError("Please enter this field");
                if (password.isEmpty())
                    et_pass.setError("Please enter this field");
                intent = new Intent(Login.this,NavigationDrawer.class);
                startActivity(intent);
            }
        });

        if(!checkConnected())
        {
            AlertDialog.Builder build = new AlertDialog.Builder(Login.this);
            build.setMessage("Device not connected to Network.Click ok to exit")
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
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
                    .setCancelable(false);
            AlertDialog a = build.create();
            a.setTitle("Error!");
            a.show();
        }

    }



    public boolean checkConnected(){
        ConnectivityManager connectivityManager = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        return connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED;
    }

}
