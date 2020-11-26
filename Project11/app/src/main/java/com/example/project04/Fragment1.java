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

    private Button bt_js,bt_tf,bt_zg;
    private TextView tv_js,tv_tf,tv_zg;
    private int i = 0;//按钮点击次数
    private int u = 2;
    private boolean zg = false;//遮光关闭状态

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
                    tv_tf.setText("通风完成！");
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            tv_tf.setText("");
                        }
                    },1500);
                    break;
                case 4:
                    tv_zg.setText("开启遮光！");
                    break;
                case 5:
                    tv_zg.setText("关闭遮光！");
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
        bt_tf = (Button)getActivity().findViewById(R.id.bt_tf);
        bt_zg = (Button)getActivity().findViewById(R.id.bt_zg);
        tv_js = (TextView)getActivity().findViewById(R.id.tv_js);
        tv_tf = (TextView)getActivity().findViewById(R.id.tv_tf);
        tv_zg = (TextView)getActivity().findViewById(R.id.tv_zg);
        //浇水
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
        //通风
        bt_tf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    handler.sendEmptyMessage(3);
            }
        });
        //遮光
        bt_zg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (zg == false){
                    handler.sendEmptyMessage(4);
                    zg = true;
                }else {
                    handler.sendEmptyMessage(5);
                    zg = false;
                }
            }
        });
    }
}
