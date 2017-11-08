package com.bcq.ui.controler.base.interfac.defauleinstance;

import android.text.Editable;
import android.text.TextWatcher;

/**
 * @author: BaiCQ
 * @ClassName: AbsTextWatch
 * @Description: AbsTextWatch 默认文本变化监听
 */
public abstract class AbsTextWatch implements TextWatcher {


    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {}
    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

    @Override
    public void afterTextChanged(Editable s) {
        onAfter(s);
    }

    public abstract void onAfter(Editable s);

}
