package com.harpestes.worktime;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.text.DecimalFormat;
import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {

    CountDownTimer countDownTimer = null;
    TextView textView;
    Button startStopButton, pauseResumeButton;

    boolean isRunning = false;
    boolean isPaused = false;
    long timeLeft = 10000;
    long startTime = 10000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView1);
        startStopButton = findViewById(R.id.startStopButton);
        pauseResumeButton = findViewById(R.id.pauseResumeButton);

        startStopButton.setOnClickListener(v -> {
            if (!isRunning) {
                startTimer(timeLeft);
                isRunning = true;
                isPaused = false;
            } else {
                stopTimer();
                isRunning = false;
                isPaused = false;
                timeLeft = startTime;
                updateText(timeLeft);
            }
        });

        pauseResumeButton.setOnClickListener(v -> {
            if (isRunning) {
                if (!isPaused) {
                    stopTimer();
                    isPaused = true;
                } else {
                    startTimer(timeLeft);
                    isPaused = false;
                }
            }
        });

        updateText(timeLeft);
    }

    private void startTimer(long timeInMillis) {
        countDownTimer = new CountDownTimer(timeInMillis, 1000) {
            @Override
            public void onTick(long l) {
                timeLeft = l;
                updateText(timeLeft);
            }

            @Override
            public void onFinish() {
                String s = "Time is up";
                textView.setText(s);
                isRunning = false;
            }
        }.start();
    }

    private void stopTimer() {
        if(countDownTimer != null) {
            countDownTimer.cancel();
        }
    }

    private void updateText(long millis) {
        NumberFormat f = new DecimalFormat("00");
        long hour = (millis/3600000) % 24;
        long minute = (millis/60000) % 60;
        long seconds = (millis/1000) % 60;
        String formatedTime = String.join(":", f.format(hour), f.format(minute), f.format(seconds));
        textView.setText(formatedTime);
    }
}