package com.huicheng;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.Toast;

import com.huicheng.toolkit.Icon;

import com.huicheng.toolkit.Icon;
import com.huicheng.toolkit.MyAdapter;

import java.util.ArrayList;

public class More extends Activity {

    private Context mContext;
    private GridView grid_photo;
    private BaseAdapter mAdapter = null;
    private ArrayList<Icon> mData = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more);

        //绑定控件
        mContext = More.this;
        grid_photo = (GridView)findViewById(R.id.grid_photo);

        //添加数据
        mData = new ArrayList<Icon>();
        mData.add(new Icon(R.mipmap.ye,"夜间模式"));
        mData.add(new Icon(R.mipmap.yv,"雨天模式"));
        mData.add(new Icon(R.mipmap.xue,"雪天模式"));
        mData.add(new Icon(R.mipmap.wu,"雾天模式"));
        mData.add(new Icon(R.mipmap.jie,"节日模式"));
        mData.add(new Icon(R.mipmap.yue,"约会模式"));
        mData.add(new Icon(R.mipmap.jv,"聚餐模式"));
        mData.add(new Icon(R.mipmap.xuexi,"学习模式"));
        mData.add(new Icon(R.mipmap.jianshen,"健身模式"));
        mData.add(new Icon(R.mipmap.yvle,"娱乐模式"));
        mData.add(new Icon(R.mipmap.more,"更多模式开发中"));

        //绑定适配器
        mAdapter = new MyAdapter<Icon>(mData, R.layout.item_grid_icon) {
            @Override
            public void bindView(ViewHolder holder, Icon obj) {
                holder.setImageResource(R.id.img_icon, obj.getiId());
                holder.setText(R.id.txt_icon, obj.getiName());
            }
        };
        grid_photo.setAdapter(mAdapter);
        //监听器
        grid_photo.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(mContext,"功能正在开发中",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
