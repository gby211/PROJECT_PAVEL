package com.example.project_pavel;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdapterMy extends RecyclerView.Adapter<AdapterMy.MyViewClass> {


    private ArrayList<DataCom> dataComs;
    public AdapterMy(ArrayList<DataCom> data){
        dataComs = data;
    }


    @NonNull
    @Override
    public AdapterMy.MyViewClass onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.stocks_recycler_view, parent, false);
        MyViewClass myViewClass = new MyViewClass(view);
        return myViewClass;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull MyViewClass holder, int position) {
        DataCom item = dataComs.get(position);

        holder.name_com.setText(item.getName_com());
        holder.change_price.setText(item.getChange_price());
        holder.favourite.setChecked(item.getFavourite());
        holder.price_com.setText(item.getPrice_com());
        holder.tiket.setText(item.getTiket());



        if (item.getChange_price().charAt(0) == '-' ){
            holder.change_price.setTextColor(Color.RED);
        }
        else {
            holder.change_price.setTextColor(Color.GREEN);
        }
    }


    @Override
    public int getItemCount() {
        return dataComs.size();
    }

    public class MyViewClass extends RecyclerView.ViewHolder {
        TextView name_com;
        TextView tiket;
        TextView price_com;
        TextView change_price;
        CheckBox favourite;

        public MyViewClass(@NonNull View itemView) {
            super(itemView);
            name_com = (TextView) itemView.findViewById(R.id.textView_name_com);
            tiket = (TextView) itemView.findViewById(R.id.textView_tiket);
            price_com = (TextView) itemView.findViewById(R.id.textView_price_com);
            change_price = (TextView) itemView.findViewById(R.id.textView_change_price);
            favourite = (CheckBox) itemView.findViewById(R.id.checkBox_favourite);
        }
    }
}
