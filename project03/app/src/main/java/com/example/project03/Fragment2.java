package com.example.project03;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;

public class Fragment2 extends Fragment {

    private ImageView imageView;
    private Button button;
    private boolean bt = false;//button初始状态

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_fragment1);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState){
        View view = inflater.inflate(R.layout.activity_fragment2,container,false);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initView();
    }

    private void initView() {
        imageView = (ImageView)getActivity().findViewById(R.id.sfm);
        button = (Button)getActivity().findViewById(R.id.sx);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (bt == false){
                    imageView.setImageResource(R.mipmap.zhanghao002);
                    bt = true;
                }else if (bt == true){
                    imageView.setImageResource(R.mipmap.zhanghao003);
                    bt = false;
                }


            }
        });
    }
}
