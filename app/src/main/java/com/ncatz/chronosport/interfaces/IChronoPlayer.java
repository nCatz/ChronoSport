package com.ncatz.chronosport.interfaces;

import com.ncatz.chronosport.model.ChronoElement;

/**
 * Created by amador on 5/02/17.
 */

public interface IChronoPlayer {

    interface View{

        void setTitleForChrono(String title);

        void setDisableChronoButtons();

        void setElementForChrono(ChronoElement element);

        void startChrono(int time);

    }

    interface Presenter{


        void verifyElements();



    }
}
