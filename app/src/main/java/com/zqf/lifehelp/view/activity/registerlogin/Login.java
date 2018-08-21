package com.zqf.lifehelp.view.activity.registerlogin;

import android.os.Bundle;
import android.support.annotation.NonNull;

import com.blankj.utilcode.util.ToastUtils;
import com.hannesdorfmann.mosby.mvp.MvpActivity;
import com.zqf.lifehelp.R;
import com.zqf.lifehelp.presenter.ILoginView;
import com.zqf.lifehelp.presenter.LoginPresenter;

/**
 * Created by zqf on 2018/7/12.
 * 登录
 */

public class Login extends MvpActivity<ILoginView, LoginPresenter> implements ILoginView {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout);
    }

    @NonNull
    @Override
    public LoginPresenter createPresenter() {
        return new LoginPresenter();
    }

    @Override
    public void login() {
        getPresenter().loginHxServer("", "");
    }

    @Override
    public void error() {
        ToastUtils.showShort("登录失败！请重试");
    }
}
