package com.bcq.ui.controler.base.utiles;


import android.content.Context;

import java.io.IOException;
import java.io.InputStream;

/**
 * 描述: 字符串的工具类
 * 作者: wangls
 * 时间：2016/6/30
 */
public class StringUtils {

    //删除字符串前的0
    public static String dislodgeZero(String str) {
        int index = 0;
        char strs[] = str.toCharArray();
        int length = strs.length;
        for (int i = 0; i < length; i++) {
            if ('0' != strs[i]) {
                index = i;
                break;
            }
        }
        return str.substring(index, length);
    }

    public static String secret(String str, int startIndex, int endindex) {
        if (startIndex < 0 || startIndex > endindex || str.length() <= endindex) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(str);
        String starts = "";
        for(int i = 0 ; i < (endindex - startIndex + 1) ; i++){
            starts += "*";
        }
        sb.replace(startIndex,endindex+1,starts);
        return sb.toString();
    }

     /*
    * 讲资源文件中的txt转成字符串
    * */

    public static String readAssText(Context context, String path, String charSet){
        String text = "";
        try {
            InputStream is = context.getAssets().open(path);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            text = new String(buffer,charSet);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return text;
    }

}
