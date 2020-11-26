package com.example.project04;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import pl.droidsonroids.gif.GifImageView;

public class Fragment1 extends Fragment {

    private Button button;
    private GifImageView gifImageView;
    private TextView tv1;
    private ImageView img1,img2;
    private boolean onoff = false;//开关初始状态
    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler(){
        @SuppressLint("WrongConstant")
        @Override
        public void handleMessage(@NonNull Message msg) {
            switch (msg.what){
                case 1:
                    tv1.setText("网络延迟，暂时无法获取数据，请稍后重试");
            }
        }
    };

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
        button = (Button)getActivity().findViewById(R.id.bt);
        gifImageView = (GifImageView) getActivity().findViewById(R.id.img);
        tv1 = (TextView)getActivity().findViewById(R.id.tv1);
        img1 = (ImageView)getActivity().findViewById(R.id.img1);
        img2 = (ImageView)getActivity().findViewById(R.id.img2);

        button.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("WrongConstant")
            @Override
            public void onClick(View v) {
                /*
                对应的三个常量值为0、4、8
                VISIBLE:0 可见的
                INVISIBLE:4 不可见的，但还占着原来的空间
                GONE:8 隐藏，不占用原来的布局空间
                 */
               gifImageView.setVisibility(0);

               handler.postDelayed(new Runnable() {
                   @Override
                   public void run() {
                       gifImageView.setVisibility(8);
                   }
               },0,4000);

               handler.sendEmptyMessage(1);
            }
        });

        img1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onoff == false){
                    img1.setImageResource(R.mipmap.on1);
                    onoff = true;
                }else if (onoff == true){
                    img1.setImageResource(R.mipmap.off1);
                    onoff = false;
                }
            }
        });

        img2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onoff == false){
                    img2.setImageResource(R.mipmap.on1);
                    onoff = true;
                }else if (onoff == true){
                    img2.setImageResource(R.mipmap.off1);
                    onoff = false;
                }
            }
        });
    }
}
