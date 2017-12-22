package com.chat.model;

import android.graphics.Bitmap;

/**
 * Created by zukizaro on 12/21/2017.
 */

public class ListTimeline {

    String title;

    Bitmap[] images;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Bitmap[] getImages() {
        return images;
    }

    public void setImages(Bitmap[] images) {
        this.images = images;
    }


    public ListTimeline(String title, Bitmap[] images) {
        this.title = title;
        this.images = images;
    }
    public ListTimeline(){

    }

}
