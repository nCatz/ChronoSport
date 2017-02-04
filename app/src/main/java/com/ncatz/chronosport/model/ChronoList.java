package com.ncatz.chronosport.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yeray697 on 2/02/17.
 */

public class ChronoList {
    private List<Chrono> chronos;

    public ChronoList() {
        chronos = new ArrayList<>();
    }

    public boolean addChrono(Chrono chrono) {
        boolean result = false;
        if (chronos != null){
            chronos.add(chrono);
            result = true;
        }
        return result;
    }

    public boolean removeChrono(Chrono chrono) {
        boolean result = false;
        if (chronos != null){
            result = chronos.remove(chrono);
        }
        return result;
    }

    public boolean editChrono(Chrono newChrono) {
        boolean result = false;

        int position = findChrono(newChrono);
        if (position != -1) {
            int id = ((Chrono)chronos.get(position)).getId();
            Chrono aux = copyChrono(newChrono);
            aux.setId(id);
            result = true;
        }
        return result;
    }

    public Chrono copyChrono(Chrono chrono){
        Chrono newChrono  = new Chrono();
        try {
            newChrono.setRepetitions(chrono.getRepetitions());
        } catch (Exception e) {
            e.printStackTrace();
        }
        newChrono.setName(chrono.getName());
        newChrono.setPlaying(chrono.isPlaying());
        newChrono.setElements(chrono.getElements());
        //You have to set the id after method call!!
        return newChrono;
    }

    private int findChrono(Chrono chrono) {
        int position = -1;

        for (int i = 0; i < chronos.size(); i++) {
            if (chronos.get(i).getId() == chrono.getId()) {
                position = i;
                break;
            }
        }

        return position;
    }

}
