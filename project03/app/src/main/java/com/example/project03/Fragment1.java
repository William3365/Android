package com.example.project03;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.fragment.app.Fragment;

public class Fragment1 extends Fragment {

    private LinearLayout linearLayout;
    String s;

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
        linearLayout = (LinearLayout)getActivity().findViewById(R.id.xtxx);
        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//错误: 不兼容的类型: Fragment无法转换为Context，使用getActivity()，代替Fragment1.this
                new dialog(getActivity(), new dialog.Callback() {
                    @Override
                    public void call(String s) {

                    }
                }).show();
            }
        });
    }
}
