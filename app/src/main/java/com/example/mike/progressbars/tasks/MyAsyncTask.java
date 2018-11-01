package com.example.mike.progressbars.tasks;

import android.os.AsyncTask;

import com.example.mike.progressbars.MyEvent;

import org.greenrobot.eventbus.EventBus;

public class MyAsyncTask extends AsyncTask<String, String, String> {
    int viewID;

    public MyAsyncTask(int viewID){
        this.viewID = viewID;
    }

    @Override
    protected String doInBackground(String... strings) {
        for (int i = 0; i < 1000; i++) {
            try {
                Thread.sleep(1000);
                MyEvent ev = new MyEvent(this.viewID, i, MyEvent.EVENT_TYPE_TEXT_VIEW);
                EventBus.getDefault().post( ev );
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        return null;
    }
}