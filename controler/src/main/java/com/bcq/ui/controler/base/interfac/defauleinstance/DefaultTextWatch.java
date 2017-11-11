package com.bcq.ui.controler.base.interfac.defauleinstance;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;

/**
 * @author: BaiCQ
 * @ClassName: DefaultTextWatch
 * @Description: DefaultTextWatch 默认文本变化监听
 */
public class DefaultTextWatch implements TextWatcher {

    private View attachView;
    private boolean isFull = false;

    public DefaultTextWatch(View attachView){
        this.attachView = attachView;
    }
    public DefaultTextWatch(){
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {}
    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

    @Override
    public void afterTextChanged(Editable s) {
        if (null != attachView){
            int length = s.toString().length();
            if (length > 0){
                attachView.setSelected(true);
            }else{
                attachView.setSelected(false);
            }
        }
    }

}
