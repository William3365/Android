package com.example.project04;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class Fragment0 extends Fragment {

    private TextView tv_ranqi,tv_shebei;
    private boolean ranqi,shebei;

    private Handler handler  = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            switch (msg.what){
                case 1:
                    tv_ranqi.setText("燃气值：" + " 正常");
                    break;
                case 2:
                    tv_shebei.setText("设备运行状态：" + " 正常");
                    break;
                case 3:
                    tv_ranqi.setText("燃气值：" + " 燃气浓度过高");
                    Toast toast = Toast.makeText(getActivity(),"燃气浓度过高，系统已关闭阀门，请及时查看处理！",Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER,0,0);//指定toast位置
                    View view = toast.getView();//指定toast的背景
                    view.setBackgroundResource(R.drawable.toast);
                    toast.show();
                    break;
                case 4:
                    tv_shebei.setText("设备运行状态：" + " 设备异常");
                    toast = Toast.makeText(getActivity(),"设备运行异常，请及时检修！",Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER,0,50);//指定toast位置
                    view = toast.getView();//指定toast的背景
                    view.setBackgroundResource(R.drawable.toast01);
                    toast.show();
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

    }

    private void initView() {
        tv_ranqi = (TextView)getActivity().findViewById(R.id.tv_ranqi);
        tv_shebei = (TextView)getActivity().findViewById(R.id.tv_shebei);

        Random random = new Random();
        ranqi = random.nextBoolean();
        shebei = random.nextBoolean();

        //燃气状态
        if (ranqi == false){
            handler.sendEmptyMessage(1);
        }else {
            new Timer().schedule(new TimerTask() {
                @Override
                public void run() {
                    handler.sendEmptyMessage(3);;
                }
            },0,6000);
        }

        //设备状态
        if (shebei == false){
            handler.sendEmptyMessage(2);
        }else {
            new Timer().schedule(new TimerTask() {
                @Override
                public void run() {
                    handler.sendEmptyMessage(4);
                }
            },0,7000);
        }
    }

}
