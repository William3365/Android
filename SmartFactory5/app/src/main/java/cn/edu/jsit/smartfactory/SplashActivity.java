package cn.edu.jsit.smartfactory;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;

import cn.edu.jsit.smartfactory.tools.SmartFactoryApplication;


public class SplashActivity extends BaseActivity {

   Handler handler = new Handler();
   private int seconds = 6;
   private boolean skipping = false;
   private boolean running = true;
   private boolean wasRunning;
   private SharedPreferences spPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState!=null){
            seconds = savedInstanceState.getInt("seconds");
        }
        setContentView(R.layout.activity_splash);
        spPreferences = getSharedPreferences("loginSet", MODE_PRIVATE);
        SmartFactoryApplication.language = spPreferences.getString("language", "default");
        changeLanguage(SmartFactoryApplication.language);

        goToMain();
    }

    private void goToMain(){
        final TextView textView = (TextView) findViewById(R.id.count_tv);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                String time = String.format("%d"+"s",seconds);
                textView.setText(time);
                if(seconds==0||skipping==true) {
                    Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
                    startActivity(intent);
                }else{
                    if(running) seconds--;
                    handler.postDelayed(this,1000);
                }

            }
        },6000);
    }

    public void onClickCancel(View view){
        skipping = true;
    }

    @Override
    public void onStart(){
        super.onStart();
        if(wasRunning){
            running = true;
        }

    }

    @Override
    public void onStop(){
        super.onStop();
        wasRunning = running;
        running = false;

    }
}
