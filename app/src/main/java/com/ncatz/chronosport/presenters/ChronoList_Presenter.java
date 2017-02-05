package com.ncatz.chronosport.presenters;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.widget.ListAdapter;

import com.ncatz.chronosport.Home_Activity;
import com.ncatz.chronosport.adapters.ChronoList_Adapter;
import com.ncatz.chronosport.interfaces.IChronoList;
import com.ncatz.chronosport.model.Chrono;
import com.ncatz.chronosport.model.ChronoElement;
import com.ncatz.chronosport.model.ChronoRepetitionElement;
import com.ncatz.chronosport.model.ChronoTimeElement;
import com.ncatz.chronosport.other.MultiChoiceListener;

import java.util.ArrayList;

/**
 * Created by yeray697 on 4/02/17.
 */

public class ChronoList_Presenter implements IChronoList{
    private IChronoList.View view;
    private ChronoList_Adapter adapter;

    //private IChronoList.OnAdapterClickListener onAdapterClickListener;

    public ChronoList_Presenter(final IChronoList.View view) {
        this.view = view;
        adapter = new ChronoList_Adapter(((Fragment)view).getContext(),getDefaultItems());
        adapter.setOnPlayListener(new ChronoList_Adapter.OnPlayListener() {
            @Override
            public void onPlay(Chrono clickedChrono) {
                view.onPlayListener(clickedChrono);
                /*if (onAdapterClickListener != null)
                    onAdapterClickListener.onPlayListener(clickedChrono);*/
            }
        });
        adapter.setOnSelectItemListener(new MultiChoiceListener.OnSelectItemListener() {
            @Override
            public void onClick(int position) {
                view.onSelectItemListener(position);
                /*if (onAdapterClickListener != null)
                    onAdapterClickListener.onSelectItemListener(position);*/
            }
        });
    }

    private ArrayList<Chrono> getDefaultItems() {
        ArrayList<ChronoElement> elements = new ArrayList<>();
        elements.add(new ChronoTimeElement("dfnbc",50));
        elements.add(new ChronoRepetitionElement("qttwg",15));
        ArrayList<Chrono> items = new ArrayList<>();
        Chrono chrono = new Chrono();
        elements = new ArrayList<>();
        elements.add(new ChronoTimeElement("asgasg",150));
        elements.add(new ChronoRepetitionElement("qttwg",15));
        chrono.setElements(elements);
        chrono.setName("aaaa");
        chrono.setRepetitions(5);
        items.add(chrono);
        chrono = new Chrono();
        elements = new ArrayList<>();
        elements.add(new ChronoTimeElement("asgasg",150));
        elements.add(new ChronoRepetitionElement("jgkf",20));
        elements.add(new ChronoTimeElement("dfnbc",50));
        chrono.setElements(elements);
        chrono.setName("bbbb");
        chrono.setRepetitions(4);
        items.add(chrono);
        chrono = new Chrono();
        elements = new ArrayList<>();
        elements.add(new ChronoTimeElement("asgasg",150));
        elements.add(new ChronoRepetitionElement("jgkf",20));
        elements.add(new ChronoRepetitionElement("qttwg",15));
        elements.add(new ChronoTimeElement("dfnbc",50));
        chrono.setElements(elements);
        chrono.setName("cccc");
        chrono.setRepetitions(3);
        items.add(chrono);
        chrono = new Chrono();
        elements = new ArrayList<>();
        elements.add(new ChronoRepetitionElement("jgkf",20));
        elements.add(new ChronoRepetitionElement("qttwg",15));
        chrono.setElements(elements);
        chrono.setName("dddd");
        chrono.setRepetitions(2);
        items.add(chrono);
        chrono = new Chrono();
        elements = new ArrayList<>();
        elements.add(new ChronoTimeElement("asgasg",150));
        elements.add(new ChronoRepetitionElement("jgkf",20));
        elements.add(new ChronoTimeElement("dfnbc",50));
        chrono.setName("eeee");
        chrono.setElements(elements);
        chrono.setRepetitions(1);
        items.add(chrono);
        return items;
    }

    @Override
    public boolean isPositionChecked(int position) {
        return adapter.isPositionChecked(position);
    }

    @Override
    public void setSelected(int position, boolean isSelected) {
        adapter.setSelected(position,isSelected);
    }

    @Override
    public void removeSelection(int position) {
        adapter.removeSelection(position);
    }

    @Override
    public void clearSelection() {
        adapter.clearSelection();
    }

    @Override
    public ChronoList_Adapter getAdapter() {
        return adapter;
    }

    /*@Override
    public void setOnAdapterClickListener(OnAdapterClickListener listener) {
        this.onAdapterClickListener = listener;
    }*/
}
