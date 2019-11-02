package com.example.monitor.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Switch;
import android.widget.TextView;

import com.example.monitor.R;

public class MonitorActivity extends AppCompatActivity {

    Switch simpleSwitch;
    TextView progressTv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_monitor);
        simpleSwitch = findViewById(R.id.status_Switch); // initiate Switch
        progressTv = findViewById(R.id.switch_label_tv);



    }
}
