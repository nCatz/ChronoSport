package com.nkats.chronosport;

import android.app.Application;
import android.content.Context;

import com.nkats.chronosport.database.DataBaseHelper;

/**
 * Created by amador on 3/02/17.
 */

public class ChronoSportAplications extends Application {


    private static Context context;

    public static Context getContex(){

        return context;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
        DataBaseHelper.getInstance().openDataBase();
    }




}
