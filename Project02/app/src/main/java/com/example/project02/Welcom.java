package com.example.project02;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class Welcom extends AppCompatActivity {

    Handler handler = new Handler();
    private int seconds = 6;
    private boolean skipping = false;//置初状态
    private boolean running = true;
    private boolean wasRunning;//不可见之前的状态

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //去掉顶部标题
//        getSupportActionBar().hide();
        setContentView(R.layout.activity_welcom);
        //设置背景透明度0-255
        RelativeLayout relativeLayout = (RelativeLayout)findViewById(R.id.reLayout);
        relativeLayout.getBackground().setAlpha(140);
        //得到存储在seconds对象中的数据
        if (savedInstanceState!=null){
            seconds = savedInstanceState.getInt("seconds");
        }

        GotoMain();
    }

    private void GotoMain() {
        final TextView tv_time = (TextView)findViewById(R.id.tv_time);
        //post方法，立即执行
        handler.post(new Runnable() {
            @Override
            public void run() {
                //显示倒计时
                String time = String.format("%d"+"s",seconds);
                tv_time.setText(time);
                //对界面情况进行判断以执行下一步操作
                if ((seconds==0)||(skipping==true)){
                    Intent intent = new Intent(Welcom.this,Login.class);
                    startActivity(intent);
                    //结束此线程
                    finish();
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
    public void onClickCancel(View view) {
        skipping=true;//改变状态
    }
    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        //利用put方法,将数据存储到seconds对象中
        savedInstanceState.putInt("seconds", seconds);
    }
    //不可见
    @Override
    public void onStart() {
        super.onStart();
        if(wasRunning){
            running = true;
        }
    }
    //可见
    @Override
    public void onStop() {
        super.onStop();
        wasRunning = running;
        running = false;
    }
}
