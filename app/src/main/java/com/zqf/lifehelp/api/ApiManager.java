package com.zqf.lifehelp.api;

import com.google.gson.JsonObject;
import com.zqf.lifehelp.model.HomeTag;
import com.zqf.lifehelp.model.TabModel;
import com.zqf.lifehelp.model.WeatherModel;

import retrofit2.Call;

/**
 * Created by zqf on 2017/5/23.
 * 数据管理类--->方便调用RetrofitService 中定义的方法
 */
public class ApiManager {
    private ApiService mRetrofitService;

    public ApiManager() {
        mRetrofitService = RetrofitHelper.getInstance().getServer();
    }

    /**
     * @param name-->key值
     * @return -->HomeTag实体类
     */
    public Call<HomeTag> getHomeTags(String name) {
        return mRetrofitService.getHomeTag(name);
    }

    /**
     * @param key-->key值
     * @param cid-->tagid值
     * @param page-->当前页数
     * @param size-->每一页数据大小
     * @return -->TabModel实体类
     */
    public Call<TabModel> getTabData(String key, String cid, int page, int size) {
        return mRetrofitService.getTabData(key, cid, page, size);
    }

    /**
     * @param key->key值
     * @return ->Menu实体类
     */
    public Call<Object> getMenuData(String key) {
        return mRetrofitService.getMenuData(key);
    }

    /**
     * 天气
     *
     * @param city
     * @param province
     * @return
     */
    public Call<WeatherModel> getWeathApi(String key, String city, String province) {
        return mRetrofitService.getWeathApi(key, city, province);
    }

    /**
     * 获取城市列表
     *
     * @param key --mob_key
     * @return
     */
    public Call<JsonObject> getCityApi(String key) {
        return mRetrofitService.getCityApi(key);
    }
}
