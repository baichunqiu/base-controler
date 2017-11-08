package com.bcq.ui.controler.base;

import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.bcq.ui.controler.base.api.Constant;
import com.bcq.ui.controler.base.interfac.IRefreshUI;
import com.bcq.ui.controler.base.utiles.CommonUtil;

import java.util.HashMap;
import java.util.Map;

public class BaseActivity extends FragmentActivity implements IRefreshUI {
    public BaseActivity mActivity;
    //参数集合
    public Map<String,String> params;
    // 退出应用的广播接受者
    private ExitReceiver exitReceiver;
    public View contentView;

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (null != exitReceiver) {
            unregisterReceiver(exitReceiver);
        }
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            if (CommonUtil.isFastDoubleClick()) {
                return true;
            }
        }
        return super.dispatchTouchEvent(ev);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity = this;
        params = new HashMap<>();
        regListener();
    }

    protected View getTitleView(){
        if (null == contentView) {
            contentView = findViewById(android.R.id.content);
        }
        return ((ViewGroup) contentView).getChildAt(0);
    }

    /**
     * 注册退出事件监听
     **/
    public void regListener() {
        exitReceiver = new ExitReceiver();
        IntentFilter intentfilter = new IntentFilter();
        intentfilter.addAction(this.getPackageName() + Constant.ACTION_EXIT_APP_RECEIVER);
        this.registerReceiver(exitReceiver, intentfilter);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // 事件被消费的标志
        boolean isEventConsumed = false;
        if ((keyCode == KeyEvent.KEYCODE_BACK)) {
            isEventConsumed = backCode();
        }
        return isEventConsumed? true : super.onKeyDown(keyCode, event);
    }
    /**
     * 点击物理返回键或者点击界面上返回键回调的方法
     * @RETURN
     */
    public boolean backCode() {
        finish();
        return false;
    }

    @Override
    public void getNetData(boolean showDialog, boolean isRefresh) {}

    /**
     * 刷新UI回调
     * @param pushMsg
     */
    @Override
    public void onRefresh(Object pushMsg) {}

    /**
     * 网络变化回调
     * @param netType 网络类型
     */
    @Override
    public void onRefreshByNet(String netType) {}

}
