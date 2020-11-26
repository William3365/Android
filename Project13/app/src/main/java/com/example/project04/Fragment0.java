package com.example.project04;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import java.text.DecimalFormat;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class Fragment0 extends Fragment {
    /*
      温湿度、pm2.5、二氧化碳、二氧化硫
     */
    private TextView tv1,tv2,tv3,tv4,tv5;
    private Button button;
    private boolean shuaxin = false;//刷新状态
    private Random random = new Random();
    private double wd,sd,pm,co2,so2;
    //设置double类型小数点后位数格式  
    DecimalFormat df = new DecimalFormat("0.00");

    Handler handler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            switch (msg.what){
                case 1:
                    tv1.setText("温度：" + df.format(wd) + " ℃");
                    tv2.setText("湿度：" + df.format(sd) + " %");
                    tv3.setText("PM2.5浓度：" + df.format(pm) + " μg/m³");
                    tv4.setText("CO2浓度:" + df.format(co2) + " %");
                    tv5.setText("SO2浓度：" + df.format(so2) + " %");
                    break;
            }
        }
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_fragment0);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle saveInstanceState){
        View view = inflater.inflate(R.layout.activity_fragment0,container,false);
        return view;
    }
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initView();
        myRandom();
    }

    private void initView() {
        tv1 = (TextView)getActivity().findViewById(R.id.tv1);
        tv2 = (TextView)getActivity().findViewById(R.id.tv2);
        tv3 = (TextView)getActivity().findViewById(R.id.tv3);
        tv4 = (TextView)getActivity().findViewById(R.id.tv4);
        tv5 = (TextView)getActivity().findViewById(R.id.tv5);
        button = (Button)getActivity().findViewById(R.id.bt);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sx();
                Toast toast = Toast.makeText(getActivity(),"数据刷新成功",Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.CENTER,0,0);
                toast.show();
            }
        });
    }

    //随机数
    private void myRandom() {
        //生成效数
        wd = random.nextDouble()*(25-18)+18;
        sd = random.nextDouble()*(30-12)+12;
        pm = random.nextDouble()*(45-12)+12;
        co2 =random.nextDouble()*(4-2)+2;
        so2 =random.nextDouble()*(2-1)+0.5;

        handler.sendEmptyMessage(1);
    }
    //刷新数据
    private void sx() {
        if (shuaxin == false){
            wd = wd - 0.5;
            sd = sd - 0.7;
            pm = pm - 0.9;
            co2 = co2 - 0.4;
            so2 = so2 - 0.4;
            shuaxin = true;
        }else {
            wd = wd + 0.5;
            sd = sd + 0.7;
            pm = pm + 0.9;
            co2 = co2 + 0.4;
            so2 = so2 + 0.4;
            shuaxin = false;
        }

        handler.sendEmptyMessage(1);
    }
}
