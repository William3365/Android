package com.example.test002;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
//测试测试
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RingView ringView = (RingView)findViewById(R.id.ringView);
        //添加颜色
        List<Integer> colorList = new ArrayList<>();
        colorList.add(R.color.Red);
        colorList.add(R.color.colorTransparent);
        colorList.add(R.color.Green);
        colorList.add(R.color.Blue);
        colorList.add(R.color.Yellow);

        //添加百分比
        List<Float> rateList = new ArrayList<>();
        rateList.add(72.5f);//管线
        rateList.add(11.8f);//仪器
        rateList.add(16.2f);//手动
        rateList.add(46f);//电动
        rateList.add(5f);//其他
        ringView.setShow(colorList,rateList,false,true);
    }
}
