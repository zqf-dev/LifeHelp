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
import com.zqf.lifehelp.db.dao.QueryIdDao;
import com.zqf.lifehelp.db.table.QueryIdSql;
import com.zqf.lifehelp.factory.base.BaseActivity;
import com.zqf.lifehelp.model.QueryIDBean;
import com.zqf.lifehelp.presenter.IQueryIdPresenter;
import com.zqf.lifehelp.presenter.QueryIdPresenter;
import com.zqf.lifehelp.utils.Util;
import com.zqf.lifehelp.view.adapter.SearchHistoryAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by zqf on 2018/4/3.
 * 身份证查询
 */

public class QueryID extends BaseActivity<QueryIdPresenter> implements IQueryIdPresenter, BaseQuickAdapter.OnItemChildClickListener {


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
    private QueryIdDao queryIdDao;

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
        //数据库表
        queryIdDao = new QueryIdDao(this);
        //分割线||布局风格
        Util.RecycleCommSet(this, searchHistoryRecycle, LinearLayoutManager.VERTICAL);
        //适配
        mAdapter = new SearchHistoryAdapter(R.layout.history_recycle_item_layout, mHistorySearchList);
        searchHistoryRecycle.setAdapter(mAdapter);
        mAdapter.addFooterView(getFooterView(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //清空历史搜索,和数据库
                queryIdDao.deleteAll(queryIdDao.queryAll());
                mHistorySearchList.clear();
                mAdapter.notifyDataSetChanged();
            }
        }));
        mAdapter.setOnItemChildClickListener(this);
//        mAdapter.setOnItemLongClickListener(this);
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
        mHistorySearchList = queryIdDao.queryAll();
        if (mHistorySearchList != null) {
            mAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onGetQueryIDDataSuccess(QueryIDBean bean) {
        Logger.e("onGetQueryIDDataSuccess");
        Util.setOnCancelLinstener();
        ShowResult(bean);
    }

    @Override
    public void onError() {
        queryIdResultTv.setText("搜索错误!");
    }

    private void ShowResult(QueryIDBean bean) {
        Logger.e("ShowResult");
        if (bean.getResult() != null && !bean.getResult().equals("null")) {
            String query_idNum = queryidEdit.getText().toString().trim();
            queryIdResultTv.setText("身份证号:" + query_idNum + "\n"
                    + "地址:" + bean.getResult().getArea() + "\n" + "出生日期:" + bean.getResult().getBirthday()
                    + "\n" + "性别:" + bean.getResult().getSex());
            //增加到数据库
            QueryIdSql queryIdSql = new QueryIdSql(query_idNum);
            queryIdDao.add(queryIdSql);
            mHistorySearchList.add(queryIdSql);
            mAdapter.notifyDataSetChanged();
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
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
        Logger.e(position + "");
    }

//    @Override
//    public boolean onItemLongClick(BaseQuickAdapter adapter, View view, int position) {
//        return true;
//    }

    private View getFooterView(View.OnClickListener listener) {
        View view = getLayoutInflater().inflate(R.layout.recycle_foot_view, (ViewGroup) searchHistoryRecycle.getParent(), false);
        view.setOnClickListener(listener);
        return view;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
