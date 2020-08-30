package com.example.project03;

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

public class dialog extends Dialog {

    private Dialog dialog;
    private Callback callback;
    private Button button;
    private TextView tv_yj,tv_yh,tv_dh;
    private int yijie,yihuan,daihuan;//借阅信息
    Handler handler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            switch (msg.what){
                case 1:
                   tv_yj.setText(yijie + "");
                   tv_yh.setText(yihuan+ "");
                   tv_dh.setText(daihuan+ "");
                   break;
            }
        }
    };
    //实现接口
    public interface  Callback{
        public void call(String s);
    }

    //重写
    public dialog(Context context, Callback callback) {
        super(context);
        dialog = this;
        this.callback = callback;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog);

        button = (Button)findViewById(R.id.bt_dialog);
        tv_yj = (TextView)findViewById(R.id.tv_yj);
        tv_yh = (TextView)findViewById(R.id.tv_yh);
        tv_dh = (TextView)findViewById(R.id.tv_dh);

        //随机数
        Random random = new Random();
        yijie = random.nextInt(8)%(8-2+2)+2;
        yihuan = random.nextInt(6)%(6-2+2)+2;
        //保证随机数数量正确
        if (yijie > yihuan){
            daihuan = yijie - yihuan;
        }else if (yijie == yihuan){
            daihuan = 0;
        }else if (yijie < yihuan){
            daihuan = yijie;
        }
        handler.sendEmptyMessage(1);

        //设置标题
        dialog.setTitle("借阅消息");

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

    }
}
