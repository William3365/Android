package com.example.onenet01;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView textView;
    Handler handler = new Handler();
    Mythread mythread = new Mythread();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                handler.postDelayed(this,5*50);
                textView=(TextView)findViewById(R.id.tv);
                String Temp1 = mythread.Getvalue1();//将数据赋值给Temp1
                textView.setText(""+Temp1);//数据写入layout里面的tvtemp1
                Log.d("---","A");
            }
        };
        handler.post(runnable);
    }

}
