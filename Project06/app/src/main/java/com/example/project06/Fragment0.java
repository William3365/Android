package com.example.project06;

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

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class Fragment0 extends Fragment {

    private TextView tv1,tv2,tv3,tv4,tv5,tv6,tv7,tv8;
    private Button button;
    private Random random = new Random();
    private int lwd,lsd,wd,sd,co2,o2;
    private boolean body;

    Handler handler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            switch (msg.what){
                case 1:
                    tv1.setText("粮堆温度：" + lwd + " ℃");
                    tv2.setText("粮堆湿度：" + lwd + " %");
                    tv3.setText("仓内温度：" + lwd + " ℃");
                    tv4.setText("仓内温度：" + lwd + " %");
                    tv5.setText("仓内CO2浓度：" + lwd + " %");
                    tv6.setText("仓内O2浓度：" + lwd + " %");
                    tv8.setText("未发现火焰：");
                    break;
                case 2:
                    Log.d("---",body+"=====");
                    if (body == true){
                        tv7.setText("仓内人员：" + "有人（已认证）");
                    }else {
                        tv7.setText("仓内人员：" + "有人（未认证）");
                        Toast toast = Toast.makeText(getActivity(),"检测到非法入侵！请及时处理！",Toast.LENGTH_SHORT);
                        toast.setGravity(Gravity.CENTER,0,0);//指定toast位置
                        View view = toast.getView();//指定toast的背景
                        view.setBackgroundResource(R.drawable.toast02);
                        toast.show();
                    }
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
        tv6 = (TextView)getActivity().findViewById(R.id.tv6);
        tv7 = (TextView)getActivity().findViewById(R.id.tv7);
        tv8 = (TextView)getActivity().findViewById(R.id.tv8);
        button = (Button)getActivity().findViewById(R.id.bt);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast toast = Toast.makeText(getActivity(),"数据刷新成功",Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.CENTER,0,0);
                toast.show();
            }
        });
    }
    //随机数
    private void myRandom() {
        lwd = random.nextInt(45-22)+22;
        lsd = random.nextInt(45-12)+12;
        wd = random.nextInt(35-18)+18;
        sd = random.nextInt(30-12)+12;
        co2 = random.nextInt(65-22)+22;
        o2 = random.nextInt(35-22)+22;
        //生成Boolean形随机数
        body = random.nextBoolean();
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                handler.sendEmptyMessage(2);
            }
        },0,7000);
        handler.sendEmptyMessage(1);
    }

}
