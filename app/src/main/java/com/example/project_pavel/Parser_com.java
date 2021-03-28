package com.example.project_pavel;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import static com.example.project_pavel.MainActivity.favourite_data;

public class Parser_com extends AsyncTask<String, Void, ArrayList<String>> {

    ArrayList<String> tiker = new ArrayList<>();

    URLC responseFromURL = new URLC();


    @Override
    protected ArrayList<String> doInBackground(String... strings_start) {
        try {
            String response = "";
            String url = "";

            String symbol = strings_start[0];
            String key = "c13njrv48v6qin45q270";
            url = "https://finnhub.io/api/v1/search?q=" + symbol + "&token=" + key;

            response = responseFromURL.Connection(url);

            JSONObject jsonObject = new JSONObject(response);
            //System.out.println();
            JSONArray arr = jsonObject.getJSONArray("result");
            for (int i = 0; i < arr.length() || i < 5 ;i++ ){

                JSONObject object = arr.getJSONObject(i);
                String tiker_com = object.getString("symbol");
                if (tiker_com.contains(".")){
                    continue;
                }else {

                }
                tiker.add(tiker_com);

            }

        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
        return tiker;
    }

    @Override
    protected void onPostExecute(ArrayList<String> tiker) {
        super.onPostExecute(tiker);
    }



}