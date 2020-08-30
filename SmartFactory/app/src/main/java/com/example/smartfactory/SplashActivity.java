package com.example.smartfactory;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;

public class SplashActivity extends AppCompatActivity {

    Handler handler = new Handler();
    private int seconds = 6;
    private boolean skipping = false;//置初状态
    private boolean running = true;
    private boolean wasRunning;//不可见之前的状态

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        //得到存储在seconds对象中的数据
        if (savedInstanceState!=null){
            seconds = savedInstanceState.getInt("seconds");
        }

        GotoMain();
    }

    private void GotoMain() {
        final TextView tv3 = (TextView)findViewById(R.id.tv3);
        /*postDelayed方法，用来提交将来运行的代码
         * 两个参数：一个是 Runnable 对象，包含run方法，其中有要运行的代码
         *         一个是 延时时间
         *
         *    handler.postDelayed(new Runnable() {
         *    @Override
         *     public void run() {
         *        //Intent-意图，from.this to that.clasa
         *         Intent intent = new Intent(SplashActivity.this,MainActivity.class);
         *        //start the intent
         *         startActivity(intent);
         *       },6000);
         *  }
         */
        //post方法，立即执行
        handler.post(new Runnable() {
            @Override
            public void run() {
                //显示倒计时
                String time = String.format("%d"+"s",seconds);
                tv3.setText(time);
                //对界面情况进行判断以执行下一步操作
                if ((seconds==0)||(skipping==true)){
                    Intent intent = new Intent(SplashActivity.this,MainActivity.class);
                    startActivity(intent);
                }else {
                    if (running){
                        seconds--;
                    }
                    handler.postDelayed(this,1000);
                }
            }
        });
    }
    //此处参数意为，触发此方法的GUI组件
    public void onClickCancel(View view){
        skipping=true;//改变状态
    }

    //任务二：创建Splash界面--15
    @Override
    public void onSaveInstanceState(Bundle savedInstanceState){
        //利用put方法,将数据存储到seconds对象中
        savedInstanceState.putInt("seconds",seconds);
        savedInstanceState.putBoolean("wasRunning",wasRunning);
    }
    //不可见
    @Override
    protected void onStop(){
        super.onStop();
        wasRunning = running;
        running = false;
    }
    //可见
    @Override
    protected void onStart(){
        super.onStart();
        if (wasRunning){
            running = true;
        }
    }
}
