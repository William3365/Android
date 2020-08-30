package com.example.project04;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

import javax.security.auth.callback.Callback;

public class mydialog extends Dialog {

    private com.example.project04.mydialog mydialog;
    private Callback callback;
    private Button button;
    private TextView tv_all,tv_yxh,tv_wdc;
    private int all,yxh,wdc;

    Handler handler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            switch (msg.what){
                case 1:
                    tv_all.setText(all + "卡路里");
                    tv_yxh.setText(yxh+ "卡路里");
                    tv_wdc.setText(wdc+ "卡路里");
                    break;
            }
        }
    };

    //实现接口
    public interface  Callback{
        public void call(String s);
    }

    //重写
    public mydialog(Context context, Callback callback) {
        super(context);
        mydialog = this;
        this.callback = callback;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mydialog);

        button = (Button)findViewById(R.id.bt_dialog);
        tv_all = (TextView)findViewById(R.id.tv_all);
        tv_yxh = (TextView)findViewById(R.id.tv_yxh);
        tv_wdc = (TextView)findViewById(R.id.tv_wdc);
        //随机数
        Random random = new Random();
        all = random.nextInt(108)%(108-20+20)+20;
        yxh = random.nextInt(106)%(106-20+20)+20;
        //保证随机数数量正确
        if (all > yxh){
            wdc = all - yxh;
        }else if (all == yxh){
            wdc = 0;
        }else if (all < yxh){
            wdc = all;
        }
        handler.sendEmptyMessage(1);

        //设置标题
        mydialog.setTitle("训练计划");

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mydialog.dismiss();
            }
        });
    }
}
