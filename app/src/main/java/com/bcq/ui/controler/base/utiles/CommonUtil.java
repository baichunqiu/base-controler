package com.bcq.ui.controler.base.utiles;

import android.webkit.WebSettings;
import android.webkit.WebView;

import com.bcq.ui.controler.R;

/**
 * @author: BaiCQ
 * @ClassName:
 * @Description:
 */
public class CommonUtil {

    /**
     * 根据网络情况设置webview的缓存模式
     *
     * @param webView WebVeiew
     * @param isHttps 是否是https
     */
    public static void openWebViewCache(WebView webView, boolean isHttps) {
        WebSettings webSettings = webView.getSettings();
        webSettings.setUseWideViewPort(true);//设置此属性，可任意比例缩放
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setBuiltInZoomControls(true); // 设置显示缩放按钮
        webSettings.setSupportZoom(true); // 支持缩放
        //加载https 的不要设置
        webSettings.setJavaScriptEnabled(true);
        webSettings.setRenderPriority(WebSettings.RenderPriority.HIGH);
        //设置缓存模式
        if (!NetWorkUtil.isNetworkConnected()) {
            webSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK); //优先加载缓存
        } else {
            webSettings.setCacheMode(WebSettings.LOAD_DEFAULT); //默认
        }
        // 开启 DOM storage API 功能
        webSettings.setDomStorageEnabled(true);
        //开启 database storage API 功能
        webSettings.setDatabaseEnabled(true);
        //开启 ApplicationCaches 功能
        webSettings.setAppCacheEnabled(true);
    }

    /**
     * 将cookie同步到WebView
     *
     * @param url WebView要加载的url
     * @return true 同步cookie成功，false同步cookie失败
     * @Author JPH
     */
    public static void loadUrl(WebView webView, String url) {
        if (!NetWorkUtil.isNetworkConnected()) {
            ToastUtil.show(R.string.str_check_network);
            return;
        }
        webView.loadUrl(url);
    }


    private static long lastClickTime;

    /**
     * 判断是否快速双击 250毫秒未临界值
     *
     * @return
     */
    public static boolean isFastDoubleClick() {
        long time = System.currentTimeMillis();
        long timeD = time - lastClickTime;
        if (0 < timeD && timeD < 250) {
            return true;
        }
        lastClickTime = time;
        return false;
    }
}
