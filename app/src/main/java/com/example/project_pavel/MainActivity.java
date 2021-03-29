package com.example.project_pavel;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toolbar;

import com.google.android.material.tabs.TabLayout;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

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

    public static ArrayList<String> favourite_data;
    public static ArrayList<DataCom> DataCom_favourite_data;
    public static String search_result;
    public static String search_text;



    EditText editText ;
    StocksFragment fragS;
    public FavouriteFragment fragF;
    androidx.fragment.app.FragmentTransaction fTrans;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        favourite_data = readFileFavourite(this);
//        favourite_data.clear();
//        writeFileFavourite(favourite_data,this);


        //////////////////////////////////

        fragF = new FavouriteFragment();
        fragS = new StocksFragment(fragF);

        fragF.setStocksFragment1(fragS);

        //////////////////////////////////


        editText = findViewById(R.id.editTextTextPersonName);

//        Log.d("pepe",favourite_data.toString());
//        favourite_data.clear();
//        writeFileFavourite(favourite_data,this);
//        favourite_data.add("FB");
//        favourite_data.add("INTC");


        viewPager = (ViewPager) findViewById(R.id.viewPager);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        tabLayout.setupWithViewPager(viewPager);

    }


    public void serch_com(View view) {
        search_text = editText.getText().toString();
        Intent intent = new Intent(MainActivity.this, ActivitySerch.class);

        startActivity(intent);
    }
    public void NEWS(View view) {
        Intent intent = new Intent(MainActivity.this, ActivityNEWS.class);

        startActivity(intent);
    }


    public static void writeFileFavourite(ArrayList<String> arrayList, Context context){
        try{
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(context.openFileOutput("favourite.txt", MODE_PRIVATE)));
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

    public static ArrayList<String> readFileFavourite(Context context){
        String str = "";
        ArrayList<String> gg = new ArrayList<>();
        try{

            BufferedReader br = new BufferedReader(new InputStreamReader(context.openFileInput("favourite.txt")));

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




    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());

        adapter.addFragment(fragS, "Stocks");
        adapter.addFragment(fragF, "Favourite");
        viewPager.setAdapter(adapter);
    }

}