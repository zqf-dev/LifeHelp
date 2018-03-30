package com.zqf.lifehelp.view.base;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zqf.lifehelp.R;
import com.zqf.lifehelp.utils.customview.NetLoadView;

/**
 * Created by zqf on 2017/3/21.
 * 基类Fragment
 */

public abstract class BaseFragment extends Fragment {
    protected Activity mActivity;
    protected NetLoadView mNetLoadView;
    //Fragment的View加载完毕的标记
    private boolean isViewCreated;
    //Fragment对用户可见的标记
    private boolean isUIVisible;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mActivity = getActivity();
    }

    // fragment开始创建
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        isViewCreated = true;
        lazyLoad();
    }


    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        //isVisibleToUser这个boolean值表示:该Fragment的UI 用户是否可见
        if (isVisibleToUser) {
            isUIVisible = true;
            lazyLoad();
        } else {
            isUIVisible = false;
        }
    }

    private void lazyLoad() {
        //这里进行双重标记判断,
        // 是因为setUserVisibleHint会多次回调,
        // 并且会在onCreateView执行前回调,
        // 必须确保onCreateView加载完毕且页面可见,才加载数据
        if (isViewCreated && isUIVisible) {
            clickToRefresh();
            //数据加载完毕,恢复标记,防止重复加载
            isViewCreated = false;
            isUIVisible = false;
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        //页面销毁,恢复标记
        isViewCreated = false;
        isUIVisible = false;
    }


    public boolean isInitRefreshEnable() {
        return true;
    }

    public boolean isDelayRefreshEnable() {
        return false;
    }

    // 依附的activity创建完成
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
//        clickToRefresh();
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
}
