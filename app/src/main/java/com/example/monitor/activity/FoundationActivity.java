package com.example.monitor.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.example.monitor.R;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

public class FoundationActivity extends AppCompatActivity {
    PieChart pieChart, pieChart2, pieChart3, pieChart4, pieChart5, pieChart6;
    PieData pieData;
    PieDataSet pieDataSet;
    ArrayList pieEntries;
    ArrayList PieEntryLabels;
    CardView cardViewone;
    LinearLayout firstLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_foundation);

        cardViewone = findViewById(R.id.cardview_one);

        firstLayout = findViewById(R.id.first_card);
        getEntries();
        Description description =new Description();
        description.setText("");




        pieChart = findViewById(R.id.piechart);
        pieChart.setData(pieData);
        pieChart.setDescription(description);

        pieChart2 = findViewById(R.id.piechart2);
        pieChart2.setData(pieData);
        pieChart2.setDescription(description);

        pieChart3 = findViewById(R.id.piechart3);
        pieChart3.setData(pieData);
        pieChart3.setDescription(description);

        pieChart4 = findViewById(R.id.piechart4);
        pieChart4.setData(pieData);
        pieChart4.setDescription(description);

        pieChart5 = findViewById(R.id.piechart5);
        pieChart5.setData(pieData);
        pieChart5.setDescription(description);

        pieChart6 = findViewById(R.id.piechart6);
        pieChart6.setData(pieData);
        pieChart6.setDescription(description);





        pieDataSet.setSliceSpace(5f);

        firstLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(FoundationActivity.this,MonitorActivity.class));
            }
        });
    }
    private void getEntries() {
        pieEntries = new ArrayList<>();
        pieEntries.add(new PieEntry(2f,"used", 0));
        pieEntries.add(new PieEntry(4f, "available",1));


        pieDataSet = new PieDataSet(pieEntries, "");
        pieData = new PieData(pieDataSet);
        pieDataSet.setColors(ColorTemplate.JOYFUL_COLORS);
        pieDataSet.setSliceSpace(2f);
        pieDataSet.setValueTextColor(Color.BLACK);
        pieDataSet.setValueTextSize(6f);

    }
}
