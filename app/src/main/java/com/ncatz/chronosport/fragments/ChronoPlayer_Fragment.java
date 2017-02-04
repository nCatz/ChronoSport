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

public class ChronoPlayer_Fragment extends Fragment {

    public ChronoPlayer_Fragment(){

    }
    public static ChronoPlayer_Fragment newFragment(Bundle args){
        ChronoPlayer_Fragment fragment = new ChronoPlayer_Fragment();
        if (args != null)
            fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.chronoplayer_fragment,container,false);

        return rootView;
    }
}
