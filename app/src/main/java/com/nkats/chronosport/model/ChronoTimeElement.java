package com.nkats.chronosport.model;

/**
 * Created by yeray697 on 2/02/17.
 */

public class ChronoTimeElement extends ChronoElement {
    private int time;

    public ChronoTimeElement(int time) {
        this.time = time;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }
}
