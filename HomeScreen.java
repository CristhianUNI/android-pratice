package com.example.cristhian.practiceretrofit.Model;

import android.graphics.Picture;

/**
 * Created by cristhian on 11/11/17.
 */

public class HomeScreen {

    private Picture picture;
    private String title;

    public Picture getPicture() {
        return picture;
    }

    public void setPicture(Picture picture) {
        this.picture = picture;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public HomeScreen(Picture picture, String title) {

        this.picture = picture;
        this.title = title;
    }
}
