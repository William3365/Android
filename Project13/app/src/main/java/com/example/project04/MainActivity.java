package com.example.project04;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.project04.tools.MyFragmentPagerAdapter;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    TabLayout tabLayout;
    ViewPager viewPager;
    List<String> titles;
    List<Fragment> fragments;
    Fragment fragment0,fragment1;

    //首先导入依赖 implementation 'com.google.android.material:material:1.0.0-rc01'

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tabLayout = (TabLayout)findViewById(R.id.mytab);
        viewPager = (ViewPager)findViewById(R.id.myViewPager);

        titles = new ArrayList<>();
        titles.add("空气质量");
        titles.add("系统控制");

        fragments = new ArrayList<>();

        fragment0 = new Fragment0();
        fragment1 = new Fragment1();

        fragments.add(fragment0);
        fragments.add(fragment1);

        MyFragmentPagerAdapter myFragmentPagerAdapter = new MyFragmentPagerAdapter(getSupportFragmentManager(),fragments,titles);
        viewPager.setAdapter(myFragmentPagerAdapter);
        //绑定
        tabLayout.setupWithViewPager(viewPager);
    }
}
