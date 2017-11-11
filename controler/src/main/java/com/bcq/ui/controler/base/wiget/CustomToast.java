package com.bcq.ui.controler.base.wiget;

import android.view.Gravity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.bcq.ui.controler.R;
import com.bcq.ui.controler.base.api.ApiUtils;
import com.bcq.ui.controler.base.utiles.DeviceUtil;
import com.bcq.ui.controler.base.utiles.ResourceUtil;


/**
 * @author: BaiCQ
 * @ClassName: ToastUtil
 * @Description: 显示通知的工具类
 */
public class CustomToast extends Toast {

    private static CustomToast instance;
    private static boolean isHind = false;
    private TextView showText;

    public static CustomToast getInstance() {
        if (instance == null) {
            synchronized (CustomToast.class) {
                if (instance == null) {
                    instance = new CustomToast();
                }
            }
        }
        return instance;
    }

    public CustomToast(){
        super(ApiUtils.getContext());
        init(this);
    }

    private void init(Toast toast) {
        View tipsView = ApiUtils.inflate(R.layout.toast_mofw);
        showText = ApiUtils.getView(tipsView, R.id.toast_content);
        int distance = DeviceUtil.getDeviceSize().y * 35 / 100;
        toast.setGravity(Gravity.NO_GRAVITY, 0, distance);
        toast.setView(tipsView);
    }

    public CustomToast makeText(int resouceId) {
        String tosstMsg = "";
        if (resouceId >0){
            tosstMsg = ResourceUtil.getString(resouceId);
        }
        return makeText(tosstMsg);
    }

    public CustomToast makeText(String showMsg) {
        if (null !=showText){
            showText.setText(showMsg);
        }
        return this;
    }

    public CustomToast setDurations(int duration) {
        this.setDuration(duration);
        return this;
    }

    /**
     * 显示通知
     * @param message 显示的字符串
     * @param length  显示时间
     */
    public static void show(final String message, final int length) {
        getInstance().makeText(message).setDurations(length).show();
    }
    public static void show(final int resouceId, final int length) {
        getInstance().makeText(resouceId).setDurations(length).show();
    }

    @Override
    public void cancel() {
        super.cancel();
        isHind = true;
    }

    @Override
    public void show() {
        super.show();
        isHind = false;
    }
}
