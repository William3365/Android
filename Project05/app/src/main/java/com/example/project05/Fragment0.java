package com.example.project05;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import static com.example.project05.R.id.img_1_onoff;

public class Fragment0 extends Fragment {
    //声明图片控件
    private ImageView img_1_onoff,img_2_onoff,img_3_onoff;
    private ImageView img_1_fire,img_2_fire,img_3_fire;
    //给定初始状态
    private boolean b_onoff1 = false;
    private boolean b_onoff2 = false;
    private boolean b_onoff3 = false;
    private boolean b_fire1 = false;
    private boolean b_fire2 = false;
    private boolean b_fire3= false;

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
    }

    //初始化控件
    private void initView() {
        img_1_onoff = (ImageView)getActivity().findViewById(R.id.img_1_onoff);
        img_2_onoff = (ImageView)getActivity().findViewById(R.id.img_2_onoff);
        img_3_onoff = (ImageView)getActivity().findViewById(R.id.img_3_onoff);

        img_1_fire = (ImageView)getActivity().findViewById(R.id.img_1_fire);
        img_2_fire = (ImageView)getActivity().findViewById(R.id.img_2_fire);
        img_3_fire = (ImageView)getActivity().findViewById(R.id.img_3_fire);
        //调用监听
        img_1_onoff.setOnClickListener(new mylisten_onoff());
        img_2_onoff.setOnClickListener(new mylisten_onoff());
        img_3_onoff.setOnClickListener(new mylisten_onoff());
        img_1_fire.setOnClickListener(new mylisten_fire());
        img_2_fire.setOnClickListener(new mylisten_fire());
        img_3_fire.setOnClickListener(new mylisten_fire());
    }
    //开关的监听
    private class mylisten_onoff implements View.OnClickListener {
        @Override
        public void onClick(View v) {

            int key = v.getId();

            switch (key){
                case R.id.img_1_onoff:
                    if (b_onoff1 == false){
                        img_1_onoff.setImageResource(R.mipmap.on);
                        b_onoff1 = true;
                        //指定toast的位置
                        Toast toast = Toast.makeText(getActivity(),"设备已开启",Toast.LENGTH_SHORT);
                        toast.setGravity(Gravity.CENTER,0,0);
                        View view = toast.getView();
                        //指定toast的背景
                        view.setBackgroundResource(R.drawable.toast01);
                        toast.show();
                    }else if (b_onoff1 == true){
                        img_1_onoff.setImageResource(R.mipmap.off);
                        b_onoff1 = false;
                        Toast toast = Toast.makeText(getActivity(),"设备已关闭",Toast.LENGTH_SHORT);
                        toast.setGravity(Gravity.CENTER,0,0);
                        View view = toast.getView();
                        view.setBackgroundResource(R.drawable.toast01);
                        toast.show();
                    }
                    break;
                case R.id.img_2_onoff:
                    if (b_onoff2 == false){
                        img_2_onoff.setImageResource(R.mipmap.on);
                        b_onoff2 = true;
                        //指定toast的位置
                        Toast toast = Toast.makeText(getActivity(),"设备已开启",Toast.LENGTH_SHORT);
                        toast.setGravity(Gravity.CENTER,0,0);
                        View view = toast.getView();
                        //指定toast的背景
                        view.setBackgroundResource(R.drawable.toast01);
                        toast.show();
                    }else if (b_onoff2 == true){
                        img_2_onoff.setImageResource(R.mipmap.off);
                        b_onoff2 = false;
                        Toast toast = Toast.makeText(getActivity(),"设备已关闭",Toast.LENGTH_SHORT);
                        toast.setGravity(Gravity.CENTER,0,0);
                        View view = toast.getView();
                        view.setBackgroundResource(R.drawable.toast01);
                        toast.show();
                    }
                    break;
                case R.id.img_3_onoff:
                    if (b_onoff3 == false){
                        img_3_onoff.setImageResource(R.mipmap.on);
                        b_onoff3 = true;
                        //指定toast的位置
                        Toast toast = Toast.makeText(getActivity(),"设备已开启",Toast.LENGTH_SHORT);
                        toast.setGravity(Gravity.CENTER,0,0);
                        View view = toast.getView();
                        //指定toast的背景
                        view.setBackgroundResource(R.drawable.toast01);
                        toast.show();
                    }else if (b_onoff3 == true){
                        img_3_onoff.setImageResource(R.mipmap.off);
                        b_onoff3 = false;
                        Toast toast = Toast.makeText(getActivity(),"设备已关闭",Toast.LENGTH_SHORT);
                        toast.setGravity(Gravity.CENTER,0,0);
                        View view = toast.getView();
                        view.setBackgroundResource(R.drawable.toast01);
                        toast.show();
                    }
                    break;
                default:
                    break;
            }

        }
    }
    //火焰监听
    private class mylisten_fire implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            int key = v.getId();
            switch (key){
                case R.id.img_1_fire:
                    if (b_fire1 == false){
                        img_1_fire.setImageResource(R.mipmap.fire_on);
                        b_fire1 = true;
                        Toast toast = Toast.makeText(getActivity(),"检测到火焰，危险！",Toast.LENGTH_LONG);
                        View view = toast.getView();
                        view.setBackgroundResource(R.drawable.toast02);
                        toast.setGravity(Gravity.CENTER,0,0);
                        toast.show();
                    }else if (b_fire1 == true){
                        img_1_fire.setImageResource(R.mipmap.fire_off);
                        b_fire1 = false;
                    }
                    break;
                case R.id.img_2_fire:
                    if (b_fire2 == false){
                        img_2_fire.setImageResource(R.mipmap.fire_on);
                        b_fire2 = true;
                        Toast toast = Toast.makeText(getActivity(),"检测到火焰，危险！",Toast.LENGTH_LONG);
                        View view = toast.getView();
                        view.setBackgroundResource(R.drawable.toast02);
                        toast.setGravity(Gravity.CENTER,0,0);
                        toast.show();
                    }else if (b_fire2 == true){
                        img_2_fire.setImageResource(R.mipmap.fire_off);
                        b_fire2 = false;
                    }
                    break;
                case R.id.img_3_fire:
                    if (b_fire3 == false){
                        img_3_fire.setImageResource(R.mipmap.fire_on);
                        b_fire3 = true;
                        Toast toast = Toast.makeText(getActivity(),"检测到火焰，危险！",Toast.LENGTH_LONG);
                        View view = toast.getView();
                        view.setBackgroundResource(R.drawable.toast02);
                        toast.setGravity(Gravity.CENTER,0,0);
                        toast.show();
                    }else if (b_fire3 == true){
                        img_3_fire.setImageResource(R.mipmap.fire_off);
                        b_fire3 = false;
                    }
                    break;
                default:
                    break;
            }
        }
    }
}
