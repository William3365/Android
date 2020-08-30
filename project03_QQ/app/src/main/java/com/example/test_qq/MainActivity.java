package com.example.test_qq;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {

    //    首先导入依赖 implementation 'com.google.android.material:material:1.0.0-rc01'

    private ViewPager viewPager;
    private TabLayout tabLayout;
    //设置界面文件和文字一一对应
    private Fragment[] Lfragments = {new Fragment(), new Fragment(), new Fragment(), new Fragment()};
    private String[] Ltitles = {"界面1", "界面2", "界面3","界面4"};
    //未选中图片
    private int[] Limg = {R.mipmap.yv1,R.mipmap.yv2,R.mipmap.yv3,R.mipmap.yv4};
    //选中图片
    private int[]  Limgn = {R.mipmap.yue1,R.mipmap.yue2,R.mipmap.yue3,R.mipmap.yue4};
    //配置默认选中第几项
    private int ItemWhat=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //找控件
        tabLayout = (TabLayout) findViewById(R.id.tabs2);
        viewPager = (ViewPager) findViewById(R.id.viewpager);

        //添加tablayout中的竖线,每一项的中间分隔线
        //LinearLayout linearLayout = (LinearLayout) tabLayout.getChildAt(0);
        // linearLayout.setShowDividers(LinearLayout.SHOW_DIVIDER_MIDDLE);
        // linearLayout.setDividerDrawable(ContextCompat.getDrawable(this, R.mipmap.fg));

        //只进入一次，初始化
        viewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return Lfragments[position];
            }
            @Override
            public int getCount() {
                return Lfragments.length;
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return Ltitles[position];
            }
        });

        //绑定
        tabLayout.setupWithViewPager(viewPager);

        //设置默认选中页，宏定义
        tabLayout.getTabAt(ItemWhat).select();
        viewPager.setOffscreenPageLimit(3); //设置向左和向右都缓存的页面个数
        //初始化菜单栏显示
        for (int i = 0; i < tabLayout.getTabCount(); i++) {
            //寻找到控件
            View view = LayoutInflater.from(MainActivity.this).inflate(R.layout.items, null);
            LinearLayout mTabView = (LinearLayout) view.findViewById(R.id.item_view);
            TextView mTabText = (TextView) view.findViewById(R.id.item_text);
            ImageView mTabIcon = (ImageView) view.findViewById(R.id.item_img);

            mTabText.setText(Ltitles[i]);
            mTabIcon.setImageResource(Limg[i]);
            //设置不可点击
            // mTabView.setClickable(true);

            //更改选中项样式
            if(i==ItemWhat){
                mTabIcon.setImageResource(Limgn[i]);
                mTabText.setTextColor(ContextCompat.getColor(this, R.color.Red));
            }

            //设置样式
            tabLayout.getTabAt(i).setCustomView(view);
        }
        //是否选中监听
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                //选中时进入，改变样式
                ItemSelect(tab);
                //onTabselected方法里面调用了viewPager的setCurrentItem
                // 所以要想自定义OnTabSelectedListener，
                // 也加上mViewPager.setCurrentItem(tab.getPosition())就可以了
                viewPager.setCurrentItem(tab.getPosition());
            }
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                //未选中进入，改变样式
                ItemNoSelect(tab);
            }
            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                //重新选中

            }
        });}
    //某个项选中，改变其样式
    private void ItemSelect(TabLayout.Tab tab) {
        View customView = tab.getCustomView();
        TextView tabText = (TextView) customView.findViewById(R.id.item_text);
        ImageView tabIcon = (ImageView) customView.findViewById(R.id.item_img);
        tabText.setTextColor(ContextCompat.getColor(this, R.color.Red));
        String stitle = tabText.getText().toString();
        for(int i=0;i<Ltitles.length;i++){
            if(Ltitles[i].equals(stitle)){
                //Toast.makeText(MainActivity.this,"xxx+"+i,Toast.LENGTH_SHORT).show();
                tabIcon.setImageResource(Limgn[i]);
            }
        }
    }
    //某个项非选中，改变其样式
    private void ItemNoSelect(TabLayout.Tab tab) {
        View customView = tab.getCustomView();
        TextView tabText = (TextView) customView.findViewById(R.id.item_text);
        ImageView tabIcon = (ImageView) customView.findViewById(R.id.item_img);
        tabText.setTextColor(ContextCompat.getColor(this, R.color.Blue));
        String stitle = tabText.getText().toString();
        for(int i=0;i<Ltitles.length;i++){
            if(Ltitles[i].equals(stitle)){
                tabIcon.setImageResource(Limg[i]);
            }
        }
    }

}