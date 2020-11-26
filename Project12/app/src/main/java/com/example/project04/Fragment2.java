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
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

public class Fragment2 extends Fragment {

    private Button et_zh,et_mm,bt01,bt02;
    private TextView tv_zc;

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
    }
    private void initView() {
        et_zh = (Button)getActivity().findViewById(R.id.et_zh);
        et_mm = (Button)getActivity().findViewById(R.id.et_mm);
        tv_zc = (TextView)getActivity().findViewById(R.id.tv_zc);
        bt01 = (Button)getActivity().findViewById(R.id.bt01);
        bt02 = (Button)getActivity().findViewById(R.id.bt02);

        //注册控件监听
        tv_zc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (et_zh.getText().toString().length() == 0 && et_mm.getText().toString().length() == 0){

                    Intent intent = new Intent(getActivity(),zhuce.class);
//                    Log.d("---","=====");
                    startActivity(intent);
                }
            }
        });
        //两个虚拟按钮
        bt01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast toast = Toast.makeText(getActivity(),"硬件还没添加",Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.CENTER,0,0);
                toast.show();
            }
        });
        bt02.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast toast = Toast.makeText(getActivity(),"硬件还没添加",Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.CENTER,0,0);
                toast.show();
            }
        });
    }
}
