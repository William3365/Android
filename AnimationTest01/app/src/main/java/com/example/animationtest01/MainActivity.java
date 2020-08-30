package com.example.animationtest01;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import cn.com.newland.nle_sdk.requestEntity.SignIn;
import cn.com.newland.nle_sdk.responseEntity.User;
import cn.com.newland.nle_sdk.responseEntity.base.BaseResponseEntity;
import cn.com.newland.nle_sdk.util.NCallBack;
import cn.com.newland.nle_sdk.util.NetWorkBusiness;

public class MainActivity extends AppCompatActivity {

    ImageView imageView;
    Animation rotate;

    String token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        rotate = new RotateAnimation(
//                0f,
//                360f,
//                Animation.RELATIVE_TO_SELF,
//                0.5f,
//                Animation.RELATIVE_TO_SELF,
//                0.5f);
//        LinearInterpolator lin = new LinearInterpolator();
//        rotate.setInterpolator(lin);
//        rotate.setDuration(1500);
//        rotate.setRepeatCount(-1);
//        rotate.setFillAfter(true);
//        rotate.setStartOffset(10);

        rotate = AnimationUtils.loadAnimation(MainActivity.this, R.anim.rotate_anim);

        ((ImageView) findViewById(R.id.img_fan)).setAnimation(rotate);
        ((ImageView) findViewById(R.id.img_fan)).startAnimation(rotate);//启动动画

        Log.d("111", "----");
        NetWorkBusiness netWorkBusiness = new NetWorkBusiness("", "http://www.nlecloud.com/");

        netWorkBusiness.signIn(new SignIn("13921292908", "13921292908"), new NCallBack<BaseResponseEntity<User>>(MainActivity.this) {
            @Override
            protected void onResponse(BaseResponseEntity<User> userBaseResponseEntity) {
                if (userBaseResponseEntity.getStatus() == 0) {
                    token = userBaseResponseEntity.getResultObj().getAccessToken();
                    Toast.makeText(MainActivity.this, "success", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "fail", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
