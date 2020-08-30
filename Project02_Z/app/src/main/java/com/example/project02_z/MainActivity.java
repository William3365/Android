package com.example.project02_z;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // 找到控件
        BarChart chart = (BarChart)findViewById(R.id.mychart);
        //不显示描述
        chart.getDescription().setEnabled(false);
        //Y轴右边关闭
        chart.getAxisRight().setEnabled(false);
        //x轴设置无网格
        XAxis xa = chart.getXAxis();
        xa.setPosition(XAxis.XAxisPosition.BOTTOM);
        xa.setDrawAxisLine(false);
//        xa.setAxisMaximum(6f);
        //在放大到为轴设置的小数位数不再允许在两个轴值之间进行区分的点时，可以使用此方法来避免值重复
        xa.setGranularity(1f);

        //创建两个数据集
        List<BarEntry> valsComp1 = new ArrayList<BarEntry>();//数据集1


//        valsComp1.add(new BarEntry(2,200));
        valsComp1.add(new BarEntry(1,2));
        valsComp1.add(new BarEntry(2,2));
        valsComp1.add(new BarEntry(3,3));
        valsComp1.add(new BarEntry(4,4));
        valsComp1.add(new BarEntry(5,5));
        valsComp1.add(new BarEntry(6,6));
        valsComp1.add(new BarEntry(7,7));

        //创建条形图例对象
        BarDataSet setComp1 = new BarDataSet(valsComp1,"追风浪子");
        setComp1.setColor(Color.BLUE);
        //显示数据
        BarData data = new BarData(setComp1);
        //设置数据
        chart.setData(data);
        //刷新
        chart.invalidate();
        //设置动画样式
        chart.animateY(2000, Easing.EaseInOutQuad);
    }

}
