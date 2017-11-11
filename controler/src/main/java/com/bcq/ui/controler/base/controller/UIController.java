
package com.bcq.ui.controler.base.controller;

import android.app.Activity;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.bcq.ui.controler.R;
import com.bcq.ui.controler.base.adapter.AbsBaseAdapter;
import com.bcq.ui.controler.base.api.ApiUtils;
import com.bcq.ui.controler.base.api.Constant;
import com.bcq.ui.controler.base.interfac.IRefreshUI;
import com.bcq.ui.controler.base.interfac.defauleinstance.DefaultLoadListener;
import com.bcq.ui.controler.base.interfac.defauleinstance.DefaultRefreshListener;
import com.bcq.ui.controler.base.net.NetUtil;
import com.bcq.ui.controler.base.net.callback.BaseListCallback;
import com.bcq.ui.controler.base.utiles.ResourceUtil;
import com.bcq.ui.controler.base.wiget.BaseListView;
import com.bcq.ui.controler.base.wiget.LoadDialog;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author: BaiCQ
 * @createTime: 2017/1/13 11:38
 * @className: UIController
 * @Description:
 */
public abstract class UIController<T> implements IRefreshUI {
    private final static String Tag = "UIController";
    public Activity mActivity;
    //控制器的根节点
    private View _mContentView;
    //控制器寄主子类添加的contentView
    private View contentView;
    private View noData;
    private View loadFail;
    private View showData;
    public LinearLayout ll_contain;
    public ListView currentListView;
    private BaseListView baseRefreshListView;

    public int pageSize = 10;//每页显示的记录数据 默认10条
    private int index = 0;//当前页的索引
    private String mUrl = "";
    private String mDialogMsg = "";
    private Map<String, String> mParams;
    public Class<T> tClass;
    //适配器使用功能集合 泛型不能使用 T 接口返回类型有可能和适配器使用的不一致
    private List adapterList = new ArrayList<>();
    private AbsBaseAdapter mAdapter;
    private String dataType = Constant.UIDataType.DATA_TYPE;

    public UIController(Activity mActivity) {
        this(mActivity, Constant.UIDataType.DATA_TYPE);
    }

    public UIController(Activity mActivity, String dataType) {
        this.mActivity = mActivity;
        this.dataType = dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
        if (null == _mContentView) return;
        int viewId = R.id.no_data;
        if (Constant.UIDataType.DATA_TYPE.equals(dataType)){
            viewId = R.id.no_data;
        }else if (Constant.UIDataType.MSG_TYPE.equals(dataType)){
            viewId = R.id.no_msg;
        }
        noData = _mContentView.findViewById(viewId);
        if (null == noData) noData = new View(mActivity);
    }

    //init 手动调用
    public void _init(){
        _initBasic();
        _initChild();
        _initView(contentView);
    }

    //初始化寄主实体
    private void _initBasic() {
        _mContentView = _setLayoutParent();
        setDataType(dataType);
        loadFail = _mContentView.findViewById(R.id.load_fail);
        if (null == loadFail) loadFail = new View(mActivity);
        ll_contain = (LinearLayout) _mContentView.findViewById(R.id.ll_content);
    }

