package com.example.project_pavel;

import android.graphics.Bitmap;

public class News_cl {

    private String text;
    private String title;
    private Bitmap picture;

    public News_cl(String text, String title, Bitmap picture) {
        this.text = text;
        this.title = title;
        this.picture = picture;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Bitmap getPicture() {
        return picture;
    }

    public void setPicture(Bitmap picture) {
        this.picture = picture;
    }
}
