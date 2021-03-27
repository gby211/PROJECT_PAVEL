package com.example.project_pavel;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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

public class FavouriteFragment  extends Fragment {

    public static final String ARG_PAGE = "ARG_PAGE";

    private int mPage;

    private ArrayList<DataCom> response;
    private RecyclerView myRecyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.Adapter adapter;
    private String[] start_tiket_str = new String[]{"AAPL","MCD"};


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_favourite, container, false);
        View view = inflater.inflate(R.layout.fragment_favourite, container, false);

        myRecyclerView = (RecyclerView) view.findViewById(R.id.list_favourite_F);
        Parser parser = new Parser();
        parser.execute(start_tiket_str);
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


    public ArrayList<String> readFileFavourite(){
        String str = "";
        ArrayList<String> gg = new ArrayList<>();
        try{

            BufferedReader br = new BufferedReader(new InputStreamReader(getContext().openFileInput("favourite")));

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
