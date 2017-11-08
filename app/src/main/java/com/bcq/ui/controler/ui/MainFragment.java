package com.bcq.ui.controler.ui;

import android.view.LayoutInflater;
import android.view.View;

import com.bcq.ui.controler.R;
import com.bcq.ui.controler.base.AbsListActivity;
import com.bcq.ui.controler.base.AbsListFragment;
import com.bcq.ui.controler.base.adapter.AbsBaseAdapter;
import com.bcq.ui.controler.base.api.ApiUtils;

import java.util.ArrayList;
import java.util.List;

public class MainFragment extends AbsListFragment<String> {
    View content;
    @Override
    public AbsBaseAdapter setAdapter() {
        return new MainAdapter(mActivity);
    }

    @Override
    public View initContentView(LayoutInflater inflater) {
        return ApiUtils.inflate(R.layout.activity_main);
    }


    @Override
    public void initChildView(View parent) {
        getTestData();
    }

    private void getTestData() {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            list.add("TestData --> "+i);
        }
        super.onRefreshData(list,true);
    }

}
