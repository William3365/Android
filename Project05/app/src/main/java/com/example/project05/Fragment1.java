package com.example.project05;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.fragment.app.Fragment;

public class Fragment1 extends Fragment {

    private LinearLayout linear_kt,linear_sh,linear_jsq;

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

        initView();
    }

    private void initView() {
        linear_kt = (LinearLayout)getActivity().findViewById(R.id.linear_kt);
        linear_sh = (LinearLayout)getActivity().findViewById(R.id.linear_sh);
        linear_jsq = (LinearLayout)getActivity().findViewById(R.id.linear_jsq);
        //调用监听器
        //空调
        linear_kt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new MyDialog(getActivity(), new MyDialog.Callback() {
                    @Override
                    public void call(String s) {

                    }
                }).show();
            }
        });
        //水壶
        linear_sh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new MyDialog(getActivity(), new MyDialog.Callback() {
                    @Override
                    public void call(String s) {

                    }
                }).show();
            }
        });
        //加湿器
        linear_jsq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new MyDialog(getActivity(), new MyDialog.Callback() {
                    @Override
                    public void call(String s) {

                    }
                }).show();
            }
        });
    }
}
