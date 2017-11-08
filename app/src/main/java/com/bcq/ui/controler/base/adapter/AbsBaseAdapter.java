package com.bcq.ui.controler.base.adapter;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import android.widget.BaseAdapter;


import com.bcq.ui.controler.base.net.NetUtil;
import com.bcq.ui.controler.base.wiget.LoadDialog;

import java.util.List;
import java.util.Map;

public abstract class AbsBaseAdapter<T> extends BaseAdapter {
	private final static String Tag = "AbsBaseAdapter";
	protected Context mContext;
	protected List<T> mDatas;
	protected LoadDialog wdg;

	public AbsBaseAdapter(Context context){
		this.mContext = context;
	}

	public List<T> getData() {
		return mDatas;
	}

	/**
	 * 设置数据
	 * @param list
     */
	public abstract void setData(List<T> list);


	/**
	 * 设置数据 和 检索内容
	 * @param list 数据集
	 * @param search 搜索内容
     */
	public void setData(List<T> list, String search){};

	/**
	 * 设置双集合数据
	 * @param list1
	 * @param list2
     */
	public void setData(List<T> list1, List list2){};

//	/**
//	 *
//	 */
//	public void refreshByPos(T t,int pos,BaseListView listview){};
//
//	/**
//	 * @param mUrl url
//	 * @param params 参数
//	 * @param mDialogMsg 进度条的信息 为空 择不显示
//	 */
//	public void postOperate(final String mUrl, final Map<String, String> params, final String mDialogMsg) {
//		//加载进度条
//		if (TextUtils.isEmpty(mDialogMsg)) {
//			wdg = new LoadDialog((Activity) mContext, mDialogMsg);
//		}
//		NetUtil.post(wdg, mUrl, params, new BaseCallback<LoadDialog>(null) {
//					@Override
//					public void onSuccess(LoadDialog tag, int status, int pageIndex) {
//						super.onSuccess(tag, status, pageIndex);
//						onResponceCallBack(mUrl,true);
//					}
//
//					@Override
//					public void onError(LoadDialog tag, int status, String errMsg) {
//						super.onError(tag, status, errMsg);
//						onResponceCallBack(mUrl,false);
//					}
//				}
//		);
//	}
//
//	/**
//	 * 接口响应回调
//	 * @param url 接口连接
//	 * @param success 是否成功
//	 */
//	public void onResponceCallBack(String url, boolean success){
//		LogUtil.d(Tag,"onResponceCallBack : "+success+" ----> "+url);
//	}

}
