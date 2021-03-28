package com.example.project_pavel;

import android.graphics.Bitmap;

public class DataCom {
    private String name_com;
    private String tiker;
    private String price_com;
    private String change_price;
    private Boolean favourite;
    private Bitmap picture;

    public DataCom(String name_com, String tiker, String price_com, String change_price, Boolean favourite, Bitmap picture) {
        this.name_com = name_com;
        this.tiker = tiker;
        this.price_com = price_com;
        this.change_price = change_price;
        this.favourite = favourite;
        this.picture = picture;
    }

    public Bitmap getPicture() {
        return picture;
    }

    public void setPicture(Bitmap picture) {
        this.picture = picture;
    }

    public String getName_com() {
        return name_com;
    }

    public void setName_com(String name_com) {
        this.name_com = name_com;
    }

    public String getTiker() {
        return tiker;
    }

    public void setTiker(String tiker) {
        this.tiker = tiker;
    }

    public String getPrice_com() {
        return price_com;
    }

    public void setPrice_com(String price_com) {
        this.price_com = price_com;
    }

    public String getChange_price() {
        return change_price;
    }

    public void setChange_price(String change_price) {
        this.change_price = change_price;
    }

    public Boolean getFavourite() {
        return favourite;
    }

    public void setFavourite(Boolean favourite) {
        this.favourite = favourite;
    }

    @Override
    public String toString() {
        return "DataCom{" +
                "name_com='" + name_com + '\'' +
                ", tiker='" + tiker + '\'' +
                ", price_com='" + price_com + '\'' +
                ", change_price='" + change_price + '\'' +
                ", favourite=" + favourite +
                ", picture=" + picture +
                '}';
    }
}
