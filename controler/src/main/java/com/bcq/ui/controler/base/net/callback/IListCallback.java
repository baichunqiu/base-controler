package com.bcq.ui.controler.base.net.callback;

import java.util.List;

/**
 * @author: BaiCQ
 * @ClassName: IListCallback
 * @Description: 有body网络请求的回调
 */
public interface IListCallback<T,R>{

    List<R> onPreHanleData(List<R> netData);
    /**
     * @param tag Tag
     * @param netData 网络数据
     * @param pageIndex 当前页码<分页使用>
     * @param pageTotal 总页码<分页使用>
     */
     void onSuccess(T tag, List<R> netData, int pageIndex, int pageTotal);

    /**
     * @param tag Tag
     * @param errMsg 错误信息
     */
     void onError(T tag, int state, String errMsg);

     void onNoData(T tag);

    /**
     * @param tag
     * @param delayTime 延迟时间
     */
     void onAfter(T tag, long delayTime);
}
