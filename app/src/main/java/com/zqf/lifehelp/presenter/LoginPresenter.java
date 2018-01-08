package com.zqf.lifehelp.presenter;


import com.zqf.lifehelp.model.api.ApiService;

public class LoginPresenter implements LoginContract.Presenter {
    private LoginContract.View view;
    private ApiService apiService;

    public LoginPresenter(LoginContract.View view, ApiService apiService) {
        this.view = view;
        this.apiService = apiService;
    }

    @Override
    public void postLogin(String codeToken) {

    }
}
