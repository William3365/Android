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

import java.text.DecimalFormat;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class Fragment0 extends Fragment {

    private TextView tv1,tv2,tv3,tv4;
    private Button button;
    private Random random = new Random();
    private double wd,ph,cl,hz;//温度、ph、氯、浊度
    private DecimalFormat df = new DecimalFormat("0.00");
    private DecimalFormat dd = new DecimalFormat("0.0");
    private boolean shujv = false;

    Handler handler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            switch (msg.what){
                case 1:
                    tv1.setText(df.format(wd) + " ℃");
                    tv2.setText(dd.format(ph) + " ");
                    tv3.setText(df.format(cl) + "mg/L");
                    tv4.setText(df.format(hz) + " NTU");
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
        button = (Button)getActivity().findViewById(R.id.bt);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (shujv == false){
                    wd = wd - 0.2;
                    ph = ph - 0.1;
//                    cl = cl + 0.35;
//                    hz = hz + 0.34;
                    cl = cl + 0.1;
                    hz = hz + 0.1;
                    shujv = true;
                }else {
                    wd = wd + 0.2;
                    ph = ph + 0.1;
                    cl = cl - 0.01;
                    hz = hz - 0.01;
                    shujv = false;
                }
                handler.sendEmptyMessage(1);
                Toast toast = Toast.makeText(getActivity(),"数据刷新成功",Toast.LENGTH_LONG);
                toast.setGravity(Gravity.CENTER,0,0);
                toast.show();
            }
        });
    }
    //随机数
    private void myRandom() {
        wd = random.nextInt(29-27)+27;
        ph = random.nextInt(7-6)+6;
//        cl = random.nextInt(1-0)+0;
//        hz = random.nextInt(1-0)+0;
        cl = random.nextDouble();
        hz = random.nextDouble();
        handler.sendEmptyMessage(1);
    }

}
