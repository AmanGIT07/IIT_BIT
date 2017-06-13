package com.example.android.iitbit;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LockScreen extends AppCompatActivity {
        Button btn;
        EditText et;
        String lockPassword,enteredLockPassword;
        Intent intent;

    @Override
    public void onBackPressed() {
        finish();
        Intent intent = new  Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
        System.exit(0);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lock_screen);

        et = (EditText)findViewById(R.id.et_LoackScreenPassword);
        btn = (Button)findViewById(R.id.btn_LockScreen);

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
                        intent = new Intent(LockScreen.this,Login.class);
                        startActivity(intent);
                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(),"Incorrect PIN",Toast.LENGTH_LONG).show();
                        et.setText("");
                    }
                }
            }
        });


    }
}
