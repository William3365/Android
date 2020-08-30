package com.example.project04;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import java.util.Random;

public class Fragment2 extends Fragment {

    private ImageView imageView;
    private Button button;
    private boolean bt = false;//button初始状态
    private TextView tv_zhanghao,tv_zhuangtai,tv_shijian,tv_taocan;
    Random random = new Random();
    private String zhanghao;//账号
    private boolean b = true;//持卡状态
    private int shijian,taocan;

    Handler handler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            switch (msg.what){
                case 1:
                    //将传递过来的账号显示在此处
                    tv_zhanghao.setText(zhanghao);
                    //时间
                    shijian = random.nextInt(300)%(300-5+5)+5;
                    tv_shijian.setText(shijian+"天");
                    //持卡状态
                    if (b == true){
                        tv_zhuangtai.setText("持卡");
                        b = false;
                    }else if (b == false){
                        tv_zhuangtai.setText("交易");
                        b = true;
                    }
                    taocan = random.nextInt(999)%(999-30+30)+30;
                    tv_taocan.setText(taocan+" 元");
                    break;
                default:
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
        View view = inflater.inflate(R.layout.activity_fragment2,container,false);
        return view;
    }
//在fragment中获取控件需使用getActivity()
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initView();
    }

    private void initView() {
        imageView = (ImageView)getActivity().findViewById(R.id.sfm);
        button = (Button)getActivity().findViewById(R.id.sx);
        tv_zhanghao = (TextView)getActivity().findViewById(R.id.tv_zhanghao);
        tv_zhuangtai = (TextView)getActivity().findViewById(R.id.tv_zhuangtai);
        tv_shijian = (TextView)getActivity().findViewById(R.id.tv_shijian);
        tv_taocan = (TextView)getActivity().findViewById(R.id.tv_taocan);

        //获取传递来的intent
        Intent intent1 = getActivity().getIntent();
        //存储获取到的值
        zhanghao = intent1.getStringExtra("zhanghao");
//Log.d("---",zhanghao);
        handler.sendEmptyMessage(1);

        //改变身份码
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (bt == false){
                    imageView.setImageResource(R.mipmap.zhanghao002);
                    bt = true;
                }else if (bt == true){
                    imageView.setImageResource(R.mipmap.zhanghao003);
                    bt = false;
                }

            }
        });
    }
}
