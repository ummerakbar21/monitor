package com.example.monitor.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.text.style.TextAppearanceSpan;
import android.widget.EditText;
import android.widget.TextView;

import com.example.monitor.R;

public class MainActivity extends AppCompatActivity {

 TextView marqueTv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        marqueTv = findViewById(R.id.marque_text);
        marqueTv.setSelected(true);
        marqueTv.setEllipsize(TextUtils.TruncateAt.MARQUEE);
        marqueTv.setSingleLine(true);
    }
}
