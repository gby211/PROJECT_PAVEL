package com.example.project_pavel;
/////////////////////////
//пожалуйста прочитайте README
/////////////////////////

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.Toolbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;

import static com.example.project_pavel.Entry.IDUSER;

public class MainActivity extends AppCompatActivity {

    public static String KEY_FINNHUB = "c13njrv48v6qin45q270";
    private ArrayList<DataCom> response;
    private RecyclerView myRecyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.Adapter adapter;

    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;

    private ArrayList<String> arrayList;

    public static String user_name_now;
    public static ArrayList<String> favourite_data;
    public static ArrayList<DataCom> DataCom_favourite_data;
    public static String search_result;
    public static String search_text;

    Button admBtn;
    EditText editText;
    StocksFragment fragS;
    public FavouriteFragment fragF;
    androidx.fragment.app.FragmentTransaction fTrans;


    private DatabaseReference mDataBase;
    private String USER = "user";
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        Log.d("ggs","sdsdsdasaasdasdasdasasddsasadasdasdsadadsadssadsad");
//
//        if (isOnline(this)) {
//            Intent intent = new Intent(MainActivity.this, Entry.class);
//
//            startActivity(intent);
//        }
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        if (isOnline(this)) {
            setContentView(R.layout.activity_main);
            getIntentM();

            favourite_data = readFileFavourite(this);
//        favourite_data.clear();
//        writeFileFavourite(favourite_data,this);


            //////////////////////////////////

            fragF = new FavouriteFragment();
            fragS = new StocksFragment(fragF);

            fragF.setStocksFragment1(fragS);

            //////////////////////////////////


            editText = findViewById(R.id.editTextTextPersonName);


            viewPager = (ViewPager) findViewById(R.id.viewPager);
            setupViewPager(viewPager);

            tabLayout = (TabLayout) findViewById(R.id.tabLayout);
            tabLayout.setupWithViewPager(viewPager);
        } else {
            setContentView(R.layout.activity_fail);
        }


    }



    //TODO
    public void getIntentM()
    {
        admBtn = findViewById(R.id.adm_btn);
        ArrayList<String> tmpp = new ArrayList<>();

        Intent i = getIntent();
        Log.d("gogo",i.getStringExtra("admin"));
        if (!i.getStringExtra("admin").equals("1")){
            admBtn.setVisibility(View.GONE);
        }
        user_name_now = i.getStringExtra("user_name");
        String str = i.getStringExtra("fCom");
        String[] words = str.split(" ");
        for (String wo : words){
            tmpp.add(wo);
        }
        writeFileFavourite(tmpp,this);
    }

    public static boolean isOnline(Context context) {
        ConnectivityManager cm =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        if (netInfo != null && netInfo.isConnectedOrConnecting()) {
            return true;
        }
        return false;
    }


    public void serch_com(View view) {
        search_text = editText.getText().toString();
        if (search_text.equals("")) {
            Toast.makeText(this, "Enter company name", Toast.LENGTH_SHORT).show();
        } else {
            Intent intent = new Intent(MainActivity.this, ActivitySerch.class);

            startActivity(intent);
        }
    }

    public void NEWS(View view) {
        Intent intent = new Intent(MainActivity.this, ActivityNEWS.class);

        startActivity(intent);
    }


    public static void writeFileFavourite(ArrayList<String> arrayList, Context context) {

        try {
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(context.openFileOutput("favourite.txt", MODE_PRIVATE)));
            for (int i = 0; i < arrayList.size(); i++) {
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

    public static ArrayList<String> readFileFavourite(Context context) {
        String str = "";
        ArrayList<String> gg = new ArrayList<>();
        try {

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


    @Override
    protected void onDestroy() {
        super.onDestroy();
        mDataBase = FirebaseDatabase.getInstance().getReference(USER).child(IDUSER).child("fCom");
        String tmp = "";
        for (String fa : favourite_data){
            tmp += fa+" ";
        }
        mDataBase.setValue(tmp.trim());
        finish();
    }

    public void adminPan(View view){
        Intent intent = new Intent(MainActivity.this, Delate.class);
        startActivity(intent);
    }
    public void signout(View view){
        Intent intent = new Intent(MainActivity.this, Entry.class);
        startActivity(intent);
        finish();
    }
}