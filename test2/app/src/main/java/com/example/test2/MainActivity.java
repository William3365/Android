package com.example.test2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText editText = (EditText)findViewById(R.id.et1);
        Button button = (Button)findViewById(R.id.bt1);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//说明editText.getText().toString()，初始等于null
//                if (editText.getText().toString().isEmpty()){
//                if (editText.getText().toString().equals("")){
//                    Toast.makeText(MainActivity.this,"请输入账号",Toast.LENGTH_SHORT).show();
//
//                }else {
//                    Intent intent = new Intent(MainActivity.this,Main2Activity.class);
//                    startActivity(intent);
//                }
                //解决办法（1）
                String ah = editText.getText().toString();
                if (ah.length()==0){
                    Toast.makeText(MainActivity.this,"请输入账号",Toast.LENGTH_SHORT).show();
                }else {
                    Intent intent = new Intent(MainActivity.this,Main2Activity.class);
                    startActivity(intent);
                }
            }
        });
    }
}
