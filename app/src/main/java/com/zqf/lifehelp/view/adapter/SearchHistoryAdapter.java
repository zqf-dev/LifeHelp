package com.zqf.lifehelp.view.adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zqf.lifehelp.model.QueryIDBean;

import java.util.List;

/**
 * class from 历史搜索的recycleview 适配器
 * Created by zqf
 * Time 2018/4/8 15:03
 */

public class SearchHistoryAdapter extends BaseQuickAdapter<QueryIDBean.ResultBean, BaseViewHolder> {

    public SearchHistoryAdapter(@LayoutRes int layoutResId, @Nullable List<QueryIDBean.ResultBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, QueryIDBean.ResultBean item) {

    }
}
