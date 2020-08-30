package com.example.project05;

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

public class MyDialog extends Dialog {

    private com.example.project05.MyDialog myDialog;
    private Callback callback;
    private Button button;
    private TextView tv_r,tv_z,tv_y;
    private int day,week,month;

    Handler handler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            switch (msg.what){
                case 1:
                    tv_r.setText(day + "°");
                    tv_z.setText(week+ "°");
                    tv_y.setText(month+ "°");
                    break;
            }
        }
    };
    //实现接口
    public interface Callback{
        public void call(String s);
    }

    public MyDialog(Context context,Callback callback) {
        super(context);
        myDialog = this;
        this.callback = callback;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mydialog);

        button = (Button)findViewById(R.id.bt_dialog);
        tv_r = (TextView)findViewById(R.id.tv_r);
        tv_z = (TextView)findViewById(R.id.tv_z);
        tv_y = (TextView)findViewById(R.id.tv_y);
        //随机数
        Random random = new Random();
//        day = random.nextInt(4)%(4-1+1)+1;
        day = random.nextInt(4);
        week = random.nextInt(28);
        month = random.nextInt(125);
        handler.sendEmptyMessage(1);

        myDialog.setTitle("耗电量统计");

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDialog.dismiss();
            }
        });
    }
}
