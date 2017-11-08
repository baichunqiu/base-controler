package com.bcq.ui.controler.base.net;

import android.os.SystemClock;
import android.text.TextUtils;

import com.bcq.ui.controler.base.api.Constant;
import com.bcq.ui.controler.base.net.callback.IListCallback;
import com.bcq.ui.controler.base.utiles.GsonUtil;
import com.bcq.ui.controler.base.utiles.LogUtil;
import com.bcq.ui.controler.base.wiget.LoadDialog;
import com.zhy.http.okhttp.callback.Callback;

import org.json.JSONObject;

import java.util.List;

import okhttp3.Call;
import okhttp3.Response;

/**
 * @author: BaiCQ
 * @ClassName: BussinessListCallBack
 * @Description: 业务处理回调
 */
public class BussinessListCallBack<T, R> extends Callback<List<R>> {
    private final static String TAG = "BussinessListCallBack";
    private IListCallback<T, R> iListCallback;
    private long startTime;
    private T tag;
    private Class<R> rClass;


    public BussinessListCallBack(T tag, Class<R> rClass, IListCallback<T, R> iListCallback, long startTime) {
        this.iListCallback = iListCallback;
        this.startTime = startTime;
        this.tag = tag;
        this.rClass = rClass;
    }

    private int status = 0;
    private String sysMsg = "";
    private JSONObject pageJsonObj;
    private int pageIndex = 0;
    private int pageTotal = 0;

    @Override
    public List<R> parseNetworkResponse(Response response, int id) throws Exception {
        // 请求提示语
        String result = response.body().string();
        List<R> resultData = null;
        if (!TextUtils.isEmpty(result)) {
            LogUtil.i("result = " + result);
            JSONObject resulObject = new JSONObject(result);
            //header
            JSONObject headerJObj = resulObject.optJSONObject(NetUtil._header);
            status = headerJObj.optInt(NetUtil._statue);
            sysMsg = headerJObj.optString(NetUtil._sysMssage);
            //body 原始数据解析
            resultData = GsonUtil.json2List(resulObject.optString(NetUtil._body), rClass);
            //预处理数据
            resultData = iListCallback.onPreHanleData(resultData);
            //page
            pageJsonObj = resulObject.optJSONObject(NetUtil._page);
            if (null != pageJsonObj) {
                pageIndex = pageJsonObj.optInt(NetUtil._pageIndex);
                pageTotal = pageJsonObj.optInt(NetUtil._pageTotal);
            }
        }
        return resultData;
    }

    @Override
    public void onResponse(List<R> response, int id) {
        if (null != response) {
            if (!response.isEmpty()) {
                iListCallback.onSuccess(tag, response, pageIndex, pageTotal);
            } else {
                iListCallback.onNoData(tag);
            }
        } else {
            iListCallback.onError(tag, status, NetUtil._unKnow_error);
        }
    }

    @Override
    public void onError(Call call, Exception e, int id) {
        if (!TextUtils.isEmpty(sysMsg)) {
            LogUtil.e(TAG, sysMsg);
        }
        iListCallback.onError(tag, status, e.getMessage());
    }

    @Override
    public void onAfter(int id) {
        long currentTime = SystemClock.currentThreadTimeMillis();
        if (null != tag && tag instanceof LoadDialog) {
            ((LoadDialog) tag).dismiss();
        }
        iListCallback.onAfter(tag, Constant.LOAD_TIME - (currentTime  -  startTime));
    }
}
