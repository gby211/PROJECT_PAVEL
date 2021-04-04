package com.example.project_pavel;

import android.util.Log;

import java.util.Date;

public class GraphData {
    private double price;
    private Date time;

    public GraphData(double price, Date time) {
        this.price = price;
        this.time = time;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "GraphData{" +
                "price=" + price +
                ", time=" + time +
                '}';
    }
}
