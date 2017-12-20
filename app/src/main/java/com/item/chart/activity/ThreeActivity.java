package com.item.chart.activity;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.item.chart.R;

import java.util.ArrayList;

public class ThreeActivity extends AppCompatActivity {
    private PieChart mPicChart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_three);
        mPicChart = findViewById(R.id.pieChart);

        mPicChart.setUsePercentValues(true);
        mPicChart.getDescription().setEnabled(false);
        mPicChart.setExtraOffsets(5,10,5,5);
        mPicChart.setDragDecelerationFrictionCoef(0.95f);

        mPicChart.setDrawHoleEnabled(true);
        mPicChart.setHoleColor(Color.WHITE);

        mPicChart.setHoleRadius(48f);
        mPicChart.setTransparentCircleRadius(51f);

        mPicChart.setDrawCenterText(true);
        mPicChart.setRotationAngle(0);
        mPicChart.setRotationEnabled(true);
        mPicChart.setHighlightPerTapEnabled(true);

        Legend l = mPicChart.getLegend();
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
        l.setOrientation(Legend.LegendOrientation.VERTICAL);
        l.setDrawInside(false);
        l.setXEntrySpace(7f);
        l.setYEntrySpace(0f);
        l.setYOffset(0f);
        
        setData(5);
    }
    protected String[] mParties = new String[] {
            "Party A", "Party B", "Party C", "Party D", "Party E", "Party F", "Party G", "Party H",
            "Party I", "Party J", "Party K", "Party L", "Party M", "Party N", "Party O", "Party P",
            "Party Q", "Party R", "Party S", "Party T", "Party U", "Party V", "Party W", "Party X",
            "Party Y", "Party Z"
    };

    private void setData(int count) {
        ArrayList<PieEntry> entries = new ArrayList<>();
        for(int i =0; i < count; i++){
            entries.add(new PieEntry((float)(Math.random() *50 +2),
                    mParties[i % mParties.length]));
        }
        PieDataSet dataSet = new PieDataSet(entries,"Election Results");
        dataSet.setSliceSpace(3f);
        dataSet.setSelectionShift(5f);
        // add a lot of colors
        ArrayList<Integer> colors = new ArrayList<>();
        for (int c : ColorTemplate.VORDIPLOM_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.JOYFUL_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.COLORFUL_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.LIBERTY_COLORS)
            colors.add(c);
        colors.add(ColorTemplate.getHoloBlue());
        dataSet.setColors(colors);

        PieData data = new PieData(dataSet);
        data.setValueFormatter(new PercentFormatter());
        data.setValueTextSize(11f);
        data.setValueTextColor(Color.WHITE);
        mPicChart.setData(data);

        //undo all highlights
        mPicChart.highlightValue(null);
        mPicChart.invalidate();
    }
}
