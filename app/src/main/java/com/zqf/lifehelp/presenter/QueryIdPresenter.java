package com.zqf.lifehelp.presenter;

import com.orhanobut.logger.Logger;
import com.zqf.lifehelp.factory.base.BasePresenter;
import com.zqf.lifehelp.model.QueryIDBean;
import com.zqf.lifehelp.utils.Constants;

import rx.Subscriber;

/**
 * Created by zqf on 2018/4/3.
 * 身份证Presenter
 */

public class QueryIdPresenter extends BasePresenter<IQueryIdPresenter> {
    public QueryIdPresenter(IQueryIdPresenter view) {
        super(view);
    }

    public void getQueryIdInfo(String mQueryCid) {
        addSubscription(mApiService.getQueryIDInFoData(Constants.MOBKEY, mQueryCid)
                , new Subscriber<QueryIDBean>() {

                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Logger.d(e.getMessage());
                        mView.onError();
                    }

                    @Override
                    public void onNext(QueryIDBean queryIDBean) {
                        Logger.d("--获取身份证数据" + queryIDBean);
                        if (queryIDBean != null && queryIDBean.getRetCode().equals("200")) {
                            mView.onGetQueryIDDataSuccess(queryIDBean.getResult());
                        }
                    }
                });
    }
}
