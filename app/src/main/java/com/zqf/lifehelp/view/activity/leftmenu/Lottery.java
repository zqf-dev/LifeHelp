package com.zqf.lifehelp.view.activity.leftmenu;

import com.zqf.lifehelp.factory.base.BaseActivity;
import com.zqf.lifehelp.presenter.ILotteryPresenter;
import com.zqf.lifehelp.presenter.LotteryPresenter;

/**
 * Created by zqf on 2018/4/10.
 * 彩票开奖
 */

public class Lottery extends BaseActivity<LotteryPresenter> implements ILotteryPresenter {
    @Override
    protected LotteryPresenter createPresenter() {
        return null;
    }

    @Override
    protected int provideContentViewId() {
        return 0;
    }

    @Override
    public void onGetLotteryListSuccess() {

    }

    @Override
    public void onError() {

    }
}
