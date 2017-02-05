package com.ncatz.chronosport.custom_widgets;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.CountDownTimer;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.Chronometer;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;


import com.ncatz.chronosport.R;

import java.text.SimpleDateFormat;

import at.grabner.circleprogress.CircleProgressView;


/**
 * Created by amador on 4/02/17.
 */

public class ChronoWidget extends RelativeLayout {

    private ChronoThread chronoThread;
    private TextView textView;
    private  boolean running;
    private CircleProgressView progressBar;
    private ImageButton btnStartPause, btnNext;
    private IChronoActionListener callBack;
    private ChronoThread.IChronoIteractionListener listener = new ChronoThread.IChronoIteractionListener() {
        @Override
        public void onStart() {

            throwOnStart();
        }

        @Override
        public void onTick(final int actualTime) {

            final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("mm:ss");
           ((Activity)getContext()).runOnUiThread(new Runnable() {
                @Override
                public void run() {

                    textView.setText(simpleDateFormat.format(actualTime));
                    progressBar.setValue(actualTime);
                }
            });
        }

        @Override
        public void onFinish() {

            running = false;
            chronoThread = null;
            ((Activity)getContext()).runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    btnStartPause.setImageResource(android.R.drawable.ic_media_play);

                }
            });
            throwOnFinish();

        }
    };

    public interface IChronoActionListener extends ChronoThread.IChronoIteractionListener{

        void onNextButtonCliked();

        void onButtonStartPauseCliked();
    }

    public ChronoWidget(Context context, AttributeSet attrs) {
        super(context, attrs);
        ((Activity)context).getLayoutInflater().inflate(R.layout.mixer_chrono,this,true);
        setGravity(Gravity.CENTER);
        running = false;
        textView = (TextView)findViewById(R.id.chronoText);
        textView.setText("00:00");
        progressBar = (CircleProgressView) findViewById(R.id.progress);
        btnStartPause = (ImageButton)findViewById(R.id.btnStartPause);
        btnNext = (ImageButton)findViewById(R.id.btnNext);

        btnStartPause.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {

                callBack.onButtonStartPauseCliked();

                if(chronoThread != null) {

                    if (chronoThread.isPaused()) {

                        chronoThread.setPaused(false);
                        btnStartPause.setImageResource(android.R.drawable.ic_media_pause);

                    } else {

                        chronoThread.setPaused(true);
                        btnStartPause.setImageResource(android.R.drawable.ic_media_play);
                    }
                }

            }
        });


        btnNext.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {

                throwOnNextButtonCliked();
            }
        });

        if(attrs != null){

            TypedArray typedArray = getContext().obtainStyledAttributes(attrs,R.styleable.ChronoWidget);
            setShapeButtons(typedArray.getDrawable(R.styleable.ChronoWidget_shape_buttons));
            setRimColor(typedArray.getColor(R.styleable.ChronoWidget_bar_progress_color, Color.BLUE));
            setBackProgressColor(typedArray.getColor(R.styleable.ChronoWidget_background_progress_color, Color.YELLOW));
            setTimeColor(typedArray.getColor(R.styleable.ChronoWidget_time_color, Color.BLACK));
            typedArray.recycle();
        }
    }

    public boolean isRunning(){


        return running;
    }

    public void disableButtons(){

        btnStartPause.setVisibility(GONE);
        btnNext.setVisibility(GONE);
    }

    public void setVisivilityForButtonStart(boolean visibility){

        btnStartPause.setVisibility( visibility ? VISIBLE:GONE);
    }

    public void setShapeButtons(Drawable drawable){

        btnNext.setBackground(drawable);
        btnStartPause.setBackground(drawable);
    }

    public void setBackProgressColor(int color){

        progressBar.setBarColor(color);
    }

    public void setRimColor(int color){

        progressBar.setRimColor(color);
    }

    public void setOnChronoListener(IChronoActionListener listener){

        this.callBack = listener;
    }

    public void activateChrono(int time, int interval){
        running = true;
        chronoThread = new ChronoThread(time, interval);
        chronoThread.setChronoListener(listener);
        chronoThread.startChrono();
        progressBar.setValue(time);
        progressBar.setMaxValue(time);
    }

    public void setButonStartPauseBackColor(int color){

        btnStartPause.setBackgroundColor(color);
    }

    public void setButonAvancedColor(int color){

        btnNext.setBackgroundColor(color);
    }

    public void setTimeColor(int color){

        textView.setTextColor(color);
    }

    private void throwOnStart(){

        if(callBack != null){

            callBack.onStart();
        }
    }

    private void throwOnFinish(){

        if(callBack != null){

            callBack.onFinish();
        }
    }

    private void throwOnNextButtonCliked(){

        if(callBack != null){

            callBack.onNextButtonCliked();
        }
    }

    public void destroyChrono(){

        if(chronoThread != null){

            chronoThread.stopChrono();
        }
    }

}
