package com.zqf.lifehelp.view.fragment;


import android.view.View;

import com.zqf.lifehelp.R;
import com.zqf.lifehelp.factory.base.BaseFragment;
import com.zqf.lifehelp.model.TabModel;
import com.zqf.lifehelp.presenter.INewPresenter;
import com.zqf.lifehelp.presenter.NewPresenter;
import com.zqf.lifehelp.view.adapter.HomeTabAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zqf on 2018/3/31.
 * 测试公用的Fg
 */

public class CommFragment extends BaseFragment<NewPresenter> implements INewPresenter {

    private List<TabModel.ResultBean.ListBean> mList = new ArrayList<>();
    private HomeTabAdapter mTabAdapter;

    @Override
    protected NewPresenter createPresenter() {
        return new NewPresenter(this);
    }

    @Override
    protected int provideContentViewId() {
        return R.layout.commlayout;
    }

    @Override
    public void initView(View rootView) {

    }

    @Override
    public void initListener() {

    }

    @Override
    protected void loadData() {
        mPresenter.getNewsList();
        //找新闻集合并展示
        mTabAdapter.notifyDataSetChanged();//刷新adapter
    }

    @Override
    public void onGetNewsListSuccess(List<TabModel.ResultBean.ListBean> newList, String tipInfo) {

    }

    @Override
    public void onError() {

    }
}
