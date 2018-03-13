package com.example.scroviewscrenn.utils;

import android.app.Application;

public class MyApplication extends Application {
    public static MyApplication application;

    @Override
    public void onCreate() {
        super.onCreate();
        application = this;
    }

    public static MyApplication getApp(){
        return application;
    }
}
