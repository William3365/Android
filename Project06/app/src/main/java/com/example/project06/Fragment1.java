package com.example.project06;

import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

public class Fragment1 extends Fragment {

    private ImageView img1,img2,img3,img4,img5;
    private boolean b1,b2,b3,b4,b5;

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

        img1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (b1 == false){
                    img1.setImageResource(R.mipmap.on);
                    b1 = true;
                    //指定toast的位置
                    Toast toast = Toast.makeText(getActivity(),"已开启",Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER,0,0);
                    toast.show();
                }else if (b1 == true){
                    img1.setImageResource(R.mipmap.off);
                    b1 = false;
                    Toast toast = Toast.makeText(getActivity(),"已关闭",Toast.LENGTH_SHORT);
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
                    Toast toast = Toast.makeText(getActivity(),"已开启",Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER,0,0);
                    toast.show();
                }else if (b2 == true){
                    img2.setImageResource(R.mipmap.off);
                    b2 = false;
                    Toast toast = Toast.makeText(getActivity(),"已关闭",Toast.LENGTH_SHORT);
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
                    Toast toast = Toast.makeText(getActivity(),"已开启",Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER,0,0);
                    toast.show();
                }else if (b3 == true){
                    img3.setImageResource(R.mipmap.off);
                    b3 = false;
                    Toast toast = Toast.makeText(getActivity(),"已关闭",Toast.LENGTH_SHORT);
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
                    Toast toast = Toast.makeText(getActivity(),"已开启",Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER,0,0);
                    toast.show();
                }else if (b4 == true){
                    img4.setImageResource(R.mipmap.off);
                    b4 = false;
                    Toast toast = Toast.makeText(getActivity(),"已关闭",Toast.LENGTH_SHORT);
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
                    Toast toast = Toast.makeText(getActivity(),"已开启",Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER,0,0);
                    toast.show();
                }else if (b5 == true){
                    img5.setImageResource(R.mipmap.off);
                    b5 = false;
                    Toast toast = Toast.makeText(getActivity(),"已关闭,视频内容已保存到相册",Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER,0,0);
                    toast.show();
                }
            }
        });
    }
}
