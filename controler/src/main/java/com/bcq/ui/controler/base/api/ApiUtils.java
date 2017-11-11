package com.bcq.ui.controler.base.api;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.view.View;

import com.bcq.ui.controler.base.BaseActivity;
import com.bcq.ui.controler.base.utiles.LogUtil;

import java.io.Serializable;

/**
 * @author: BaiCQ
 * @ClassName: ApiUtils
 * @Description: 封装常用api的工具类
 */
public class ApiUtils {
    public static Context getContext() {
        return ApiApplication.getmBaseContext();
    }

    public static Thread getMainThread() {
        return ApiApplication.getMainThread();
    }

    public static int getMainThreadId() {
        return ApiApplication.getMainThreadId();
    }

    public static Handler getHandler() {
        return ApiApplication.getHandler();
    }

    public static View inflate(int layoutId) {
        return View.inflate(getContext(), layoutId, null);
    }

    /**
     * 获取view的方法 避免强转
     *
     * @param parent 父控件
     * @param viewId 获取子空间的ID
     * @param <T>    类型
     * @return
     */
    public static <T extends View> T getView(View parent, int viewId) {
        return (T) parent.findViewById(viewId);
    }

    /**
     * 获取view 并设置可见
     */
    public static <T extends View> T getVisiableView(View parent, int viewId) {
        T resultView = getView(parent, viewId);
        resultView.setVisibility(View.VISIBLE);
        return resultView;
    }

    /**
     * 获取view的方法 避免强转
     *
     * @param layoutId 获取子空间的ID
     * @param <T>      类型
     * @return
     */
    public static <T extends View> T getView(Activity activity, int layoutId) {
        return (T) activity.findViewById(layoutId);
    }

    /**
     * 获取开启activity的意图
     * @param context
     * @param obj 传递的对象
     * @param tclass 目标字节码
     * @return
     */
    public static <T extends Serializable,R extends BaseActivity> Intent getIntent(Context context, T obj, Class<R> tclass) {
        String key = Constant.KEY_OBJ;
        if (obj instanceof String){
            key = Constant.KEY_BASIC;
        }
        LogUtil.e("getIntent","key = "+key);
        Intent intent = new Intent(context, tclass);
        intent.putExtra(key, obj);
        return intent;
    }

    public static String getStringParam(Activity activity) {
        return activity.getIntent().getStringExtra(Constant.KEY_BASIC);
    }
    public static <T extends Serializable> T getObjParam(Activity activity) {
        return (T) activity.getIntent().getSerializableExtra(Constant.KEY_OBJ);
    }

    /**
     * 带一个参数 跳转界面
     * @param activity
     * @param transParams
     * @param clazz
     * @param <R>
     * @param <T>
     */
    public static <R extends Serializable,T extends BaseActivity > void startTargetActivity(Activity activity, R transParams, Class<T> clazz) {
        activity.startActivity(getIntent(activity,transParams,clazz));
    }

    public static <T extends BaseActivity > void startTargetActivity(Activity activity, int requestCode, Class<T> clazz) {
        activity.startActivityForResult(new Intent(activity, clazz), requestCode);
    }

}
