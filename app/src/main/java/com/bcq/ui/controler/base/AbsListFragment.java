package com.bcq.ui.controler.base;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;

import com.bcq.ui.controler.R;
import com.bcq.ui.controler.base.adapter.AbsBaseAdapter;
import com.bcq.ui.controler.base.controller.BaseUIController;
import com.bcq.ui.controler.base.controller.UIController;
import com.bcq.ui.controler.base.wiget.LoadDialog;

import java.util.List;
import java.util.Map;

/**
 * @author: BaiCQ
 * @createTime: 2017/1/13 11:38
 * @className: AbsListFragment
 * @Description:
 */
public abstract class AbsListFragment<T> extends BaseFragment {
    private final static String Tag = "AbsListFragment";
    private UIController<T> _mController;

    @Override
    public int setLayoutId() {
        return R.layout.frag_abs_list;
    }

    @Override
    public void initView(final View parent) {
        _mController = new BaseUIController<T>(mActivity,parent,this);
        _mController._init();
    }

    @Override
    public void getNetData(boolean showDialog, boolean isRefresh) {
        if (null == _mController) return;
        _mController.getNetData(showDialog, isRefresh);
    }

    public void getNetData(boolean isRefresh, String url, Map<String, String> params, Class<T> tClass, String dialogMsg) {
        if (null == _mController) return;
        LoadDialog loadDialog = null;
        if (!TextUtils.isEmpty(dialogMsg)) {
            loadDialog = new LoadDialog(mActivity, dialogMsg);
        }
        _mController._getNetData(isRefresh, url, params, tClass, loadDialog);
    }

    /**
     * 预处理数据
     * @param netData
     * @return
     */
    public List<T> preHandleData(List<T> netData) {
        return netData;
    }

    public void onRefreshData(List<T> netData, boolean isRefresh) {
        if (null == _mController) return;
        _mController._onRefreshData(netData,isRefresh);
    }

    /**
     * 适配器设置数据前 处理数据 有可能类型转换
     * @param netData
     * @return
     */
    public List preRefreshData(List<T> netData) {
        return netData;
    }

    public void showViewType(int type) {
        if (null == _mController) return;
            _mController._showViewType(type);
    }

    /**
     * init ContentView
     * @return
     */
    public abstract View initContentView(LayoutInflater inflater);

    /**
     * 初始化之类控件
     * @param parent
     * @return
     */
    public abstract void initChildView(View parent);

    /**
     * 设置适配器
     * @return
     */
    public abstract AbsBaseAdapter setAdapter();
}
