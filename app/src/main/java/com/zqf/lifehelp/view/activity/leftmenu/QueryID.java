package com.zqf.lifehelp.view.activity.leftmenu;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.orhanobut.logger.Logger;
import com.zqf.lifehelp.R;
import com.zqf.lifehelp.db.daoImpl.QueryIdDao;
import com.zqf.lifehelp.db.daoImpl.QueryIdDaoImpl;
import com.zqf.lifehelp.db.table.QueryIdSql;
import com.zqf.lifehelp.factory.base.BaseActivity;
import com.zqf.lifehelp.model.QueryIDBean;
import com.zqf.lifehelp.presenter.IQueryIdPresenter;
import com.zqf.lifehelp.presenter.QueryIdPresenter;
import com.zqf.lifehelp.utils.Util;
import com.zqf.lifehelp.view.adapter.SearchHistoryAdapter;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by zqf on 2018/4/3.
 * 身份证查询
 */

public class QueryID extends BaseActivity<QueryIdPresenter> implements IQueryIdPresenter, BaseQuickAdapter.OnItemChildClickListener, BaseQuickAdapter.OnItemClickListener {


    @Bind(R.id.act_left_iv)
    ImageView actLeftIv;
    @Bind(R.id.act_left_tv)
    TextView actLeftTv;
    @Bind(R.id.act_right_tv)
    TextView actRightTv;
    @Bind(R.id.query_search_tv)
    TextView querySearchTv;
    @Bind(R.id.queryid_edit)
    EditText queryidEdit;
    @Bind(R.id.search_cancel_img)
    ImageView searchCancelImg;
    @Bind(R.id.query_id_result_tv)
    TextView queryIdResultTv;
    @Bind(R.id.search_history_recycle)
    RecyclerView searchHistoryRecycle;
    private SearchHistoryAdapter mAdapter;
    private List<QueryIdSql> mHistorySearchList = new ArrayList<>();
    private QueryIdDao mQueryIdDao;

    @Override
    protected QueryIdPresenter createPresenter() {
        return new QueryIdPresenter(this);
    }

    @Override
    protected int provideContentViewId() {
        return R.layout.query_id_layout;
    }

    @Override
    public void initView() {
        super.initView();
        actLeftTv.setText("身份证号查询");
        mQueryIdDao = new QueryIdDaoImpl();
        queryidEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (queryidEdit.getText().toString().length() > 0) {
                    searchCancelImg.setVisibility(View.VISIBLE);
                } else {
                    queryIdResultTv.setText("");
                    searchCancelImg.setVisibility(View.GONE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    @Override
    public void initData() {
        super.initData();
        //查询数据库
        try {
            mHistorySearchList = mQueryIdDao.getAllQueryID();
            if (mHistorySearchList != null && mHistorySearchList.size() > 0) {
                recycleviewinit();
                mAdapter.notifyDataSetChanged();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //初始化RecycleView
    private void recycleviewinit() {
        //分割线||布局风格
        Util.RecycleCommSet(this, searchHistoryRecycle, LinearLayoutManager.VERTICAL);
        //适配
        mAdapter = new SearchHistoryAdapter(R.layout.history_recycle_item_layout, mHistorySearchList);
        searchHistoryRecycle.setAdapter(mAdapter);
        mAdapter.addFooterView(getFooterView(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //清空历史搜索,和数据库
                try {
                    mQueryIdDao.deleteAll();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                mHistorySearchList.clear();
                mAdapter.notifyDataSetChanged();
            }
        }));
        mAdapter.setOnItemClickListener(this);
        mAdapter.setOnItemChildClickListener(this);
        mAdapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_LEFT);
        mAdapter.isFirstOnly(false);
    }

    @Override
    public void onGetQueryIDDataSuccess(QueryIDBean bean) {
        Logger.e("onGetQueryIDDataSuccess");
        Util.setOnCancelLinstener();
        showResult(bean);
    }

    @Override
    public void onError() {
        queryIdResultTv.setText("搜索错误!");
    }

    private void showResult(QueryIDBean bean) {
        Logger.e("showResult");
        if (bean.getMsg().equals("success") && bean.getRetCode().equals("200")) {
            String query_idNum = queryidEdit.getText().toString().trim();
            String queryid_key = Util.QueryIDNumPermissKey(query_idNum);//主键以身份证后四位为标准
            queryIdResultTv.setText("身份证号:" + query_idNum + "\n"
                    + "地址:" + bean.getResult().getArea() + "\n" + "出生日期:" + bean.getResult().getBirthday()
                    + "\n" + "性别:" + bean.getResult().getSex());
            //增加到数据库
            try {
                final QueryIdSql sql = new QueryIdSql();
                sql.setQuery_id(queryid_key);
                sql.setQueryidnum(query_idNum);
                int flag = mQueryIdDao.insertQueryIdAsync(sql, queryid_key);
                if (flag != -1) {
                    Logger.e("插入new数据");
                    mQueryIdDao.insert(sql);
                    mHistorySearchList.add(sql);
                    mAdapter.notifyDataSetChanged();
                }
            } catch (Exception e) {
                e.printStackTrace();
                Logger.e("插入 search 数据异常");
            }
        } else {
            queryIdResultTv.setText(bean.getMsg());
        }
    }

    @OnClick({R.id.act_left_iv, R.id.query_search_tv, R.id.search_cancel_img})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.act_left_iv:
                finish();
                break;
            case R.id.query_search_tv:
                //搜索
                String search_msg = queryidEdit.getText().toString().trim();
                if (TextUtils.isEmpty(search_msg)) {
                    ToastUtils.showShort("空的空的...");
                    return;
                }
                Util.setShowLoading(this);
                mPresenter.getQueryIdInfo(search_msg);
                break;
            case R.id.search_cancel_img:
                queryidEdit.setText("");
                break;
        }
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        queryidEdit.setText(mHistorySearchList.get(position).getQueryidnum());
    }

    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
        if (!TextUtils.isEmpty(mHistorySearchList.get(position).getQueryidnum())) {
            queryidEdit.setText(mHistorySearchList.get(position).getQueryidnum());
        }
    }

    private View getFooterView(View.OnClickListener listener) {
        View view = getLayoutInflater().inflate(R.layout.recycle_foot_view, (ViewGroup) searchHistoryRecycle.getParent(), false);
        view.setOnClickListener(listener);
        return view;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mHistorySearchList != null) {
            mHistorySearchList.clear();
            mHistorySearchList = null;
        }
    }


}
