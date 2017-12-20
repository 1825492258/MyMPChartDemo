package com.item.chart.activity;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.formatter.LargeValueFormatter;
import com.item.chart.R;

import java.util.ArrayList;

public class OneActivity extends AppCompatActivity {

    private BarChart mChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one);
        mChart = findViewById(R.id.barChart);
        initView();
    }

    private void initView() {
        mChart.getDescription().setEnabled(false);
        mChart.setDrawBarShadow(false);
        mChart.setDrawGridBackground(false);
        Legend l = mChart.getLegend();
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
        l.setOrientation(Legend.LegendOrientation.VERTICAL);
        l.setDrawInside(true);
        //l.setTypeface();

        l.setXOffset(10f);
        l.setYEntrySpace(0f);
        l.setTextSize(8f);
        // 对X轴进行简单设置
        XAxis xAxis = mChart.getXAxis();
        xAxis.setGranularity(1f);
        xAxis.setCenterAxisLabels(true);
        xAxis.setTextSize(10f);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM); // 设置在下方
        xAxis.setTextColor(Color.BLUE);
        xAxis.setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                // return String.valueOf((int)value);
                //return mMonths[(int)value];
                Log.d("jiejie", " " + (int) value);
                // return mMonths[(int)value%mMonths.length];
                // return String.valueOf((int)value);
                int index = (int) value;
                return index >= 0 && index < 4 ? mMonths[index] : "";
            }
        });
        YAxis leftAxis = mChart.getAxisLeft();
        //leftAxis.setTypeface(mTfLight);
        leftAxis.setValueFormatter(new LargeValueFormatter());
        leftAxis.setDrawGridLines(false);
        leftAxis.setSpaceTop(35f);
        leftAxis.setAxisMinimum(0f); // this replaces setStartAtZero(true)

        mChart.getAxisRight().setEnabled(false);
        initData();
    }

    protected String[] mMonths = new String[]{
            "第一个", "第二个", "第三个", "第四个"
    };

    private void initData() {
        float groupSpace = 0.2f;
        float barSpace = 0.1f; // x4 DataSet
        float barWidth = 0.3f; // x4 DataSet
        // (0.2 + 0.03) * 4 + 0.08 = 1.00 -> interval per "group"
        // (0.3 + 0.1) *2 + 0.2
        int startYear = 0;
        int endYear = 4;
        ArrayList<BarEntry> Y1 = new ArrayList<>();
        ArrayList<BarEntry> Y2 = new ArrayList<>();
        ArrayList<BarEntry> Y3 = new ArrayList<>();
        ArrayList<BarEntry> Y4 = new ArrayList<>();
        for (int i = startYear; i < endYear; i++) {
            Y1.add(new BarEntry(i, (float) (Math.random() * 100)));
            Y2.add(new BarEntry(i, (float) (Math.random() * 80)));
            Y3.add(new BarEntry(i, (float) (Math.random() * 20)));
            Y4.add(new BarEntry(i, (float) (Math.random() * 90)));
        }
        BarDataSet set1, set2;
        BarDataSet set3, set4;
        if (mChart.getData() != null && mChart.getData().getDataSetCount() > 0) {
            set1 = (BarDataSet) mChart.getData().getDataSetByIndex(0);
            set2 = (BarDataSet) mChart.getData().getDataSetByIndex(1);
            set1.setValues(Y1);
            set2.setValues(Y2);
            mChart.getData().notifyDataChanged();
            mChart.notifyDataSetChanged();
        } else {
            set1 = new BarDataSet(Y1, "Company A");
            set1.setColor(Color.rgb(104, 241, 175));
            set2 = new BarDataSet(Y2, "Company B");
            set2.setColor(Color.rgb(164, 228, 251));
            set3 = new BarDataSet(Y3, "Company C");
            set3.setColor(Color.rgb(242, 247, 158));
            set4 = new BarDataSet(Y4, "Company D");
            set4.setColor(Color.rgb(255, 102, 0));
            BarData data = new BarData(set1, set2);
            //  BarData data = new BarData(set1,set2,set3,set4);
            data.setValueFormatter(new LargeValueFormatter());

            mChart.setData(data);
        }
        mChart.getBarData().setBarWidth(barWidth);
        mChart.getXAxis().setAxisMinimum(startYear);
        mChart.getXAxis().setAxisMaximum(startYear + mChart.getBarData().getGroupWidth(groupSpace, barSpace) * 4);
        mChart.groupBars(startYear, groupSpace, barSpace);
        mChart.invalidate();
    }
}
