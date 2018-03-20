package com.item.chart.activity;

import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.item.chart.R;
import com.item.chart.custom.Bean;
import com.item.chart.custom.XYMarkerView;

import java.util.ArrayList;
import java.util.List;

public class TestActivity extends AppCompatActivity {
    private List<Bean> beans = new ArrayList<>();
    private LineChart mLineChart;
    private XAxis xAxis;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        mLineChart = findViewById(R.id.lineChart);
        beans.add(new Bean("2018-03-20",4f));
        beans.add(new Bean("2018-03-21",5f));
        beans.add(new Bean("2018-03-22",8f));
        beans.add(new Bean("2018-03-23",10f));
        beans.add(new Bean("2018-03-24",0f));
        beans.add(new Bean("2018-03-25",3f));
        beans.add(new Bean("2018-03-26",1f));
        setLineChart(mLineChart);
    }

    private void setLineChart(LineChart mChart) {
        mChart.setDrawGridBackground(false);
        mChart.getDescription().setEnabled(false);
        mChart.setTouchEnabled(true);
        mChart.setDragEnabled(false);
        mChart.setScaleEnabled(true);
        mChart.setPinchZoom(false);
        mChart.setNoDataText("暂无数据");
        mChart.setDoubleTapToZoomEnabled(false);
        mChart.animateXY(2500, 2500);
        // 设置X
         xAxis = mChart.getXAxis();
        xAxis.setEnabled(true);
        xAxis.setDrawAxisLine(true);
        xAxis.setDrawGridLines(true);
       // xAxis.setCenterAxisLabels(true);
        xAxis.setTextSize(10f);
        // 设置是否绘制X轴上的对应值
        xAxis.setDrawLabels(true);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        // 设置左侧的Y
        YAxis yAxis = mChart.getAxisLeft();
        yAxis.setEnabled(true);
        yAxis.setSpaceTop(20f);
        yAxis.enableGridDashedLine(10f, 10f, 10f);
        mChart.getAxisRight().setEnabled(false); // 右侧的Y不显示

        mChart.getLegend().setEnabled(false);
        XYMarkerView xyMarkerView = new XYMarkerView(this, xAxis.getValueFormatter());
        xyMarkerView.setChartView(mChart);
        mChart.setMarker(xyMarkerView);
        setLine();
    }

    private void setLine() {
        xAxis.setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                int index = (int)value;
                return index>=0&&index<beans.size()?beans.get(index).getBean() : "";
            }
        });
        ArrayList<Entry> yValue = new ArrayList<>();
        for (int i = 0; i < beans.size(); i++) {
           // float val = (float) (Math.random() * 50) + 50;
            yValue.add(new Entry(i, beans.get(i).getHeight()));
        }
        LineDataSet set;
        if(mLineChart.getData() != null&& mLineChart.getData().getDataSetCount() >0){
            set = (LineDataSet)mLineChart.getData().getDataSetByIndex(0);
            set.setValues(yValue);
            mLineChart.getData().notifyDataChanged();
            mLineChart.notifyDataSetChanged();
        }else {
            set = new LineDataSet(yValue,null);
           // set.setAxisDependency(YAxis.AxisDependency.LEFT);
            set.setColor(ColorTemplate.getHoloBlue());
            set.setCircleColor(Color.RED);
            set.setLineWidth(2f);
            set.setCircleRadius(3f);
            set.setFillAlpha(65);
            set.setDrawFilled(true);
            set.setDrawCircles(true);
            // 设置平滑曲线模式
            set.setMode(LineDataSet.Mode.CUBIC_BEZIER);
            set.setDrawValues(true); // 设置显示点坐标值
            set.setFillColor(ColorTemplate.getHoloBlue());
            set.setHighLightColor(Color.rgb(244, 117, 117));
            set.setDrawCircleHole(true);

            set.setFormLineWidth(1f);
            set.setFormLineDashEffect(new DashPathEffect(new float[]{10f, 5f}, 0f));
            set.setFormSize(15.f);
            LineData data = new LineData(set);
            data.setValueTextColor(Color.WHITE);
            data.setValueTextSize(9f);
            mLineChart.setData(data);
        }
    }
}
