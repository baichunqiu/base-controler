package com.bcq.ui.controler.base.api;

import android.app.Application;
import android.content.Context;
import android.os.Handler;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: BaiCQ
 * @ClassName: ApiApplication
 * @Description: 通用api的封装
 */
public class ApiApplication extends Application {
    public static final boolean bOpenLog = false;
    //huander对象
    private static Handler mHandler;
    //UI线程的id
    private static int mainThreadId;
    //UI线程
    private static Thread mainThread;
    //ApplicationContext
    private static Context mBaseContext;
    private static Map<String,Object> mPreLoadMap;

    @Override
    public void onCreate() {
        super.onCreate();
        initObj();
    }

    //赋值
    private void initObj() {
        mBaseContext = getApplicationContext();
        mHandler = new Handler();
        mainThreadId = android.os.Process.myTid();
        mainThread = Thread.currentThread();
    }

    public static Context getmBaseContext() {
        return mBaseContext;
    }

    public static Handler getHandler() {
        return mHandler;
    }

    public static int getMainThreadId() {
        return mainThreadId;
    }

    public static Thread getMainThread() {
        return mainThread;
    }

    public static Map<String, Object> getPreLoadMap() {
        if (null == mPreLoadMap){
            mPreLoadMap = new HashMap<>();
        }
        return mPreLoadMap;
    }

    public static void setPreLoadMap(Map<String, Object> mPreLoadMap) {
        ApiApplication.mPreLoadMap = mPreLoadMap;
    }
}
