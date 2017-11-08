package com.bcq.ui.controler.base.controller;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;

import com.bcq.ui.controler.base.AbsListActivity;
import com.bcq.ui.controler.base.AbsListFragment;
import com.bcq.ui.controler.base.adapter.AbsBaseAdapter;
import com.bcq.ui.controler.base.interfac.IRefreshUI;

import java.util.List;

/**
 * @author: BaiCQ
 * @ClassName:
 * @Description:
 */
public class BaseUIController<T> extends UIController<T> {
    private final static String Tag = "BaseUIController";
    private boolean isActivity = false;
    private View parent;
    private AbsListActivity<T> aOpenator;
    private AbsListFragment<T> fOpenator;

    public <O extends IRefreshUI> BaseUIController(Activity activity, View parent, O operator) {
        super(activity);
        this.parent = parent;
        if (operator instanceof Activity) {
            isActivity = true;
            aOpenator = (AbsListActivity) operator;
        } else {
            isActivity = false;
            fOpenator = (AbsListFragment) operator;
        }
    }

    @Override
    public View _setLayoutParent() {
        if (null == parent){
            throw new IllegalArgumentException("No ParentLayout added for UI");
        }
        return parent;
    }

    @Override
    public View _initContentView() {
        View contentView;
        if (isActivity) {
            contentView = aOpenator.initContentView();
        } else {
            contentView = fOpenator.initContentView(LayoutInflater.from(mActivity));
        }
        return contentView;
    }

    @Override
    public void _initView(View parent) {
        if (isActivity) {
            aOpenator.initView(parent);
        } else {
            fOpenator.initChildView(parent);
        }
    }

    @Override
    public AbsBaseAdapter _setAdapter() {
        AbsBaseAdapter absBaseAdapter;
        if (isActivity) {
            absBaseAdapter = aOpenator.setAdapter();
        } else {
            absBaseAdapter = fOpenator.setAdapter();
        }
        return absBaseAdapter;
    }

    @Override
    public List<T> _preHandleData(List<T> netData) {
        List<T> tList;
        if (isActivity) {
            tList = aOpenator.preHandleData(netData);
        } else {
            tList = fOpenator.preHandleData(netData);
        }
        return tList;
    }

    @Override
    public List _preRefreshData(List<T> netData) {
        List resultList;
        if (isActivity) {
            resultList = aOpenator.preRefreshData(netData);
        } else {
            resultList = fOpenator.preRefreshData(netData);
        }
        return resultList;
    }
}
