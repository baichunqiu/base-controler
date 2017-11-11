package com.bcq.ui.controlerdemo;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.bcq.ui.controler.base.AbsListActivity;
import com.bcq.ui.controler.base.adapter.AbsBaseAdapter;
import com.bcq.ui.controler.base.api.ApiUtils;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AbsListActivity<String> {
    View content;
    @Override
    public AbsBaseAdapter setAdapter() {
        return new MainAdapter(mActivity);
    }

    @Override
    public View initContentView() {
        content = ApiUtils.inflate(R.layout.activity_main);
        return content;
    }

    @Override
    public void initView(View parent) {
        TextView tv = ApiUtils.getView(parent,R.id.tv);
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(mActivity,SecondActivity.class));
            }
        });
        getTestData();
    }

    private void getTestData() {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 40; i++) {
            list.add("TestData --> "+i);
        }
        super.onRefreshData(list,true);
    }

}
