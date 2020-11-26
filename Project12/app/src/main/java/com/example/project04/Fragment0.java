package com.example.project04;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import java.sql.Time;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class Fragment0 extends Fragment {

    private TextView tv_wd,tv_sd,tv_gz,tv_tr;
    private Button button;
    private Random random = new Random();
    private int wd,sd,gz,tr;
    private Toast toast;
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            switch (msg.what){
                case 1:
                    tv_wd.setText(wd + " °C");
                    tv_sd.setText(sd + " %");
                    tv_gz.setText(gz + " lux");
                    tv_tr.setText(tr + " %");
                    break;
                default:
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
        tv_wd = (TextView)getActivity().findViewById(R.id.tv_wd);
        tv_sd = (TextView)getActivity().findViewById(R.id.tv_sd);
        tv_gz = (TextView)getActivity().findViewById(R.id.tv_gz);
        tv_tr = (TextView)getActivity().findViewById(R.id.tv_tr);
    }

    //随机数
    private void myRandom() {
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                wd = random.nextInt(18-13)+13;
                sd = random.nextInt(78-44)+44;
                gz = random.nextInt(3000-1700)+1700;
                tr = random.nextInt(55-12)+12;

                handler.sendEmptyMessage(1);
            }
        },0,8000);

    }

}
