package com.harpestes.worktime;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.text.DecimalFormat;
import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {

    CountDownTimer countDownTimer = null;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView1);

        startTimer(10000);
    }

    private void startTimer(long timeInMillis) {
        countDownTimer = new CountDownTimer(timeInMillis, 1000) {
            @Override
            public void onTick(long l) {
                NumberFormat f = new DecimalFormat("00");
                long hour = (l/3600000) % 24;
                long minute = (l/60000) % 60;
                long seconds = (l/1000) % 60;
                String formatedTime = String.join(":", f.format(hour), f.format(minute), f.format(seconds));
                textView.setText(formatedTime);
            }

            @Override
            public void onFinish() {
                String s = "Time is up";
                textView.setText(s);
            }
        }.start();
    }

    private void stopTimer() {
        if(countDownTimer != null) {
            countDownTimer.cancel();
        }
    }
}