package com.item.chart.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.item.chart.R;

import java.util.ArrayList;

/**
 * ScrollView
 */
public class EightActivity extends AppCompatActivity {

    private BarChart mChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eight);
        mChart = findViewById(R.id.chart1);

        mChart.getDescription().setEnabled(false);
        mChart.setPinchZoom(false);

        mChart.setDrawBarShadow(false);
        mChart.setDrawGridBackground(false);

        XAxis xAxis = mChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawGridLines(false);

        mChart.getAxisLeft().setDrawGridLines(false);
        mChart.getLegend().setEnabled(false);

        setData(30);
        mChart.setFitBars(false);
    }

    private void setData(int count) {
        ArrayList<BarEntry> yVals = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            float val = (float)(Math.random() * count) + 15;
            yVals.add(new BarEntry(i , (int)val));
        }

        BarDataSet set = new BarDataSet(yVals,"Data Set");
        set.setColors(ColorTemplate.VORDIPLOM_COLORS);
        set.setDrawValues(false);

        BarData data = new BarData(set);
        mChart.setData(data);
        mChart.invalidate();
        mChart.animateY(800);
    }
}
