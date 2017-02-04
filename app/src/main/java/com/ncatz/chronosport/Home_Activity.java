package com.ncatz.chronosport;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.ncatz.chronosport.database.DataBaseHelper;
import com.ncatz.chronosport.fragments.ChronoList_Fragment;
import com.ncatz.chronosport.fragments.ChronoPlayer_Fragment;
import com.ncatz.chronosport.fragments.ManageChrono_Fragment;

public class Home_Activity extends AppCompatActivity {

    private static final String CURRENT_FRAGMENT_TAG_KEY = "CURRENT_FRAGMENT_TAG";
    private final static String CHRONO_LIST_TAG = "chronolist";
    private final static String CHRONO_PLAYER_TAG = "chronoplayer";
    private final static String MANAGE_CHRONO_TAG = "managechrono";

    private String currentFragmentTag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        currentFragmentTag = "";
        if (savedInstanceState != null){
            currentFragmentTag = savedInstanceState.getString(CURRENT_FRAGMENT_TAG_KEY,"");
        }

        if (currentFragmentTag.equals("")) { //There wasn't a fragment open
            setFragment(CHRONO_LIST_TAG,null);
        } else {
            setFragment(currentFragmentTag,null);
        }

        findViewById(R.id.btChronoPlayer).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setFragment(CHRONO_PLAYER_TAG,null);
            }
        });
        findViewById(R.id.btManageChrono).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setFragment(MANAGE_CHRONO_TAG,null);
            }
        });
    }

    private void setFragment(String tag, Bundle args){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction ft = fragmentManager.beginTransaction();
        Fragment fragment;
        fragment = fragmentManager.findFragmentByTag(tag);
        if (fragment == null) {
            switch (tag) {
                case CHRONO_LIST_TAG:
                    fragment = ChronoList_Fragment.newFragment();
                    break;
                case CHRONO_PLAYER_TAG:
                    fragment = ChronoPlayer_Fragment.newFragment(args);
                    break;
                case MANAGE_CHRONO_TAG:
                    fragment = ManageChrono_Fragment.newFragment(args);
                    break;
            }
        }
        ft.replace(R.id.fl_frameHome, fragment,tag);
        if (!tag.equals(CHRONO_LIST_TAG) && !currentFragmentTag.equals(tag))
            ft.addToBackStack(null);
        ft.commit();
        currentFragmentTag = tag;
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (!currentFragmentTag.equals("")) {
            outState.putString(CURRENT_FRAGMENT_TAG_KEY,currentFragmentTag);
        }
    }
}
