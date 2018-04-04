package com.zqf.lifehelp.view.activity.leftmenu;

import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;
import com.zqf.lifehelp.R;
import com.zqf.lifehelp.factory.base.BaseActivity;
import com.zqf.lifehelp.model.QueryIDBean;
import com.zqf.lifehelp.presenter.IQueryIdPresenter;
import com.zqf.lifehelp.presenter.QueryIdPresenter;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by zqf on 2018/4/3.
 * 身份证查询
 */

public class QueryID extends BaseActivity<QueryIdPresenter> implements IQueryIdPresenter {


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

    @Override
    protected QueryIdPresenter createPresenter() {
        return new QueryIdPresenter(this);
    }

    @Override
    protected int provideContentViewId() {
        return R.layout.query_id_layout;
    }

    @Override
    public void onGetQueryIDDataSuccess(QueryIDBean.ResultBean bean) {

    }

    @Override
    public void onError() {

    }

    @OnClick({R.id.act_left_iv, R.id.query_search_tv})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.act_left_iv:
                finish();
                break;
            case R.id.query_search_tv:
                //搜索
                String search_msg = querySearchTv.getText().toString().trim();
                if (TextUtils.isEmpty(search_msg)) {
                    ToastUtils.showShort("空的空的...");
                    return;
                }
                mPresenter.getQueryIdInfo(search_msg);
                break;
        }
    }
}
