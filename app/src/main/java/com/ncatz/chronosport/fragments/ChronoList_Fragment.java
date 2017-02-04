package com.ncatz.chronosport.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.ncatz.chronosport.R;
import com.ncatz.chronosport.adapters.ChronoList_Adapter;
import com.ncatz.chronosport.interfaces.IChronoList;
import com.ncatz.chronosport.interfaces.ManageFragmentCallback;
import com.ncatz.chronosport.model.Chrono;
import com.ncatz.chronosport.model.ChronoElement;
import com.ncatz.chronosport.model.ChronoRepetitionElement;
import com.ncatz.chronosport.model.ChronoTimeElement;
import com.ncatz.chronosport.presenters.ChronoList_Presenter;

import java.util.ArrayList;

/**
 * Created by yeray697 on 4/02/17.
 */

public class ChronoList_Fragment extends Fragment implements IChronoList.View{

    private RecyclerView recyclerView;
    private FloatingActionButton fabAdd;

    private ChronoList_Adapter adapter;
    private IChronoList presenter;
    private ManageFragmentCallback mCallback;

    public ChronoList_Fragment(){

    }

    public static ChronoList_Fragment newFragment(){
        ChronoList_Fragment fragment = new ChronoList_Fragment();
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new ChronoList_Presenter(this);
        adapter = new ChronoList_Adapter(getDefaultItems());
        adapter.setOnPlayListener(new ChronoList_Adapter.OnPlayListener() {
            @Override
            public void onPlay(Chrono clickedChrono) {
                Toast.makeText(getContext(), "Play " + clickedChrono.getName(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.chronolist_fragment,container,false);
        recyclerView = (RecyclerView) rootView.findViewById(R.id.rvChronoList);
        fabAdd = (FloatingActionButton) rootView.findViewById(R.id.fabAddChrono);
        fabAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fabClick();
            }
        });
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        return rootView;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mCallback = (ManageFragmentCallback) activity;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mCallback = null;
    }

    private void fabClick() {
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
}
