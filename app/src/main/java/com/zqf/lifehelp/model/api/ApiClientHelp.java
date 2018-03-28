package com.zqf.lifehelp.model.api;

import android.content.Context;

import com.google.gson.GsonBuilder;
import com.zqf.lifehelp.model.api.factory.StringConverterFactory;
import com.zqf.lifehelp.model.api.okhttp.OkHttpClientHelp;
import com.zqf.lifehelp.utils.Constants;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * class from Retrofit相关API基本配置
 * Created by zqf
 * Time 2018/1/2 11:27
 */
public class ApiClientHelp {

    /**
     * GsonConverterFactory解析器
     */
    private GsonConverterFactory factory = GsonConverterFactory.create(new GsonBuilder().create());
    /**
     * ApiClientHelp 对象引用
     */
    private static ApiClientHelp instance = null;
    /**
     * Retrofit
     */
    private Retrofit mRetrofit = null;

    public static ApiClientHelp getInstance(Context context) {
        if (instance == null) {
            instance = new ApiClientHelp(context);
        }
        return instance;
    }

    /**
     * OkHttpClientHelp辅助类
     */
    private OkHttpClientHelp mOkHttpClientHelp;

    private ApiClientHelp(Context mContext) {
        mOkHttpClientHelp = new OkHttpClientHelp(mContext);
        init();
    }

    /**
     * 初始化
     */
    private void init() {
        resetApp();
    }

    /**
     * 初始化Retrofit的配置
     */
    private void resetApp() {
        mRetrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)//服务器地址
                .client(mOkHttpClientHelp.getOkHttp())//请求的框架
                .addConverterFactory(factory)//统一解析
                .addConverterFactory(StringConverterFactory.create())//自定义解析
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())//添加RxJava
                .build();
    }

    /**
     * get--ApiService对象
     *
     * @return ApiService
     */
    public ApiService getServer() {
        return mRetrofit.create(ApiService.class);
    }
}
