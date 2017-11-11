
package com.bcq.ui.controler.base;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;

import com.bcq.ui.controler.R;
import com.bcq.ui.controler.base.controller.NetController;
import com.bcq.ui.controler.base.wiget.LoadDialog;

import java.util.List;
import java.util.Map;

/**
 * @author: BaiCQ
 * @createTime: 2017/1/13 11:38
 * @className: AbsListActivity
 * @Description:
 */
public abstract class AbsActivity<T> extends BaseActivity {
    private final static String Tag = "AbsActivity";
    public LinearLayout Ll_content;
    private View contentView;
    private NetController<T> _mController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_abs);
        _initNetController();
        _init();
    }

    public void _initNetController(){
        _mController = new NetController<T>() {
            @Override
            public void _onResponceCallBack(String url, int state, String msg) {
                onResponceCallBack(url,state,msg);
            }

            @Override
            public void _bindData(List<T> mNetData) {
                if (null != mNetData && !mNetData.isEmpty()){
                    bindData(mNetData.get(0));
                }
            }
        };
    }

    public void _init() {
        //初始化内容视图
        contentView = initContentView();
        Ll_content = (LinearLayout)findViewById(R.id.Ll_content);
        Ll_content.addView(contentView, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        initView(contentView);
    }

    public void getNetData(final String mUrl, final Map<String, String> params, Class<T> tClass, final String mDialogMsg) {
        if (null == _mController){
            return;
        }
        _mController.postArr(mUrl,params,tClass,bulidDialog(mDialogMsg));
    }

    public void postOperate(final String mUrl, final Map<String, String> params, final String mDialogMsg) {
        if (null == _mController){
            return;
        }
        _mController.post(mUrl,params,bulidDialog(mDialogMsg));
    }

    public LoadDialog bulidDialog(String dialogMsg){
        LoadDialog build = null;
        if (!TextUtils.isEmpty(dialogMsg)) {
            build = new LoadDialog(mActivity, dialogMsg);
        }
        return build;
    }

    /**
     * 接口响应回调
     * @param url 接口连接
     * @param state 是否成功
     * @param msg 服务器返回msg
     */
    public void onResponceCallBack(String url, int state, String msg){
    }

    /**
     * 绑定数据
     * @param t 调接口获取数据后必执行此方法 故可能为null
     */
    public void bindData(T t) {
    }

    /**
     * init ContentView
     * @return
     */
    public abstract View initContentView();
    public abstract void initView(View parent);
}
