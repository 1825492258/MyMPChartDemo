package com.item.chart.activity;

import android.annotation.SuppressLint;
import android.graphics.Matrix;
import android.graphics.RectF;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.github.mikephil.charting.utils.MPPointF;
import com.item.chart.R;
import com.item.chart.custom.MyAxisValueFormatter;
import com.item.chart.custom.XYMarkerView;

import java.util.ArrayList;

public class TwoActivity extends AppCompatActivity implements OnChartValueSelectedListener {
    private BarChart mBarChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two);
        mBarChart = findViewById(R.id.barChart);
        mBarChart.setOnChartValueSelectedListener(this);
        initView();
        findViewById(R.id.btn_test).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mBarChart.animateY(1200);
            }
        });
    }

    private void initView() {
        mBarChart.setDrawBarShadow(false);
        mBarChart.setDrawValueAboveBar(true);
        mBarChart.setPinchZoom(false);
        mBarChart.setDrawGridBackground(false);
        // IAxisValueFormatter xAxisFormatter = new Da
        XAxis xAxis = mBarChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawGridLines(false);
        xAxis.setGranularity(1f);
        xAxis.setLabelCount(7);

        IAxisValueFormatter custom = new MyAxisValueFormatter();
        YAxis leftAxis = mBarChart.getAxisLeft();
        leftAxis.setLabelCount(8, false);
        leftAxis.setValueFormatter(custom);
        leftAxis.setPosition(YAxis.YAxisLabelPosition.OUTSIDE_CHART);
        leftAxis.setSpaceTop(15f);
        leftAxis.setAxisMinimum(0f);

        mBarChart.getAxisRight().setEnabled(false);

        Legend l = mBarChart.getLegend();
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.LEFT);
        l.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        l.setDrawInside(false);
        l.setForm(Legend.LegendForm.SQUARE);
        l.setFormSize(9f);
        l.setTextSize(11f);
        l.setXEntrySpace(4f);

        XYMarkerView mv = new XYMarkerView(this,xAxis.getValueFormatter());
        mv.setChartView(mBarChart); // For bounds control
        mBarChart.setMarker(mv); // Set the marker to the chart
        setData(14);
    }

    private void setData(int count) {
        float start = 1f;
        ArrayList<BarEntry> yValues1 = new ArrayList<>();
        for (int i = (int) start; i < start + count + 1; i++) {
            float val = (float) (Math.random() * 50);
            if (Math.random() * 100 < 25) {
                yValues1.add(new BarEntry(i, val, getResources().getDrawable(R.drawable.star)));
            } else {
                yValues1.add(new BarEntry(i, val));
            }
        }

        BarDataSet set1;
        if (mBarChart.getData() != null && mBarChart.getData().getDataSetCount() > 0) {
            set1 = (BarDataSet) mBarChart.getData().getDataSetByIndex(0);
            set1.setValues(yValues1);
            mBarChart.getData().notifyDataChanged();
            mBarChart.notifyDataSetChanged();
        } else {
            set1 = new BarDataSet(yValues1, "The Year 2017");
            set1.setDrawIcons(true);
            set1.setColors(ColorTemplate.MATERIAL_COLORS);
            ArrayList<IBarDataSet> dataSets = new ArrayList<>();
            dataSets.add(set1);

            BarData data = new BarData(dataSets);
            data.setValueTextSize(10f);
            data.setValueTextColor(getResources().getColor(R.color.colorAccent));
            data.setBarWidth(0.9f);
            mBarChart.setData(data);
        }
        mBarChart.invalidate();

        // 如何将MPAndroidChart设置为左右滑动的样式
        // http://blog.csdn.net/Dao_Li/article/details/52608319?locationNum=11&fps=1
        Matrix matrix = new Matrix();
        matrix.postScale(1.5f,1f);
        mBarChart.getViewPortHandler().refresh(matrix,mBarChart,false);
        mBarChart.animateY(800);
    }

    protected RectF mOnValueSelectedRectF = new RectF();

    @SuppressLint("NewApi")
    @Override
    public void onValueSelected(Entry e, Highlight h) {
        if (e == null) return;
        RectF bounds = mOnValueSelectedRectF;
        mBarChart.getBarBounds((BarEntry) e, bounds);
        MPPointF position = mBarChart.getPosition(e, YAxis.AxisDependency.LEFT);
        MPPointF.recycleInstance(position);
    }

    @Override
    public void onNothingSelected() {

    }
}
