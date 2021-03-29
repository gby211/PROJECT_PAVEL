package com.example.project_pavel;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;


import static com.example.project_pavel.MainActivity.search_result;
import static com.example.project_pavel.MainActivity.search_text;

public class ActivitySerch extends Activity {

    private RecyclerView myRecyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private static AdapterMy adapter;
    private String str;
    private  ArrayList<String> arrayList_tiker;
    private ArrayList<DataCom> response;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        try {
            str = search_text;
        }catch (Exception e){

            e.printStackTrace();
        }

        myRecyclerView = (RecyclerView) findViewById(R.id.list_serch);
        Parser_com parser = new Parser_com();
        parser.execute(str);
        try {
            arrayList_tiker = parser.get();

        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String [] list_tiker = null;
        try {
            list_tiker = new String[arrayList_tiker.size()];
        }catch (Exception e){

        }


        for (int i = 0; i < arrayList_tiker.size();i++){
            list_tiker[i] = arrayList_tiker.get(i);
        }

        Parser parser1 = new Parser();
        parser1.execute(list_tiker);
        try {
            response = parser1.get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        myRecyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getApplicationContext());

        adapter = new AdapterMy(response); // TODO добавить ссетором фрагмент

        myRecyclerView.setLayoutManager(layoutManager);
        myRecyclerView.setAdapter(adapter);
    }
}
