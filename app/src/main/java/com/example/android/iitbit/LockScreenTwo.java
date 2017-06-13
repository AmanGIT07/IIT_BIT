package com.example.android.iitbit;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LockScreenTwo extends AppCompatActivity {
    Button btn;
    EditText et;
    String lockPassword,enteredLockPassword;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lock_screen_two);
        et = (EditText)findViewById(R.id.et_LoackScreenTwoPassword);
        btn = (Button)findViewById(R.id.btn_LockScreenTwo);

        SharedPreferences sharedPreferences = getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        lockPassword = sharedPreferences.getString("LockPIN","");

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enteredLockPassword = et.getText().toString().trim();

                if(enteredLockPassword.isEmpty())
                    et.setError("Required");
                else
                {
                    if(lockPassword.equals(enteredLockPassword))
                    {
                        intent = new Intent(LockScreenTwo.this,LockScreenThree.class);
                        startActivity(intent);
                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(),"Sorry Password don't match",Toast.LENGTH_LONG).show();
                        et.setText("");
                    }
                }
            }
        });

    }
}
