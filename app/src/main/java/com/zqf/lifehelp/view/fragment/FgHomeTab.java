package com.zqf.lifehelp.view.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;

import com.zqf.lifehelp.R;
import com.zqf.lifehelp.api.ApiManager;
import com.zqf.lifehelp.model.TabModel;
import com.zqf.lifehelp.utils.Constants;
import com.zqf.lifehelp.utils.LogUtil;
import com.zqf.lifehelp.utils.customview.recycler.CommonRecyclerView;
import com.zqf.lifehelp.utils.customview.recycler.SpacesItemDecoration;
import com.zqf.lifehelp.view.activity.newsdetail.TencentWebview;
import com.zqf.lifehelp.view.adapter.HomeTabAdapter;
import com.zqf.lifehelp.view.base.BaseFragment;

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
public class FgHomeTab extends BaseFragment implements CommonRecyclerView.LoadMoreListener, CommonRecyclerView.OnItemClickListener {

    @Bind(R.id.tab_swipe_refresh)
    SwipeRefreshLayout tabSwipeRefresh;
    @Bind(R.id.tab_comrecycle)
    CommonRecyclerView tabComrecycle;
    private String mCid;
    private ApiManager mDataManager;
    private int page = 1;//页数
    private int size = 10;//条数
    private List<TabModel.ResultBean.ListBean> mList = new ArrayList<>();
    private HomeTabAdapter mTabAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mDataManager = new ApiManager();
    }

    @Override
    public View getContentView(LayoutInflater inflater, Bundle savedInstanceState) {
        mCid = getArguments().getString("tags_cid");//标签cid
        View view = LayoutInflater.from(mActivity).inflate(R.layout.fghometab_layout, null);
        ButterKnife.bind(this, view);
        init();
        return view;
    }

    @Override
    protected void ChildRequestServiceData() {
        mDataManager.getTabData(Constants.MOBKEY, mCid, page, size).enqueue(new Callback<TabModel>() {
            @Override
            public void onResponse(Call<TabModel> call, Response<TabModel> response) {
                LogUtil.logD("当前tab数据" + response.body() + "");
                TabModel model = response.body();
                if (model.getRetCode().equals("200")) {
                    refresh_success_gone();
                    mList.addAll(model.getResult().getList());
                    mTabAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<TabModel> call, Throwable t) {
                LogUtil.logD("加载失败");
                refresh_failed();
            }
        });
    }

    /**
     * 初始化设置
     */
    private void init() {
        int leftRight = (int) getResources().getDimension(R.dimen.size_10dp);
        int topBottom = (int) getResources().getDimension(R.dimen.size_0_5dp);
        int lineColor = ContextCompat.getColor(mActivity, R.color.tinge_gray);

        tabComrecycle.addItemDecoration(new SpacesItemDecoration(leftRight, topBottom, lineColor));
        tabComrecycle.setLayoutManager(new LinearLayoutManager(mActivity));
        tabComrecycle.setOnLoadMoreListener(this);

        tabComrecycle.setOnItemClickListener(this);

        mTabAdapter = new HomeTabAdapter(mActivity, mList);
        tabComrecycle.setAdapter(mTabAdapter);


        /**
         * 刷新
         */
        tabSwipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                page = 0;
                mList.clear();
                ChildRequestServiceData();
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
        ChildRequestServiceData();
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
     * @param itemView -- 当前点击的itemView
     */
    @Override
    public void onItemClick(int position, View itemView) {
        String sourceurl = mList.get(position).getSourceUrl();
        Intent detail_intent = new Intent(mActivity, TencentWebview.class);
        detail_intent.putExtra("sourceurl", sourceurl);
        startActivity(detail_intent);
    }
}
