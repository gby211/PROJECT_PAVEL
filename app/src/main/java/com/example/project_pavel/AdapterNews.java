package com.example.project_pavel;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdapterNews extends RecyclerView.Adapter<AdapterNews.MyViewClass1> {

    public ArrayList<News_cl> news_cl;

    public AdapterNews(ArrayList<News_cl> news_cl) {
        this.news_cl = news_cl;
    }

    @NonNull
    @Override
    public MyViewClass1 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.news_rec, parent, false);
        AdapterNews.MyViewClass1 myViewClass1 = new AdapterNews.MyViewClass1(view);
        return myViewClass1;
    }


    @Override
    public void onBindViewHolder(@NonNull MyViewClass1 holder, int position) {

        News_cl item = news_cl.get(position);
        holder.textView_text.setText(item.getText());
        holder.textView_title.setText(item.getTitle());
        holder.picture.setImageBitmap(item.getPicture());
    }

    @Override
    public int getItemCount() {
        return news_cl.size();
    }

    public class MyViewClass1 extends RecyclerView.ViewHolder {
        TextView textView_title;
        TextView textView_text;
        ImageView picture;

        public MyViewClass1(@NonNull View itemView) {
            super(itemView);
            textView_title = (TextView) itemView.findViewById(R.id.textView_title);
            textView_text = (TextView) itemView.findViewById(R.id.textView_text);
            picture = (ImageView) itemView.findViewById(R.id.imageView5);
        }
    }
}
