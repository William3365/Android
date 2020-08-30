package cn.edu.jsit.smartfactory;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

import cn.edu.jsit.smartfactory.tools.CloudHelper;
import cn.edu.jsit.smartfactory.tools.SmartFactoryApplication;

public class MainActivity extends Activity {
    private TextView tvlightValue,tvTemValue,tvHumValue;
    CloudHelper cloudHelper;
    SmartFactoryApplication smartFactory;
    private String lightValue,tempValue,humValue;
    private Spinner spVentilation,spAc,spLight;
    private int GET_CODE = 0;
    private Animation rotate;
    Handler handler= new Handler(){
        @Override
        public void handleMessage(Message msg){
            switch (msg.what){
                case 1:
                    tvlightValue.setText(lightValue+"lx");
                    tvTemValue.setText(tempValue+"℃");
                    tvHumValue.setText(humValue+"%RH");
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
        tvlightValue = findViewById(R.id.tv_light_value);
        tvTemValue = findViewById(R.id.tv_temp_value);
        tvHumValue = findViewById(R.id.tv_humility_value);

        rotate = AnimationUtils.loadAnimation(MainActivity.this,R.anim.notate_anim);

        Resources res = getResources();
        String[] controlStatus = res.getStringArray(R.array.control_status);

        spVentilation = findViewById(R.id.sp_ventilation_control);
        final ArrayAdapter<String> adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,
                controlStatus);
        spVentilation = findViewById(R.id.sp_ventilation_control);
        spVentilation.setAdapter(adapter);
        spVentilation.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Context c = getApplicationContext();
                String address = smartFactory.getServerAddress();
                String projLabel = smartFactory.getProjectLabel();
                String controllerId = smartFactory.getVentilationControllerId();
                String status = spVentilation.getItemAtPosition(position).toString();
                if(cloudHelper.getToken()!=""){
                    switch (status){
                        case"打开":
                            cloudHelper.onOff(c,address,projLabel,controllerId,1);
                            ((ImageView)findViewById(R.id.img_fan)).setAnimation(rotate);
                            ((ImageView)findViewById(R.id.img_fan)).startAnimation(rotate);

                            break;
                        case"关闭":
                            cloudHelper.onOff(c,address,projLabel,controllerId,0);
                            ((ImageView)findViewById(R.id.img_fan)).setAnimation(rotate);
                            ((ImageView)findViewById(R.id.img_fan)).clearAnimation();

                            break;
                        case"自动":
                            if (Float.parseFloat(tempValue)>smartFactory.getTempThresholdValue()){
                                cloudHelper.onOff(c,address,projLabel,controllerId,1);

                        }
                        else {
                                cloudHelper.onOff(c,address,projLabel,controllerId,0);

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
        spVentilation.setSelection(1,true);
        spAc = findViewById(R.id.sp_air_control);
        spAc.setAdapter(adapter);
        spAc.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Context c = getApplicationContext();
                String address = smartFactory.getServerAddress();
                String projLabel = smartFactory.getProjectLabel();
                String controllerId = smartFactory.getAirControllerId();
                String status = spAc.getItemAtPosition(position).toString();
                ImageView imageView  = findViewById(R.id.img_ac);
                imageView.setImageResource(R.drawable.frame_anim);
                AnimationDrawable ad = (AnimationDrawable)imageView.getDrawable();
                if(cloudHelper.getToken()!=""){
                    switch (status){
                        case"打开":
                            cloudHelper.onOff(c,address,projLabel,controllerId,1);
                            ad.start();
                            break;
                        case"关闭":
                            cloudHelper.onOff(c,address,projLabel,controllerId,0);
                            ad.stop();
                            imageView.setImageResource(R.drawable.ac1);
                            break;
                        case"自动":
                            if (Float.parseFloat(humValue)>smartFactory.getHumThresholdValue()){
                                cloudHelper.onOff(c,address,projLabel,controllerId,1);
                                ad.start();
                            }
                            else {
                                cloudHelper.onOff(c,address,projLabel,controllerId,0);
                                ad.stop();
                                imageView.setImageResource(R.drawable.ac1);
                            }
                            break;
                        default:
                            ad.stop();
                            imageView.setImageResource(R.drawable.ac1);
                            break;
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spAc.setSelection(1,true);

        final ImageView  imgLight = findViewById(R.id.img_light);
        final ObjectAnimator oa = ObjectAnimator.ofFloat(imgLight,"alpha",1f,0f,1f).setDuration(2000);
        oa.addListener(new AnimatorListenerAdapter() {
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
                String projLabel = smartFactory.getProjectLabel();
                String controllerId = smartFactory.getLightControllerId();
                String status = spLight.getItemAtPosition(position).toString();
                if(cloudHelper.getToken()!=""){
                    switch (status){
                        case"打开":
                            cloudHelper.onOff(c,address,projLabel,controllerId,1);
                            oa.start();
                            break;
                        case"关闭":
                            cloudHelper.onOff(c,address,projLabel,controllerId,0);
                            imgLight.setImageResource(R.drawable.light_off);
                            break;
                        case"自动":
                            if (Float.parseFloat(lightValue)>smartFactory.getLightThresholdValue()){
                                cloudHelper.onOff(c,address,projLabel,controllerId,1);
                                oa.start();
                            }
                            else {
                                cloudHelper.onOff(c,address,projLabel,controllerId,0);
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
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spLight.setSelection(1,true);


        loadCloudData();

    }
    public  void  loadCloudData(){
        smartFactory = (SmartFactoryApplication)getApplication();
        cloudHelper = new CloudHelper();
        if(smartFactory!=null&&
                smartFactory.getServerAddress()!=""&&
                smartFactory.getCloudAccount()!=""&&
                smartFactory.getCloudAccountPassword()!=""){
            cloudHelper.signIn(getApplicationContext(),
                    smartFactory.getServerAddress(),
                    smartFactory.getCloudAccount(),
                    smartFactory.getCloudAccountPassword());
        }
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                if (cloudHelper.getToken() != "") {
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
                                    Log.d("temValue", s);
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
                                    Log.d("humValue", s);
                                }
                            });
                    handler.sendEmptyMessage(1);
                }
            }
        },0,5000);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem){
        switch (menuItem.getItemId()){
            case R.id.action_setting:
                Intent intent = new  Intent(MainActivity.this,SettingActivity.class);

                intent.putExtra("tempValue",tempValue);
                intent.putExtra("humValue",humValue);
                intent.putExtra("lightValue",lightValue);
                Bundle bundle = new Bundle();
                bundle.putString("uid",MainActivity.this.toString());
                intent.putExtras(bundle);
                startActivityForResult(intent,GET_CODE);

                return true;
                default:
                    return super.onOptionsItemSelected(menuItem);
        }
    }
    @Override
    protected  void onActivityResult(int requestCode,int resultCode,Intent data){
        if (requestCode==GET_CODE){
            if (resultCode==RESULT_OK){
                loadCloudData();
            }
        }
    }


}
