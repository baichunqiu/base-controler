package com.bcq.ui.controler.base.api;

import com.bcq.ui.controler.base.utiles.StorageUtil;

import java.io.File;

/**
 * @author: BaiCQ
 * @ClassName:
 * @Description: 通用常量参数值
 */
public class Constant {
    //根目录
    public static final String LOCAL_PATH = StorageUtil.getExternalStoragePath();

    // 退出应用广播action
    public static final String ACTION_EXIT_APP_RECEIVER = ".ExitListenerReceiver";
    //默认sharePreference 文件名
    public static final String SHARE_PREFERENCE_FILE_NAME = "sp_normal";
    //传递 基本数据和String类型的的key
    public static final String KEY_BASIC = "key_basic";
    //传递 URL
    public static final String KEY_URL = "key_url";
    public static final String KEY_OBJ = "key_obj";

    public static final long LOAD_TIME = 800;
    //缓存download目录
    public static final String DOWNLOAD = "download";
    public static final String CACHE_DOWNLOAD_PATH = LOCAL_PATH + File.separator + DOWNLOAD;

    /*showViewType*/
    //没有数据
    public static int NO_DATA = 0;
    //加载失败
    public static int LOAD_FAIL = -1;
    //显示数据
    public static int SHOW_DATA = 1;

    //分页的页码
    public static String pageIndex = "pageIndex";
    //每页记录数
    public static String pageSize = "pageSize";

    public static class UIDataType{
        public final static String MSG_TYPE = "no_msg";
        //数据类型
        public final static String DATA_TYPE = "no_data";
    }
}