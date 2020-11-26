package com.example.testtzl;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    private final static int NOTIFY_ID = 100;//通知id，尽量设置为全局唯一，不然在其他地方发起的通知将会覆盖这边通知栏

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        showNotification();
    }

    private void showNotification() {
        //获取一个通知管理器
        NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        //创建启动触发项
        Intent hangIntent = new Intent(MainActivity.this, DetailActivity.class);
        //点击查看时候触发
        PendingIntent pi = PendingIntent.getActivity(this, 1001, hangIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        String CHANNEL_ID = "your_custom_id";//应用频道Id唯一值， 长度若太长可能会被截断，
        String CHANNEL_NAME = "your_custom_name";//最长40个字符，太长会被截断
        //创建一个通知对象
        Notification notification = new NotificationCompat.Builder(MainActivity.this, CHANNEL_ID)
                //设置通知属性
                .setContentTitle("这是一个猫头")//标题
                .setContentText("点我返回应用")//内容
                .setSmallIcon(R.mipmap.ic_launcher)//设置通知显示小图标
                .setContentIntent(pi)//设置通知栏点击跳转
                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.icon))//设置通知显示图标
                .setAutoCancel(true)//点击后消失
                .build();

        //Android 8.0 以上需包添加渠道
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel notificationChannel = new NotificationChannel(CHANNEL_ID, CHANNEL_NAME, NotificationManager.IMPORTANCE_LOW);
            manager.createNotificationChannel(notificationChannel);
        }
        manager.notify(NOTIFY_ID, notification);
    }
}
