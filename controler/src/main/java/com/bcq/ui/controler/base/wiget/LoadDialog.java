package com.bcq.ui.controler.base.wiget;

import android.app.Dialog;
import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bcq.ui.controler.R;
import com.bcq.ui.controler.base.utiles.ResourceUtil;

/**
 * @author: BaiCQ
 * @ClassName: LoadDialog
 * @Description: 标准等待进度条
 */
public class LoadDialog extends Dialog {
    private Dialog dialog;
    private View rootView;
    private TextView tv_info;
    private String dialogMsg;

    public LoadDialog(Context ctx) {
        this(ctx,"");
    }

    public LoadDialog(Context ctx, String dialogMsg) {
        super(ctx);
        dialog = new Dialog(ctx, R.style.CustomProgressDialog);
        rootView = LayoutInflater.from(ctx).inflate(R.layout.layout_load_dialog, null);
        tv_info = (TextView) rootView.findViewById(R.id.tv_info);
        if (TextUtils.isEmpty(dialogMsg)){
            dialogMsg = ResourceUtil.getString(R.string.str_loading);
        }
        this.dialogMsg = dialogMsg;
        tv_info.setText(dialogMsg);
        // 允许点返回键取消
        setCancelable(true);
        // 触碰其他地方不消失
        dialog.setCanceledOnTouchOutside(false);
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.addContentView(rootView, params);
        if (null != dialog){
            dialog.show();
        }
    }

    @Override
    public void dismiss() {
        try {
            if (dialog != null && dialog.isShowing()) {
                dialog.dismiss();
                dialog = null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getMsg(){
        return dialogMsg;
    }
}