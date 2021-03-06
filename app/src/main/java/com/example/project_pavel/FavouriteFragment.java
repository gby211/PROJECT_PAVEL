package com.example.project_pavel;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import static com.example.project_pavel.MainActivity.favourite_data;

public class FavouriteFragment extends Fragment {

    public static final String ARG_PAGE = "ARG_PAGE";

    private int mPage;

    private ArrayList<DataCom> response;
    private RecyclerView myRecyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private static AdapterMy adapter;
    public static StocksFragment stocksFragment1;

    public void setStocksFragment1(StocksFragment stocksFragment1) {
        this.stocksFragment1 = stocksFragment1;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_favourite, container, false);
        View view = inflater.inflate(R.layout.fragment_favourite, container, false);

        String[] start_T = new String[favourite_data.size()];
        for (int i = 0; i < favourite_data.size(); i++) {
            start_T[i] = favourite_data.get(i);
        }

        Log.d("pup", start_T.toString());
        myRecyclerView = (RecyclerView) view.findViewById(R.id.list_favourite_F);
        Parser parser = new Parser();
        parser.execute(start_T);
        try {
            response = parser.get();
        } catch (
                ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //System.out.println(response);
//        myRecyclerView = container.getRootView().findViewById(R.id.list_stocks_F);
        myRecyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getContext());

        adapter = new AdapterMy(response);
        adapter.setStocksFragment(stocksFragment1);
        adapter.setFavouriteFragment(this);


        myRecyclerView.setLayoutManager(layoutManager);
        myRecyclerView.setAdapter(adapter);


        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mPage = getArguments().getInt(ARG_PAGE);
        }
    }

    //////////////////////////////////
    public void addData(DataCom dataCom) {
        adapter.dataComs.add(dataCom);
        adapter.notifyDataSetChanged();
    }

    public void delData(DataCom dataCom) {
        adapter.dataComs.remove(dataCom);
        adapter.notifyDataSetChanged();
    }
    //////////////////////////////////
}
