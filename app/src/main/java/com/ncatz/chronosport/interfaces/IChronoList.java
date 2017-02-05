package com.ncatz.chronosport.interfaces;

import android.widget.ListAdapter;

import com.ncatz.chronosport.adapters.ChronoList_Adapter;
import com.ncatz.chronosport.model.Chrono;

/**
 * Created by yeray697 on 4/02/17.
 */

public interface IChronoList {

    boolean isPositionChecked(int position);

    interface View{
        void onPlayListener(Chrono clickedChrono);
        void onSelectItemListener(int position);
    }
    /*interface OnAdapterClickListener{
        void onPlayListener(Chrono clickedChrono);
        void onSelectItemListener(int position);
    }*/

    void setSelected(int position, boolean isSelected);
    void removeSelection(int position);
    void clearSelection();
    ChronoList_Adapter getAdapter();
    //void setOnAdapterClickListener(OnAdapterClickListener listener);
}
