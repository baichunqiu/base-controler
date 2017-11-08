package com.bcq.ui.controler.ui;

import android.content.Context;

import com.bcq.ui.controler.R;
import com.bcq.ui.controler.base.adapter.BaseSampleAdapter;
import com.bcq.ui.controler.base.adapter.ViewHolder;


/**
 * @author: BaiCQ
 * @ClassName: MainAdapter
 * @date: 2017/11/8
 * @Description: MainAdapter测试
 */
public class MainAdapter extends BaseSampleAdapter<String>{

    public MainAdapter(Context context){
        super(context,R.layout.item);
    }
    @Override
    protected void convert(ViewHolder viewHolder, String item, int position) {
        viewHolder.setText(R.id.info,item);
    }
}
