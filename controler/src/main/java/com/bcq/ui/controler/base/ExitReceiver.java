package com.bcq.ui.controler.base;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * @author: BaiCQ
 * @ClassName: ExitReceiver
 * @Description: exitApp的广播
 */
public class ExitReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if (context != null) {
            ((Activity) context).finish();
        }
    }
}
