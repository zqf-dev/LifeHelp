package com.zqf.lifehelp.di.modules;

import com.zqf.lifehelp.presenter.WeatherContract;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Mr.Yangxiufeng
 * DATE 2016/10/22
 * owspace
 */
@Module
public class WeatherModule {
    private WeatherContract.View mView;

    public WeatherModule(WeatherContract.View mView) {
        this.mView = mView;
    }
    @Provides
    public WeatherContract.View provideView(){
        return mView;
    }
}
