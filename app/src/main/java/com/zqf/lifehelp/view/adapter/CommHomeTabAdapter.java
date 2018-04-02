package com.zqf.lifehelp.view.adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zqf.lifehelp.R;
import com.zqf.lifehelp.model.TabModel;

import java.util.List;

/**
 * class from 首页Tab标签下RecycleView的适配器
 * Created by zqf
 * Time 2017/6/8 16:24
 */

public class CommHomeTabAdapter extends BaseQuickAdapter<TabModel.ResultBean.ListBean, BaseViewHolder> {


    public CommHomeTabAdapter(@LayoutRes int layoutResId, @Nullable List<TabModel.ResultBean.ListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, TabModel.ResultBean.ListBean item) {
        helper.setText(R.id.tab_recycle_item_tv, item.getTitle());
        helper.setText(R.id.tab_recycle_item_pubTime, item.getPubTime());
        // 加载网络图片
        Glide.with(mContext).load(item.getThumbnails())
                .placeholder(R.mipmap.placeholder_icon)
                .error(R.mipmap.placeholder_icon)
                .into((ImageView) helper.getView(R.id.tab_recycle_item_iv));
    }
}
