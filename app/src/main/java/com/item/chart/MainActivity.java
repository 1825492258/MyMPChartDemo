package com.item.chart;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.item.chart.activity.EightActivity;
import com.item.chart.activity.FiveActivity;
import com.item.chart.activity.FourActivity;
import com.item.chart.activity.OneActivity;
import com.item.chart.activity.SevenActivity;
import com.item.chart.activity.SixActivity;
import com.item.chart.activity.TestActivity;
import com.item.chart.activity.ThreeActivity;
import com.item.chart.activity.TwoActivity;

/**
 * MPAndroidChart
 * https://github.com/PhilJay/MPAndroidChart
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.btn_one).setOnClickListener(this);
        findViewById(R.id.btn_two).setOnClickListener(this);
        findViewById(R.id.btn_three).setOnClickListener(this);
        findViewById(R.id.btn_four).setOnClickListener(this);
        findViewById(R.id.btn_five).setOnClickListener(this);
        findViewById(R.id.btn_six).setOnClickListener(this);
        findViewById(R.id.btn_seven).setOnClickListener(this);
        findViewById(R.id.btn_eight).setOnClickListener(this);
        findViewById(R.id.btn_text).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_one: // BarChart(grouped DataSets)
                startActivity(new Intent(this, OneActivity.class));
                break;
            case R.id.btn_two: // 柱状图 BarChart
                startActivity(new Intent(this, TwoActivity.class));
                break;
            case R.id.btn_three: // 饼图 PieChart
                startActivity(new Intent(this, ThreeActivity.class));
                break;
            case R.id.btn_four: // 饼图 PieChart
                startActivity(new Intent(this, FourActivity.class));
                break;
            case R.id.btn_five: // 折线图 LineChart
                startActivity(new Intent(this, FiveActivity.class));
                break;
            case R.id.btn_six: // ListView
                startActivity(new Intent(this, SixActivity.class));
                break;
            case R.id.btn_seven: // 混合图
                startActivity(new Intent(this, SevenActivity.class));
                break;
            case R.id.btn_eight: // 外面有个ScrollView
                startActivity(new Intent(this, EightActivity.class));
                break;
            case R.id.btn_text:
                startActivity(new Intent(this, TestActivity.class));
                break;
        }
    }
}
