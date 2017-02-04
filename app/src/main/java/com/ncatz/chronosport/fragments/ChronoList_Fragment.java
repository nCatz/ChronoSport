package com.ncatz.chronosport.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ncatz.chronosport.R;

/**
 * Created by yeray697 on 4/02/17.
 */

public class ChronoList_Fragment extends Fragment {
    public ChronoList_Fragment(){

    }
    public static ChronoList_Fragment newFragment(){
        ChronoList_Fragment fragment = new ChronoList_Fragment();
        return fragment;
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.chronolist_fragment,container,false);

        return rootView;
    }
}
