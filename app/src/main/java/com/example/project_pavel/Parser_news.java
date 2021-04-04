package com.example.project_pavel;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class Parser_news extends AsyncTask<Void, Void, ArrayList<News_cl>> {

    ArrayList<News_cl> news_cls = new ArrayList<>();

    URLC responseFromURL = new URLC();

    ArrayList<String> text = new ArrayList<>();
    ArrayList<String> title = new ArrayList<>();
    ArrayList<Bitmap> picture = new ArrayList<>();


    @Override
    protected ArrayList<News_cl> doInBackground(Void... voids) {
        try {
            String response = "";
            String url = "";

            String key = "c13njrv48v6qin45q270";
            url = "https://finnhub.io/api/v1/news?category=general&token=" + key;

            response = responseFromURL.Connection(url);
            JSONArray jsonArray = new JSONArray(response);
            for (int i = 0; i < 10; i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String title__ = jsonObject.getString("headline");
                String text__ = jsonObject.getString("summary");
                Bitmap icon = null;
                String url_icon = jsonObject.getString("image");
                try {
                    InputStream in = new java.net.URL(url_icon).openStream();
                    icon = BitmapFactory.decodeStream(in);
                    icon = Bitmap.createScaledBitmap(icon, 160, 90, true);
                } catch (OutOfMemoryError e) {
                    e.printStackTrace();
                }

                text.add(text__);
                title.add(title__);
                picture.add(icon);


            }
            for (int i = 0; i < text.size(); i++) {
                news_cls.add(new News_cl(text.get(i), title.get(i), picture.get(i)));
            }

        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
        return news_cls;
    }

    @Override
    protected void onPostExecute(ArrayList<News_cl> news_cls) {
        super.onPostExecute(news_cls);
    }


}
