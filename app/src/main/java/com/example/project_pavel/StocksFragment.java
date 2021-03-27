package com.example.project_pavel;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class StocksFragment  extends Fragment {

    RecyclerView recyclerView;
    ArrayList<String> name_com = new ArrayList<>();
    ArrayList<String> tiket = new ArrayList<>();
    ArrayList<Double> price_com = new ArrayList<>();
    ArrayList<String> change_price = new ArrayList<>();
    ArrayList<Boolean> favourite = new ArrayList<>();
    private String[] start_tiket_str = new String[]{"AAPL", "MSFT", "AMZN", "FB"};
    private ArrayList<String> start_tiket = new ArrayList<>();
    private String KEY_FINNHUB = "c13njrv48v6qin45q270";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_stocks, container, false);
        View view = inflater.inflate(R.layout.fragment_stocks, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.list_stocks_F);





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
}
