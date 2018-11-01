package com.example.mike.progressbars;

import android.widget.ProgressBar;

public class MyEvent {

    int progress;
    int viewID;
    int eventType;
    public static final int EVENT_TYPE_PROGRESS_BAR = 0;
    public static final int EVENT_TYPE_TEXT_VIEW = 1;

    public MyEvent(int viewID, int progress, int eventType ) {
        this.progress = progress;
        this.viewID = viewID;
        this.eventType = eventType;
    }

    public int getProgress() {
        return progress;
    }

    public void setProgress(int progress) {
        this.progress = progress;
    }
}
