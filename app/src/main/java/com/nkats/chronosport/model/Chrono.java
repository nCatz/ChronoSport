package com.nkats.chronosport.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yeray697 on 2/02/17.
 */

public class Chrono {
    private final int MIN_REPETITIONS = 1;
    private final int MAX_ELEMENTS = 20;

    private int id;
    private String name;
    private List<ChronoElement> elements;
    private int repetitions;
    private boolean playing;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Chrono() {
        elements = new ArrayList<>();
        repetitions = MIN_REPETITIONS;
        playing = false;
        name = "";

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<ChronoElement> getElements() {
        return elements;
    }

    public void setElements(List<ChronoElement> elements) {
        this.elements = new ArrayList<>(elements);
    }

    public int getRepetitions() {
        return repetitions;
    }

    public void setRepetitions(int repetitions) throws Exception {
        if (repetitions < MIN_REPETITIONS)
            throw new Exception("You can not set '"+repetitions+"' repetitions. You have to set a number greater or equal than "+ MIN_REPETITIONS);
        this.repetitions = repetitions;
    }

    public boolean isPlaying() {
        return playing;
    }

    public void setPlaying(boolean playing) {
        this.playing = playing;
    }

    public boolean addElement(ChronoElement element) {
        boolean result = false;
        if (elements != null && elements.size() < MAX_ELEMENTS) {
            elements.add(element);
            result = true;
        }
        return result;
    }

    public boolean editElement(ChronoElement newElement){
        boolean result = false;
        int position = findElement(newElement);
        if (position != -1) {
            int id = ((ChronoElement)elements.get(position)).getId();
            ChronoElement aux = copyElement(newElement);
            aux.setId(id);
            result = true;
        }
        return result;
    }

    public boolean removeElement(ChronoElement element){
        boolean result = false;
        int position = findElement(element);
        if (position != -1) {
            elements.remove(element);
            result = true;
        }
        return result;
    }

    public ChronoElement copyElement(ChronoElement element){
        ChronoElement newElement = null;
        if (element instanceof ChronoTimeElement){
            newElement = new ChronoTimeElement(((ChronoTimeElement) element).getTime());
        } else {
            newElement = new ChronoRepetitionElement(((ChronoRepetitionElement) element).getRepetitions());
        }
        newElement.setName(element.getName());
        //You have to set the id after method call!!
        return newElement;
    }

    private int findElement(ChronoElement elementToFind){
        int position = -1;

        if (elements != null && elements.size() > 0) {
            for (int i = 0; i < elements.size(); i++) {
                if (elements.get(i).getId() == elementToFind.getId()) {
                    position = i;
                    break;
                }
            }
        }

        return position;
    }
}
