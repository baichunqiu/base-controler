package com.bcq.ui.controler.base.utiles;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.bcq.ui.controler.base.api.ApiUtils;

/**
 * @author: BaiCQ
 * @ClassName: NetWorkUtil
 * @Description: 网络工具类
 */
public class NetWorkUtil {

    /**
     * 是否连接上网络
     * @return 连接上true，未连接上false
     * @date: 2017-1-16 下午17:52
     */
    public static boolean isNetworkConnected() {
        // 网络连接的状态
        boolean isConnected = false;
        ConnectivityManager connectivityManager = (ConnectivityManager) ApiUtils.getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager != null) {
            // 建立网络数组
            NetworkInfo[] net_info = connectivityManager.getAllNetworkInfo();
            if (net_info != null) {
                for (int i = 0; i < net_info.length; i++) {
                    // 判断获得的网络状态是否是处于连接状态
                    if (net_info[i].getState() == NetworkInfo.State.CONNECTED) {
                        isConnected = true;
                        break;
                    }
                }
            }
        }
        return isConnected;

    }

    /**
     * 判断当前网络是否为wifi状态
     * @return wifi状态true，非wifi状态false
     * @date: 2017-1-16 下午17:52
     */
    public static boolean isWifi() {
        // 判断是否为wifi的状态
        boolean isWifi = false;
        if (isNetworkConnected()) {
            ConnectivityManager connectivityManager = (ConnectivityManager) ApiUtils.getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo activeNetInfo = connectivityManager.getActiveNetworkInfo();

            if (activeNetInfo != null && activeNetInfo.getType() == ConnectivityManager.TYPE_WIFI) {

                isWifi = true;
            }
        }
        return isWifi;
    }

}
