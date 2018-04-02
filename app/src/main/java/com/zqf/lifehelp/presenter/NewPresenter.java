package com.zqf.lifehelp.presenter;

import com.orhanobut.logger.Logger;
import com.zqf.lifehelp.factory.base.BasePresenter;
import com.zqf.lifehelp.model.TabModel;
import com.zqf.lifehelp.utils.Constants;

import java.util.ArrayList;
import java.util.List;

import rx.Subscriber;

public class NewPresenter extends BasePresenter<INewPresenter> {

    List<TabModel.ResultBean.ListBean> mNewServerList = new ArrayList<>();

    public NewPresenter(INewPresenter view) {
        super(view);
    }


    public void getNewsList(String mcid, int page) {
        addSubscription(mApiService.getCommTabData(Constants.MOBKEY, mcid, page, Constants.page_size)
                , new Subscriber<TabModel>() {

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
                        Logger.d("--获取到数据" + tabModel);
                        mNewServerList.clear();
                        mNewServerList.addAll(tabModel.getResult().getList());
                        mView.onGetNewsListSuccess(mNewServerList, "10");
                    }
                });
    }
}
