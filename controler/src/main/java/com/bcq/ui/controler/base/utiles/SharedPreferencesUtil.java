package com.bcq.ui.controler.base.utiles;

import android.content.Context;
import android.content.SharedPreferences;

import com.bcq.ui.controler.base.api.ApiUtils;
import com.bcq.ui.controler.base.api.Constant;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;

/**
 * @author: BaiCQ
 * @ClassName: SharedPreferencesUtil
 * @Description: SharedPreferences 工具类
 */
public class SharedPreferencesUtil {
    /**
     * SharedPreferences兼容类
     */
    private static class SharedPreferencesCompat {
        private static final Method sApplyMethod = findApplyMethod();

        /**
         * 反射查找apply的方法
         * @return
         */
        private static Method findApplyMethod() {
            try {
                Class clz = SharedPreferences.Editor.class;
                return clz.getMethod("apply");
            } catch (NoSuchMethodException e) {
            }
            return null;
        }

        /**
         * 如果找到则使用apply执行，否则使用commit
         * @param editor
         */
        public static void apply(SharedPreferences.Editor editor) {
            try {
                if (sApplyMethod != null) {
                    sApplyMethod.invoke(editor);
                    return;
                }
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
            editor.commit();
        }
    }

    /**
     * 保存键值对
     * @param key      key
     * @param value    value
     */
    public static void set(String key, String value) {
        set(Constant.SHARE_PREFERENCE_FILE_NAME,key,value);
    }

    /**
     * 保存键值对
     * @param key      key
     * @param value    value
     */
    public static void set(String key, boolean value) {
       set(Constant.SHARE_PREFERENCE_FILE_NAME,key,value);
    }

    /**
     * 保存键值对
     * @param fileName 文件名
     * @param key      key
     * @param value    value
     */
    public static void set(String fileName, String key, String value) {
        SharedPreferences sharedPreferences = ApiUtils.getContext().getSharedPreferences(fileName, Context.MODE_MULTI_PROCESS);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key, value);
        SharedPreferencesCompat.apply(editor);
    }

    /**
     * 获取键对应的值
     * @param fileName     文件名
     * @param key          key
     * @param defaultValue 默认值，无对应value时返回
     * @return value
     */
    public static String get(String fileName, String key, String defaultValue) {
        return ApiUtils.getContext().getSharedPreferences(fileName, Context.MODE_MULTI_PROCESS).getString(key, defaultValue);
    }

    /**
     * 保存键值对
     * @param fileName 文件名
     * @param key      key
     * @param value    value
     */
    public static void set(String fileName, String key, boolean value) {
        SharedPreferences sharedPreferences = ApiUtils.getContext().getSharedPreferences(fileName, Context.MODE_MULTI_PROCESS);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(key, value);
        SharedPreferencesCompat.apply(editor);
    }

    /**
     * 获取键对应的值
     * @param fileName     文件名
     * @param key          key
     * @param defaultValue 默认值，无对应value时返回
     * @return value
     */
    public static boolean get(String fileName, String key, boolean defaultValue) {
        SharedPreferences sharedPreferences = ApiUtils.getContext().getSharedPreferences(
                fileName, Context.MODE_MULTI_PROCESS);
        boolean value = sharedPreferences.getBoolean(key, defaultValue);// 第二个参数为默认值
        return value;
    }

    /**
     * 获取键对应的值，找不到则返回""
     * @param fileName 文件名
     * @param key      key
     * @return value
     */
    public static String get(String fileName, String key) {
        return get(fileName, key, "");
    }

    /**
     * 移除key对应的项
     * @param fileName 文件名
     * @param key      key
     */
    public static void remove(String fileName, String key) {
        SharedPreferences sharedPreferences = ApiUtils.getContext().getSharedPreferences(fileName, Context.MODE_MULTI_PROCESS);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove(key);
        SharedPreferencesCompat.apply(editor);
    }

    /**
     * 清除所有数据
     * @param fileName 文件名
     */
    public static void clear(String fileName) {
        SharedPreferences sharedPreferences = ApiUtils.getContext().getSharedPreferences(fileName, Context.MODE_MULTI_PROCESS);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        SharedPreferencesCompat.apply(editor);
    }

    /**
     * 查询某个key对应的项是否存在
     *
     * @param fileName 文件名
     * @param key      key
     * @return 是否存在
     */
    public static boolean contatins(String fileName, String key) {
        return ApiUtils.getContext().getSharedPreferences(fileName, Context.MODE_MULTI_PROCESS).contains(key);
    }

    /**
     * 返回所有键值对
     * @param fileName 文件名
     * @return Map组成的键值对
     */
    public static Map<String, ?> getAll(String fileName) {
        return ApiUtils.getContext().getSharedPreferences(fileName, Context.MODE_MULTI_PROCESS).getAll();
    }

}
