package com.bcq.ui.controler.base.net.callback;


import com.bcq.ui.controler.base.api.ApiUtils;
import com.bcq.ui.controler.base.utiles.LogUtil;
import com.bcq.ui.controler.base.wiget.BaseListView;

import java.util.List;

/**
 * @author: BaiCQ
 * @ClassName: BaseListCallback
 * @Description: 有body网络请求的回调
 */
public class BaseListCallback<T,R> implements IListCallback<T,R> {
    private final static String Tag = "BaseListCallback";
    private BaseListView baseListView;

    public BaseListCallback(BaseListView baseListView){
        this.baseListView = baseListView;
    }

    @Override
    public List<R> onPreHanleData(List<R> netData){
        LogUtil.e(Tag,"onPreHanleData");
        return netData;
    }
    /**
     * @param tag Tag
     * @param netData 网络数据
     * @param pageIndex 当前页码<分页使用>
     */
    public void onSuccess(T tag, List<R> netData, int pageIndex, int pageTotal){
        LogUtil.e(Tag,"onSuccess");
        if (null != baseListView){
            baseListView.setLoadFull(pageIndex >= pageTotal);
        }
    }

    /**
     * @param tag Tag
     * @param errMsg 错误信息
     */
    public void onError(T tag, int status,String errMsg){
        LogUtil.e(Tag,"onError");
    }

    public void onNoData(T tag){}

    @Override
    public void onAfter(T tag,long delayTime) {
        LogUtil.e(Tag,"onAfter delayTime = "+delayTime);
        if (null != baseListView){
            if (delayTime > 0){
                ApiUtils.getHandler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        baseListView.onRefreshComplete();
                        baseListView.onLoadComplete();
                    }
                }, delayTime);
            }else {
                baseListView.onRefreshComplete();
                baseListView.onLoadComplete();
            }
        }
    }
}
