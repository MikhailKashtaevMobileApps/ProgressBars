package com.example.mike.progressbars;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.mike.progressbars.tasks.MyAsyncTask;
import com.example.mike.progressbars.tasks.ProgressUpdater;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class MainActivity extends AppCompatActivity {

    private ThreadPoolExecutor executor;

    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(4);
    }

    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void callback(MyEvent event){
        if ( event.eventType == MyEvent.EVENT_TYPE_PROGRESS_BAR ){
            ProgressBar pb = (ProgressBar)findViewById(event.viewID);
            pb.setProgress( event.getProgress() );
        }else if ( event.eventType == MyEvent.EVENT_TYPE_TEXT_VIEW ){
            TextView tv = (TextView)findViewById(event.viewID);
            tv.setText( String.valueOf( event.progress ) );
        }
    }

    public void runTasks(View view) {

        ProgressUpdater progressUpdater1 = new ProgressUpdater(findViewById( R.id.pb1 ));
        ProgressUpdater progressUpdater2 = new ProgressUpdater(findViewById( R.id.pb2 ));
        ProgressUpdater progressUpdater3 = new ProgressUpdater(findViewById( R.id.pb3 ));
        ProgressUpdater progressUpdater4 = new ProgressUpdater(findViewById( R.id.pb4 ));

        executor.execute( progressUpdater1 );
        executor.execute( progressUpdater2 );
        executor.execute( progressUpdater3 );
        executor.execute( progressUpdater4 );

    }


    public void runAsync(View view) {

        AsyncTask<String, String, String> at0 = new MyAsyncTask(R.id.tvAsync0);
        at0.executeOnExecutor( executor );

        AsyncTask<String, String, String> at1 = new MyAsyncTask(R.id.tvAsync1);
        at1.executeOnExecutor( executor );
    }
}
