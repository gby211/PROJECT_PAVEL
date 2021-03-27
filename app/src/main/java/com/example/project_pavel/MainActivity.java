package com.example.project_pavel;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toolbar;

import com.google.android.material.tabs.TabLayout;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {

    public static  String KEY_FINNHUB = "c13njrv48v6qin45q270";
    private ArrayList<DataCom> response;
    private RecyclerView myRecyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.Adapter adapter;

    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;

    private ArrayList<String> arrayList;

    StocksFragment fragS;
    FavouriteFragment fragF;
    androidx.fragment.app.FragmentTransaction fTrans;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




        viewPager = (ViewPager) findViewById(R.id.viewPager);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        tabLayout.setupWithViewPager(viewPager);


    }


    void writeFileFavourite(ArrayList<String> arrayList){
        try{
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(
                    openFileOutput("favourite", MODE_PRIVATE)));
            for (int i =0 ; i < arrayList.size();i++){
                bw.write(arrayList.get(i));
                bw.write("\n");
            }
            bw.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<String> readFileFavourite(){
        String str = "";
        ArrayList<String> gg = new ArrayList<>();
        try{

            BufferedReader br = new BufferedReader(new InputStreamReader(openFileInput("favourite")));

            // читаем содержимое
            while ((str = br.readLine()) != null) {
                gg.add(str);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return gg;
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


    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new StocksFragment(), "Stocks");
        adapter.addFragment(new FavouriteFragment(), "Favourite");
        viewPager.setAdapter(adapter);
    }

}