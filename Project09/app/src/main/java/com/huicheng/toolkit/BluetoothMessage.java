package com.huicheng.toolkit;

import android.bluetooth.BluetoothDevice;

public class BluetoothMessage {

    private BluetoothDevice mDevice;
    private String mName;

    public BluetoothMessage(BluetoothDevice device){
        this.mDevice = device;
    }

    public void setName(String name){
        this.mName = name;
    }

    public BluetoothDevice getDevice(){
        return mDevice;
    }

    public String getName(){
        return mName;
    }

}
