package cn.edu.jsit.smartfactory;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import cn.edu.jsit.smartfactory.tools.WebServiceHelper;

public class UserActivity extends AppCompatActivity {

    private TextView ac_tv,sex_tv,phone_tv,e_tv;
    private String ac,sex,phone,email,result;
    private SharedPreferences sharedPreferences;

    @SuppressLint("HandlerLeak")
    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg){
            super.handleMessage(msg);
            try{
                JSONArray array = new JSONArray(result);
                JSONObject object = (JSONObject)array.get(0);
                sex = object.getString("sys_Sex");
                phone = object.getString("sys_Phone");
                email = object.getString("sys_Email");
                ac_tv.setText(ac);
                sex_tv.setText(sex);
                phone_tv.setText(phone);
                e_tv.setText(email);

            }
            catch (JSONException e){
                e.printStackTrace();
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        ((TextView)findViewById(R.id.account_type)).setText(R.string.normal_account);
        ac_tv = findViewById(R.id.account_name);
        sex_tv = findViewById(R.id.sex);
        phone_tv = findViewById(R.id.phone_number);
        e_tv = findViewById(R.id.email);
        sharedPreferences = getSharedPreferences("loginSet",MODE_PRIVATE);
        ac = sharedPreferences.getString("user","");
        /*WebServiceHelper.GetById(new WebServiceHelper.Callback(){
            @Override
            public void call(String s){
                result = s;


            }
        },ac);*/
        setListener();
    }

    public void setListener(){
        ((TextView)findViewById(R.id.tv_back)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ActivityCompat.finishAfterTransition(UserActivity.this);
            }
        });
    }
}
