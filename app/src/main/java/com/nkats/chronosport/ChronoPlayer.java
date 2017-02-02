package com.nkats.chronosport;

import android.content.Context;

import com.nkats.chronosport.model.Chrono;

/**
 * Created by yeray697 on 2/02/17.
 */

public class ChronoPlayer {
    private static ChronoPlayer instance;
    private Context context;

    private ChronoPlayer(Context context) {
        this.context = context;
    }

    public static ChronoPlayer getInstance(Context context) {
        if (instance == null)
            instance = new ChronoPlayer(context);
        return instance;
    }

    public void play(Chrono chrono) {
        chrono.setPlaying(true);
    }

    public void stop(Chrono chrono) {
        chrono.setPlaying(false);
    }

    public void pause(Chrono chrono) {
        chrono.setPlaying(false);
    }

    public void restart(Chrono chrono) {
        stop(chrono);
        play(chrono);
    }

}
