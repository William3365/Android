package com.example.project04;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

public class Fragment1 extends Fragment {

    private Button bt_js,bt_bgd;
    private TextView tv_js,tv_bgd;
    private int i = 0;//按钮点击次数
    private int u = 2;
    private boolean bgd = false;//遮光关闭状态

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            switch (msg.what){
                case 1:
                    tv_js.setText("浇水完成！");
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            tv_js.setText("");
                        }
                    },1500);
                    break;
                case 2:
                    tv_js.setText("浇水过多！");
                    break;
                case 3:
                    tv_bgd.setText("已开启补光灯！");
                    break;
                case 4:
                    tv_bgd.setText("已关闭补光灯！");
                    break;
            }
        }
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_fragment1);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState){
        View view = inflater.inflate(R.layout.activity_fragment1,container,false);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        bt_js = (Button)getActivity().findViewById(R.id.bt_js);
        bt_bgd = (Button)getActivity().findViewById(R.id.bt_bgd);
        tv_js = (TextView)getActivity().findViewById(R.id.tv_js);
        tv_bgd = (TextView)getActivity().findViewById(R.id.tv_bgd);
        //浇水
        /*
            通过限制点击次数，从而控制显示内容
         */
        bt_js.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (i <= u){
                    handler.sendEmptyMessage(1);
                    i++;
                }else {
                    handler.sendEmptyMessage(2);
                }
            }
        });

        //遮光
        bt_bgd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (bgd == false){
                    handler.sendEmptyMessage(3);
                    bgd = true;
                }else {
                    handler.sendEmptyMessage(4);
                    bgd = false;
                }
            }
        });
    }
}
