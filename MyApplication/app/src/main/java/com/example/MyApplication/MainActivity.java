package com.example.MyApplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText etName,etPwd;
    Button btBC;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etName = (EditText)findViewById(R.id.etName);
        etPwd = (EditText)findViewById(R.id.etPwd);
        btBC = (Button)findViewById(R.id.btBc);

        btBC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (etName.getText().toString().length()==0 && etName.getText().toString().length()==0){
                    Toast.makeText(MainActivity.this,"请填写学号和密码",Toast.LENGTH_SHORT).show();
                }else {
                    SharedPf();
                    Toast.makeText(MainActivity.this,"保存数据成功",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(MainActivity.this,haLashao.class);
                    startActivity(intent);
                }
            }
        });
    }

    private void SharedPf() {
        SharedPreferences preferences = getSharedPreferences("Tft", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("name",etName.getText().toString().trim());
        editor.putString("pwd",etPwd.getText().toString().trim());
        editor.commit();
    }
}
