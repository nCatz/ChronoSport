package com.ncatz.chronosport.interfaces;

import com.ncatz.chronosport.adapters.ChronoPlayer_Adapter;
import com.ncatz.chronosport.model.ChronoElement;

/**
 * Created by amador on 5/02/17.
 */

public interface IChronoPlayer {

    interface View{


        void setDisableChronoButtons();

        void setElementForChrono(ChronoElement element);

        void startChrono(int time);

        void setAdapter(ChronoPlayer_Adapter adapter);

    }

    interface Presenter{


        void verifyElements();

    }
}
