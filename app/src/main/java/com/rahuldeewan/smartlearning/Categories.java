package com.rahuldeewan.smartlearning;

/**
 * Created by Pallav
 * on 10/12/2017.
 */

public class Categories {
    private String name;
    private int thumbnail;

    Categories(String name, int thumbnail) {
        this.name = name;
        this.thumbnail = thumbnail;
    }

    int getThumbnail() {
        return thumbnail;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setThumbnail(int thumbnail) {
        this.thumbnail = thumbnail;
    }
}