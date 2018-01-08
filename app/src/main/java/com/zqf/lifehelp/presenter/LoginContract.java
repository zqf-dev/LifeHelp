package com.zqf.lifehelp.presenter;


import com.zqf.lifehelp.model.entity.LoginEntity;

public interface LoginContract {
    interface Presenter {
        void postLogin(String itemId);
    }

    interface View {
        void showLoading();

        void dismissLoading();

        void updateListUI(LoginEntity detailEntity);

        void showOnFailure();
    }
}
