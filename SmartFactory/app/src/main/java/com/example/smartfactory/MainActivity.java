package com.example.smartfactory;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.smartfactory.tools.CloudHelper;
import com.example.smartfactory.tools.SmartFactoryApplication;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    CloudHelper cloudHelper;
    SmartFactoryApplication smartFactory;
    private String gzvalue,sdvalude,wdvalue;
    private TextView tvgzvalue,tvwdvalue,tvsdvalue;
    private Spinner sptf,spkt,spzm;
    private int GET_CODE = 0;
    private Animation rotate;

    Handler handler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            switch (msg.what){
                case 1:
                    tvgzvalue.setText(gzvalue+"lx");
                    tvsdvalue.setText(sdvalude+"%RH");
                    tvwdvalue.setText(wdvalue+"°C");
                    break;
                default:
                    break;
            }
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();

//        rotate = AnimationUtils.loadAnimation(MainActivity.this,R.anim.rotate_anim);
        rotate = new RotateAnimation(
                0f,
                360f,
                Animation.RELATIVE_TO_SELF,
                0.5f,
                Animation.RELATIVE_TO_SELF,
                0.5f);
        LinearInterpolator lin = new LinearInterpolator();
        rotate.setInterpolator(lin);
        rotate.setDuration(1500);
        rotate.setRepeatCount(-1);
        rotate.setFillAfter(true);
        rotate.setStartOffset(10);

        Resources res = getResources();
        String[] ControlStatus = res.getStringArray(R.array.control);
        //创建适配器
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,ControlStatus);

        sptf = findViewById(R.id.sp_tf);
        //绑定适配器
        sptf.setAdapter(adapter);
        //监听器
        sptf.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Context c = getApplicationContext();
                String address = smartFactory.getEt_server_address();
                String projLabel = smartFactory.getEt_project_label();
                String controllerID = smartFactory.getEt_tfkz_id();
                String status = sptf.getItemAtPosition(position).toString();
                if (cloudHelper.getToken() != ""){
                    switch (status){
                        case "打开":
//                            cloudHelper.OnOff(c,address,projLabel,controllerID,1);
                            cloudHelper.onOff(c,address,projLabel,controllerID,1);
                            //设置图片动画
                            ((ImageView)findViewById(R.id.img_fs)).setAnimation(rotate);
                            ((ImageView)findViewById(R.id.img_fs)).startAnimation(rotate);//启动动画
//                            AnimationOn();
                            break;
                        case "关闭":
                            cloudHelper.onOff(c,address,projLabel,controllerID,0);
                            ((ImageView)findViewById(R.id.img_fs)).setAnimation(rotate);
                            ((ImageView)findViewById(R.id.img_fs)).clearAnimation();//清除动画
                            break;
                        case "自动":
                            if (Float.parseFloat(wdvalue)>smartFactory.getEt_wd_yz()){
                                cloudHelper.onOff(c,address,projLabel,controllerID,1);

                            }else {
                                cloudHelper.onOff(c,address,projLabel,controllerID,0);
                            }
                            break;
                        default:
                            break;
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        sptf.setSelection(1,true);

        spkt = findViewById(R.id.sp_kt);
        spkt.setAdapter(adapter);
        //监听器
        spkt.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Context c = getApplicationContext();
                String address = smartFactory.getEt_server_address();
                String projLabel = smartFactory.getEt_project_label();
                String controllerID = smartFactory.getEt_ktkz_id();
                String status = spkt.getItemAtPosition(position).toString();
                if (cloudHelper.getToken() != ""){
                    switch (status){
                        case "打开":
                            cloudHelper.onOff(c,address,projLabel,controllerID,1);
                            break;
                        case "关闭":
                            cloudHelper.onOff(c,address,projLabel,controllerID,0);
                            break;
                        case "自动":
                            if (Float.parseFloat(sdvalude)>smartFactory.getEt_sd_yz()){
                                cloudHelper.onOff(c,address,projLabel,controllerID,1);

                            }else {
                                cloudHelper.onOff(c,address,projLabel,controllerID,0);
                            }
                            break;
                        default:
                            break;
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spkt.setSelection(1,true);

        spzm = findViewById(R.id.sp_zm);
        spzm.setAdapter(adapter);
        //监听器
        spzm.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Context c = getApplicationContext();
                String address = smartFactory.getEt_server_address();
                String projLabel = smartFactory.getEt_project_label();
                String controllerID = smartFactory.getEt_gzkz_id();
                String status = spkt.getItemAtPosition(position).toString();
                if (cloudHelper.getToken() != ""){
                    switch (status){
                        case "打开":
                            cloudHelper.onOff(c,address,projLabel,controllerID,1);
                            break;
                        case "关闭":
                            cloudHelper.onOff(c,address,projLabel,controllerID,0);
                            break;
                        case "自动":
                            if (Float.parseFloat(gzvalue)>smartFactory.getEt_gz_yz()){
                                cloudHelper.onOff(c,address,projLabel,controllerID,1);

                            }else {
                                cloudHelper.onOff(c,address,projLabel,controllerID,0);
                            }
                            break;
                        default:
                            break;
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spzm.setSelection(1,true);

        loadCloudData();
    }

    private void initView() {
        tvgzvalue = (TextView)findViewById(R.id.tv_gz);
        tvsdvalue = (TextView)findViewById(R.id.tv_sd);
        tvwdvalue = (TextView)findViewById(R.id.tv_wd);
    }

    public void loadCloudData(){
        smartFactory = (SmartFactoryApplication)getApplication();
        cloudHelper = new CloudHelper();
        if (smartFactory!=null&& smartFactory.getEt_server_address()!=""&& smartFactory.getEt_project_label()!=""&& smartFactory.getEt_cloud_account_passwprd()!=""){
            cloudHelper.signIn(getApplicationContext(),smartFactory.getEt_server_address(),smartFactory.getEt_project_label(),smartFactory.getEt_cloud_account_passwprd());
        }
        //定时器
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
               if (cloudHelper.getToken()!=""){
                   cloudHelper.getSensorData(getApplicationContext(),
                           smartFactory.getEt_server_address(),
                           smartFactory.getEt_project_label(),
                           smartFactory.getEt_gz_id(),
                           new CloudHelper.DCallback() {
                               @Override
                               public void trans(String s) {
                                    gzvalue = s;
                                    Log.d("光照值",s);
                               }
                           });
                   cloudHelper.getSensorData(getApplicationContext(),
                           smartFactory.getEt_server_address(),
                           smartFactory.getEt_project_label(),
                           smartFactory.getEt_wd_id(),
                           new CloudHelper.DCallback() {
                               @Override
                               public void trans(String s) {
                                   wdvalue = s;
                                   Log.d("温度值",s);
                               }
                           });
                   cloudHelper.getSensorData(getApplicationContext(),
                           smartFactory.getEt_server_address(),
                           smartFactory.getEt_project_label(),
                           smartFactory.getEt_sd_id(),
                           new CloudHelper.DCallback() {
                               @Override
                               public void trans(String s) {
                                   sdvalude = s;
                                   Log.d("湿度值",s);
                               }
                           });
                   handler.sendEmptyMessage(1);
               }
            }
        },0, 5000);
    }

    /*
       onCreateOptionsMenu()方法，
            将菜 单中包含的菜单项添加到动作条。
            创建动作条的菜单时就会运行这个方法，
            它有 一个参数，表示动作条的一个 Menu 对象
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        //将动作项添加到动作条。menu 是一个 Menu 对象，表示动作条
        getMenuInflater().inflate(R.menu.menu_main,menu);
        //调用父类 onCreateOptionMenu()方法
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem){
        switch (menuItem.getItemId()){
            case R.id.action_setting:
                Intent intent = new Intent(MainActivity.this,SettingActivity.class);
//                TextView tv_wd = (TextView)findViewById(R.id.tv_wd);//----------此处易犯逻辑问题，找到当前文本控件，获取当前文本控件的值
//                TextView tv_sd = (TextView)findViewById(R.id.tv_sd);
//                TextView tv_gz = (TextView)findViewById(R.id.tv_gz);
//                //从id得到温度值文本
////                String wdz = tv_wd.getText().toString();
//                float wdz = Float.parseFloat(tv_wd.getText().toString().trim());
//                float sdz = Float.parseFloat(tv_sd.getText().toString().trim());
//                float gzz = Float.parseFloat(tv_gz.getText().toString().trim());
                /*
                   putExtra()方法，为意图增加多个额外的数据，可重复使用，但一定要确保为每个数据 指定一个唯一的名字
                 */
                intent.putExtra("温度值：",wdvalue);//发送包含String值的意图，并将这个意图命名为“温度值”
                intent.putExtra("湿度值：",sdvalude);
                intent.putExtra("光照值：",gzvalue);

                Bundle bundle = new Bundle();
                bundle.putString("uid",MainActivity.this.toString());
                intent.putExtras(bundle);
                startActivityForResult(intent,GET_CODE);

//                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(menuItem);
        }
    }

    @Override
    protected void onActivityResult(int requestCode,int resultCode,Intent data) {
        if (requestCode == GET_CODE) {
            if (resultCode == RESULT_OK) {
                loadCloudData();
            }
        }
    }
}
