package com.bcq.ui.controler.base.wiget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

import com.bcq.ui.controler.base.interfac.OnLoadListener;
import com.bcq.ui.controler.base.interfac.OnRefreshListener;


/**
 * @author: BaiCQ
 * @className:  BaseListView
 * @Description: 所有刷新listview的基类
 */
public class BaseListView extends ListView {

    public BaseListView(Context context) {
        super(context);
    }

    public BaseListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public BaseListView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    /**
     * 刷新结束
     */
    public void onRefreshComplete(){}

    /**
     * 刷新监听
     * @param onRefreshListener
     */
    public void setOnRefreshListener(OnRefreshListener onRefreshListener){

    }

    /*
     * load 结束
     */
    public void onLoadComplete(){}

    /**
     * load 监听
     * @param onRefreshListener
     */
    public void setOnLoadListener(OnLoadListener onRefreshListener){}

    /**
     * 设置loadFull
     * @param isLoadFull
     */
    public void setLoadFull(boolean isLoadFull) {}
}
