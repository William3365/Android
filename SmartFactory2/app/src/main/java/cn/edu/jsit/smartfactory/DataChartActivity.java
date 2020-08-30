package cn.edu.jsit.smartfactory;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nullable;

import cn.edu.jsit.smartfactory.tools.DatabaseHelper;

//用于显示chart
public class DataChartActivity extends Activity {

    DatabaseHelper databaseHelper;
    private LineChart lineChart;
    protected Typeface typeface;
    private ArrayList<Entry> sensorData = new ArrayList<>();
    private  String dataType;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chart);//设置资源

        Intent intent = getIntent();//接收
        dataType = intent.getStringExtra("type");
        //创建数据库对象
        databaseHelper = new DatabaseHelper(this);
        lineChart = findViewById(R.id.lineChart);//绑定id
        //创建对象
        Description description = new Description();
        description.setText("折线统计图");

        lineChart.setDescription(description);//描述信息
        lineChart.setDrawGridBackground(false);//取消背景色
        lineChart.setBackgroundColor(Color.WHITE);//设置背景色
        lineChart.setData(getLineData());//绑定数据
        lineChart.setScaleEnabled(false);//x轴y轴禁止缩放
//设置样式
        Legend legend = lineChart.getLegend();
        legend.setForm(Legend.LegendForm.LINE);//设置形状
        legend.setTypeface(typeface);//设置字体
        legend.setTextSize(11f);//字体大小
        legend.setTextColor(Color.BLACK);//字体颜色
        legend.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);//图例的方向
        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.LEFT);//居左
        legend.setOrientation(Legend.LegendOrientation.HORIZONTAL);//水平
        legend.setDrawInside(false);//区域
        lineChart.animateX(3000);//显示动画
    }

    private LineData getLineData(){
        getChartData(20);

        LineDataSet dataSet = new LineDataSet(sensorData,dataType);
        dataSet.setColor(Color.BLUE);//颜色
        dataSet.setFillColor(ColorTemplate.getHoloBlue());//填充色
        dataSet.setHighLightColor(Color.rgb(244,117,117));//通过三原色设置
        dataSet.setDrawCircleHole(true);//图标圆孔

        LineData data = new LineData(dataSet);
        data.setValueTextColor(Color.BLACK);//数据颜色
        data.setValueTextSize(9f);//字体大小
        return data;
    }

    private void getChartData(int count){
        List<Float> lists;
        lists = databaseHelper.search(DataChartActivity.this,dataType);
        if(count> lists.size()){
            for(int i=0;i<lists.size();i++){
                Entry tempEntry = new Entry(i,lists.get(1));
                sensorData.add(tempEntry);
            }
        }else{
            for(int i=0;i<count;i++){
                Entry tempEntry = new Entry(i,lists.get(1));
                sensorData.add(tempEntry);
            }
        }
    }
}
