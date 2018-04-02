package com.zqf.lifehelp.factory.base;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zqf.lifehelp.R;
import com.zqf.lifehelp.utils.customview.NetLoadView;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by zqf on 2017/3/21.
 * 基类Fragment
 */

public abstract class BaseFragment<T extends BasePresenter> extends LazyLoadFragment {
    protected Activity mActivity;
    protected NetLoadView mNetLoadView;
    protected T mPresenter;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mActivity = getActivity();
    }

    // fragment开始创建
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = createPresenter();
    }

    // 处理fragment的布局
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup view = (ViewGroup) inflater.inflate(R.layout.basefragment_root_layout, container, false);
        if (getContentView(inflater, savedInstanceState) != null) {
            view.addView(getContentView(inflater, savedInstanceState), 0);
            initBaseView(view);
        }
        return view;
    }

    private void initBaseView(ViewGroup view) {
        mNetLoadView = (NetLoadView) view.findViewById(R.id.base_netloadview);
        mNetLoadView.setMyRefreshListener(new NetLoadView.onMyRefreshListener() {
            @Override
            public void refresh() {
                clickToRefresh();
            }
        });
    }


    @Override
    protected void onFragmentFirstVisible() {
        //当第一次可见的时候，加载数据
        clickToRefresh();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.detachView();
            mPresenter = null;
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    //用于创建Presenter和判断是否使用MVP模式(由子类实现)
    protected abstract T createPresenter();

    // 依附的activity创建完成
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    /**
     * Fragment数据视图
     * 子类必须实现初始化布局的方法
     *
     * @param inflater
     * @param savedInstanceState
     * @return
     */
    protected abstract View getContentView(LayoutInflater inflater, @Nullable Bundle savedInstanceState);

    // 子类必须实现findById初始化方法
//    protected abstract void findViewByIdView();

    // 初始化数据, 可以(不)实现
    protected abstract void ChildRequestServiceData();

    /**
     * 点击刷新加载
     */
    private void clickToRefresh() {
        startRefresh();
        ChildRequestServiceData();
    }

    private void startRefresh() {
        mNetLoadView.setStatue(NetLoadView.LOADING);
    }

    /**
     * 无数据加载
     */
    public void refresh_nodata_failed() {
        mNetLoadView.setStatue(NetLoadView.NO_DATA);
    }

    /**
     * 加载、网络都失败
     */
    public void refresh_failed() {
        mNetLoadView.setStatue(NetLoadView.NO_NETWORK);
    }

    /**
     * 刷新成功后--不显示
     */
    public void refresh_success_gone() {
        mNetLoadView.setStatue(NetLoadView.ALL_GONE);
    }


    public boolean isEventBusRegisted(Object subscribe) {
        return EventBus.getDefault().isRegistered(subscribe);
    }

    public void registerEventBus(Object subscribe) {
        if (!isEventBusRegisted(subscribe)) {
            EventBus.getDefault().register(subscribe);
        }
    }

    public void unregisterEventBus(Object subscribe) {
        if (isEventBusRegisted(subscribe)) {
            EventBus.getDefault().unregister(subscribe);
        }
    }
}
