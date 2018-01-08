package com.zqf.lifehelp.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.zqf.lifehelp.R;
import com.zqf.lifehelp.model.entity.TabModel;
import com.zqf.lifehelp.view.customview.recycler.HeaderAndFooterAdapter;
import com.zqf.lifehelp.view.customview.recycler.ViewHolder;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * class from 首页Tab标签下RecycleView的适配器
 * Created by zqf
 * Time 2017/6/8 16:24
 */

public class HomeTabAdapter extends HeaderAndFooterAdapter<TabModel.ResultBean.ListBean> {
    Context mContext;

    public HomeTabAdapter(Context context, List<TabModel.ResultBean.ListBean> list) {
        super(list);
        this.mContext = context;
    }

    @Override
    public ViewHolder onCreateItemViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.tab_recycle_item_layout, parent, false);
        return new TabViewHolder(view);
    }

    @Override
    public void onBindItemViewHolder(ViewHolder holder, int position, TabModel.ResultBean.ListBean item) {
        TabViewHolder tabViewHolder = (TabViewHolder) holder;
        tabViewHolder.tabRecycleItemPubTime.setText(item.getPubTime());
        tabViewHolder.tabRecycleItemTv.setText(item.getTitle());
        Glide.with(mContext).load(item.getThumbnails())
                .placeholder(R.mipmap.placeholder_icon)
                .error(R.mipmap.placeholder_icon)
                .into(tabViewHolder.tabRecycleItemIv);
    }

    class TabViewHolder extends ViewHolder {
        @Bind(R.id.tab_recycle_item_iv)
        ImageView tabRecycleItemIv;
        @Bind(R.id.tab_recycle_item_tv)
        TextView tabRecycleItemTv;
        @Bind(R.id.tab_recycle_item_pubTime)
        TextView tabRecycleItemPubTime;

        public TabViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
