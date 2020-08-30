package cn.edu.jsit.smartfactory;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
//import java.util.Timer;
//import java.util.TimerTask;
//import cn.edu.jsit.smartfactory.tools.CloudHelper;

import cn.edu.jsit.smartfactory.tools.SmartFactoryApplication;

public class SettingActivity extends Activity {

    private EditText serverAddress;
    private EditText projectLabel;
    private EditText cloudAccount;
    private EditText cloudAccountPassword;
    private EditText cameraAddress;
    private EditText tempSensorId;
    private EditText tempThresholdValue;
    private EditText humSensorId;
    private EditText humThresholdValue;
    private EditText lightSensorId;
    private EditText lightThresholdValue;
    private EditText bodySensorId;
    private EditText lightControllerId;
    private EditText ventilationControllerId;
    private EditText airControllerId;

    private SmartFactoryApplication smartFactory;
    private String uid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        //得到对象
        Bundle bundle = getIntent().getExtras();
        if (bundle!=null){
            uid = bundle.getString("uid");

        }
        smartFactory = (SmartFactoryApplication)getApplication();

        initView();

       /* String tempValue = intent.getStringExtra("tempValue");*/

    }
    private void initView(){
        serverAddress = (EditText)findViewById(R.id.et_server_address);
        projectLabel = (EditText)findViewById(R.id.et_project_label);
        cloudAccount = (EditText)findViewById(R.id.et_cloud_account);
        cloudAccountPassword = (EditText)findViewById(R.id.et_cloud_account_password);
        cameraAddress = (EditText)findViewById(R.id.et_camera_address);
        tempSensorId = (EditText)findViewById(R.id.et_temp_sensor_id);
        tempThresholdValue = (EditText)findViewById(R.id.et_temp_threshold_value);
        humSensorId = (EditText)findViewById(R.id.et_hum_sensor_id);
        humThresholdValue = (EditText)findViewById(R.id.et_hum_threshold_value);
        lightSensorId = (EditText)findViewById(R.id.et_light_sensor_id);
        lightThresholdValue = (EditText)findViewById(R.id.et_light_threshold_value);
        bodySensorId = (EditText)findViewById(R.id.et_body_sensor_id);
        lightControllerId = (EditText)findViewById(R.id.et_light_controller_id);
        ventilationControllerId = (EditText)findViewById(R.id.et_ventilation_controller_id);
        airControllerId = (EditText)findViewById(R.id.et_air_controller_id);
        //初始化温度,湿度，光照值
        Intent intent = getIntent();
        Float tempValue = intent.getFloatExtra("tempValue",0);
        Float humValue = intent.getFloatExtra("humValue",0);
        Float lightValue = intent.getFloatExtra("lightValue",0);
        tempThresholdValue.setText(tempValue.toString());
        humThresholdValue.setText(humValue.toString());
        lightThresholdValue.setText(lightValue.toString());

    }

    public void onClickSave(View view) {
        //保存数据
//        SharedPreferences sharePref = getSharedPreferences("params",Context.MODE_PRIVATE);
//
//        SharedPreferences.Editor editor = sharePref.edit();
//
//        editor.putString("server_address",serverAddress.getText().toString());
//        editor.putString("project_label",projectLabel.getText().toString());
//        editor.putString("cloud_account",cloudAccount.getText().toString());
//        editor.putString("cloud_account_password",cloudAccountPassword.getText().toString());
//        editor.putString("camera_address",cameraAddress.getText().toString());
//        editor.putString("temp_sensor_id",tempSensorId.getText().toString());
//        editor.putString("temp_threshold_value",tempThresholdValue.getText().toString());
//        editor.putString("hum_sensor_id",humSensorId.getText().toString());
//        editor.putString("hum_threshold_value",humThresholdValue.getText().toString());
//        editor.putString("light_sensor_id",lightControllerId.getText().toString());
//        editor.putString("light_threshold_value",lightThresholdValue.getText().toString());
//        editor.putString("body_sensor_id",bodySensorId.getText().toString());
//        editor.putString("light_controller_id",lightControllerId.getText().toString());
//        editor.putString("ventilation_controller_id",ventilationControllerId.getText().toString());
//        editor.putString("air_controller_id",airControllerId.getText().toString());
//
//        editor.commit();
//
//        Toast showToast = Toast.makeText(this,"保存成功",Toast.LENGTH_SHORT);
//        showToast.setGravity(Gravity.CENTER,0,0);
//        showToast.show();
        smartFactory.setServerAddress(serverAddress.getText().toString().trim());
        smartFactory.setProjectLabel(projectLabel.getText().toString().trim());
        smartFactory.setCloudAccount(cloudAccount.getText().toString().trim());
        smartFactory.setCloudAccountPassword(cloudAccountPassword.getText().toString().trim());
        smartFactory.setCameraAddress(cameraAddress.getText().toString().trim());
        smartFactory.setTempSensorId(tempSensorId.getText().toString().trim());
        smartFactory.setTempThresholdValue(Float.parseFloat(tempThresholdValue.getText().toString().trim()));
        smartFactory.setHumSensorId(humSensorId.getText().toString().trim());
        smartFactory.setHumThresholdValue(Float.parseFloat(humThresholdValue.getText().toString().trim()));
        smartFactory.setLightSensorId(lightSensorId.getText().toString().trim());
        smartFactory.setLightThresholdValue(Float.parseFloat(lightThresholdValue.getText().toString().trim()));
        smartFactory.setBodySensorId(bodySensorId.getText().toString().trim());
        smartFactory.setLightControllerId(lightControllerId.getText().toString().trim());
        smartFactory.setVentilationControllerId(ventilationControllerId.getText().toString().trim());
        smartFactory.setAirControllerId(airControllerId.getText().toString().trim());

        if(!checkInput(smartFactory)){
            return;
        }
        else{
            SharedPreferences sharedPref = getSharedPreferences("params",Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPref.edit();
            editor.putString("server_address",smartFactory.getServerAddress());
            editor.putString("project_label",smartFactory.getProjectLabel());
            editor.putString("cloud_account",smartFactory.getCloudAccount());
            editor.putString("cloud_account_password",smartFactory.getCloudAccountPassword());
            editor.putString("camera_address",smartFactory.getCameraAddress());
            editor.putString("temp_sensor_id", smartFactory.getTempSensorId());
            editor.putFloat("temp_threshold_value", smartFactory.getTempThresholdValue());
            editor.putString("hum_sensor_id", smartFactory.getHumSensorId());
            editor.putFloat("hum_threshold_value", smartFactory.getTempThresholdValue());
            editor.putString("light_sensor_id",smartFactory.getLightSensorId());
            editor.putFloat("light_threshold_value",smartFactory.getLightThresholdValue());
            editor.putString("body_sensor_id",smartFactory.getBodySensorId());
            editor.putString("light_controller_id",smartFactory.getLightControllerId());
            editor.putString("ventilation_controller_id",smartFactory.getVentilationControllerId());
            editor.putString("air_controller_id",smartFactory.getAirControllerId());
            editor.commit();
             //显示保存成功提示信息
            showToast(R.string.save_params_success);
//            Intent intent = new Intent(this,MainActivity.class);
//            startActivity(intent);
            setResult(RESULT_OK,(new Intent()).setAction(uid));
            finish();
        }
    }
    private boolean checkInput(SmartFactoryApplication smartFactory){
        boolean result = true;
        if(smartFactory.getServerAddress().equals("")){
            showToast(R.string.server_address_empty);
            return false;
        }
        if(smartFactory.getProjectLabel().equals("")){
            showToast(R.string.cloud_project_empty);
            return false;
        }
        if(smartFactory.getCloudAccount().equals("")){
            showToast(R.string.cloud_account_empty);
            return false;
        }
        if(smartFactory.getCloudAccountPassword().equals("")) {
        showToast(R.string.cloud_account_password_empty);
        return false;
        }
        if(smartFactory.getCameraAddress().equals("")){
            showToast(R.string.camera_address_empty);
            return false;
        }
        return result;
    }

    private void showToast(int resId){
        Toast showToast;
        showToast = Toast.makeText(this,resId,Toast.LENGTH_SHORT);
        showToast.setGravity(Gravity.CENTER,0,0);
        showToast.show();
    }
}

