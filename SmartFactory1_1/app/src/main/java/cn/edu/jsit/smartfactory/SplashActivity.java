package cn.edu.jsit.smartfactory;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class SplashActivity extends Activity {
    private int seconds = 6;
    private boolean skipping = false;

    Handler handler = new Handler();
    private boolean running=true;
    private boolean wasRunning;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        if (savedInstanceState!= null) {
            seconds = savedInstanceState.getInt("seconds");
            wasRunning=savedInstanceState.getBoolean("wasRunning");
        }
        goToMain();
    }

    private void goToMain() {
        final TextView timeView = (TextView) findViewById(R.id.count_tv);
        handler.post(new Runnable() {
            @Override
            public void run() {
                String time = String.format("%d" + "s", seconds);
                timeView.setText(time);
                if ((seconds == 0) || (skipping == true)) {
                    Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                    startActivity(intent);

                } else {
                    if (running) {
                        seconds--;
                    }
                    handler.postDelayed(this, 1000);
                }
            }
        });
    }

    public void onClickCancel(View view) {
        skipping = true;
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putInt("seconds", seconds);
        savedInstanceState.putBoolean("wasRunning",wasRunning);
    }
    @Override
    public void onStop(){
        super.onStop();
        wasRunning=running;
        running=false;
    }
    @Override
    protected void onStart(){
        super.onStart();
        if (wasRunning){
            running=true;
        }
    }
}
