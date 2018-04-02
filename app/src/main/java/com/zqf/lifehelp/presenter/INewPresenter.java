package com.zqf.lifehelp.presenter;

import com.zqf.lifehelp.model.TabModel;

import java.util.List;

/**
 * Created by zqf on 2018/3/31.
 * 新闻接口
 */

public interface INewPresenter {

    void onGetNewsListSuccess(List<TabModel.ResultBean.ListBean> newList, String tipInfo);

    void  onError();
}
