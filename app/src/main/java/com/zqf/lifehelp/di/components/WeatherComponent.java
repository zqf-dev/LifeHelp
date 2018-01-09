package com.zqf.lifehelp.di.components;

import com.zqf.lifehelp.di.modules.WeatherModule;
import com.zqf.lifehelp.di.scopes.UserScope;

import dagger.Component;

/**
 * class from
 * Created by zqf
 * Time 2018/1/9 10:11
 */
@UserScope
@Component(modules = WeatherModule.class, dependencies = NetComponent.class)
public interface WeatherComponent {

}
