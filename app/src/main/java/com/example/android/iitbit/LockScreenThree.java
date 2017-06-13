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

public class LockScreenThree extends AppCompatActivity {
    Button btn;
    EditText etPass,etRePass;
    String Pass,RePass,Password;
    Intent intent;

    @Override
    public void onBackPressed() {
        intent = new Intent(LockScreenThree.this,AppLockActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lock_screen_three);
        etPass = (EditText)findViewById(R.id.et_LoackScreenThreePassword);
        btn = (Button)findViewById(R.id.btn_LockScreenThree);
        etRePass = (EditText)findViewById(R.id.et_LoackScreenThreeConfirmPassword);


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Pass = etPass.getText().toString().trim();
                if(Pass.isEmpty())
                    etPass.setError("Required");
                RePass = etRePass.getText().toString().trim();
                if(RePass.isEmpty())
                    etPass.setError("Required");
                if(!(Pass.isEmpty())&&!(RePass.isEmpty()))
                {
                    if(Pass.equals(RePass))
                    {
                        Password = RePass;
                        SharedPreferences sharedPreferences = getSharedPreferences("userInfo", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("LockPIN",Password);
                        editor.putBoolean("LockPINStatus",true);
                        editor.apply();
                        Toast.makeText(getApplicationContext(),"PIN Saved",Toast.LENGTH_LONG).show();
                        intent = new Intent(LockScreenThree.this,AppLockActivity.class);
                        startActivity(intent);

                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(),"PIN don't match",Toast.LENGTH_LONG).show();
                        etRePass.setText("");
                    }
                }
            }
        });



    }
}
