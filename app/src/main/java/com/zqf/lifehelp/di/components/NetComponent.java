package com.zqf.lifehelp.di.components;

import com.zqf.lifehelp.di.modules.NetModule;
import com.zqf.lifehelp.model.api.ApiService;

import javax.inject.Singleton;

import dagger.Component;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

@Component(modules = NetModule.class)
@Singleton
public interface NetComponent {
    ApiService getApiService();

    OkHttpClient getOkHttp();

    Retrofit getRetrofit();
}
