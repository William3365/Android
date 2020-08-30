package com.example.project06;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Login extends AppCompatActivity {

    private EditText et_zh,et_mm;
    private Button bt_dl;
    private TextView tv_zc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initView();
    }

    private void initView() {
        et_zh = (EditText)findViewById(R.id.et_zh);
        et_mm = (EditText)findViewById(R.id.et_mm);
        bt_dl = (Button)findViewById(R.id.bt_dl);
        tv_zc = (TextView)findViewById(R.id.tv_zc);

        //登录控件监听
        bt_dl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //将输入值存入变量
                String name = et_zh.getText().toString().trim();
                String pwd = et_mm.getText().toString().trim();
                //获取传递来的intent
                Intent intent = getIntent();
                //将传递来的值存入变量
                String save_name = intent.getStringExtra("zhanghao");
                String save_pwd = intent.getStringExtra("mima");

                if (name.equals("")  && pwd.equals("")){
                    Toast.makeText(Login.this,"请输入账号密码",Toast.LENGTH_SHORT).show();

                }else{
                    //将输入的变量和传递来的变量进行比对
                    if (name.equals(save_name) && pwd.equals(save_pwd)){
                        Intent intent_1 = new Intent(Login.this,MainActivity.class);
                        startActivity(intent_1);

                        //将账号信息传递到Fragment2,但是这里得写成MainActivity
                        Intent intent1 = new Intent(Login.this,MainActivity.class);
                        intent1.putExtra("zhanghao",save_name);
                        startActivity(intent1);

                        finish();
                    }else {
                        Toast.makeText(Login.this,"账号或密码错误",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        //注册控件监听
        tv_zc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (et_zh.getText().toString().length() == 0 && et_mm.getText().toString().length() == 0){

                    Intent intent = new Intent(Login.this,zhuce.class);
//                    Log.d("---","=====");
                    startActivity(intent);
                }
            }
        });

    }
}
