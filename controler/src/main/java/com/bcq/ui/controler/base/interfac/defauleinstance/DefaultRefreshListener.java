package com.bcq.ui.controler.base.interfac.defauleinstance;


import com.bcq.ui.controler.base.interfac.IRefreshUI;
import com.bcq.ui.controler.base.interfac.OnRefreshListener;

/**
 * @author: BaiCQ
 * @ClassName: DefaultRefreshListener
 * @Description: OnRefreshListener默认实现类
 */
public class DefaultRefreshListener implements OnRefreshListener {
    private IRefreshUI iRefreshUI;

    public DefaultRefreshListener(IRefreshUI iRefreshUI) {
        this.iRefreshUI = iRefreshUI;
    }

    public void onRefresh(){
        iRefreshUI.getNetData(false,true);
    }
}
