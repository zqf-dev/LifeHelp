package com.zqf.lifehelp.presenter;

import com.zqf.lifehelp.model.QueryIDBean;

/**
 * Created by zqf on 2018/3/31.
 * 身份证接口
 */

public interface IQueryIdPresenter {

    void onGetQueryIDDataSuccess(QueryIDBean bean);

    void onError();
}
