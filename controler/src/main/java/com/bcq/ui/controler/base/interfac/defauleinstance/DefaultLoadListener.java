package com.bcq.ui.controler.base.interfac.defauleinstance;

import com.bcq.ui.controler.base.interfac.IRefreshUI;
import com.bcq.ui.controler.base.interfac.OnLoadListener;

/**
 * @author: BaiCQ
 * @ClassName: DefaultRefreshListener
 * @Description: OnLoadListener
 */
public class DefaultLoadListener implements OnLoadListener {
    private IRefreshUI iRefreshUI;

    public DefaultLoadListener(IRefreshUI iRefreshUI) {
        this.iRefreshUI = iRefreshUI;
    }

    public void onLoad(){
        iRefreshUI.getNetData(false,false);
    }
}
