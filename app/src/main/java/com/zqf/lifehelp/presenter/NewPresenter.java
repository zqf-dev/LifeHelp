package com.zqf.lifehelp.presenter;

import com.orhanobut.logger.Logger;
import com.zqf.lifehelp.factory.base.BasePresenter;
import com.zqf.lifehelp.model.TabModel;
import com.zqf.lifehelp.utils.Constants;

import java.util.ArrayList;
import java.util.List;

import rx.Subscriber;

/**
 * Created by zqf on 2018/3/31.
 */

public class NewPresenter extends BasePresenter<INewPresenter> {


    public NewPresenter(INewPresenter view) {
        super(view);
    }


    public void getNewsList() {
        addSubscription(mApiService.getCommTabData(Constants.MOBKEY, "1", 1, 10), new Subscriber<TabModel>() {

            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                Logger.d(e.getMessage());
                mView.onError();
            }

            @Override
            public void onNext(TabModel tabModel) {
                List<TabModel.ResultBean.ListBean> newsList = new ArrayList<>();
                Logger.d("--获取到数据"+tabModel);
                mView.onGetNewsListSuccess(newsList, "10");
            }
        });
    }
}
