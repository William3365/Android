package com.example.test_qq2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.example.test_qq2.tools.MyFragmentPagerAdapter;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    TabLayout tabLayout;
    ViewPager viewPager;
    List<String> titles;
    List<Fragment> fragments;
    Fragment fragment0,fragment1,fragment2;

//    首先导入依赖 implementation 'com.google.android.material:material:1.0.0-rc01'

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tabLayout = (TabLayout)findViewById(R.id.mytab);
        viewPager = (ViewPager)findViewById(R.id.myViewPager);

        titles = new ArrayList<>();
        titles.add("测试0");
        titles.add("测试1");
        titles.add("测试2");

        fragments = new ArrayList<>();

        fragment0 = new Fragment0();
        fragment1 = new Fragment1();
        fragment2 = new Fragment2();

        fragments.add(fragment0);
        fragments.add(fragment1);
        fragments.add(fragment2);

        MyFragmentPagerAdapter myFragmentPagerAdapter = new MyFragmentPagerAdapter(getSupportFragmentManager(),fragments,titles);
        viewPager.setAdapter(myFragmentPagerAdapter);
        //绑定
        tabLayout.setupWithViewPager(viewPager);
    }
}
