package cn.edu.jsit.smartfactory;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import cn.edu.jsit.smartfactory.tools.SmartFactoryApplication;

public class LanguageActivity extends AppCompatActivity {

    private TextView tv_back,tv_title;
    private ListView lv_language;
    private SharedPreferences spPreferences;
    //private LanguageAdapter languageAdapter;
    private String newLanguage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_language);
        initView();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event){
        switch (keyCode){
            case KeyEvent.KEYCODE_BACK:
                closeActivity();
                return true;
                default:
                    break;
        }
        return super.onKeyDown(keyCode,event);
    }

    private void closeActivity(){
        //if (!newLanguage.equals(SmartFactoryApplication.language)){
            //SmartFactoryApplication.language = newLanguage;
            Intent intent = new Intent(this,MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|
            Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        //}
        finish();
    }

    private void initView(){
        tv_back = (TextView)findViewById(R.id.tv_back);
        tv_back.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                closeActivity();
            }
        });
        tv_title = (TextView)findViewById(R.id.tv_title);
        tv_title.setText(R.string.language_title);
        lv_language = (ListView)findViewById(R.id.lv_language);
    }
}
