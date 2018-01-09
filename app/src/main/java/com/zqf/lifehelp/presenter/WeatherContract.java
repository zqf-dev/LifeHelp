package com.zqf.lifehelp.presenter;

import com.zqf.lifehelp.model.entity.WeatherModel;

import java.util.List;


public interface WeatherContract {
    interface Presenter {
        void getWeathParams(String city, String province);
    }

    interface View {
        void showLoading();

        void dismissLoading();

        void showNoData();

        void showNoMore();

        void updateListUI(List<WeatherModel.ResultBean> itemList);

        void showOnFailure();
    }
}
