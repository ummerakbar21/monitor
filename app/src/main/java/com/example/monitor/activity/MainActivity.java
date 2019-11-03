package com.example.monitor.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.style.TextAppearanceSpan;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.monitor.R;

public class MainActivity extends AppCompatActivity {

 TextView marqueTv;
 EditText etEmail;
 EditText etPassword;
 String userName;
 String password;
 Button loginBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        marqueTv = findViewById(R.id.marque_text);
        marqueTv.setSelected(true);
        marqueTv.setEllipsize(TextUtils.TruncateAt.MARQUEE);
        marqueTv.setSingleLine(true);
        etEmail = findViewById(R.id.et_email);
        etPassword = findViewById(R.id.et_pass);
        loginBtn = findViewById(R.id.btn_login);
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ((etEmail.getText().toString().length()==0)){
                    Toast.makeText(MainActivity.this, "Please Enter UserName", Toast.LENGTH_SHORT).show();

                }

                 else if (etEmail.getText().toString().length()<5){
                     Toast.makeText(MainActivity.this, "Username should be greater than 4 characters", Toast.LENGTH_SHORT).show();
                }
                else if (TextUtils.isEmpty(etPassword.getText().toString().trim())){

                     Toast.makeText(MainActivity.this, "Please Enter Password", Toast.LENGTH_SHORT).show();

                }


               else if (etEmail.getText().toString().equalsIgnoreCase("admin")&& etPassword.getText().toString().equalsIgnoreCase("admin")){
                    Intent intent = new Intent(MainActivity.this,FoundationActivity.class);
                    startActivity(intent);
                }

                 else {
                     Toast.makeText(MainActivity.this, "Incorrect UserName/Password", Toast.LENGTH_SHORT).show();
                 }
            }
        });


    }
}
