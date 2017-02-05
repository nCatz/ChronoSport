package com.ncatz.chronosport.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.IntegerRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.ncatz.chronosport.Home_Activity;
import com.ncatz.chronosport.R;
import com.ncatz.chronosport.interfaces.IChronoList;
import com.ncatz.chronosport.interfaces.ManageFragmentCallback;
import com.ncatz.chronosport.model.Chrono;
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
    private String STATE_LIST_KEY = "list_key";
    private String SELECTED_KEY = "selected_key";
    private MultiChoiceListener multiChoiceListener;

    public ChronoList_Fragment(){
        setRetainInstance(true);
    }

    public static ChronoList_Fragment newFragment(){
        ChronoList_Fragment fragment = new ChronoList_Fragment();
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null){
            ArrayList<Chrono> list = savedInstanceState.getParcelableArrayList(STATE_LIST_KEY);
            presenter = new ChronoList_Presenter(this,list);
        }
        else
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
        //if (savedInstanceState != null) {
        //    ArrayList <Integer> selected = savedInstanceState.getIntegerArrayList(SELECTED_KEY);
        //    if (selected != null && selected.size()>0){
        //        for (int i = 0; i < selected.size(); i++) {
        //            listView.setItemChecked(selected.get(i),true);
        //        }
        //    }
        //}
    }

    private void setMultiChoiceModeListView() {
        multiChoiceListener = new MultiChoiceListener(presenter);
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

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList(STATE_LIST_KEY, presenter.getAdapter().getList());
        outState.putIntegerArrayList(SELECTED_KEY,getSelectedItems());

        multiChoiceListener.onDestroyActionMode(null);
    }

    private ArrayList<Integer> getSelectedItems() {
        ArrayList<Integer> selected = new ArrayList<>();
        int len = listView.getCount();
        SparseBooleanArray checked = listView.getCheckedItemPositions();
        for(int i=0; i<checked.size(); i++){
            int key = checked.keyAt(i);
            if (checked.get(key)){
                selected.add(key);
            }
        }
        return selected;
    }
}
