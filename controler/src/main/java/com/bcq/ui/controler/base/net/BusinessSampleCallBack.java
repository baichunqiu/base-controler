package com.bcq.ui.controler.base.net;

import android.text.TextUtils;

import com.bcq.ui.controler.base.net.callback.ICallback;
import com.bcq.ui.controler.base.utiles.LogUtil;
import com.bcq.ui.controler.base.wiget.LoadDialog;
import com.zhy.http.okhttp.callback.Callback;

import org.json.JSONObject;

import okhttp3.Call;
import okhttp3.Response;

/**
 * @author: BaiCQ
 * @ClassName: BusinessSampleCallBack
 * @Description: 业务处理 没有body的回调
 */
public class BusinessSampleCallBack <T> extends Callback<Integer> {
        private final static String TAG = "BusinessSampleCallBack";
        private ICallback<T> iCallback;
        private T tag;
        private int status = 0;
        private String sysMsg = "";
        private int pageIndex = -1;

        public BusinessSampleCallBack(T tag,ICallback<T> iCallback){
            this.iCallback = iCallback;
            this.tag = tag;
        }

        @Override
        public Integer parseNetworkResponse(Response response, int id) throws Exception {
            // 请求提示语
            String result = response.body().string();
            if (!TextUtils.isEmpty(result)) {
                JSONObject resulObject = new JSONObject(result);
                //header
                JSONObject headerJObj = resulObject.optJSONObject(NetUtil._header);
                status = headerJObj.optInt(NetUtil._statue);
                sysMsg = headerJObj.optString(NetUtil._sysMssage);
            }
            return status;
        }

        @Override
        public void onResponse(Integer statue, int id) {
            if (0 <= statue) {
                iCallback.onSuccess(tag, statue, pageIndex);
            } else {
                iCallback.onError(tag, statue, sysMsg);
            }
        }

        @Override
        public void onError(Call call, Exception e, int id) {
            if (!TextUtils.isEmpty(sysMsg)) {
                LogUtil.e(TAG, sysMsg);
            }
            iCallback.onError(tag, status, e.getMessage());
        }

        @Override
        public void onAfter(int id) {
            if (null != tag && tag instanceof LoadDialog) {
                ((LoadDialog) tag).dismiss();
            }
        }
}
