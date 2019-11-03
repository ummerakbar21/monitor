package com.example.monitor.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;

import com.example.monitor.R;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

public class FoundationActivity extends AppCompatActivity {
    PieChart pieChart;
    PieData pieData;
    PieDataSet pieDataSet;
    ArrayList pieEntries;
    ArrayList PieEntryLabels;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_foundation);

        pieChart = findViewById(R.id.piechart);
        getEntries();
        pieDataSet = new PieDataSet(pieEntries, "");
        pieData = new PieData(pieDataSet);
        pieChart.setData(pieData);
        pieDataSet.setColors(ColorTemplate.JOYFUL_COLORS);
        pieDataSet.setSliceSpace(2f);
        pieDataSet.setValueTextColor(Color.BLACK);
        pieDataSet.setValueTextSize(6f);
        Description description =new Description();
        description.setText("");
        pieChart.setDescription(description);
        pieDataSet.setSliceSpace(5f);
    }
    private void getEntries() {
        pieEntries = new ArrayList<>();
        pieEntries.add(new PieEntry(2f,"used", 0));
        pieEntries.add(new PieEntry(4f, "available",1));
    }
}
