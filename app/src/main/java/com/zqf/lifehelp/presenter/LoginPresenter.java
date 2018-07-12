package com.zqf.lifehelp.presenter;

import com.hannesdorfmann.mosby.mvp.MvpNullObjectBasePresenter;
import com.hyphenate.EMCallBack;
import com.hyphenate.chat.EMClient;
import com.orhanobut.logger.Logger;

/**
 * Created by zqf on 2018/7/12.
 * 登录数据模型
 */

public class LoginPresenter extends MvpNullObjectBasePresenter<ILoginView> {

    //登录
    public void loginHxServer(String userName, String password) {
        EMClient.getInstance().login(userName, password, new EMCallBack() {//回调
            @Override
            public void onSuccess() {
                EMClient.getInstance().groupManager().loadAllGroups();
                EMClient.getInstance().chatManager().loadAllConversations();
                Logger.e("main", "登录聊天服务器成功！");
                getView().login();
            }

            @Override
            public void onProgress(int progress, String status) {

            }

            @Override
            public void onError(int code, String message) {
                getView().error();
            }
        });
    }
}
