package com.example.stayfit.HomeActivities;

import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;

import com.example.stayfit.R;

public class Stopwatch extends AppCompatActivity {
    Button start , stop,reset;
    Chronometer time;
    private long pauseOff;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stopwatch);
        start = findViewById(R.id.buttonStart);
        stop = findViewById(R.id.buttonstop);
        reset = findViewById(R.id.buttonreset);
        time = findViewById(R.id.timehere);

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                time.setBase(SystemClock.elapsedRealtime() - pauseOff);
                time.start();
                time.getBase();
            }
        });
        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pauseOff = SystemClock.elapsedRealtime() - time.getBase();
                time.stop();
            }
        });

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                time.setBase(SystemClock.elapsedRealtime());
                pauseOff = 0;
                time.stop();
            }
        });

    }
}
