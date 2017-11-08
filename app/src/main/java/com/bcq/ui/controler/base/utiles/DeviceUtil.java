package com.bcq.ui.controler.base.utiles;

import android.app.Activity;
import android.content.Context;
import android.graphics.Point;
import android.util.DisplayMetrics;
import android.view.WindowManager;

import com.bcq.ui.controler.base.api.ApiUtils;


/**
 * @author: BaiCQ
 * @ClassName: DeviceUtil
 * @Description: 屏幕相关的工具类
 */
public class DeviceUtil {
    private static Point deviceSize = null;

    /**
     * 获取设备大小
     * @param activity
     * @return
     */
    public static Point getDeviceSize(Activity activity) {
        if (deviceSize == null) {
            deviceSize = new Point(0, 0);
        }
        DisplayMetrics metric = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(metric);
        deviceSize.x = metric.widthPixels;
        deviceSize.y = metric.heightPixels;
        return deviceSize;
    }

    public static Point getDeviceSize() {
        if (deviceSize == null) {
            deviceSize = new Point(0, 0);
        }
        DisplayMetrics metric = new DisplayMetrics();
        WindowManager wm = (WindowManager) ApiUtils.getContext().getSystemService(Context.WINDOW_SERVICE);
        wm.getDefaultDisplay().getMetrics(metric);
        deviceSize.x = metric.widthPixels;
        deviceSize.y = metric.heightPixels;
        return deviceSize;
    }
}
