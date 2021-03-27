package com.example.project_pavel;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;

import static com.example.project_pavel.MainActivity.favourite_data;

public class Parser extends AsyncTask<String, Void, ArrayList<DataCom>> {

    ArrayList<String> price_com = new ArrayList<>();
    ArrayList<String> change_price = new ArrayList<>();
    ArrayList<String> name_com = new ArrayList<>();
    ArrayList<Bitmap> icon_com = new ArrayList<>();
    ArrayList<Boolean> favourite = new ArrayList<>();

    URLC responseFromURL = new URLC();

    ArrayList<DataCom> dataComs = new ArrayList<>();

    @Override
    protected ArrayList<DataCom> doInBackground(String... strings_start) {
        try {
            String response = "";
            String url = "";
            for (int i = 0; i < strings_start.length; i++) {
                String nameCom = strings_start[i];
                String key = "c13njrv48v6qin45q270";
                url = "https://finnhub.io/api/v1/quote?symbol=" + nameCom + "&token=" + key;

                response = responseFromURL.Connection(url);

                JSONObject jsonObject = new JSONObject(response);
                //System.out.println();
                String pr_com = "$"+jsonObject.get("c").toString();
                price_com.add(pr_com);
                Double tmpc = (double) jsonObject.get("c");
                Double tmppc = (double) jsonObject.get("pc");
                Double change_price_tmp = tmpc - tmppc;
                String sign = "";

                if (change_price_tmp < 0){
                    sign = "-";
                    change_price_tmp = Math.abs(change_price_tmp);
                }


                String r1 = String.format("%.2f",change_price_tmp);
                Double change_price_tmp2 = ((tmpc-tmppc)/tmppc)*100;

                Log.d("gg",change_price_tmp2.toString());

                String r2 = String.format("%.2f",Math.abs(change_price_tmp2));
                String change_price_final = sign+"$"+r1+" ("+r2+"%)";
                change_price.add(change_price_final);

                url = "https://finnhub.io/api/v1/stock/profile2?symbol="+nameCom+"&token="+key;
                response = responseFromURL.Connection(url);
                JSONObject jsonObject_com = new JSONObject(response);
                String com_name =  jsonObject_com.get("name").toString();

                com_name = com_name.substring(0,com_name.length()-4);
                name_com.add(com_name);
                Bitmap icon = null;
                String url_icon = jsonObject_com.getString("logo");

                try {
                    InputStream in = new java.net.URL(url_icon).openStream();
                    icon = BitmapFactory.decodeStream(in);
                }catch (IOException e){
                    e.printStackTrace();
                }


                icon_com.add(icon);
                if (favourite_data.contains(strings_start[i])){
                    favourite.add(true);
                }else {
                    favourite.add(false);
                }
                Log.d("FAVOURITE",favourite.toString());
            }

            for (int i = 0; i<strings_start.length;i++){
                dataComs.add(new DataCom(name_com.get(i),strings_start[i],price_com.get(i),change_price.get(i),favourite.get(i),icon_com.get(i)));
            }

        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
        for (int i =0;i<dataComs.size();i++){
            Log.d("gg",dataComs.get(i).getName_com());
        }
        return dataComs;
    }

    @Override
    protected void onPostExecute(ArrayList<DataCom> dataComs) {
        super.onPostExecute(dataComs);
    }



}
