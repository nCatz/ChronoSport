package com.nkats.chronosport.model;

/**
 * Created by yeray697 on 2/02/17.
 */

public abstract class ChronoElement {
    private int id;
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
