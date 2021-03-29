package com.example.project_pavel;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import static com.example.project_pavel.MainActivity.favourite_data;
import static com.example.project_pavel.MainActivity.writeFileFavourite;

public class AdapterMy extends RecyclerView.Adapter<AdapterMy.MyViewClass> {

    FavouriteFragment favouriteFragment;
    public ArrayList<DataCom> dataComs;
    public AdapterMy(ArrayList<DataCom> data){
        dataComs = data;
    }

    public void setFavouriteFragment(FavouriteFragment favouriteFragment) {
        this.favouriteFragment = favouriteFragment;
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



//        if (item  == false){
//
//        }

        holder.favourite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (holder.favourite.isChecked()){
                    Log.d("pepe",favourite_data.toString()+"  до");
                    item.setFavourite(true);
                    favourite_data.add(item.getTiker());
                    writeFileFavourite(favourite_data,holder.itemView.getContext());
                    Log.d("pepe",favourite_data.toString()+"  добавление");

                    //////////////////////////////////

                    favouriteFragment.addData(item);

                    //////////////////////////////////
                } else {
                    Log.d("pepe",favourite_data.toString()+"  до");
                    for (String com: favourite_data) {
                        if (com.equals(item.getTiker().toString())) {

                            item.setFavourite(false);
                            favourite_data.remove(com);
                            writeFileFavourite(favourite_data,holder.itemView.getContext());


                            //////////////////////////////////

                            favouriteFragment.delData(item);


                            //////////////////////////////////

                            break;
                        }
                    }
                    Log.d("pepe",favourite_data.toString()+"  удаление");
                }

            }
        });


        holder.name_com.setText(item.getName_com());
        holder.change_price.setText(item.getChange_price());
        holder.favourite.setChecked(item.getFavourite());
        holder.price_com.setText(item.getPrice_com());
        holder.tiker.setText(item.getTiker());
        holder.picture.setImageBitmap(item.getPicture());

//        holder.favourite.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                if(isChecked){
//                    Log.d("pepe",favourite_data.toString()+"  до");
//                    favourite_data.add(item.getTiker());
//                    writeFileFavourite(favourite_data,holder.itemView.getContext());
//                    Log.d("pepe",favourite_data.toString()+"  добавление");
//
//                    //////////////////////////////////
//
//                    favouriteFragment.addData(item);
//
//                    //////////////////////////////////
//                }
//                else {
//                    Log.d("pepe",favourite_data.toString()+"  до");
//                    for (String com: favourite_data) {
//                        if (com.equals(item.getTiker().toString())) {
//                            favourite_data.remove(com);
//                            writeFileFavourite(favourite_data,holder.itemView.getContext());
//
//
//                            //////////////////////////////////
//
//                            favouriteFragment.delData(item);
//
//
//                            //////////////////////////////////
//
//                            break;
//                        }
//                    }
//                    Log.d("pepe",favourite_data.toString()+"  удаление");
//                }
//
//            }
//        }
//        );


        if (item.getChange_price().charAt(0) == '-'){
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
        TextView tiker;
        TextView price_com;
        TextView change_price;
        CheckBox favourite;
        ImageView picture;

        public MyViewClass(@NonNull View itemView) {
            super(itemView);
            name_com = (TextView) itemView.findViewById(R.id.textView_name_com);
            tiker = (TextView) itemView.findViewById(R.id.textView_tiket);
            price_com = (TextView) itemView.findViewById(R.id.textView_price_com);
            change_price = (TextView) itemView.findViewById(R.id.textView_change_price);
            favourite = (CheckBox) itemView.findViewById(R.id.checkBox_favourite);
            picture = (ImageView) itemView.findViewById(R.id.imageView3);
        }
    }

}
