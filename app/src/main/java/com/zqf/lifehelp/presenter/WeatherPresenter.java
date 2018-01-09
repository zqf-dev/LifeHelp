package com.zqf.lifehelp.presenter;


import com.zqf.lifehelp.model.api.ApiService;
import com.zqf.lifehelp.model.entity.WeatherModel;
import com.zqf.lifehelp.utils.Constants;
import com.zqf.lifehelp.utils.LogUtil;

import javax.inject.Inject;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Mr.Yangxiufeng
 * DATE 2016/8/3
 * owspace
 */
public class WeatherPresenter implements WeatherContract.Presenter {

    private WeatherContract.View view;
    private ApiService apiService;

    @Inject
    public WeatherPresenter(WeatherContract.View view, ApiService apiService) {
        this.view = view;
        this.apiService = apiService;
    }

    @Override
    public void getWeathParams(String city, String province) {
        apiService.getWeathApi(Constants.MOBKEY, city, province)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<WeatherModel.ResultBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(WeatherModel.ResultBean resultBean) {
                        LogUtil.logD("天气--" + resultBean);
                    }
                });
    }
}
