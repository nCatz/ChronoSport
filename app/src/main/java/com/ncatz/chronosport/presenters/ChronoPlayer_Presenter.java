package com.ncatz.chronosport.presenters;

import android.content.Context;

import com.ncatz.chronosport.R;
import com.ncatz.chronosport.fragments.ChronoPlayer_Fragment;
import com.ncatz.chronosport.interfaces.IChronoPlayer;
import com.ncatz.chronosport.model.Chrono;
import com.ncatz.chronosport.model.ChronoElement;

import java.util.List;

/**
 * Created by amador on 5/02/17.
 */

public class ChronoPlayer_Presenter {

    private IChronoPlayer.View view;
    private List<ChronoElement> listElemt;
    private Context context;
    private int repetitions;
    private int contRepetitions;

    public ChronoPlayer_Presenter(IChronoPlayer.View view, List<ChronoElement> elementList, int repetitions){

        this.view = view;
        this.context = ((ChronoPlayer_Fragment)view).getContext();
        this.listElemt = elementList;
        this.repetitions = repetitions;
        this.contRepetitions = 1;

        if(listElemt.size() == 0){

            this.view.setDisableChronoButtons();
        }else {

            this.view.setTitleForChrono(String.valueOf(contRepetitions)+"/"+String.valueOf(repetitions));
            this.view.setElementForChrono(listElemt.get(0));
        }
    }

    public void indicateElement(int lastElementShow){

        if(contRepetitions <= repetitions){

            if(lastElementShow == listElemt.size()-1){

                contRepetitions++;
                view.reloadElemts();
            }

            if(contRepetitions <= repetitions){

                this.view.setTitleForChrono(String.valueOf(contRepetitions)+"/"+String.valueOf(repetitions));
            }

            view.setElementForChrono(listElemt.get((lastElementShow+1)%listElemt.size()));


        }else {

            view.setDisableChronoButtons();
        }
    }
}
