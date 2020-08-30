package com.example.project05;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.project05.tools.MyFragmentPagerAdapter;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    TabLayout tabLayout;
    ViewPager viewPager;
    List<String> titles;
    List<Fragment> fragments;
    Fragment fragment0,fragment1,fragment2;

    //首先导入依赖 implementation 'com.google.android.material:material:1.0.0-rc01'

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tabLayout = (TabLayout)findViewById(R.id.mytab);
        viewPager = (ViewPager)findViewById(R.id.myViewPager);

        titles = new ArrayList<>();
        titles.add("我的插座");
        titles.add("电量统计");
        titles.add("账户信息");

        fragments = new ArrayList<>();

        fragment0 = new Fragment0();
        fragment1 = new Fragment1();
        fragment2 = new Fragment2();

        fragments.add(fragment0);
        fragments.add(fragment1);
        fragments.add(fragment2);
        //通过适配器绑定
        MyFragmentPagerAdapter myFragmentPagerAdapter = new MyFragmentPagerAdapter(getSupportFragmentManager(),fragments,titles);
        viewPager.setAdapter(myFragmentPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
    }
}
