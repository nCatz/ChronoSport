package com.ncatz.chronosport.presenters;

import android.content.Context;

import com.ncatz.chronosport.R;
import com.ncatz.chronosport.adapters.ChronoPlayer_Adapter;
import com.ncatz.chronosport.fragments.ChronoPlayer_Fragment;
import com.ncatz.chronosport.interfaces.IChronoPlayer;
import com.ncatz.chronosport.model.Chrono;
import com.ncatz.chronosport.model.ChronoElement;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by amador on 5/02/17.
 */

public class ChronoPlayer_Presenter implements ChronoPlayer_Adapter.IAdapterComunication {

    private IChronoPlayer.View view;
    private List<ChronoElement> listElemt;
    private Context context;
    private ChronoPlayer_Adapter adapter;

    public ChronoPlayer_Presenter(IChronoPlayer.View view, List<ChronoElement> elementList, int repetitions){

        this.view = view;
        this.context = ((ChronoPlayer_Fragment)view).getContext();
        this.adapter = new ChronoPlayer_Adapter(context, this);

        if(elementList.size() == 0){

            view.setDisableChronoButtons();

        }else {

            for(int i = 0; i < repetitions; i++){

                adapter.addAllItems(new ArrayList<ChronoElement>(elementList));

            }

            view.setAdapter(adapter);
            view.setElementForChrono(adapter.getItem(0));
        }
    }


    @Override
    public void onRemoveElement() {

        if(adapter.getItemCount() > 0){

            view.setElementForChrono(adapter.getItem(0));


        }else {

            view.setElementForChrono(null);
        }
    }
}
