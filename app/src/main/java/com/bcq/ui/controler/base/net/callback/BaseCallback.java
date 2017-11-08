package com.bcq.ui.controler.base.net.callback;

import android.app.Activity;

import com.bcq.ui.controler.base.utiles.LogUtil;
import com.bcq.ui.controler.base.wiget.LoadDialog;

/**
 * @author: BaiCQ
 * @ClassName: ICallback
 * @Description: 没有body请求回调
 */
public class BaseCallback<T> implements ICallback<T> {
    private final static String mTag = "BaseCallback";
    private Activity mActivity;

    public BaseCallback() {

    }

    public BaseCallback(Activity mActivity) {
        this.mActivity = mActivity;
    }

    /**
     * @param tag       Tag
     * @param pageIndex 当前页码<分页使用>
     */
    public void onSuccess(T tag, int status, int pageIndex) {
        LogUtil.e(mTag, "Success:status = " + status);
        if (tag instanceof LoadDialog){
            ((LoadDialog) tag).dismiss();
        }
    }

    /**
     * @param tag    Tag
     * @param errMsg 错误信息
     */
    public void onError(T tag, int status, String errMsg) {
        LogUtil.e(mTag, "Error:status = " + status);
        LogUtil.e(mTag, "Error:errMsg = " + errMsg);
        if (tag instanceof LoadDialog){
            ((LoadDialog) tag).dismiss();
        }
    }

}
