package com.example.project_pavel;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.ExecutionException;

public class ActivityCom extends AppCompatActivity {


    private LineChart mChart;
    private TextView textView_name_com2;
    private TextView textView_tiket2;
    private TextView textView_change_price2;
    private TextView textView_price_com2;
    private String name_com, change_price, price_com, tiker;
    private ArrayList<GraphData> response;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_com);

        textView_name_com2 = findViewById(R.id.textView_name_com2);
        textView_tiket2 = findViewById(R.id.textView_tiket2);
        textView_change_price2 = findViewById(R.id.textView_change_price2);
        textView_price_com2 = findViewById(R.id.textView_price_com2);

        name_com = getIntent().getStringExtra("name_com");
        change_price = getIntent().getStringExtra("change_price");
        price_com = getIntent().getStringExtra("price_com");
        tiker = getIntent().getStringExtra("tiker");

        textView_name_com2.setText(name_com);
        textView_tiket2.setText(tiker);
        textView_change_price2.setText(change_price);
        textView_price_com2.setText(price_com);

        if (change_price.charAt(0) == '-') {
            textView_change_price2.setTextColor(Color.RED);
        } else {
            textView_change_price2.setTextColor(Color.GREEN);
        }


        mChart = (LineChart) findViewById(R.id.graph);

        mChart.setDrawGridBackground(true);
        mChart.setDrawBorders(true);
        mChart.setTouchEnabled(true);
        mChart.setDragEnabled(true);
        mChart.setScaleEnabled(true);
        mChart.getDescription().setEnabled(false);
        mChart.setPinchZoom(false);


        XAxis x = mChart.getXAxis();
        //x.setEnabled(false);


        YAxis y = mChart.getAxisRight();
        //y.setTypeface(tfLight);
        y.setLabelCount(6, false);
        y.setTextColor(Color.rgb(0, 0, 0));
        y.setPosition(YAxis.YAxisLabelPosition.INSIDE_CHART);
        y.setDrawGridLines(false);
        y.setAxisLineColor(Color.WHITE);
        y.setTextSize(50);


        YAxis y1 = mChart.getAxisLeft();
        y1.setEnabled(false);

        Legend l = mChart.getLegend();
        l.setEnabled(false);


        Parser_about_company parser = new Parser_about_company();
        parser.execute(tiker);
        try {
            response = parser.get();
        } catch (
                ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        ArrayList<Entry> yValues = new ArrayList<>();

        for (int i = 0; i < response.size(); i++) {
            GraphData data = response.get(i);
            float fp1 = (float) data.getPrice();
            Date fp2 = (Date) data.getTime();
            yValues.add(new Entry(fp2.getTime() / 1000, fp1));

        }


        LineDataSet set1 = new LineDataSet(yValues, "Data set 1");


        set1.setColor(Color.rgb(252, 182, 5));
        set1.setDrawCircles(false);
        set1.setDrawFilled(true);
        set1.setLineWidth(1f);
        set1.setFillColor(Color.rgb(102, 102, 102));
        set1.setFillAlpha(200);
        set1.setCircleColor(Color.rgb(102, 102, 102));
        set1.setGradientColor(Color.rgb(102, 102, 102), Color.rgb(61, 61, 61));


        ArrayList<ILineDataSet> dataSets = new ArrayList<>();
        dataSets.add(set1);

        LineData data = new LineData(dataSets);
        data.setDrawValues(false);

        mChart.invalidate();
        mChart.setData(data);

    }

}