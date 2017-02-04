package com.ncatz.chronosport.model;

/**
 * Created by yeray697 on 2/02/17.
 */

public class ChronoRepetitionElement extends ChronoElement {
    private int repetitions;

    public ChronoRepetitionElement(int repetitions) {
        this.repetitions = repetitions;
    }

    public ChronoRepetitionElement() {

    }


    public int getRepetitions() {
        return repetitions;
    }

    public void setRepetitions(int repetitions) {
        this.repetitions = repetitions;
    }
}
