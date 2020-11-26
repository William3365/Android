package com.example.project04;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.text.DecimalFormat;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    //声明参数
    private ImageView img1, img2;
    private TextView tv_wd, tv_sd;
    private boolean i1 = false;//设置初状态
    private boolean i2 = false;
    private boolean shujv = false;
    private double wd, sd;
    private Random random = new Random();
    private DecimalFormat df = new DecimalFormat("0.00");

    Handler handler = new Handler() {
        @Override
        public void handleMessage(@NonNull Message msg) {
            switch (msg.what) {
                case 1:
                    tv_wd.setText(df.format(wd)+"");
                    tv_sd.setText(df.format(sd)+"");
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        MyRandom();

    }

    private void MyRandom() {
        wd = random.nextInt(35 -28) + 28;
        sd = random.nextInt(67 - 27) + 27;
        Log.d("----", sd + "--");
        handler.sendEmptyMessage(1);

        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                if (shujv == false){
                    wd = wd - 0.2;
                    sd = sd - 0.3;
                }else {
                    wd = wd + 0.2;
                    sd = sd + 0.3;
                }
                handler.sendEmptyMessage(1);
            }
        },0,8000);
    }

    private void initView() {
        img1 = (ImageView) findViewById(R.id.img1);
        img2 = (ImageView) findViewById(R.id.img2);
        tv_sd = (TextView) findViewById(R.id.tv_sd);
        tv_wd = (TextView) findViewById(R.id.tv_wd);

        img1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (i1 == false) {
                    img1.setImageResource(R.mipmap.off);
                    i1 = true;
                } else if (i1 == true) {
                    img1.setImageResource(R.mipmap.on);
                    i1 = false;
                }
            }
        });
        img2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (i2 == false) {
                    img2.setImageResource(R.mipmap.off);
                    i2 = true;
                } else if (i2 == true) {
                    img2.setImageResource(R.mipmap.on);
                    i2 = false;
                }
            }
        });

    }
}
