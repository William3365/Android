package com.example.smartfactory.tools;

import android.app.Application;

public class SmartFactoryApplication extends Application {

    private String et_server_address="";
    private String et_project_label="";
    private String et_cloud_account="";
    private String et_cloud_account_passwprd="";
    private String et_camera_address="";
    private String et_wd_id="";
    private float et_wd_yz=0;
    private String et_sd_id="";
    private float et_sd_yz=0;
    private String et_gz_id="";
    private float et_gz_yz=0;
    private String et_rt_id="";
    private String et_gzkz_id="";
    private String et_tfkz_id="";
    private String et_ktkz_id="";
    private boolean isLogin=false;

    //重写父类的onCreate（）方法
    @Override
    public void onCreate() {
        super.onCreate();
    }
//封装
    public void setEt_server_address(String s){
        this.et_server_address=s;
    }
    public String getEt_server_address(){
        return this.et_server_address;
    }

    public void setEt_project_label(String s){
        this.et_project_label=s;
    }
    public String getEt_project_label(){
        return this.et_project_label;
    }

    public void setEt_cloud_account(String s){
        this.et_cloud_account=s;
    }
    public String getEt_cloud_account(){
        return this.et_cloud_account;
    }

    public void setEt_cloud_account_passwprd(String s){
        this.et_cloud_account_passwprd=s;
    }
    public String getEt_cloud_account_passwprd(){
        return this.et_cloud_account_passwprd;
    }

    public void setEt_camera_address(String s){
        this.et_camera_address=s;
    }
    public String getEt_camera_address(){
        return this.et_camera_address;
    }

    public void setEt_wd_id(String s){
        this.et_wd_id=s;
    }
    public String getEt_wd_id(){
        return this.et_wd_id;
    }

    public void setEt_wd_yz(float s){
        this.et_wd_yz=s;
    }
    public float getEt_wd_yz(){
        return this.et_wd_yz;
    }

    public void setEt_sd_id(String s){
        this.et_sd_id=s;
    }
    public String getEt_sd_id(){
        return this.et_sd_id;
    }

    public void setEt_sd_yz(float s){
        this.et_sd_yz=s;
    }
    public float getEt_sd_yz() {
        return et_sd_yz;
    }

    public void setEt_gz_id(String s){
        this.et_gz_id=s;
    }
    public String getEt_gz_id(){
        return this.et_gz_id;
    }

    public void setEt_gz_yz(float s){
        this.et_gz_yz=s;
    }
    public float getEt_gz_yz() {
        return et_gz_yz;
    }

    public void setEt_rt_id(String s){
        this.et_rt_id=s;
    }
    public String getEt_rt_id(){
        return this.et_rt_id;
    }

    public void setEt_gzkz_id(String s){
        this.et_gzkz_id=s;
    }
    public String getEt_gzkz_id(){
        return this.et_gzkz_id;
    }

    public void setEt_tfkz_id(String s){
        this.et_tfkz_id=s;
    }
    public String getEt_tfkz_id () {
        return et_tfkz_id;
    }

    public void setEt_ktkz_id(String s){
        this.et_ktkz_id=s;
    }
    public String getEt_ktkz_id(){
        return this.et_ktkz_id;
    }

    public void setIslogin(boolean s){
        this.isLogin=s;
    }
    public boolean getIslogin(){
        return this.isLogin;
    }

}
