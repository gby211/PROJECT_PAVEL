package com.example.project_pavel;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

public class Parser_about_company extends AsyncTask<String, Void, ArrayList<GraphData>> {

    ArrayList<GraphData> res = new ArrayList<>();
    ArrayList<Double> price = new ArrayList<>();
    ArrayList<Date> time_p = new ArrayList<>();
    URLC responseFromURL = new URLC();


    @Override
    protected ArrayList<GraphData> doInBackground(String... strings_start) {
        try {
            String response = "";
            String url = "";

            String symbol = strings_start[0];
            String key = "c13njrv48v6qin45q270";

            Date date = new Date();

            Log.d("time", String.valueOf(date.getTime()));


            Long time_initial = date.getTime() / 1000 - 2629743;
            Long time_end = date.getTime() / 1000;

            url = "https://finnhub.io/api/v1//stock/candle?symbol=" + symbol + "&resolution=30&from=" + time_initial + "&to=" + time_end + "&token=" + key;

            Log.d("url", url);

            response = responseFromURL.Connection(url);

            JSONObject jsonObject = new JSONObject(response);
            //System.out.println();

            JSONArray arr = jsonObject.getJSONArray("o");
            for (int i = 0; i < arr.length(); i++) {
                price.add((Double) arr.getDouble(i));
            }
            arr = jsonObject.getJSONArray("t");
            for (int i = 0; i < arr.length(); i++) {

                time_p.add(new Date(arr.getLong(i)));
            }

            for (int i = 0; i < price.size(); i++) {
                res.add(new GraphData(price.get(i), time_p.get(i)));
            }


        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
        return res;
    }

    @Override
    protected void onPostExecute(ArrayList<GraphData> ff) {
        super.onPostExecute(ff);
    }


}
