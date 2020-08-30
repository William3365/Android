package com.example.onenet01;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class Mythread implements Runnable {
    public String Getvalue1() {
        String respon = "";
        try {
            //1创建一个URL对象，并传入目标网络地址，其中device_id是你i要查询的数据流的设备id，datastream_id是查询的数据流的名字。
            URL url = new URL("https://open.iot.10086.cn/hewu/website/products/" + "367476" + "/datastreams/"+"gas_sensor");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();      //2然后用openConnection方法
            conn.setConnectTimeout(1 * 1000);   //设置连接超时的时间
            conn.setRequestMethod("GET");      //3HTTP请求的方法是GET
            conn.setRequestProperty("api-key", ""); //4消息头
            if (conn.getResponseCode() == 200) {  //返回码是200，网络正常
                InputStream is = conn.getInputStream();            //读取获得的输入流
                ByteArrayOutputStream os = new ByteArrayOutputStream();//字节数组输出流在内存中创建一个字节数组缓冲区，
                int len = 0;                                          //所有发送到输出流的数据保存在该字节数组缓冲区中。
                byte buffer[] = new byte[1024];
                while ((len = is.read(buffer)) != -1) {        // // 将内容读到buffer中，读到末尾为-1
                    os.write(buffer, 0, len);
                }
                is.close();//关闭
                os.close();
                JSONObject root = new JSONObject(os.toString());   //将缓冲区的内容转换为字符串，然后字符串构建JSON对象
                respon = root.getJSONObject("data").getString("current_value");
                Log.e("返回数据",""+root);
            }else {
                //返回码不是200，网络异常
            }
        }  catch (IOException | JSONException e) {
            e.printStackTrace();
        }
        return respon;
    }

    @Override
    public void run() {

    }
}
