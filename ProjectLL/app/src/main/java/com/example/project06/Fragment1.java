package com.example.project06;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

public class Fragment1 extends Fragment {

    private ImageView img1,img2,img3,img4,img5;
    private boolean b1,b2,b3,b4,b5;
    private EditText et1,et2,et3,et4;
    private Button button;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_fragment1);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState){
        View view = inflater.inflate(R.layout.activity_fragment1,container,false);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initView();
    }

    private void initView() {
        img1 = (ImageView)getActivity().findViewById(R.id.img1);
        img2 = (ImageView)getActivity().findViewById(R.id.img2);
        img3 = (ImageView)getActivity().findViewById(R.id.img3);
        img4 = (ImageView)getActivity().findViewById(R.id.img4);
        img5 = (ImageView)getActivity().findViewById(R.id.img5);
        et1 = (EditText)getActivity().findViewById(R.id.et1);
        et2 = (EditText)getActivity().findViewById(R.id.et2);
        et3 = (EditText)getActivity().findViewById(R.id.et3);
        et4 = (EditText)getActivity().findViewById(R.id.et4);
        button = (Button)getActivity().findViewById(R.id.btt);

        img1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (b1 == false){
                    img1.setImageResource(R.mipmap.on);
                    b1 = true;
                    //指定toast的位置
                    Toast toast = Toast.makeText(getActivity(),"已开启",Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.CENTER,0,0);
                    toast.show();
                }else if (b1 == true){
                    img1.setImageResource(R.mipmap.off);
                    b1 = false;
                    Toast toast = Toast.makeText(getActivity(),"已关闭",Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.CENTER,0,0);
                    toast.show();
                }
            }
        });

        img2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (b2 == false){
                    img2.setImageResource(R.mipmap.on);
                    b2 = true;
                    //指定toast的位置
                    Toast toast = Toast.makeText(getActivity(),"已开启",Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.CENTER,0,0);
                    toast.show();
                }else if (b2 == true){
                    img2.setImageResource(R.mipmap.off);
                    b2 = false;
                    Toast toast = Toast.makeText(getActivity(),"已关闭",Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.CENTER,0,0);
                    toast.show();
                }
            }
        });

        img3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (b3 == false){
                    img3.setImageResource(R.mipmap.on);
                    b3 = true;
                    //指定toast的位置
                    Toast toast = Toast.makeText(getActivity(),"已开启",Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.CENTER,0,0);
                    toast.show();
                }else if (b3 == true){
                    img3.setImageResource(R.mipmap.off);
                    b3 = false;
                    Toast toast = Toast.makeText(getActivity(),"已关闭",Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.CENTER,0,0);
                    toast.show();
                }
            }
        });

        img4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (b4 == false){
                    img4.setImageResource(R.mipmap.on);
                    b4 = true;
                    //指定toast的位置
                    Toast toast = Toast.makeText(getActivity(),"已开启",Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.CENTER,0,0);
                    toast.show();
                }else if (b4 == true){
                    img4.setImageResource(R.mipmap.off);
                    b4 = false;
                    Toast toast = Toast.makeText(getActivity(),"已关闭",Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.CENTER,0,0);
                    toast.show();
                }
            }
        });

        img5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (b5 == false){
                    img5.setImageResource(R.mipmap.on);
                    b5 = true;
                    //指定toast的位置
                    Toast toast = Toast.makeText(getActivity(),"已开启",Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.CENTER,0,0);
                    toast.show();
                }else if (b5 == true){
                    img5.setImageResource(R.mipmap.off);
                    b5 = false;
                    Toast toast = Toast.makeText(getActivity(),"已关闭",Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.CENTER,0,0);
                    toast.show();
                }
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast toast = Toast.makeText(getActivity(),"数据保存成功",Toast.LENGTH_LONG);
                toast.setGravity(Gravity.CENTER,0,0);
                View view = toast.getView();//指定toast的背景
                view.setBackgroundResource(R.drawable.toast);
                toast.show();
//                SharedPf();
            }
        });
    }
//    private void SharedPf() {
//        SharedPreferences preferences = getParentFragment()("Tft", Context.MODE_PRIVATE);
//        SharedPreferences.Editor editor = preferences.edit();
//        editor.putString("1",et1.getText().toString().trim());
//        editor.putString("2",et2.getText().toString().trim());
//        editor.putString("3",et3.getText().toString().trim());
//        editor.putString("4",et4.getText().toString().trim());
//        editor.commit();
//    }
}
