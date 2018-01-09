package com.zqf.lifehelp.view.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.zqf.lifehelp.R;
import com.zqf.lifehelp.model.entity.SettingEnity;
import com.zqf.lifehelp.utils.Util;
import com.zqf.lifehelp.view.adapter.SettingAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by zqf on 2018/1/8.
 * 设置界面
 */

public class Setting extends Activity {

    @Bind(R.id.act_left_iv)
    ImageView actLeftIv;
    @Bind(R.id.act_left_tv)
    TextView actLeftTv;
    @Bind(R.id.act_right_tv)
    TextView actRightTv;
    @Bind(R.id.setting_recycle)
    RecyclerView settingRecycle;
    private List<SettingEnity> mList = new ArrayList<>();
    private SettingAdapter mSettingAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setting_layout);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        actLeftTv.setText(R.string.setting_title_str);
        mList.add(new SettingEnity("1", "提醒设置", ""));
        mList.add(new SettingEnity("2", "修改密码", ""));
        mList.add(new SettingEnity("3", "个人主页", ""));
        mList.add(new SettingEnity("4", "检测更新", ""));
        mList.add(new SettingEnity("5", "退出", ""));

        Util.RecycleCommSet(this, settingRecycle, LinearLayoutManager.VERTICAL);
        mSettingAdapter = new SettingAdapter(R.layout.setting_recycleitem_layout, mList);
        settingRecycle.setAdapter(mSettingAdapter);
        mSettingAdapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_LEFT);

        mSettingAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

            }
        });
    }

    @OnClick(R.id.act_left_iv)
    public void onViewClicked() {
        finish();
    }
}
