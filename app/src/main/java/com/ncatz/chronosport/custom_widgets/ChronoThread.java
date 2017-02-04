package com.ncatz.chronosport.custom_widgets;

/**
 * Created by amador on 4/02/17.
 */

public class ChronoThread extends Thread{

    private int totalTime;
    private int actualTime;
    private int interval;
    private boolean paused;
    private IChronoIteractionListener listener;

    interface IChronoIteractionListener{

        void onStart();

        void onTick(int actualTime);

        void onFinish();
    }

    public ChronoThread(int totalTime, int interval){

        this.totalTime = totalTime;
        this.actualTime = totalTime;
        this.paused = true;
        this.interval = interval;

    }


    public void setChronoListener(IChronoIteractionListener listener){

        this.listener = listener;
    }

    public void startChrono(){

      start();
    }

    public void pauseChrono(){

        paused = true;
    }

    public void stopChrono(){

        interrupt();
    }

    public int getTotalTime() {
        return totalTime;
    }

    public void setTotalTime(int totalTime) {
        this.totalTime = totalTime;
    }

    public int getActualTime() {
        return actualTime;
    }

    public void setActualTime(int actualTime) {
        this.actualTime = actualTime;
    }

    public int getInterval() {
        return interval;
    }

    public void setInterval(int interval) {
        this.interval = interval;
    }

    public boolean isPaused() {
        return paused;
    }

    public void setPaused(boolean paused) {
        this.paused = paused;
    }

    @Override
    public void run() {

        throwOnStart();

          while (actualTime > 0){

              if(!paused){

                  throwOnTick();
                  try {
                      Thread.sleep(interval);
                  } catch (InterruptedException e) {
                      break;

                  }
              }
          }

        throwOnFinish();
    }

    private void throwOnStart(){

        if(listener != null){

            listener.onStart();
        }
    }

    private void throwOnTick(){

        if(listener != null){

            listener.onTick((actualTime -= interval));
        }
    }

    private void throwOnFinish(){

        if(listener != null){

            listener.onFinish();
        }
    }
}
