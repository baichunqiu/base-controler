package com.bcq.ui.controler.base.net;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.SystemClock;
import android.util.Log;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.bcq.ui.controler.R;
import com.bcq.ui.controler.base.api.Constant;
import com.bcq.ui.controler.base.net.callback.ICallback;
import com.bcq.ui.controler.base.net.callback.IListCallback;
import com.bcq.ui.controler.base.utiles.LogUtil;
import com.bcq.ui.controler.base.utiles.NetWorkUtil;
import com.bcq.ui.controler.base.utiles.ResourceUtil;
import com.bcq.ui.controler.base.utiles.ToastUtil;
import com.bcq.ui.controler.base.wiget.LoadDialog;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.builder.GetBuilder;
import com.zhy.http.okhttp.builder.PostFormBuilder;
import com.zhy.http.okhttp.callback.BitmapCallback;
import com.zhy.http.okhttp.callback.FileCallBack;

import java.io.File;
import java.util.Map;

import okhttp3.Call;

/**
 * @author: BaiCQ
 * @ClassName: OkUtil
 * @Description: OkHttp网络请求工具类
 */
public class NetUtil {
    public final static String TAG = "NetUtil";
    public final static String _header = "header";
    public final static String _statue = "status";
    public final static String _sysMssage = "sysMessage";

    public final static String _body = "body";
    public final static String _page = "page";
    public final static String _pageIndex = "index";
    public final static String _pageTotal = "totalPage";
    public final static String _endPage = "endPage";
    //区分请求来源
    public final static String _sysType = "origin";
    public final static String _sysAndroid = "mofangworld-Android";

    public final static String _unKnow_error = "未知错误!";

    /**
     * init getBuider
     *
     * @return
     */
    private static <T> GetBuilder initGetBuider(T tag, String url, Map<String, String> params) {
        int size = params.size();
        if (size > 0) {
            for (Map.Entry<String, String> entry : params.entrySet()) {
                Log.e(TAG, "initGetBuider: " + entry.getKey() + " = " +entry.getValue());
            }
        }
        return OkHttpUtils.get()
                .url(url)
                .tag(tag)
                .addHeader(_sysType, _sysAndroid)
                .params(params);
    }

    /**
     * init PostFormBuilder
     *
     * @return
     */
    private static <T> PostFormBuilder initPostBuilder(T tag, String url, Map<String, String> params) {
        int size = params.size();
        if (size > 0) {
            for (Map.Entry<String, String> entry : params.entrySet()) {
                Log.e(TAG, "initPostBuilder: " + entry.getKey() + " == " + entry.getValue());
            }
        }
        return OkHttpUtils.post()
                .url(url)
                .tag(tag)
                .addHeader(_sysType, _sysAndroid)
                .params(params);
    }

    /**
     * 检查网 并根据tag的类型 取消加载动画
     * @param tag 请求的TAG 可以根据tag 取消请求
     * @param <T> tag类型
     * @return
     */
    public static <T> boolean checkNet(T tag) {
        if (!NetWorkUtil.isNetworkConnected()) {
            ToastUtil.show(R.string.str_check_network);
            if (tag instanceof LoadDialog){
                ((LoadDialog) tag).dismiss();
            }
            return true;
        }
        return false;
    }

    public static <T, R> void postArr(String url, Map<String, String> params, final Class<R> tClazz, final IListCallback<T, R> iListCallback) {
        postArr(null, url, params, tClazz, iListCallback);
    }

    public static <T> void post(String url, Map<String, String> params, final ICallback<T> iCallback) {
        post(null, url, params, iCallback);
    }


    /**
     * @param tag          请求的tag
     * @param url          url
     * @param params       Map<String,String>
     * @param tClazz       解析字段的class
     * @param iListCallback 有body的网络回调
     * @param <T>          body实体的泛型
     * @param <R>          tag的泛型
     */
    public static <T, R> void postArr(final T tag, String url, Map<String, String> params, final Class<R> tClazz, final IListCallback<T, R> iListCallback) {
        if (checkNet((T) tag)){
            iListCallback.onAfter(tag,0);
            return;
        }
        initPostBuilder(tag, url, params)
                .build()
                .execute(new BussinessListCallBack(tag,tClazz, iListCallback, SystemClock.currentThreadTimeMillis()));
    }


    /**
     * @param tag          请求的tag
     * @param url          url
     * @param params       Map<String,String>
     * @param tClazz       解析字段的class
     * @param iListCallback 有body的网络回调
     * @param <T>          body实体的泛型
     * @param <R>          tag的泛型
     */
    public static <T, R> void getArr(final T tag, String url, Map<String, String> params, final Class<R> tClazz, final IListCallback<T, R> iListCallback) {
        if (checkNet(tag)){
            iListCallback.onAfter(tag,0);
            return;
        }
        initGetBuider(tag, url, params)
                .build()
                .execute(new BussinessListCallBack(tag,tClazz, iListCallback, SystemClock.currentThreadTimeMillis()));
    }

    /**
     * @param tag       请求的tag
     * @param url       url
     * @param params    Map<String,String>
     * @param iCallback 无body的网络回调
     * @param <T>       tag的泛型
     */
    public static <T> void post(final T tag, String url, Map<String, String> params, final ICallback<T> iCallback) {
        if (checkNet(tag)){
            return;
        }
        initPostBuilder((T) tag, url, params)
                .build()
                .execute(new BusinessSampleCallBack(tag,iCallback));
    }

    /**
     * okhttp 直接显示image 没有缓存
     * @param url
     * @param params
     * @param imageView
     */
    public static void display(final String url, Map<String, String> params, final ImageView imageView) {
        initGetBuider(null, url, params)
                .build()
                .execute(new BitmapCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        LogUtil.e("display", "e.getMessage = " + e.getMessage());
                    }

                    @Override
                    public void onResponse(Bitmap bitmap, int id) {
                        imageView.setImageBitmap(bitmap);
                    }

                });
    }

    /**
     * 下载文件
     * @param tag
     * @param url
     * @param params
     * @param fileName 文件名称 存放在downLoad 目录下
     * @param <T>
     */
    public static <T> void downLoad(final T tag, String url, Map<String, String> params, String fileName, final ProgressBar mProgressBar) {
        initGetBuider(tag, url, params)
                .build()
                .execute(new FileCallBack(Constant.CACHE_DOWNLOAD_PATH, fileName) {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        LogUtil.e("downLoad", "e.getMessage = " + e.getMessage());
                    }

                    @Override
                    public void onResponse(File response, int id) {
                    }

                    @Override
                    public void inProgress(float progress, long total, int id) {
                        super.inProgress(progress, total, id);
                        mProgressBar.setProgress((int) (100 * progress));
                    }

                });
    }

    /**
     * 根据tag取消请求WE
     *
     * @param ctx tag标记
     */
    public static void cancel(Context ctx) {
        OkHttpUtils.getInstance().cancelTag(ctx);
    }
}
