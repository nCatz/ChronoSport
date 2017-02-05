package com.ncatz.chronosport.fragments;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.ActionMode;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.ncatz.chronosport.Home_Activity;
import com.ncatz.chronosport.R;
import com.ncatz.chronosport.custom_widgets.ChronoWidget;
import com.ncatz.chronosport.adapters.ChronoList_Adapter;
import com.ncatz.chronosport.interfaces.IChronoList;
import com.ncatz.chronosport.interfaces.ManageFragmentCallback;
import com.ncatz.chronosport.model.Chrono;
import com.ncatz.chronosport.model.ChronoElement;
import com.ncatz.chronosport.model.ChronoRepetitionElement;
import com.ncatz.chronosport.model.ChronoTimeElement;
import com.ncatz.chronosport.other.MultiChoiceListener;
import com.ncatz.chronosport.presenters.ChronoList_Presenter;

import java.util.ArrayList;

/**
 * Created by yeray697 on 4/02/17.
 */

public class ChronoList_Fragment extends Fragment implements IChronoList.View{

    private ListView listView;

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
        /*presenter.setOnAdapterClickListener(new IChronoList.OnAdapterClickListener() {
            @Override
            public void onPlayListener(Chrono clickedChrono) {
                Bundle args = new Bundle();
                args.putParcelable(Home_Activity.CHRONO_ARGS_KEY,clickedChrono);
                mCallback.swapFragment(Home_Activity.CHRONO_PLAYER_TAG,args);
            }

            @Override
            public void onSelectItemListener(int position) {
                listView.setItemChecked(position, !presenter.isPositionChecked(position));
            }
        });*/
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.chronolist_fragment,container,false);

        listView = (ListView) rootView.findViewById(R.id.lvChronoList);
        listView.setAdapter(presenter.getAdapter());
        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setMultiChoiceModeListView();
    }

    private void setMultiChoiceModeListView() {
        listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE_MODAL);
        MultiChoiceListener multiChoiceListener = new MultiChoiceListener(presenter);
        listView.setMultiChoiceModeListener(multiChoiceListener);
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

            @Override
            public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
                                           int position, long arg3) {
                // TODO Auto-generated method stub

                listView.setItemChecked(position, !presenter.isPositionChecked(position));
                return true;
            }
        });
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

    @Override
    public void onPlayListener(Chrono clickedChrono) {
        Bundle args = new Bundle();
        args.putParcelable(Home_Activity.CHRONO_ARGS_KEY,clickedChrono);
        mCallback.swapFragment(Home_Activity.CHRONO_PLAYER_TAG,args);
    }

    @Override
    public void onSelectItemListener(int position) {
        listView.setItemChecked(position, !presenter.isPositionChecked(position));
    }
}
