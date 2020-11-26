package com.example.project04;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

public class Fragment1 extends Fragment {

    private LinearLayout lly1,lly2,lly3;
    private boolean ly1 = false, ly2 = false, ly3 = false;

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
        lly1 = (LinearLayout)getActivity().findViewById(R.id.lly1);
        lly2 = (LinearLayout)getActivity().findViewById(R.id.lly2);
        lly3 = (LinearLayout)getActivity().findViewById(R.id.lly3);

        lly1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction("android.media.action.STILL_IMAGE_CAMERA");
                startActivity(intent);
            }
        });

        lly2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ly2 == false){
                    ShowToastOpen();
                    ly2 = true;
                }else if (ly2 == true){
                    ShowToastClose();
                    ly2 = false;
                }
            }
        });

        lly3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ly3 == false){
                    ShowToastOpen();
                    ly3 = true;
                }else if (ly3 == true){
                    ShowToastClose();
                    ly3 = false;
                }
            }
        });


    }

    private void ShowToastOpen(){
        Toast toast = Toast.makeText(getActivity(),"已打开",Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER,10,15);
        View view = toast.getView();
        view.setBackgroundResource(R.drawable.toast1);
        toast.show();
    }

    private void ShowToastClose(){
        Toast toast = Toast.makeText(getActivity(),"已关闭",Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER,10,15);
        View view = toast.getView();
        view.setBackgroundResource(R.drawable.toast1);
        toast.show();
    }
}
