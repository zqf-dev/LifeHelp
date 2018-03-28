package com.zqf.lifehelp.app;

import android.app.Application;
import android.content.Context;

import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.Utils;
import com.orhanobut.logger.LogLevel;
import com.orhanobut.logger.Logger;
import com.tencent.smtt.sdk.QbSdk;
import com.zqf.lifehelp.BuildConfig;
import com.zqf.lifehelp.service.manage.DataManager;
import com.zqf.lifehelp.utils.LogUtil;

/**
 * Created by zqf on 2017/5/31.
 * pplication类-->设置应用全局的一些属性
 */
public class App extends Application {

    private static SPUtils mSPUtils;

    private static App instance;

    private static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        mContext = getApplicationContext();
        Utils.init(this);
        mSPUtils = SPUtils.getInstance("life");
        initLogger();
        QbSdk.PreInitCallback preInitCallback = new QbSdk.PreInitCallback() {
            @Override
            public void onCoreInitFinished() {

            }

            @Override
            public void onViewInitFinished(boolean b) {
                LogUtil.logD("x5初始化返回结果--" + b);
            }
        };
        QbSdk.initX5Environment(getApplicationContext(), preInitCallback);
    }

    /**
     * Logger
     * init("mytag")    //LOG TAG默认是PRETTYLOGGER
     * methodCount(3)                 // 决定打印多少行（每一行代表一个方法）默认：2
     * hideThreadInfo()               // 隐藏线程信息 默认：显示
     * logLevel(LogLevel.NONE)        // 是否显示Log 默认：LogLevel.FULL（全部显示）
     * methodOffset(2)                // 默认：0
     * logAdapter(new AndroidLogAdapter()); //可以自己构造适配器默认：AndroidLogAdapter
     */
    private void initLogger() {
        LogLevel logLevel;
        if (!BuildConfig.API_ENV) {
            logLevel = LogLevel.FULL;
        } else {
            logLevel = LogLevel.NONE;
        }
        Logger.init("LifeHelp")
                .methodCount(3)
                .logLevel(logLevel);
    }

    public DataManager getDataManager(Context context) {
        return new DataManager(context);
    }

    //获取Sp实例
    public static SPUtils getSp() {
        return mSPUtils;
    }

    //全局获取App
    public static App get(Context context) {
        return (App) context.getApplicationContext();
    }

    /**
     * 全局获取App
     *
     * @return App
     */
    public static App getInstance() {
        return instance;
    }

    //获取Context全局实例
    public static Context getCon() {
        return mContext;
    }
}
