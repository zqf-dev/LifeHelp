package com.zqf.lifehelp.app;

import android.app.Application;
import android.content.Context;

import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.Utils;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.FormatStrategy;
import com.orhanobut.logger.Logger;
import com.orhanobut.logger.PrettyFormatStrategy;
import com.tencent.smtt.sdk.QbSdk;
import com.zqf.lifehelp.BuildConfig;
import com.zqf.lifehelp.api.RetrofitHelper;
import com.zqf.lifehelp.db.help.RealmHelp;
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
        //App的实例
        instance = this;
        //ApplicationContext对象
        mContext = getApplicationContext();
        //工具类初始化
        Utils.init(this);
        //SharePreference
        mSPUtils = SPUtils.getInstance("life");
        //初始化日志
        initLogger();
        //腾讯X5WebView内核初始化
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
        //初始化Retrofit
        RetrofitHelper.getInstance().init();
        //初始化Realm数据库
        RealmHelp.getInstance().init(this);
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
        //Logger 2.1.1版本使用方法
        FormatStrategy formatStrategy = PrettyFormatStrategy.newBuilder()
                .showThreadInfo(true)  //（可选）是否显示线程信息。 默认值为true
                .methodCount(2)         // （可选）要显示的方法行数。 默认2
                .methodOffset(7)        // （可选）隐藏内部方法调用到偏移量。 默认5
                .tag("LifeHelp")           //（可选）每个日志的全局标记。 默认PRETTY_LOGGER
                .build();
        if (BuildConfig.API_ENV) {
            //日志输出
            Logger.addLogAdapter(new AndroidLogAdapter(formatStrategy));
        } else {
            //上线时停止日志
            Logger.addLogAdapter(new AndroidLogAdapter() {
                @Override
                public boolean isLoggable(int priority, String tag) {
                    return BuildConfig.DEBUG;
                }
            });
        }
    }

    //获取Sp实例
    public static SPUtils getSp() {
        return mSPUtils;
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
