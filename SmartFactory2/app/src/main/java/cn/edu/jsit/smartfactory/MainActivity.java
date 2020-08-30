package cn.edu.jsit.smartfactory;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.AnimationDrawable;
import android.media.Image;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

import cn.edu.jsit.smartfactory.tools.CloudHelper;
import cn.edu.jsit.smartfactory.tools.DatabaseHelper;
import cn.edu.jsit.smartfactory.tools.SmartFactoryApplication;

public class MainActivity extends Activity implements View.OnClickListener{
    private TextView tvLightValue,tvTempValue,tvHumValue;
    CloudHelper cloudHelper;
    SmartFactoryApplication smartFactory;
    DatabaseHelper databaseHelper;//声明
    private String lightValue,tempValue,humValue;
    private Spinner spVentilation,spAc,spLight;
    private  int GET_CODE = 0;
    private Animation rotate;

     final Handler handler = new Handler(){
        public void handleMessage(android.os.Message msg){
            switch(msg.what){
                case 1:
                    tvLightValue.setText(lightValue + "lx");
                    tvTempValue.setText(tempValue + "°C");
                    tvHumValue.setText(humValue + "%RH");
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

        tvLightValue = findViewById(R.id.tv_light_value);
        tvLightValue.setOnClickListener(this);
        tvTempValue = findViewById(R.id.tv_temp_value);
        tvTempValue.setOnClickListener(this);
        tvHumValue = findViewById(R.id.tv_humility_value);
        tvHumValue.setOnClickListener(this);

        databaseHelper = new DatabaseHelper(this);//创建数据库对象

       // rotate = AnimationUtils.loadAnimation(MainActivity.this,R.anim.rotate_anim);
        rotate = new RotateAnimation(
                0f,
                360f,
                Animation.RELATIVE_TO_SELF,
                0.5f,
                Animation.RELATIVE_TO_SELF,
                0.5f
        );
        LinearInterpolator lin = new LinearInterpolator();
        rotate.setInterpolator(lin);
        rotate.setDuration(1500);
        rotate.setRepeatCount(-1);
        rotate.setFillAfter(true);
        rotate.setStartOffset(10);


        Resources res = getResources();
        String[] controlStatus = res.getStringArray(R.array.control_status);

        spVentilation = findViewById(R.id.sp_ventilation_control);
        //数组适配器的初始化
        final ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,android.R.layout.simple_list_item_1,
                controlStatus
        );
        spVentilation = findViewById(R.id.sp_ventilation_control);
        spVentilation.setAdapter(adapter);
        spVentilation.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Context c = getApplicationContext();
        String address = smartFactory.getServerAddress();
        String projectLabel = smartFactory.getProjectLabel();
        String controllerId = smartFactory.getVentilationControllerId();
        String status = spVentilation.getItemAtPosition(position).toString();
        if (cloudHelper.getToken()!=""){
        switch(status){
            case "打开":
            cloudHelper.onOff(c,address,projectLabel,controllerId,1);
            ((ImageView)findViewById(R.id.img_fan)).setAnimation(rotate);
            ((ImageView)findViewById(R.id.img_fan)).startAnimation(rotate);
            break;
        case "关闭":
            cloudHelper.onOff(c,address,projectLabel,controllerId,0);
            ((ImageView)findViewById(R.id.img_fan)).setAnimation(rotate);
            ((ImageView)findViewById(R.id.img_fan)).clearAnimation();
        case "自动":
            if (Float.parseFloat(tempValue)>smartFactory.getTempThresholdValue()){
                cloudHelper.onOff(c,address,projectLabel,controllerId,1);
            }
            else {
            cloudHelper.onOff(c,address,projectLabel,controllerId,0);
            }
            break;
        default:
            break;
             }
            }
        }
        @Override
        public void onNothingSelected(AdapterView<?> parent){

            }
        });
        spVentilation.setSelection(1,true);

        spAc = findViewById(R.id.sp_air_control);
        spAc.setAdapter(adapter);
        spAc.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Context c = getApplicationContext();
                String address = smartFactory.getServerAddress();
                String projectLabel = smartFactory.getProjectLabel();
                String controllerId = smartFactory.getAirControllerId();
                String status = spAc.getItemAtPosition(position).toString();

                ImageView imageView = findViewById(R.id.img_ac);
                imageView.setImageResource(R.drawable.frame_anim);//找到动画资源文件
                AnimationDrawable ad = (AnimationDrawable)imageView.getDrawable();//创建动画对象

                if (cloudHelper.getToken()!=""){
                    switch(status){
                        case "打开":
                            cloudHelper.onOff(c,address,projectLabel,controllerId,1);
                            ad.start();//打开动画
                            break;
                        case "关闭":
                            cloudHelper.onOff(c,address,projectLabel,controllerId,0);
                            ad.stop();
                            imageView.setImageResource(R.drawable.ac1);//动画停止画面，再ac1
                        case "自动":
                            if (Float.parseFloat(humValue)>smartFactory.getHumThresholdValue()){
                                cloudHelper.onOff(c,address,projectLabel,controllerId,1);
                                ad.start();
                            }
                            else {
                                cloudHelper.onOff(c,address,projectLabel,controllerId,0);
                                ad.stop();
                                imageView.setImageResource(R.drawable.ac1);
                            }
                            break;
                        default:
                            ad.stop();//默认情况是关闭的
                            imageView.setImageResource(R.drawable.ac1);
                            break;
                    }
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent){

            }
        });
        spAc.setSelection(1,true);

        final ImageView imgLight = findViewById(R.id.img_light);
        //参数：资源、显示效果，渐变透明度，持续时间
