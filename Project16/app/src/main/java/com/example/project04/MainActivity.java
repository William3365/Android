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

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    //声明参数
    private ImageView img1,img2,img3;//干烧、UV杀菌
    private TextView tv_sd;
    private boolean i1 = false;//设置初状态
    private boolean i2 = false;
    private boolean i3 = false;
    private int sd;
    private Random random = new Random();

    Handler handler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            switch (msg.what){
                case 1:
                    tv_sd.setText("<   "+sd+" %RH"+"   >");
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

      initView();

      sd = random.nextInt(37-11)+11;
      Log.d("----",sd+"--");
      handler.sendEmptyMessage(1);
    }
    private void initView() {
        img1 = (ImageView)findViewById(R.id.img1);
        img2 = (ImageView)findViewById(R.id.img2);
        img3 = (ImageView)findViewById(R.id.img3);
        tv_sd = (TextView)findViewById(R.id.tv_sd);

        img1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (i1 == false){
                    img1.setImageResource(R.mipmap.off);
                    i1 = true;
                }else if (i1 == true){
                    img1.setImageResource(R.mipmap.on);
                    i1 = false;
                }
            }
        });
        img2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (i2 == false){
                    img2.setImageResource(R.mipmap.off);
                    i2 = true;
                }else if (i2 == true){
                    img2.setImageResource(R.mipmap.on);
                    i2 = false;
                }
            }
        });
        img3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (i3 == false){
                    img3.setImageResource(R.mipmap.off);
                    i3 = true;
                }else if (i3 == true){
                    img3.setImageResource(R.mipmap.on);
                    i3 = false;
                }
            }
        });

    }
}
