package com.example.project_pavel;

public class DataCom {
    private String name_com;
    private String tiket;
    private String price_com;
    private String change_price;
    private Boolean favourite;

    public DataCom(String name_com, String tiket, String price_com, String change_price, Boolean favourite) {
        this.name_com = name_com;
        this.tiket = tiket;
        this.price_com = price_com;
        this.change_price = change_price;
        this.favourite = favourite;
    }

    public String getName_com() {
        return name_com;
    }

    public void setName_com(String name_com) {
        this.name_com = name_com;
    }

    public String getTiket() {
        return tiket;
    }

    public void setTiket(String tiket) {
        this.tiket = tiket;
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
}
