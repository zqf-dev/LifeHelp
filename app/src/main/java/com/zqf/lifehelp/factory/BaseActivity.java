package com.zqf.lifehelp.factory;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zqf.lifehelp.R;
import com.zqf.lifehelp.view.customview.NetLoadView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * class from BaseActivity基类封装
 * Created by zqf
 * Time 2018/1/2 16:07
 */

public abstract class BaseActivity extends Activity {

    @Bind(R.id.act_left_iv)
    ImageView actLeftIv;
    @Bind(R.id.act_left_tv)
    TextView actLeftTv;
    @Bind(R.id.act_right_tv)
    TextView actRightTv;
    @Bind(R.id.base_netloadview)
    NetLoadView baseNetloadview;
    @Bind(R.id.base_activity_content)
    LinearLayout baseActivityContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.base_activity_layout);
        ButterKnife.bind(this);
        addChildView();
        netloadviewListener();
    }

    /**
     * 子类必须实现初始化布局的方法
     *
     * @return child_view
     */
    private View addChildView() {
        View content = ChildSetContentView();
        if (content != null) {
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT
                    , LinearLayout.LayoutParams.MATCH_PARENT);
            baseActivityContent.addView(content, params);
        }
        return content;
    }

    /**
     * baseActivity的点击事件
     *
     * @param view
     */
    @OnClick({R.id.act_left_iv, R.id.act_right_tv})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.act_left_iv:
                finish();
                break;
            case R.id.act_right_tv:
                onClickRight();
                break;
        }
    }

    /**
     * 初始化加载中点击事件......
     */
    private void netloadviewListener() {
        baseNetloadview.setMyRefreshListener(new NetLoadView.onMyRefreshListener() {
            @Override
            public void refresh() {
                clickToRefresh();
            }
        });
    }

    /**
     * 显示加载中......
     */
    private void startrefresh() {
        baseNetloadview.setStatue(NetLoadView.LOADING);
    }

    /**
     * 显示无数据......
     */
    private void loading_fail_nodata() {
        baseNetloadview.setStatue(NetLoadView.NO_DATA);
    }

    /**
     * 显示无网络或者服务器加载错误......
     */
    private void loading_fail_no_net() {
        baseNetloadview.setStatue(NetLoadView.NO_NETWORK);
    }

    /**
     * 加载成功后--不显示
     */
    public void loading_success_all_gone() {
        baseNetloadview.setStatue(NetLoadView.ALL_GONE);
    }

    /**
     * 点击重新加载
     */
    private void clickToRefresh() {
        startrefresh();
        childrequestdata();
    }

    /**
     * 子类的View
     *
     * @return View
     */
    public abstract View ChildSetContentView();

    /**
     * 子类请求服务端接口方法
     */
    public abstract void childrequestdata();

    /**
     * 右边点击的抽象方法
     */
    public abstract void onClickRight();

    //设置标题text
    public void set_base_act_leftTitle(String text) {
        actLeftTv.setText(text);
    }

    //设置右边text
    public void set_base_act_rightTitle(String text) {
        actRightTv.setText(text);
    }

    //设置右边是否可见
    public void set_base_act_rightVisible(int vis) {
        actRightTv.setVisibility(vis);
    }

}
