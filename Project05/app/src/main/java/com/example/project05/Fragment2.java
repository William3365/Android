package com.example.project05;

import android.content.Intent;
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

public class Fragment2 extends Fragment {

    private ImageView imageView;
    private Button button;
    private TextView textView;
    private boolean bt = false;//button初始状态
    private String zhanghao;

    Handler handler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            switch (msg.what){
                case 1:
                    //将传递过来的账号显示在此处
                    textView.setText(zhanghao);
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
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initView();

        //获取传递来的账号信息
        Intent intent1 = getActivity().getIntent();
        zhanghao = intent1.getStringExtra("zhanghao");
//        Log.d("---",zhanghao);
        handler.sendEmptyMessage(1);
    }

    private void initView() {
        imageView = (ImageView)getActivity().findViewById(R.id.sfm);
        button = (Button)getActivity().findViewById(R.id.sx);
        textView = (TextView)getActivity().findViewById(R.id.tv_zhanghao);

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
