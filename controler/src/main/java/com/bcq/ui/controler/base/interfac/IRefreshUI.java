package com.bcq.ui.controler.base.interfac;

/**
 * @author: BaiCQ
 * @ClassName: IRefreshUI
 * @Description: 刷新UI的接口
 */
public interface IRefreshUI {

    /**
     * 刷新UI回调接口
     * @param obj
     */
    void onRefresh(Object obj);

    /**
     * 网络变化回调接口
     * @param netType
     */
    void onRefreshByNet(String netType);

    void getNetData(boolean showDialog, boolean isRefresh);
}