    //处理寄主子类添加contentView
    private void _initChild() {
        contentView = _initContentView(); //初始化内容视图
        currentListView = ApiUtils.getView(contentView, R.id.pullToRefreshView);
        showData = ApiUtils.getView(contentView, R.id.show_data);
        ll_contain.addView(contentView, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        if (null != showData) {
            //此处特殊处理 如果contentView 里面已经定义了showData控件 则使用该控件 并将noData 和loadFail的控件同时移动到showData控件的父控件中
            ViewGroup parent = (ViewGroup) showData.getParent();
            if (null != parent) {
                if (null != noData.getParent()) {
                    ((ViewGroup) (noData.getParent())).removeView(noData);
                }
                if (null != loadFail.getParent()) {
                    ((ViewGroup) (loadFail.getParent())).removeView(loadFail);
                }
                parent.addView(noData, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
                parent.addView(loadFail, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
            }
        } else {
            showData = ll_contain;
        }
        if (null != currentListView) {
            mAdapter = _setAdapter();
            baseRefreshListView = (BaseListView) currentListView;
            baseRefreshListView.setAdapter(mAdapter);
            baseRefreshListView.setOnRefreshListener(new DefaultRefreshListener(this));
            baseRefreshListView.setOnLoadListener(new DefaultLoadListener(this));
        }
        noData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getNetData(true, true);
            }
        });
        loadFail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getNetData(true, true);
            }
        });
    }

    /**
     * 重新获取数据
     * @param isWait    是否显示进度条
     * @param isRefresh 是否刷新
     */
    @Override
    public void getNetData(boolean isWait, boolean isRefresh) {
        if (TextUtils.isEmpty(mUrl)) {
            baseRefreshListView.onRefreshComplete();
            baseRefreshListView.onLoadComplete();
            return;
        }
        LoadDialog load = null;
        if (isWait) {
            if (TextUtils.isEmpty(mDialogMsg))
                mDialogMsg = ResourceUtil.getString(R.string.str_loading);
            load = new LoadDialog(mActivity, mDialogMsg);
        }
        _getNetData(isRefresh, mUrl, mParams, tClass,load);
    }

    private void cacheParam(String mUrl, Map<String, String> params, Class<T> tClass, LoadDialog dialog) {
        this.mUrl = mUrl;
        this.mParams = params;
        if (null != dialog){this.mDialogMsg = dialog.getMsg();}
        this.tClass = tClass;
    }

    /**
     * @param isRefresh 是否刷新
     * @param mUrl      mUrl
     * @param params    参数 注意：不包含page
     * @param tClass    解析的实体
     * @param dialog    进度调
     */
    public void _getNetData(final boolean isRefresh, final String mUrl, final Map<String, String> params, Class<T> tClass, LoadDialog dialog) {
        //缓存参数
        cacheParam(mUrl, params, tClass, dialog);
        if (isRefresh) {
            index = 1;
        }
        if (null != params) {
            params.put(Constant.pageIndex, index + "");
            //没有设置size 使用默认 一页10条
            if (!params.containsKey(Constant.pageSize)) {
                params.put(Constant.pageSize, pageSize + "");
            }
        }
        NetUtil.postArr(dialog, mUrl, params, tClass, new BaseListCallback<LoadDialog, T>(baseRefreshListView) {
                    @Override
                    public List<T> onPreHanleData(List<T> netData) {
                        return _preHandleData(netData);
                    }

                    @Override
                    public void onSuccess(LoadDialog tag, List<T> netData, int pageIndex, int pageTotal) {
                        super.onSuccess(tag, netData, pageIndex, pageTotal);
                        _onRefreshData(netData, isRefresh);
                        if (pageIndex >= pageTotal) {//最后一页
                            baseRefreshListView.setLoadFull(true);
                            index = pageIndex;
                        } else {
                            index = pageIndex + 1;
                            baseRefreshListView.setLoadFull(false);
                        }
                    }

                    @Override
                    public void onNoData(LoadDialog tag) {
                        super.onNoData(tag);
                        _showViewType(Constant.NO_DATA);
                        _onRefreshData(null, isRefresh);
                    }

                    @Override
                    public void onError(LoadDialog tag, int status, String errMsg) {
                        super.onError(tag, status, errMsg);
                        _showViewType(Constant.LOAD_FAIL);
                        _onRefreshData(null, isRefresh);
                    }
                }
        );
    }

    @Override
    public void onRefresh(Object obj) {}

    @Override
    public void onRefreshByNet(String netType) {}

    /**
     * 预处理数据
     * @param netData
     * @return
     */
    public List<T> _preHandleData(List<T> netData) {
        return netData;
    }

    /**
     * 设置数据前处理数据 此处不能使用泛型 特殊情况需要修改类型
     * @param netData
     * @return
     */
    public List _preRefreshData(List<T> netData) {
        return netData;
    }

    /**
     * 设置适配器数据回调
     * @param netData   接口放回数据
     * @param isRefresh 是否刷新
     */
    public final void _onRefreshData(List<T> netData, boolean isRefresh) {
        //设置适配器前  数据处理
        List preData = _preRefreshData(netData);
        if (isRefresh) {
            adapterList.clear();
        }
        if (null != preData) {
            adapterList.addAll(preData);
        }
        if (!adapterList.isEmpty()) {
            _showViewType(Constant.SHOW_DATA);
            mAdapter.setData(adapterList);
        } else {
            _showViewType(Constant.NO_DATA);
        }
    }

    public final void _showViewType(final int type) {
        noData.setVisibility(View.GONE);
        loadFail.setVisibility(View.GONE);
        showData.setVisibility(View.GONE);
        if (Constant.SHOW_DATA == type) {
            showData.setVisibility(View.VISIBLE);
        } else if (Constant.NO_DATA == type) {
            noData.setVisibility(View.VISIBLE);
        } else {
            loadFail.setVisibility(View.VISIBLE);
        }
    }

    //寄主添加视图根节点
    public abstract View _setLayoutParent();
    //寄主子类添加的ContentView
    public abstract View _initContentView();
    //寄主子类的contentView的findviewById
    public abstract void  _initView(View parent);
    //设置适配器
    public abstract AbsBaseAdapter _setAdapter();
}