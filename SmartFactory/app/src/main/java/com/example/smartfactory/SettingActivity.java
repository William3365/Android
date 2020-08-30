package com.example.smartfactory;

import androidx.appcompat.app.AppCompatActivity;

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

import com.example.smartfactory.tools.SmartFactoryApplication;

public class SettingActivity extends AppCompatActivity {

    private EditText et_server_address;
    private EditText et_project_label;
    private EditText et_cloud_account;
    private EditText et_cloud_account_passwprd;
    private EditText et_camera_address;
    private EditText et_wd_id;
    private EditText et_wd_yz;
    private EditText et_sd_id;
    private EditText et_sd_yz;
    private EditText et_gz_id;
    private EditText et_gz_yz;
    private EditText et_rt_id;
    private EditText et_gzkz_id;
    private EditText et_tfkz_id;
    private EditText et_ktkz_id;
    private SmartFactoryApplication smartFactory;
    private String uid;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        /*
           getIntent（）方法。获取通过意图额外发送的信息
         */
//        Intent intent = getIntent();

        //得到对象

        Bundle bundle = getIntent().getExtras();
        if (bundle != null){
            uid = bundle.getString("uid");
        }

        smartFactory = (SmartFactoryApplication)getApplication();
        initView();
    }

    private void initView() {
        et_server_address = (EditText)findViewById(R.id.et_server_address);
        et_project_label = (EditText)findViewById(R.id.et_project_label);
        et_cloud_account = (EditText)findViewById(R.id.et_cloud_account);
        et_cloud_account_passwprd = (EditText)findViewById(R.id.et_cloud_account_passwprd);
        et_camera_address = (EditText)findViewById(R.id.et_camera_address);
        et_wd_id = (EditText)findViewById(R.id.et_wd_id);
        et_wd_yz = (EditText)findViewById(R.id.et_wd_yz);
        et_sd_id = (EditText)findViewById(R.id.et_sd_id);
        et_sd_yz = (EditText)findViewById(R.id.et_sd_yz);
        et_gz_id = (EditText)findViewById(R.id.et_gz_id);
        et_gz_yz = (EditText)findViewById(R.id.et_gz_yz);
        et_rt_id = (EditText)findViewById(R.id.et_rt_id);
        et_gzkz_id = (EditText)findViewById(R.id.et_gzkz_id);
        et_tfkz_id = (EditText)findViewById(R.id.et_tfkz_id);
        et_ktkz_id = (EditText)findViewById(R.id.et_ktkz_id);

        Intent intent = getIntent();

        Float wdz = intent.getFloatExtra("温度值：",0);
        Float sdz = intent.getFloatExtra("湿度值：",0);
        Float gzz = intent.getFloatExtra("光照值：",0);

        et_wd_yz.setText(wdz.toString());
        et_sd_yz.setText(sdz.toString());
        et_gz_yz.setText(gzz.toString());
    }

    public void onClickSave(View view){
//        //通过getSharedPreferences（）方法，获取SharedPreferences对象
//            /*
//                参数：
//                1、名称，用于区分SharedPreferences文件
//                2、权限：
//                    MODE_PRIVATE，则该配置文件只能被自己的应用程序访问。
//                    MODE_WORLD_READABLE，则该配置文件除了自己访问外还可 以被其它应该程序读取
//                    MODE_WORLD_WRITEABLE，则该配置文件除了自己访问外还 可以被其它应该程序读取和写入
//             */
//        SharedPreferences sharePref = getSharedPreferences("params", Context.MODE_PRIVATE);
//        //edit()方法，创建一个 SharedPreferences.Editor
//        SharedPreferences.Editor editor = sharePref.edit();
//        //传递键值对数据
//        editor.putString("server_address",et_server_address.getText().toString());
//        editor.putString("cloud_project_label",et_project_label.getText().toString());
//        editor.putString("cloud_account",et_cloud_account.getText().toString());
//        editor.putString("cloud_account_password",et_cloud_account_passwprd.getText().toString());
//        editor.putString("camera_address",et_camera_address.getText().toString());
//        editor.putString("wd_id",et_wd_id.getText().toString());
//        editor.putString("wd_yz",et_wd_yz.getText().toString());
//        editor.putString("sd_id",et_sd_id.getText().toString());
//        editor.putString("sd_yz",et_sd_yz.getText().toString());
//        editor.putString("gz_id",et_gz_id.getText().toString());
//        editor.putString("gz_yz",et_gz_yz.getText().toString());
//        editor.putString("rt_id",et_rt_id.getText().toString());
//        editor.putString("gz_kz_id",et_gzkz_id.getText().toString());
//        editor.putString("tf_kz_id",et_tfkz_id.getText().toString());
//        editor.putString("kt_kz_id",et_ktkz_id.getText().toString());
//        //commit()方法，提交数据
//        editor.commit();
//        //提示保存数据成功
//        Toast toast = Toast.makeText(this,"success",Toast.LENGTH_SHORT);
//        //setGravity（）方法，设置Toast显示的位置
//        toast.setGravity(Gravity.CENTER,0,0);
//        //show（）方法，显示
//        toast.show();
        //trim()方法，去除空格
        smartFactory.setEt_server_address(et_server_address.getText().toString().trim());
        smartFactory.setEt_project_label(et_project_label.getText().toString().trim());
        smartFactory.setEt_cloud_account(et_cloud_account.getText().toString().trim());
        smartFactory.setEt_cloud_account_passwprd(et_cloud_account_passwprd.getText().toString().trim());
        smartFactory.setEt_camera_address(et_camera_address.getText().toString().trim());
        smartFactory.setEt_wd_id(et_wd_id.getText().toString().trim());
        smartFactory.setEt_wd_yz(Float.parseFloat(et_wd_yz.getText().toString().trim()));
        smartFactory.setEt_sd_id(et_sd_id.getText().toString().trim());
        smartFactory.setEt_sd_yz(Float.parseFloat(et_sd_yz.getText().toString().trim()));
        smartFactory.setEt_gz_id(et_gz_id.getText().toString().trim());
        smartFactory.setEt_gz_yz(Float.parseFloat(et_gz_yz.getText().toString().trim()));
        smartFactory.setEt_rt_id(et_rt_id.getText().toString().trim());
        smartFactory.setEt_gzkz_id(et_gzkz_id.getText().toString().trim());
        smartFactory.setEt_tfkz_id(et_tfkz_id.getText().toString().trim());
        smartFactory.setEt_ktkz_id(et_ktkz_id.getText().toString().trim());

        if (!checkInput(smartFactory)){
            return;
        }else {
        SharedPreferences sharePref = getSharedPreferences("params", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharePref.edit();
        editor.putString("server_address",smartFactory.getEt_server_address());
        editor.putString("cloud_project_label",smartFactory.getEt_project_label());
        editor.putString("cloud_account",smartFactory.getEt_cloud_account());
        editor.putString("cloud_account_password",smartFactory.getEt_cloud_account_passwprd());
        editor.putString("camera_address",smartFactory.getEt_camera_address());
        editor.putString("wd_id",smartFactory.getEt_wd_id());
        editor.putFloat("wd_yz",smartFactory.getEt_wd_yz());
        editor.putString("sd_id",smartFactory.getEt_sd_id());
        editor.putFloat("sd_yz",smartFactory.getEt_sd_yz());
        editor.putString("gz_id",smartFactory.getEt_gz_id());
        editor.putFloat("gz_yz",smartFactory.getEt_gz_yz());
        editor.putString("rt_id",smartFactory.getEt_rt_id());
        editor.putString("gz_kz_id",smartFactory.getEt_gzkz_id());
        editor.putString("tf_kz_id",smartFactory.getEt_tfkz_id());
        editor.putString("kt_kz_id",smartFactory.getEt_ktkz_id());
        editor.commit();

        showToast(R.string.showToast_success);
//        Intent intent = new Intent(this,MainActivity.class);
//        startActivity(intent);
         setResult(RESULT_OK,(new Intent()).setAction(uid));
         finish();

        }
    }

    private boolean checkInput(SmartFactoryApplication smartFactory){
        boolean result = true;
        if (smartFactory.getEt_server_address().equals("")){
            showToast(R.string.server_address_empty);
            return false;
        }
        if (smartFactory.getEt_project_label().equals("")){
            showToast(R.string.cloud_project_label_empty);
            return false;
        }
        if (smartFactory.getEt_cloud_account().equals("")){
            showToast(R.string.cloud_account_empty);
            return false;
        }
        if (smartFactory.getEt_cloud_account_passwprd().equals("")){
            showToast(R.string.cloud_account_password_empty);
            return false;
        }
        if (smartFactory.getEt_camera_address().equals("")){
            showToast(R.string.camera_address_empty);
            return false;
        }
        return result;
    }

    private  void showToast(int resId){
        Toast showToast;
        showToast = Toast.makeText(this,resId,Toast.LENGTH_SHORT);
        showToast.setGravity(Gravity.CENTER,0,0);
        showToast.show();
    }
}
