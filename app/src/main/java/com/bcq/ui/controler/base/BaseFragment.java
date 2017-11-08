package com.bcq.ui.controler.base;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.bcq.ui.controler.base.interfac.IRefreshUI;
import com.bcq.ui.controler.base.utiles.LogUtil;

import java.util.HashMap;
import java.util.Map;


public abstract class BaseFragment extends Fragment implements IRefreshUI {
    private final static String tag = "BaseFragment";
    public BaseActivity mActivity;
    public Map<String, String> params = new HashMap<>();
    private View rootView;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mActivity = (BaseActivity) context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(setLayoutId(), null);
        initView(rootView);
        return rootView;
    }

    /**
     * 布局的资源ID
     *
     * @return
     */
    public abstract int setLayoutId();

    /**
     * 初始化view
     * @param parent fragment的根布局
     */
    public abstract void initView(View parent);

    /**
     * 刷新UI回调接口
     *
     * @param obj
     */
    @Override
    public void onRefresh(Object obj) {
        LogUtil.e(tag, "onRefresh");
    }

    /**
     * 网络变化回调
     *
     * @param netType 网络类型
     */
    @Override
    public void onRefreshByNet(String netType) {
    }

    @Override
    public void getNetData(boolean showDialog, boolean isRefresh) {
    }
}
