package com.example.mike.progressbars;

import android.util.Log;

import com.example.mike.progressbars.tasks.ProgressUpdater;

import java.util.ArrayList;
import java.util.concurrent.Executor;

public class DirectExecutor implements Executor {

    @Override
    public synchronized void execute(Runnable command) {
        ProgressUpdater progressUpdater = (ProgressUpdater) command;
        Thread t = new Thread( progressUpdater );
        t.start();
    }
}
