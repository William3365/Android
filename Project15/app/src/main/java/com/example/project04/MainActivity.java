package com.example.project04;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    //声明参数
    private ImageView ganshao,uv;//干烧、UV杀菌
    private TextView tv_ganshao,tv_uv;
    private boolean G = false;//设置初状态
    private boolean U = false;

    Handler handler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            switch (msg.what){
                case 1:
                    tv_uv.setText("UV杀菌：关");
                    break;
                case 2:
                    tv_uv.setText("UV杀菌：开");
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

      initView();
    }
    private void initView() {
        ganshao = (ImageView)findViewById(R.id.ganshao);
        uv = (ImageView)findViewById(R.id.uv);
        tv_ganshao = (TextView)findViewById(R.id.tv_ganshao);
        tv_uv = (TextView)findViewById(R.id.tv_uv);

        //监听
        ganshao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (G == false){
                    ganshao.setImageResource(R.mipmap.water);
                    G = true;
                }else if (G == true){
                    ganshao.setImageResource(R.mipmap.fire);
                    G = false;
                    Toast toast = Toast.makeText(MainActivity.this,"水位过低，干烧危险！",Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.CENTER,0,0);
                    toast.show();
                }
            }
        });
        uv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (U == false){
                    uv.setImageResource(R.mipmap.off);
                    U = true;
                    handler.sendEmptyMessage(1);
                }else if (U == true){
                    uv.setImageResource(R.mipmap.on);
                    U = false;
                    handler.sendEmptyMessage(2);
                }
            }
        });
    }
}
