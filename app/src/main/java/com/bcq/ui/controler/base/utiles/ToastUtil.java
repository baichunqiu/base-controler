package com.bcq.ui.controler.base.utiles;

import android.widget.Toast;

import com.bcq.ui.controler.base.wiget.CustomToast;


/**
 * @author: BaiCQ
 * @ClassName: ToastUtil
 * @Description: 显示通知的工具类
 */
public class ToastUtil {

    public static void show(String msg, int duration){
        CustomToast.show(msg,duration);
    }

    public static void show(String msg){
        CustomToast.show(msg, Toast.LENGTH_SHORT);
    }

    public static void show(int resouceId){
        CustomToast.show(resouceId, Toast.LENGTH_SHORT);
    }

    public static void showLong(String msg){
        CustomToast.show(msg, Toast.LENGTH_LONG);
    }

    public static void showLong(int resouceId){
        CustomToast.show(resouceId, Toast.LENGTH_LONG);
    }
    public static void cancle(){
        CustomToast.getInstance().cancel();
    }
}
