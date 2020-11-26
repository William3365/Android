package com.example.project04;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

public class Fragment1 extends Fragment {

    private ImageView img1;
    private boolean b1;

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
    }
}
