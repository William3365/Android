package cn.edu.jsit.smartfactory;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;

public class SplashActivity extends Activity {

    Handler handler = new Handler();
    private int seconds = 6;
    private boolean skipping = false;
    private boolean running = true;
    private boolean wasRunning;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {
            seconds = savedInstanceState.getInt("seconds");
        }
        setContentView(R.layout.activity_splash);
        goToMain();
    }

    private void goToMain() {
        final TextView textView = (TextView) findViewById(R.id.count_tv);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                String time = String.format("%d" + "s", seconds);
                textView.setText(time);
                if (seconds == 0 || skipping == true) {
                    Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                    startActivity(intent);
                } else {
                    if(running) seconds--;
                    handler.postDelayed(this, 1000);
                }

            }
        }, 6000);
    }

    public void onClickCancel(View view) {
        skipping = true;
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putInt("seconds", seconds);
    }

    @Override
    public void onStart() {
        super.onStart();
        if(wasRunning){
            running = true;
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        wasRunning = running;
        running = false;

    }
}