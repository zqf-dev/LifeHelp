package com.zqf.lifehelp.view.fragment;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;

import com.orhanobut.logger.Logger;
import com.zqf.lifehelp.R;
import com.zqf.lifehelp.factory.BaseFragment;
import com.zqf.lifehelp.model.entity.TabModel;
import com.zqf.lifehelp.service.manage.DataManager;
import com.zqf.lifehelp.utils.Constants;
import com.zqf.lifehelp.view.adapter.HomeTabAdapter;
import com.zqf.lifehelp.view.customview.recycler.CommonRecyclerView;
import com.zqf.lifehelp.view.customview.recycler.SpacesItemDecoration;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * class from-->首页共用懒加载的Fragment
 * --CommonRecyclerView---
 * Created by zqf
 * Time 2017/6/8 10:06
 */
public class FgHomeTab extends BaseFragment implements CommonRecyclerView.LoadMoreListener {

    @Bind(R.id.tab_swipe_refresh)
    SwipeRefreshLayout tabSwipeRefresh;
    @Bind(R.id.tab_comrecycle)
    CommonRecyclerView tabComrecycle;
    private String mCid;
    private DataManager mDataManager;
    private int page = 1;//页数
    private int size = 20;//条数
    private List<TabModel.ResultBean.ListBean> mList = new ArrayList<>();
    private HomeTabAdapter mTabAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mDataManager = new DataManager(mContext);
    }

    @Override
    public View getContentView(LayoutInflater inflater, Bundle savedInstanceState) {
        mCid = getArguments().getString("tags_cid");//标签cid
        View view = LayoutInflater.from(mContext).inflate(R.layout.fghometab_layout, null);
        ButterKnife.bind(this, view);
        init();
        refreshData();
        return view;
    }

    private void GetInitData() {
        startRefresh();
        refreshData();
    }

    @Override
    public void refreshData() {
        mDataManager.getTabData(Constants.MOBKEY, mCid, page, size).enqueue(new Callback<TabModel>() {
            @Override
            public void onResponse(Call<TabModel> call, Response<TabModel> response) {
                Logger.i("LifeHelp", "tab数据" + response.body());
                TabModel model = response.body();
                if (model.getRetCode().equals("200")) {
                    refreshSuccess();
                    mList.addAll(model.getResult().getList());
                    mTabAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<TabModel> call, Throwable t) {
                Logger.i("LifeHelp", "加载失败");
                refreshFailed();
            }
        });
    }

    private void init() {
        int leftRight = (int) getResources().getDimension(R.dimen.size_10dp);
        int topBottom = (int) getResources().getDimension(R.dimen.size_0_5dp);
        int lineColor = ContextCompat.getColor(mContext, R.color.tinge_gray);

        tabComrecycle.addItemDecoration(new SpacesItemDecoration(leftRight, topBottom, lineColor));
        tabComrecycle.setLayoutManager(new LinearLayoutManager(mContext));
        tabComrecycle.setOnLoadMoreListener(this);

        mTabAdapter = new HomeTabAdapter(mContext, mList);
        tabComrecycle.setAdapter(mTabAdapter);

        tabSwipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                page = 0;
                mList.clear();
                GetInitData();
                tabSwipeRefresh.setRefreshing(false);
            }
        });
    }

    /**
     * 加载更多
     */
    @Override
    public void onLoadMore() {
        page++;
        refreshData();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
