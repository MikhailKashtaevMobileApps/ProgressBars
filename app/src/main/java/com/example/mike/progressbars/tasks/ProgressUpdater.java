package com.example.mike.progressbars.tasks;

import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.example.mike.progressbars.MyEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.Random;

public class ProgressUpdater implements Runnable {

    public static final String TAG = ProgressUpdater.class.getSimpleName()+"__TAG__";

    int progressBarID;
    Integer timer;
    Integer refreshRate;
    Integer delay;

    public ProgressUpdater(int progressBarID, Integer timer, Integer refreshRate, Integer delay) {
        this.progressBarID = progressBarID;
        this.timer = timer;
        this.refreshRate = refreshRate;
        this.delay = delay;
    }

    public ProgressUpdater(View view) {
        this.progressBarID = view.getId();
        this.timer = 60000;
        this.refreshRate = 5;  // 5 times a second
        this.delay = new Random().nextInt( 20000 );
    }

    @Override
    public void run() {
        // Delay the start up
        try {
            Thread.sleep( this.delay );
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Integer interval = 1000/refreshRate;
        Integer total = 0;

        while (total < timer){
            EventBus.getDefault().post( new MyEvent( progressBarID, (Integer) 100*total/timer , MyEvent.EVENT_TYPE_PROGRESS_BAR ));
            total += interval;

            try {
                Thread.sleep(interval);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }






    public int getProgressBar() {
        return progressBarID;
    }

    public void setProgressBar(int progressBar) {
        this.progressBarID = progressBar;
    }

    public Integer getTimer() {
        return timer;
    }

    public void setTimer(Integer timer) {
        this.timer = timer;
    }

    public Integer getRefreshRate() {
        return refreshRate;
    }

    public void setRefreshRate(Integer refreshRate) {
        this.refreshRate = refreshRate;
    }

    public Integer getDelay() {
        return delay;
    }

    public void setDelay(Integer delay) {
        this.delay = delay;
    }
}
