package com.master.normtester.app.tester;

import android.graphics.Bitmap;

/**
 * Created by lpalonek on 04/01/16.
 */
public class Image {
    private int size;

    private Bitmap data;

    private String name;

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public Bitmap getData() {
        return data;
    }

    public void setData(Bitmap data) {
        this.data = data;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
