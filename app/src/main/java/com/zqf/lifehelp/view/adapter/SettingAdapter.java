package com.zqf.lifehelp.view.adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zqf.lifehelp.R;
import com.zqf.lifehelp.model.SettingEnity;

import java.util.List;

/**
 * class from
 * Created by zqf
 * Time 2018/1/9 14:56
 */

public class SettingAdapter extends BaseQuickAdapter<SettingEnity, BaseViewHolder> {
    public SettingAdapter(@LayoutRes int layoutResId, @Nullable List<SettingEnity> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, SettingEnity item) {
        helper.setText(R.id.setting_recycleitem_tv, item.getTitle_name());
    }
}
