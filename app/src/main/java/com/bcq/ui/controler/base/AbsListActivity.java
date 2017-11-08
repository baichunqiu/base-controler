
package com.bcq.ui.controler.base;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;

import com.bcq.ui.controler.R;
import com.bcq.ui.controler.base.adapter.AbsBaseAdapter;
import com.bcq.ui.controler.base.api.ApiUtils;
import com.bcq.ui.controler.base.controller.BaseUIController;
import com.bcq.ui.controler.base.controller.UIController;
import com.bcq.ui.controler.base.utiles.ResourceUtil;
import com.bcq.ui.controler.base.wiget.LoadDialog;

import java.util.List;
import java.util.Map;

/**
 * @author: BaiCQ
 * @createTime: 2017/1/13 11:38
 * @className: AbsListActivity
 * @Description: 注意：若用户的内容contentView 里面已经定义了showData控件 则使用该控件 并将noData 和loadFail的控件同时移动到showData控件的父控件中
 * 默认的 showData就是 ll_contain
 */
public abstract class AbsListActivity<T> extends BaseActivity {
    private final static String Tag = "AbsListActivity";
    private View view_title_shape;
    private UIController<T> _mController;

    /**
     * 设置先显示nodata 还是显示nomessage
     * @param dataType
     */
    public void setDataType(String dataType) {
        if (null == _mController) return;
        _mController.setDataType(dataType);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_abs_list);
        _mController = new BaseUIController<T>(mActivity, ApiUtils.getView(mActivity, R.id.pll_parent), this);
        _mController._init();

    }

    @Override
    protected void onDestroy() {
        _mController = null;
        super.onDestroy();
    }

    /**
     * 重新获取数据
     *
     * @param isWait    是否显示进度条
     * @param isRefresh 是否刷新
     */
    public void getNetData(boolean isWait, boolean isRefresh) {
        if (null == _mController) return;
        _mController.getNetData(isWait, isRefresh);
    }

    public void getNetData(boolean isWait, boolean isRefresh, String apiType, Map<String, String> params, Class<T> tClass) {
        getNetData(isWait, isRefresh, apiType, params, tClass, "");
    }

    /**
     * @param isShow     是否显示进度调
     * @param isRefresh  是否刷新
     * @param mUrl       mUrl
     * @param params     参数 注意：不包含page
     * @param tClass     解析的实体
     * @param mDialogMsg 进度条显示信息
     */
    private void getNetData(final boolean isShow, final boolean isRefresh, final String mUrl, final Map<String, String> params, Class<T> tClass, final String mDialogMsg) {
        if (null == _mController) return;
        String dialogMsg = mDialogMsg;
        //加载进度条
        LoadDialog loadDialog = null;
        if (isShow) {
            if (TextUtils.isEmpty(mDialogMsg)) {
                dialogMsg = ResourceUtil.getString(R.string.str_loading);
            }
            loadDialog = new LoadDialog(mActivity, dialogMsg);
        }
        _mController._getNetData(isRefresh, mUrl, params, tClass, loadDialog);
    }

    /**
     * 接口解析数据后子线程预处理数据
     * @param netData
     * @return
     */
    public List<T> preHandleData(List<T> netData) {
        return netData;
    }

    /**
     * 设置适配器数据回调
     * @param netData   接口放回数据
     * @param isRefresh 是否刷新
     */
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
    public abstract View initContentView();

    /**
     * 初始化view
     * @param parent
     */
    public abstract void initView(View parent);

    /**
     * 设置适配器
     * @return
     */
    public abstract AbsBaseAdapter setAdapter();
}