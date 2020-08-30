package com.example.project03;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.project03.tools.MyFragmentPagerAdapter;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private List<String> titles;
    private List<Fragment> fragments;
    private Fragment fragment0,fragment1,fragment2;
    private TextView textView;


//    首先导入依赖 implementation 'com.google.android.material:material:1.0.0-rc01'

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    private void initView() {
        tabLayout = (TabLayout)findViewById(R.id.mytab);
        viewPager = (ViewPager)findViewById(R.id.myViewPager);


        titles = new ArrayList<>();
        titles.add("书籍检索");
        titles.add("消息");
        titles.add("账号信息");

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
