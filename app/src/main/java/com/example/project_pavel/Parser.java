package com.example.project_pavel;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class Parser extends AsyncTask<Void, Void, ArrayList<DataCom>> {

    ArrayList<String> price_com = new ArrayList<>();
    ArrayList<String> change_price = new ArrayList<>();

    URLC responseFromURL = new URLC();
    private String[] start_tiket_str = new String[]{"AAPL", "MSFT", "AMZN", "FB", "GOOG", "INTC", "AAPL", "MSFT", "AMZN", "FB", "GOOG", "INTC"};

    ArrayList<DataCom> dataComs = new ArrayList<>();

    @Override
    protected ArrayList<DataCom> doInBackground(Void... voids) {
        try {
            String response = "";
            String url = "";
            for (int i = 0; i < start_tiket_str.length; i++) {
                String nameCom = start_tiket_str[i];
                String key = "c13njrv48v6qin45q270";
                url = "https://finnhub.io/api/v1/quote?symbol=" + nameCom + "&token=" + key;

                response = responseFromURL.Connection(url);

                JSONObject jsonObject = new JSONObject(response);
                System.out.println();
                String pr_com = "$"+jsonObject.get("c").toString();
                price_com.add(pr_com);
                Double tmpc = (double) jsonObject.get("c");
                Double tmppc = (double) jsonObject.get("pc");
                Double change_price_tmp = tmpc - tmppc ;
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
            }
            for (int i = 0; i<start_tiket_str.length;i++){
                dataComs.add(new DataCom(start_tiket_str[i],start_tiket_str[i],price_com.get(i),change_price.get(i),false));
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
