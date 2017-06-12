package com.example.android.iitbit;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Register extends Activity {
        TextView tc;
    Intent intent;
    CheckBox checkBox;
    Button register;
    EditText et_email,et_mobile,et_userName,et_password,et_confirmPassword;
    String email,mobile,userName,password,pass1,pass2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        et_email = (EditText)findViewById(R.id.et_reg_email);
        et_mobile = (EditText)findViewById(R.id.et_reg_mobile);
        et_userName = (EditText)findViewById(R.id.et_reg_username);
        et_password = (EditText)findViewById(R.id.et_reg_password);
        et_confirmPassword = (EditText)findViewById(R.id.et_reg_confirmPassword);
        register = (Button)findViewById(R.id.btn_signUP);
        checkBox = (CheckBox)findViewById(R.id.checkbox_termsCond);
        tc = (TextView)findViewById(R.id.tv_TermsCond);

        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                    register.setEnabled(true);
                else
                    register.setEnabled(false);
            }
        });

        tc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(Register.this,TermsConditions.class);
                startActivity(intent);
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email = et_email.getText().toString().trim();
                mobile = et_mobile.getText().toString().trim();
                userName = et_userName.getText().toString().trim();
                pass1 = et_password.getText().toString().trim();
                pass2 = et_confirmPassword.getText().toString().trim();

                if(email.isEmpty())
                    et_email.setError("Required");
                if(mobile.isEmpty())
                    et_mobile.setError("Required");
                if(userName.isEmpty())
                    et_userName.setError("Required");
                if(pass1.isEmpty())
                    et_password.setError("Required");
                if(pass2.isEmpty())
                    et_confirmPassword.setError("Required");
                if(!(pass2.isEmpty())&&!(pass1.isEmpty()))
                {
                    if(!(pass2.equals(pass1)))
                    {
                        Toast.makeText(getApplicationContext(),"Passwords don't match",Toast.LENGTH_SHORT).show();
                    }
                    else
                        password = pass1;
                }

            }
        });



    }
}
