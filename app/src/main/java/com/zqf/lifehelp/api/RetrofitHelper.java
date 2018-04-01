package com.zqf.lifehelp.api;

import com.google.gson.GsonBuilder;
import com.zqf.lifehelp.api.okhttp.OkHttpClientHelp;
import com.zqf.lifehelp.utils.Constants;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by zqf on 2017/5/22.
 * Retrofit助手类
 */

public class RetrofitHelper {

    private OkHttpClient client = new OkHttpClient();//默认使用
    private OkHttpClientHelp mOkHttpClientHelp;
    private GsonConverterFactory factory = GsonConverterFactory.create(new GsonBuilder().create());
    private static RetrofitHelper instance = null;
    private Retrofit mRetrofit = null;

    public static RetrofitHelper getInstance() {
        if (instance == null) {
            instance = new RetrofitHelper();
        }
        return instance;
    }

    //私有构造
    private RetrofitHelper() {
    }

    public void init() {
//        mOkHttpClientHelp = new OkHttpClientHelp(App.getCon());
        mRetrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)//基类BASE_URL
                .client(client)//默认
//                .client(mOkHttpClientHelp.getOkHttp())//封装过的
                .addConverterFactory(factory)//Ggson解析
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())//RxJava
                .build();
    }

    public ApiService getServer() {
        return mRetrofit.create(ApiService.class);
    }
}
