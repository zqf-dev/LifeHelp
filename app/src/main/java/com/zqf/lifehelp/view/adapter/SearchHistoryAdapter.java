package com.zqf.lifehelp.view.adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zqf.lifehelp.R;
import com.zqf.lifehelp.db.table.QueryIdSql;

import java.util.List;

/**
 * class from 历史搜索的recycleview 适配器
 * Created by zqf
 * Time 2018/4/8 15:03
 */

public class SearchHistoryAdapter extends BaseQuickAdapter<QueryIdSql, BaseViewHolder> {


    public SearchHistoryAdapter(@LayoutRes int layoutResId, @Nullable List<QueryIdSql> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, QueryIdSql item) {
        helper.setText(R.id.search_history_recycle_item_tv, item.getQueryidnum());
    }
}
