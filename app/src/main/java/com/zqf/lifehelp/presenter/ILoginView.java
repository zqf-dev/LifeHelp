package com.zqf.lifehelp.presenter;

import com.hannesdorfmann.mosby.mvp.MvpView;

/**
 * Created by zqf on 2018/7/12.
 * 登录
 */

public interface ILoginView extends MvpView {

    void login();

    void error();
}
