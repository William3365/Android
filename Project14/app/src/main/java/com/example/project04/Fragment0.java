package com.example.project04;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import java.util.Timer;
import java.util.TimerTask;

public class Fragment0 extends Fragment {

    private TextView tv1,tv2;

    Handler handler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            switch (msg.what){
                case 1:
                    tv1.setText("宝宝不哭！");
                    tv2.setText("宝宝很乖！");
                    break;
                case 2:
                    tv2.setText("宝宝尿床啦！");
                    Toast toast = Toast.makeText(getActivity(),"宝宝尿床啦！",Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER,0,0);//指定toast位置
                    View view = toast.getView();//指定toast的背景
                    view.setBackgroundResource(R.drawable.toast);
                    toast.show();
                    break;
                case 3:
                    tv1.setText("宝宝哭了！");
                    Toast toast1 = Toast.makeText(getActivity(),"宝宝哭了！",Toast.LENGTH_SHORT);
                    toast1.setGravity(Gravity.CENTER,0,0);//指定toast位置
                    View view1 = toast1.getView();//指定toast的背景
                    view1.setBackgroundResource(R.drawable.toast);
                    toast1.show();
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

        handler.sendEmptyMessage(1);
    }

    private void initView() {

        tv1 = (TextView)getActivity().findViewById(R.id.tv1);
        tv2 = (TextView)getActivity().findViewById(R.id.tv2);

        //延时函数
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                for (int i = 0;i<3;i++){
                //定时器
                handler.sendEmptyMessage(2);
                }

                for (int j = 1;j<=2;j++){
                    handler.sendEmptyMessage(3);
                }
            }
        },5000);


    }

}
