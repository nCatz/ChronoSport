package com.ncatz.chronosport.presenters;

import com.ncatz.chronosport.interfaces.IChronoList;

/**
 * Created by yeray697 on 4/02/17.
 */

public class ChronoList_Presenter implements IChronoList{
    private IChronoList.View view;

    public ChronoList_Presenter(View view) {
        this.view = view;
    }
}
