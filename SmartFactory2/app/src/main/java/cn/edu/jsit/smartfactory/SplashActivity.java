package cn.edu.jsit.smartfactory;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

public class SplashActivity extends Activity {
    Handler handler = new Handler();
    private int seconds = 6;
    private boolean skipping = false;//初置状态
    private boolean running = true;
    private boolean wasRunning;//不可见之前的状态

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //得到存储在seconds对象中的数据
        if (savedInstanceState != null) {
            seconds = savedInstanceState.getInt("seconds");
        }
        setContentView(R.layout.activity_splash);

        goTOMain();
    }

    private void goTOMain() {
        final TextView textView = (TextView) findViewById(R.id.count_tv);
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
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                //显示倒计时
                String time = String.format("%d" + "s", seconds);
                textView.setText(time);
                //对界面情况进行判断以执行下一步操作
                if ((seconds == 0) || (skipping == true)) {
                    Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                    startActivity(intent);
                } else {
                    if (running){
                        seconds--;
                    }
                    handler.postDelayed(this, 1000);
                }
            }
        },0);
    }

    //此处参数意为，触发此方法的GUI组件
    public void onClickCancel(View view) { skipping = true; }
    //任务二：创建Splash界面--15
    @Override
    public void onSaveInstanceState(Bundle savedInstanceState){
        //利用put方法,将数据存储到seconds对象中
        savedInstanceState.putInt("seconds",seconds);
        savedInstanceState.putBoolean("wasRunning",wasRunning);
    }
    //不可见
    @Override
    public void onStart(){
        super.onStart();
        if(wasRunning){
            running = true;
        }
    }
    //可见
    @Override
    public void onStop(){
        super.onStop();
        wasRunning = running;
        running = false;
    }
}
