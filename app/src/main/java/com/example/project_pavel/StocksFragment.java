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

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class StocksFragment extends Fragment {

    public static final String ARG_PAGE = "ARG_PAGE";

    private int mPage;

    private ArrayList<DataCom> response;
    private RecyclerView myRecyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private AdapterMy adapter;

    private Toolbar toolbar;

    public static FavouriteFragment favouriteFragment1;


    private String[] start_tiket_str = new String[]{"TSLA", "AAPL", "MSFT", "AMZN", "FB", "INTC", "KO", "ORCL", "NVDA", "NFLX", "GE"};


    public StocksFragment(FavouriteFragment favouriteFragment) {
        favouriteFragment1 = favouriteFragment;
    }

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
        adapter.setFavouriteFragment(favouriteFragment1);
        adapter.setStocksFragment(this);

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

    public void appdate() {
        adapter.notifyDataSetChanged();
    }

}
