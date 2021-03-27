package com.example.project_pavel;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toolbar;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import static android.content.Context.MODE_PRIVATE;

public class StocksFragment  extends Fragment {

    public static final String ARG_PAGE = "ARG_PAGE";

    private int mPage;

    public static  String KEY_FINNHUB = "c13njrv48v6qin45q270";
    private ArrayList<DataCom> response;
    private RecyclerView myRecyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.Adapter adapter;

    private Toolbar toolbar;


    private String[] start_tiket_str = new String[]{"TSLA","AAPL", "MSFT", "AMZN", "FB", "INTC","KO","ORCL","NVDA","NFLX","GE"};
//    RecyclerView recyclerView;
//    ArrayList<String> name_com = new ArrayList<>();
//    ArrayList<String> tiker = new ArrayList<>();
//    ArrayList<Double> price_com = new ArrayList<>();
//    ArrayList<String> change_price = new ArrayList<>();
//    ArrayList<Boolean> favourite = new ArrayList<>();
//    private String[] start_tiket_str = new String[]{"AAPL", "MSFT", "AMZN", "FB"};
//    private ArrayList<String> start_tiket = new ArrayList<>();
//    private String KEY_FINNHUB = "c13njrv48v6qin45q270";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_stocks, container, false);
        View view = inflater.inflate(R.layout.fragment_stocks, container, false);


        myRecyclerView = (RecyclerView) view.findViewById(R.id.list_stocks_F);
        Parser parser = new Parser();
        parser.execute(start_tiket_str);
        try {
            response = parser.get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //System.out.println(response);
//        myRecyclerView = container.getRootView().findViewById(R.id.list_stocks_F);
        myRecyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getContext());
        adapter = new AdapterMy(response);

        myRecyclerView.setLayoutManager(layoutManager);
        myRecyclerView.setAdapter(adapter);



//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
//        recyclerView.setLayoutManager(linearLayoutManager);
//        start_tiket.addAll(Arrays.asList(start_tiket_str));
//
//
//
//        System.out.println(change_price);
//        System.out.println(start_tiket);
//        AdapterMy adapterMy = new AdapterMy(start_tiket, start_tiket,price_com,change_price,favourite);
//        recyclerView.setAdapter(adapterMy);
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mPage = getArguments().getInt(ARG_PAGE);
        }
    }

    public ArrayList<String> readFileFavourite(){
        String str = "";
        ArrayList<String> favourite_list = new ArrayList<>();
        try{

            BufferedReader br = new BufferedReader(new InputStreamReader(getContext().openFileInput("favourite")));
            while ((str = br.readLine()) != null) {
                favourite_list.add(str);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return favourite_list;
    }

    public void writeFileFavourite(ArrayList<String> arrayList){
        try{
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(
                    getContext().openFileOutput("favourite", MODE_PRIVATE)));
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
}
