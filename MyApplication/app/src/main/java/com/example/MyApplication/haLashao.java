package com.example.MyApplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import cn.com.newland.nle_sdk.requestEntity.SignIn;
import cn.com.newland.nle_sdk.responseEntity.SensorInfo;
import cn.com.newland.nle_sdk.responseEntity.User;
import cn.com.newland.nle_sdk.responseEntity.base.BaseResponseEntity;
import cn.com.newland.nle_sdk.util.NCallBack;
import cn.com.newland.nle_sdk.util.NetWorkBusiness;

/*
    注释的部分，可以实现本地化假数据。
    现行部分，逻辑语法是这样，但是没有测试过。
 */

public class haLashao extends AppCompatActivity {

    TextView tvWd,tvSd;
    Button btOpen,btClose;
//    int numWd;
//    int numSd = 83;
int numWd;
    int numSd;
String token;

    Handler handler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            switch (msg.what){
                case 1:
                    tvWd.setText("温度值：" + numWd + "°C");
                    break;
                case 2:
                    tvSd.setText("湿度值：" + numSd + "%");
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ha_lashao);

        NetWorkBusiness netWorkBusiness = new NetWorkBusiness("","http://www.nlecloud.com/");
        netWorkBusiness.signIn(new SignIn("", ""), new NCallBack<BaseResponseEntity<User>>(haLashao.this) {
            @Override
            protected void onResponse(BaseResponseEntity<User> userBaseResponseEntity) {
                if (userBaseResponseEntity.getStatus()==0){
                    token=userBaseResponseEntity.getResultObj().getAccessToken();
                    Toast.makeText(haLashao.this,"success",Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(haLashao.this,"fail",Toast.LENGTH_SHORT).show();
                }
            }
        });

        tvWd = (TextView)findViewById(R.id.tvWd);
        tvSd = (TextView)findViewById(R.id.tvSd);
        btOpen = (Button) findViewById(R.id.btOpen);
        btClose = (Button) findViewById(R.id.btClose);

//        new Timer().schedule(new TimerTask() {
//            @Override
//            public void run() {
////                myRandom();
////                handler.sendEmptyMessage(1);
////                handler.sendEmptyMessage(2);
//                NetWorkBusiness netWorkBusiness = new NetWorkBusiness(token,"http://www.nlecloud.com/");
//                //温度
//                netWorkBusiness.getSensor("TftDemo", "35", new NCallBack<BaseResponseEntity<SensorInfo>>(haLashao.this) {
//                    @Override
//                    protected void onResponse(BaseResponseEntity<SensorInfo> sensorInfoBaseResponseEntity) {
//                        SensorInfo sensorInfo = sensorInfoBaseResponseEntity.getResultObj();
//                        numWd = sensorInfo.getValue();
//                    }
//                });
//                handler.sendEmptyMessage(1);
//                //湿度
//                netWorkBusiness.getSensor("TftDemo", "36", new NCallBack<BaseResponseEntity<SensorInfo>>(haLashao.this) {
//                    @Override
//                    protected void onResponse(BaseResponseEntity<SensorInfo> sensorInfoBaseResponseEntity) {
//                        SensorInfo sensorInfo = sensorInfoBaseResponseEntity.getResultObj();
//                        numSd=sensorInfo.getValue();
//                    }
//                });
//                handler.sendEmptyMessage(2);
//            }
//        },0,5000);

        btOpen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                On();
                Toast.makeText(haLashao.this,"打开成功",Toast.LENGTH_SHORT).show();
            }
        });
        btClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Off();
                Toast.makeText(haLashao.this,"关闭成功",Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void myRandom() {
        Random random = new Random();
        numWd = random.nextInt(36)%(36-31+1)+31;
        numSd = random.nextInt(85)%(85-84+1)+84;
    }
//private void On() {
//    NetWorkBusiness netWorkBusiness = new NetWorkBusiness(token,"http://www.nlecloud.com/");
//    netWorkBusiness.control("TftDemo", "vsmmfcivnkub", 1, new NCallBack<BaseResponseEntity>(haLashao.this) {
//        @Override
//        protected void onResponse(BaseResponseEntity baseResponseEntity) {
//
//        }
//    });
//}
//    private void Off() {
//        NetWorkBusiness netWorkBusiness = new NetWorkBusiness(token,"http://www.nlecloud.com/");
//        netWorkBusiness.control("TftDemo", "vsmmfcivnkub", 0, new NCallBack<BaseResponseEntity>(haLashao.this) {
//            @Override
//            protected void onResponse(BaseResponseEntity baseResponseEntity) {
//
//            }
//        });
//    }

}
