package com.zqf.lifehelp.view.activity.leftmenu;

import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.orhanobut.logger.Logger;
import com.zqf.lifehelp.R;
import com.zqf.lifehelp.factory.base.BaseActivity;
import com.zqf.lifehelp.model.QueryIDBean;
import com.zqf.lifehelp.presenter.IQueryIdPresenter;
import com.zqf.lifehelp.presenter.QueryIdPresenter;
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
    private List<QueryIDBean.ResultBean> mHistorySearchList = new ArrayList<>();
    private View mFoot_view;

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
        mFoot_view = LayoutInflater.from(this).inflate(R.layout.recycle_foot_view, null);
        queryidEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (queryidEdit.getText().toString().length() > 0) {
                    searchCancelImg.setVisibility(View.VISIBLE);
                } else {
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
        mAdapter = new SearchHistoryAdapter(R.layout.history_recycle_item_layout, mHistorySearchList);
        searchHistoryRecycle.setAdapter(mAdapter);
        mAdapter.addFooterView(mFoot_view);
        mAdapter.setOnItemChildClickListener(this);
    }

    @Override
    public void onGetQueryIDDataSuccess(QueryIDBean bean) {
        BaseOnCancelLinstener();
        ShowResult(bean);
        mHistorySearchList.add(bean.getResult());
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void onError() {
        BaseOnCancelLinstener();
        queryIdResultTv.setText("搜索错误!");
    }

    private void ShowResult(QueryIDBean bean) {
        Logger.e("ShowResult");
        if (bean.getResult() != null && !bean.getResult().equals("null")) {
            queryIdResultTv.setText("身份证号:" + queryidEdit.getText().toString().trim() + "\n"
                    + "地址:" + bean.getResult().getArea() + "\n" + "出生日期:" + bean.getResult().getBirthday()
                    + "\n" + "性别:" + bean.getResult().getSex());
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
                BaseSetShowLoading();
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
}
