package com.example.project06;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class zhuce extends AppCompatActivity {

    private EditText et_zh,et_mm;
    private Button bt_zc;
    private SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zhuce);

        initView();
    }

    private void initView() {
        et_zh = (EditText)findViewById(R.id.et_zh);
        et_mm = (EditText)findViewById(R.id.et_mm);
        bt_zc = (Button)findViewById(R.id.bt_zc);

        bt_zc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //将输入值存入变量
                String Z_name = et_zh.getText().toString().trim();
                String Z_pwd = et_mm.getText().toString().trim();
                //判断输入框状态
                if (Z_name.equals("") && Z_pwd.equals("")){
                    Toast.makeText(zhuce.this,"请填写账号和密码",Toast.LENGTH_SHORT).show();
                }else {
                    if (Z_pwd.length() >= 6){
                        SharedPF();
                        //读取存入的数据，并存入变量
                        String save_name = preferences.getString("zhanghao","");
                        String save_pwd = preferences.getString("mima","");

                        Intent intent = new Intent(zhuce.this,Login.class);
                        //将变量中的值，通过intent传递到Login界面
                        intent.putExtra("zhanghao",save_name);
                        intent.putExtra("mima",save_pwd);
                        startActivity(intent);
                        finish();
                    }else {
                        Toast.makeText(zhuce.this,"请输入6位以上密码",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

    }
    //存储数据
    private void SharedPF() {
        preferences = getSharedPreferences("Name_Pwd", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("zhanghao",et_zh.getText().toString().trim());
        editor.putString("mima",et_mm.getText().toString().trim());
        editor.commit();
        Toast.makeText(zhuce.this,"注册成功，欢迎使用",Toast.LENGTH_SHORT).show();
    }
}
