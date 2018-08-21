package com.zqf.lifehelp.view.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;

import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.zqf.lifehelp.R;
import com.zqf.lifehelp.factory.base.BaseFragment;
import com.zqf.lifehelp.model.TabModel;
import com.zqf.lifehelp.presenter.INewPresenter;
import com.zqf.lifehelp.presenter.NewPresenter;
import com.zqf.lifehelp.utils.customview.recycler.SpacesItemDecoration;
import com.zqf.lifehelp.view.activity.newsdetail.TencentWebview;
import com.zqf.lifehelp.view.adapter.CommHomeTabAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

import static com.zqf.lifehelp.utils.Constants.REFRESH_MILLIS;

/**
 * class from-->首页共用懒加载的Fragment
 * --CommonRecyclerView---
 * Created by zqf
 * Time 2017/6/8 10:06
 */
public class FgHome extends BaseFragment<NewPresenter> implements INewPresenter, BaseQuickAdapter.OnItemClickListener, BaseQuickAdapter.RequestLoadMoreListener {

    @Bind(R.id.tab_swipe_refresh)
    SwipeRefreshLayout tabSwipeRefresh;
    @Bind(R.id.tab_comrecycle)
    RecyclerView tabComrecycle;
    private String mCid;
    private int page = 1;//页数参数
    private List<TabModel.ResultBean.ListBean> mList = new ArrayList<>();
    private CommHomeTabAdapter mTabAdapter;
    private Handler mHandler = new Handler();

    @Override
    public View getContentView(LayoutInflater inflater, Bundle savedInstanceState) {
        mCid = getArguments().getString("tags_cid");//标签cid
        View view = LayoutInflater.from(mActivity).inflate(R.layout.fghometab_layout, null);
        ButterKnife.bind(this, view);
        init();
        return view;
    }

    //获取数据
    @Override
    protected void ChildRequestServiceData() {
        mPresenter.getNewsList(mCid, page);
    }

    @Override
    protected NewPresenter createPresenter() {
        return new NewPresenter(this);
    }

    /**
     * 初始化设置
     */
    private void init() {
        //分割线
        int leftRight = (int) getResources().getDimension(R.dimen.size_5dp);
        int topBottom = (int) getResources().getDimension(R.dimen.size_0_5dp);
        int lineColor = ContextCompat.getColor(mActivity, R.color.tinge_gray);
        tabComrecycle.addItemDecoration(new SpacesItemDecoration(leftRight, topBottom, lineColor));
        //布局风格
        tabComrecycle.setLayoutManager(new LinearLayoutManager(mActivity));
        mTabAdapter = new CommHomeTabAdapter(R.layout.tab_recycle_item_layout, mList);
        tabComrecycle.setAdapter(mTabAdapter);
        //item点击
        mTabAdapter.setOnItemClickListener(this);
        //刷新
        tabSwipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                page = 1;
                ChildRequestServiceData();
                mTabAdapter.notifyDataSetChanged();
                tabSwipeRefresh.setRefreshing(false);
            }
        });
        //加载更多
        mTabAdapter.setEnableLoadMore(true);
        mTabAdapter.setOnLoadMoreListener(this, tabComrecycle);
    }

    @Override
    public void onGetNewsListSuccess(List<TabModel.ResultBean.ListBean> newList, String tipInfo) {
        refresh_success_gone();
        if (page == 1) {
            //刷新情况
            if (mList.size() > 0) {
                //不是首次加载判断数据是否更新了
                if (newList.get(0).getTitle().equals(mList.get(0).getTitle())) {
                    ToastUtils.showShort("已是最新内容!");
                    return;
                }
                mList.addAll(0, newList);
            } else {
                mList.addAll(newList);
            }
        } else {
            mList.addAll(newList);
        }
        mTabAdapter.notifyDataSetChanged();
        mTabAdapter.loadMoreComplete();
    }

    @Override
    public void onError() {
        if (page == 1 && mList.size() == 0) {
            refresh_failed();
        } else if (page == 1 && mList.size() > 0) {
            ToastUtils.showShort("失败;请检查网络!");
        } else {
            mTabAdapter.loadMoreFail();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    /**
     * 点击事件
     *
     * @param position --位置
     */
    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        String sourceurl = mList.get(position).getSourceUrl();
        Intent detail_intent = new Intent(mActivity, TencentWebview.class);
        detail_intent.putExtra("sourceurl", sourceurl);
        startActivity(detail_intent);
    }

    @Override
    public void onLoadMoreRequested() {
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                page++;
                ChildRequestServiceData();
                mTabAdapter.loadMoreComplete();
            }
        }, REFRESH_MILLIS);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mHandler != null) {
            mHandler.removeCallbacksAndMessages(null);
            mHandler = null;
        }
    }
}
