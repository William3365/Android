package com.example.project04;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;

public class Fragment1 extends Fragment {


    private LinearLayout linearLayout;

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

//        bt1 = (TextView) getActivity().findViewById(R.id.bt1);
//        bt1.setOnClickListener(new mylisten());
        init();

    }

    private void init() {
        linearLayout = (LinearLayout)getActivity().findViewById(R.id.xtxx);
        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//错误: 不兼容的类型: Fragment无法转换为Context，使用getActivity()，代替Fragment1.this
                new mydialog(getActivity(), new mydialog.Callback() {
                    @Override
                    public void call(String s) {

                    }
                }).show();
            }
        });
    }

}
