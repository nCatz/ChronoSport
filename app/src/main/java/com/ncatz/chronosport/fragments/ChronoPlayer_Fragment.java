package com.ncatz.chronosport.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ncatz.chronosport.Home_Activity;
import com.ncatz.chronosport.R;
import com.ncatz.chronosport.adapters.ChronoPlayer_Adapter;
import com.ncatz.chronosport.custom_widgets.ChronoWidget;
import com.ncatz.chronosport.interfaces.IChronoPlayer;
import com.ncatz.chronosport.model.Chrono;
import com.ncatz.chronosport.model.ChronoElement;
import com.ncatz.chronosport.model.ChronoRepetitionElement;
import com.ncatz.chronosport.model.ChronoTimeElement;
import com.ncatz.chronosport.presenters.ChronoPlayer_Presenter;

import jp.wasabeef.recyclerview.animators.SlideInLeftAnimator;

/**
 * Created by yeray697 on 4/02/17.
 */

public class ChronoPlayer_Fragment extends Fragment implements IChronoPlayer.View
{

    private Chrono chrono;
    private ChronoPlayer_Adapter adapter;
    private RecyclerView recyclerView;
    private ChronoWidget chronoWidget;
    private ChronoPlayer_Presenter presenter;
    private ChronoElement actualElement;
    private boolean active;
    private final int INTERVAL = 1000;
    private int limit;

    public ChronoPlayer_Fragment(){


    }
    public static ChronoPlayer_Fragment newFragment(Bundle args){
        ChronoPlayer_Fragment fragment = new ChronoPlayer_Fragment();
        if (args != null)
            fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setRetainInstance(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.chronoplayer_fragment,container,false);
        chrono = getArguments().getParcelable(Home_Activity.CHRONO_ARGS_KEY);
        chronoWidget = (ChronoWidget)rootView.findViewById(R.id.chrono);
        active = false;
        registerChronoEvents();
        limit = chrono.getRepetitions();
        recyclerView = (RecyclerView)rootView.findViewById(R.id.recycler);
        recyclerView.setItemAnimator(new SlideInLeftAnimator());
        recyclerView.getItemAnimator().setRemoveDuration(1000);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        presenter = new  ChronoPlayer_Presenter(this, chrono.getElements(), limit);
        return rootView;
    }


    private void registerChronoEvents(){

        chronoWidget.setOnChronoListener(new ChronoWidget.IChronoActionListener() {
            @Override
            public void onNextButtonCliked() {

              if(actualElement instanceof ChronoTimeElement){


              }else {

                  removeItem();
              }

            }

            @Override
            public void onButtonStartPauseCliked() {

                if(!active){

                    startChrono(((ChronoTimeElement)actualElement).getTime());
                }
            }

            @Override
            public void onStart() {

            }

            @Override
            public void onTick(int actualTime) {

            }

            @Override
            public void onFinish() {
                active = false;
              removeItem();
            }
        });
    }

    @Override
    public void onDetach() {

        if(chronoWidget != null){

            chronoWidget.destroyChrono();
        }
        super.onDetach();
    }

    @Override
    public void setDisableChronoButtons() {

        chronoWidget.disableButtons();
    }

    private void removeItem(){

        if(getActivity() != null){

            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {

                    adapter.removeItem();
                    chronoWidget.destroyChrono();

                }
            });
        }
    }

    @Override
    public void setElementForChrono(ChronoElement element) {

        if(element != null){

            actualElement = element;

            if(element instanceof ChronoTimeElement){

                chronoWidget.setVisivilityForButtonStart(true);
                chronoWidget.setVisivilityForButtonNext(false);
                chronoWidget.setTimeVisible(true);


            }else {

                chronoWidget.setTimeVisible(false);
                chronoWidget.setVisivilityForButtonNext(true);
                chronoWidget.setVisivilityForButtonStart(false);
            }


        }else {

            chronoWidget.disableButtons();
            chronoWidget.destroyChrono();
        }
    }

    @Override
    public void startChrono(int time) {

        active = true;
        chronoWidget.activateChrono(time, INTERVAL);
    }

    @Override
    public void setAdapter(ChronoPlayer_Adapter adapter) {

        this.adapter = adapter;
        recyclerView.setAdapter(adapter);
    }


}
