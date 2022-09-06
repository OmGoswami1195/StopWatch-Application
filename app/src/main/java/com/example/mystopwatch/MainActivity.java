package com.example.mystopwatch;


import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;

@SuppressWarnings("ALL")
public class MainActivity extends Activity {

    private int seconds = 0;
    private boolean running;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(savedInstanceState != null){
            seconds = savedInstanceState.getInt("seconds");
            running = savedInstanceState.getBoolean("running");
        }
        runTimer();
    }

    public void onSaveInstanceState(Bundle saveInstanceState) {
        saveInstanceState.putInt("seconds",seconds);
        saveInstanceState.putBoolean("running",running);
    }

    public void onclickstart(View view){
        running = true;
    }

    public void onclickstop(View view){
        running = false;
    }

    public void onclickreset(View view){
        running = false;
        seconds = 0;
    }

    private void runTimer(){
        final TextView timeview = (TextView) findViewById(R.id.time_view);
        final Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                int hours = seconds/3600;
                int minutes = (seconds%3600)/60;
                int secs = seconds%60;

                String time = String.format("%02d:%02d:%02d",hours,minutes,secs);

                timeview.setText(time);
                if(running){
                    seconds++;
                }
                handler.postDelayed(this,1000);
            }
        });
    }
}