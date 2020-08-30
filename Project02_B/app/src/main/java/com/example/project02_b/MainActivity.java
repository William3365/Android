package com.example.project02_b;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RingView ringView = (RingView)findViewById(R.id.ringView);
//        //添加颜色
//        List<Integer> colorList = new ArrayList<>();
//        colorList.add(R.color.color_ff3e60);
//        colorList.add(R.color.color_ffa200);
//        colorList.add(R.color.color_31cc64);
//        colorList.add(R.color.color_3377ff);
//        colorList.add(R.color.color_77ff);
//
//        //添加百分比
//        List<Float> rateList = new ArrayList<>();
//        rateList.add(30f);
//        rateList.add(5f);
//        rateList.add(40f);
//        rateList.add(5f);
//        rateList.add(20f);
//        ringView.setShow(colorList,rateList,false,true);
        //添加颜色
        List<Integer> colorList = new ArrayList<>();
        colorList.add(R.color.Red);
        colorList.add(R.color.colorTransparent);
        colorList.add(R.color.Green);
        colorList.add(R.color.Blue);
        colorList.add(R.color.Yellow);

        //添加百分比
        List<Float> rateList = new ArrayList<>();
        rateList.add(11.3f);//仪器
        rateList.add(60f);
        rateList.add(4.3f);//电动
        rateList.add(15.4f);//手动
        rateList.add(9f);
        ringView.setShow(colorList,rateList,false,true);
    }
}
