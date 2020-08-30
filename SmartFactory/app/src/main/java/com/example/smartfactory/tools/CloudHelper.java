package com.example.smartfactory.tools;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;
import cn.com.newland.nle_sdk.requestEntity.SignIn;
import cn.com.newland.nle_sdk.responseEntity.SensorInfo;
import cn.com.newland.nle_sdk.responseEntity.User;
import cn.com.newland.nle_sdk.responseEntity.base.BaseResponseEntity;
import cn.com.newland.nle_sdk.util.NCallBack;
import cn.com.newland.nle_sdk.util.NetWorkBusiness;

public class CloudHelper {

    private String token = "";

    public String getToken(){
        return this.token;
    }
//一直提示账号不存在问题
    public void signIn(final Context c,String address,String account,String pwd){
        NetWorkBusiness nb = new NetWorkBusiness("",address);
        nb.signIn(new SignIn(account, pwd), new NCallBack<BaseResponseEntity<User>>(c) {//c参数，上下文
            @Override
            protected void onResponse(BaseResponseEntity<User> response) {//userBaseResponseEntity,繁琐
                if (response.getStatus()==0){
                    token = response.getResultObj().getAccessToken();
                    Log.d("======",token);
                    Toast.makeText(c,"success",Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(c,"faile",Toast.LENGTH_SHORT).show();
                    Log.d("-----",response.getMsg());
                    Toast.makeText(c,response.getMsg(),Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public interface DCallback{
        void trans(String s);
    }

    public void getSensorData(Context c,String address,String prijLable,String sensorID,final DCallback dccallback){
        NetWorkBusiness nb = new NetWorkBusiness(token,address);
        nb.getSensor(prijLable, sensorID, new NCallBack<BaseResponseEntity<SensorInfo>>(c){
            @Override
            protected void onResponse(BaseResponseEntity<SensorInfo> arg0) {//sensorInfoBaseResponseEntity，繁琐，arg0
                //老师视频中讲的
                if (arg0 != null && arg0.getResultObj() != null && arg0.getResultObj().getValue() != null){
                //我一直用的
//                if (sensorInfoBaseResponseEntity != null && sensorInfoBaseResponseEntity.getResultObj() != null && sensorInfoBaseResponseEntity.getResultObj().getSensorType() != null){
                    dccallback.trans(arg0.getResultObj().getValue());
                }
            }
        });
    }
    //state,表示状态，1表示打开，0表示关闭
    public void onOff(final Context c,String address,String prjLabel,String controllerID,int state){
        NetWorkBusiness nb = new NetWorkBusiness(token,address);
        nb.control(prjLabel, controllerID, state, new NCallBack<BaseResponseEntity>(c) {
            @Override
            protected void onResponse(BaseResponseEntity baseResponseEntity) {
                Toast.makeText(c,"控制器操作成功",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
