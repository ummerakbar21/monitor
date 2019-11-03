package com.example.monitor.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;

import com.example.monitor.R;

public class MonitorActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    Switch simpleSwitch;
    TextView progressTv;
    private Spinner spin1;
    private Spinner spin2;
    private String[]  stations = { "From","Baramula", "USA", "China", "Japan", "Other"};
    private TextView distanceTV;
    private   String[] distance={"9","7","6","7","0"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_monitor);
        simpleSwitch = findViewById(R.id.status_Switch); // initiate Switch
        progressTv = findViewById(R.id.switch_label_tv);




        //Getting the instance of Spinner and applying OnItemSelectedListener on it
        spin1 = findViewById(R.id.block_name_from_spinner);
        spin1.setOnItemSelectedListener(this);
        spin2 = findViewById(R.id.block_name_to_spinner);
        distanceTV=findViewById(R.id.distance_tv);

        //Creating the ArrayAdapter instance having the country list
        ArrayAdapter aa = new ArrayAdapter(this,android.R.layout.simple_dropdown_item_1line,stations){
            @Override
            public boolean isEnabled(int position){
                if(position == 0)
                {
                    // Disable the first item from Spinner
                    // First item will be use for hint
                    return false;
                }
                else
                {
                    return true;
                }
            }
            @Override
            public View getDropDownView(int position, View convertView,
                                        ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);
                TextView tv = (TextView) view;
                if(position == 0){
                    // Set the hint text color gray
                    tv.setTextColor(Color.GRAY);
                }
                else {
                    tv.setTextColor(Color.BLACK);
                }
                return view;
            }
        };
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
        spin1.setAdapter(aa);
        spin2.setAdapter(aa);


    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if(position > 0){
            if(position<stations.length-1){
                spin2.setSelection(position+1);
            }else {
                spin2.setSelection(position);
            }
            distanceTV.setText(distance[position]);

        }else {
            TextView tv = (TextView) view;
           tv.setTextColor(Color.GRAY);

        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
