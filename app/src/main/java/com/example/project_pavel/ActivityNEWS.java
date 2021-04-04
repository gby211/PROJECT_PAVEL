package com.example.project_pavel;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class ActivityNEWS extends Activity {

    private RecyclerView myRecyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private AdapterNews adapter1;
    private ArrayList<News_cl> response;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);

        myRecyclerView = (RecyclerView) findViewById(R.id.recy_news);
        Parser_news parser = new Parser_news();
        parser.execute();
        try {
            response = parser.get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        myRecyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getApplicationContext());

        adapter1 = new AdapterNews(response);
        myRecyclerView.setLayoutManager(layoutManager);
        myRecyclerView.setAdapter(adapter1);


    }
}
