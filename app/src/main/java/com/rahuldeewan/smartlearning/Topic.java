package com.rahuldeewan.smartlearning;

/**
 * Created by
 * Rahul on 02-11-2017.
 */

public class Topic {
    private int ID;
    private String Name;

    public Topic() {

    }

    public Topic(int id, String name) {
        this.ID = id;
        this.Name = name;
    }

    public int getId() {
        return ID;
    }

    public void setId(int id) {
        this.ID = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        this.Name = name;
    }
}