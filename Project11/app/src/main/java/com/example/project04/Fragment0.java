package com.example.project04;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import java.util.Random;

public class Fragment0 extends Fragment {

    private TextView tv_wd,tv_sd,tv_gz,tv_o2,tv_co2;
    private Button button;
    private Random random = new Random();
    private int wd,sd,gz,o2,co2;
//    private Toast toast;
    private boolean bt = false;
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            switch (msg.what){
                case 1:
                    tv_wd.setText(wd + " °C");
                    tv_sd.setText(sd + " %");
                    tv_gz.setText(gz + " lux");
                    tv_o2.setText(o2 + " %");
                    tv_co2.setText(co2 + " %");
                    break;
                default:
                    break;
            }
        }
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_fragment0);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle saveInstanceState){
        View view = inflater.inflate(R.layout.activity_fragment0,container,false);
        return view;
    }
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
        myRandom();
}

    private void initView() {
        tv_wd = (TextView)getActivity().findViewById(R.id.tv_wd);
        tv_sd = (TextView)getActivity().findViewById(R.id.tv_sd);
        tv_gz = (TextView)getActivity().findViewById(R.id.tv_gz);
        tv_o2 = (TextView)getActivity().findViewById(R.id.tv_o2);
        tv_co2 = (TextView)getActivity().findViewById(R.id.tv_co2);
        button = (Button)getActivity().findViewById(R.id.bt_shuaxin);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                otherRandom();
            }
        });
    }

    //随机数
    private void myRandom() {
        wd = random.nextInt(18-13)+13;
        sd = random.nextInt(78-44)+44;
        gz = random.nextInt(3000-1700)+1700;

        o2 = random.nextInt(45-22)+22;

        handler.sendEmptyMessage(1);
    }
    //刷新
    private void otherRandom() {
        if (bt == false){
            wd = wd-1;
            sd = sd-2;
            gz = gz-10;
            co2 = co2-2;
            o2 = o2-1;
            bt = true;
        }else {
            wd = wd+1;
            sd = sd+2;
            gz = gz+10;
            co2 = co2+2;
            o2 = o2+1;
            bt = false;
        }

        handler.sendEmptyMessage(1);

        Toast toast = Toast.makeText(getActivity(),"数据刷新成功",Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER,0,0);
        toast.show();
    }
}
