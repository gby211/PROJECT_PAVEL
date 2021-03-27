package com.example.project_pavel;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {

    public static  String KEY_FINNHUB = "c13njrv48v6qin45q270";
    private ArrayList<DataCom> response;
    private RecyclerView myRecyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.Adapter adapter;

    StocksFragment fragS;
    FavouriteFragment fragF;
    androidx.fragment.app.FragmentTransaction fTrans;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fragF = new FavouriteFragment();
        fragS = new StocksFragment();
//        gg();

        Parser parser = new Parser();
        parser.execute();
        try {
            response = parser.get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //System.out.println(response);
        myRecyclerView = findViewById(R.id.list_stocks);
        myRecyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        adapter = new AdapterMy(response);

        myRecyclerView.setLayoutManager(layoutManager);
        myRecyclerView.setAdapter(adapter);


    }


    void writeFileFavourite(String string){
        try{
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(
                    openFileOutput("favourite", MODE_APPEND)));
            bw.append(string);
            bw.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


//    public void gg() {
//        fTrans = getSupportFragmentManager().beginTransaction();
//        fTrans.replace(R.id.fr_place, fragS);
//        fTrans.commit();
//    }


//    @SuppressLint("NonConstantResourceId")
//    public void fragmentChange(View view) {
//        fTrans = getSupportFragmentManager().beginTransaction();
//
//
//        switch (view.getId()) {
//            case R.id.button_favorite:
//                fTrans.replace(R.id.fr_place, fragF);
//                break;
//            case R.id.button_stocks:
//                fTrans.replace(R.id.fr_place, fragS);
//                break;
//            default:
//                break;
//        }
//        fTrans.commit();
//    }

}