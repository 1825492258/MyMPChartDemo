package com.item.chart.custom;

/**
 * Created by Administrator on 2018/3/20 0020.
 */

public class Bean {
    private String bean;
    private float height;

    public Bean(String bean, float height) {
        this.bean = bean;
        this.height = height;
    }

    public String getBean() {
        return bean;
    }

    public void setBean(String bean) {
        this.bean = bean;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }
}
