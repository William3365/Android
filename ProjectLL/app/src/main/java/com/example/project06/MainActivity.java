package com.example.project06;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.project06.tools.MyFragmentPagerAdapter;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    TabLayout tabLayout;
    ViewPager viewPager;
    List<String> titles;
    List<Fragment> fragments;
    Fragment fragment0,fragment1;

    private final static int NOTIFI_ID = 100;

    //首先导入依赖 implementation 'com.google.android.material:material:1.0.0-rc01'

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tabLayout = (TabLayout)findViewById(R.id.mytab);
        viewPager = (ViewPager)findViewById(R.id.myViewPager);

        titles = new ArrayList<>();
        titles.add("环境信息");
        titles.add("设备操作");

        fragments = new ArrayList<>();

        fragment0 = new Fragment0();
        fragment1 = new Fragment1();

        fragments.add(fragment0);
        fragments.add(fragment1);

        MyFragmentPagerAdapter myFragmentPagerAdapter = new MyFragmentPagerAdapter(getSupportFragmentManager(),fragments,titles);
        viewPager.setAdapter(myFragmentPagerAdapter);
        //绑定
        tabLayout.setupWithViewPager(viewPager);

        NotificationManager notificationManager = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
        Intent intent = new Intent(this,MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this,1001,intent,PendingIntent.FLAG_CANCEL_CURRENT);

        String CHANNEL_ID = "your_custom_id";
        String CHANNEL_NAME = "your_custom_name";
        //创建一个通知对象
        Notification notification = new NotificationCompat.Builder(MainActivity.this, CHANNEL_ID)
                //设置通知属性
                .setContentTitle("警报通知")
                .setContentText("水浊度和氯气值超标，请尽快解决！")
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentIntent(pendingIntent)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.liangcang))
                .setAutoCancel(true)
                .build();

        //Android 8.0 以上需包添加渠道
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel notificationChannel = new NotificationChannel(CHANNEL_ID, CHANNEL_NAME, NotificationManager.IMPORTANCE_LOW);
            notificationManager.createNotificationChannel(notificationChannel);
        }
        notificationManager.notify(NOTIFI_ID, notification);
    }
}
