package com.bcq.ui.controler.base.utiles;

import android.util.Log;

import com.bcq.ui.controler.base.api.ApiApplication;

/**
 * @author: BaiCQ
 * @ClassName: LogUtil
 * @Description: 日志输出文件
 */
public class LogUtil {
	// 其他日志TAG
	public static final String TAG = "yxck";
	//是否开启log
	public static final boolean bOpenLog = ApiApplication.bOpenLog;

	public static int d(String msg) {
		if (bOpenLog) {
			return Log.d(TAG, msg);
		} else {
			return 0;
		}
	}

	public static int d(String tag, String msg) {
		if (bOpenLog) {
			return Log.d(TAG, tag + ":" +msg);
		} else {
			return 0;
		}
	}
	
	public static int d(String tag, String msg, boolean mark) {
		if (bOpenLog) {
			if(mark){
			  return Log.d(tag,msg);
			}else{
			  return Log.d(TAG, tag + ":" +msg);
			}
		} else {
			return 0;
		}
	}

	public static int i(String tag, String msg) {
		if (bOpenLog) {
			return Log.i(TAG, tag + " ===== " +msg);
		} else {
			return 0;
		}
	}
	
	public static int i(String msg) {
		if (bOpenLog) {
			return Log.i(TAG, msg);
		} else {
			return 0;
		}
	}

	public static int w(String msg) {
		if (bOpenLog) {
			return Log.w(TAG, msg);
		} else {
			return 0;
		}
	}

	public static int e(String msg) {
		if (bOpenLog) {
		    int ret = Log.e(TAG, msg);
			return ret;
		} else {
			return 0;
		}
	}

	public static int e(Throwable e) {
		if (bOpenLog) {
		    int ret = Log.e(TAG, "", e);
			return ret;
		} else {
			return 0;
		}
	}

	public static int e(String tag, String msg) {
		if (bOpenLog) {
            int ret = Log.e(TAG, tag + ":" + msg);
			return ret;
		} else {
			return 0;
		}
	}

	public static int e(String msg, Throwable e) {
		if (bOpenLog) {
            int ret = Log.e(TAG, msg, e);
			return ret;
		} else {
			return 0;
		}
	}
}
