package com.bcq.ui.controlerdemo;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;

import com.bcq.ui.controler.base.BaseActivity;


public class SecondActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        initView();
    }

    private void initView() {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.fl_content,new MainFragment());
        ft.commitAllowingStateLoss();
    }
}