//        final ObjectAnimator oa = ObjectAnimator.ofFloat(imgLight,"alpha",1f,0f,1f).setDuration(2000);
            //监听器
//        oa.addListener(new AnimatorListenerAdapter() {
//            @Override
//            public void onAnimationEnd(Animator animation) {
//                super.onAnimationEnd(animation);
                    //结束时显示的图片
//                imgLight.setImageResource(R.drawable.light_on);
//            }
//        });

        final Animator animator = AnimatorInflater.loadAnimator(this,R.animator.anim);//创建对象，并设置资源
        animator.setTarget(imgLight);
        animator.start();
        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                imgLight.setImageResource(R.drawable.light_on);
            }
        });
        spLight = findViewById(R.id.sp_light_control);
        spLight.setAdapter(adapter);
        spLight.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Context c = getApplicationContext();
                String address = smartFactory.getServerAddress();
                String projectLabel = smartFactory.getProjectLabel();
                String controllerId = smartFactory.getLightControllerId();
                String status = spLight.getItemAtPosition(position).toString();
                if (cloudHelper.getToken()!=""){
                    switch(status){
                        case "打开":
                            cloudHelper.onOff(c,address,projectLabel,controllerId,1);
//                            oa.start();
                            animator.start();
                            break;
                        case "关闭":
                            cloudHelper.onOff(c,address,projectLabel,controllerId,0);
                            imgLight.setImageResource(R.drawable.light_off);
                        case "自动":
                            if (Float.parseFloat(lightValue)>smartFactory.getLightThresholdValue()){
                                cloudHelper.onOff(c,address,projectLabel,controllerId,1);
                                animator.start();
                            }
                            else {
                                cloudHelper.onOff(c,address,projectLabel,controllerId,0);
                                imgLight.setImageResource(R.drawable.light_off);
                            }
                            break;
                        default:
                            imgLight.setImageResource(R.drawable.light_off);
                            break;
                    }
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent){

            }
        });
        spLight.setSelection(1,true);

        loadCloudData();
    }

    @Override
    public void onClick(View view){
        Intent intent = new Intent(MainActivity.this,DataChartActivity.class);
        switch(view.getId()){
            case R.id.tv_temp_value:
                intent.putExtra("type","温度");
                startActivity(intent);
                break;
            case R.id.tv_humility_value:
                intent.putExtra("type","湿度");
                startActivity(intent);
                break;
            case R.id.tv_light_value:
                intent.putExtra("type","光照");
                startActivity(intent);
                break;
        }
    }

    public void loadCloudData(){
        smartFactory = (SmartFactoryApplication) getApplication();
        cloudHelper = new CloudHelper();
        if(smartFactory!=null && smartFactory.getServerAddress()!="" && smartFactory.getCloudAccount()!="" && smartFactory.getCloudAccountPassword()!=""){
            cloudHelper.signIn(getApplicationContext(), smartFactory.getServerAddress(),smartFactory.getCloudAccount(), smartFactory.getCloudAccountPassword());
        }

        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                if (cloudHelper.getToken()!="") {
                    cloudHelper.getSensorData(getApplicationContext(),
                            smartFactory.getServerAddress(),
                            smartFactory.getProjectLabel(),
                            smartFactory.getLightSensorId(),
                            new CloudHelper.DCallback() {
                                @Override
                                public void trans(String s) {
                                    lightValue = s;
                                    Log.d("lightValue", s);
                                }
                            });
                    cloudHelper.getSensorData(getApplicationContext(),
                            smartFactory.getServerAddress(),
                            smartFactory.getProjectLabel(),
                            smartFactory.getTempSensorId(),
                            new CloudHelper.DCallback() {
                                @Override
                                public void trans(String s) {
                                    tempValue = s;
                                    Log.d("tempValue", s);
                                }
                            });
                    cloudHelper.getSensorData(getApplicationContext(),
                            smartFactory.getServerAddress(),
                            smartFactory.getProjectLabel(),
                            smartFactory.getHumSensorId(),
                            new CloudHelper.DCallback() {
                                @Override
                                public void trans(String s) {
                                    humValue = s;
                                    Log.d("humValue",s);
                                }
                            });
                    if(!((tempValue == null)&&(humValue == null)&&(lightValue == null)))
                    databaseHelper.insert(MainActivity.this,tempValue,humValue,lightValue);//插入数据
                    handler.sendEmptyMessage(1);
                }
                }
            },0,5000);

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
                 /*
                   putExtra()方法，为意图增加多个额外的数据，可重复使用，但一定要确保为每个数据 指定一个唯一的名字
                 */
                intent.putExtra("tempValue",tempValue);//发送包含String值的意图，并将这个意图命名为“温度值”
                intent.putExtra("humValue",humValue);
                intent.putExtra("lightValue",lightValue);
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
    protected void onActivityResult(int requestCode,int resultCode,Intent data){
        if(requestCode==GET_CODE){
            if (resultCode==RESULT_OK){
                loadCloudData();
            }
        }
    }
}




