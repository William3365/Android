package com.example.project04;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.fragment.app.Fragment;

public class Fragment0 extends Fragment {

    private LinearLayout li_1,li_2,li_3,li_4;

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
        li_1 = (LinearLayout)getActivity().findViewById(R.id.li_1);
        li_2 = (LinearLayout)getActivity().findViewById(R.id.li_2);
        li_3 = (LinearLayout)getActivity().findViewById(R.id.li_3);
        li_4 = (LinearLayout)getActivity().findViewById(R.id.li_4);

        li_1.setOnClickListener(new mylisten());
        li_2.setOnClickListener(new mylisten());
        li_3.setOnClickListener(new mylisten());
        li_4.setOnClickListener(new mylisten());

//        Intent intent= new Intent();
//        intent.setAction("android.intent.action.VIEW");
//        Uri ss = Uri.parse("https://www.baidu.com/");
//        intent.setData(ss);
//        startActivity(intent);


    }
    private class mylisten implements View.OnClickListener {
        @SuppressLint("ResourceAsColor")
        @Override
        public void onClick(View v){
            int key = v.getId();

            Intent intent= new Intent();
            intent.setAction("android.intent.action.VIEW");

            switch (key){
                case R.id.li_1:
                    Uri myurl1 = Uri.parse("https://www.bilibili.com/video/BV1254y1i7FA?from=search&seid=12787789987368896092");
                    intent.setData(myurl1);
                    startActivity(intent);
                    break;
                case R.id.li_2:
                    Uri myurl2 = Uri.parse("https://www.bilibili.com/video/BV1kz411B7jL?from=search&seid=12787789987368896092");
                    intent.setData(myurl2);
                    startActivity(intent);
                    break;
                case R.id.li_3:
                    Uri myurl3 = Uri.parse("https://www.bilibili.com/video/BV1A54y1S7mn?from=search&seid=12787789987368896092");
                    intent.setData(myurl3);
                    startActivity(intent);
                    break;
                case R.id.li_4:
                    Uri myurl4 = Uri.parse("https://www.bilibili.com/video/BV1dK4y1v7Co?from=search&seid=12787789987368896092");
                    intent.setData(myurl4);
                    startActivity(intent);
                    break;
                default:
                    break;
            }
        }
    }

}
