package com.zqf.lifehelp.service.manage;

import android.content.Context;

import com.zqf.lifehelp.model.entity.HomeTag;
import com.zqf.lifehelp.model.entity.TabModel;
import com.zqf.lifehelp.service.RetrofitHelper;
import com.zqf.lifehelp.service.RetrofitService;

import retrofit2.Call;

/**
 * Created by zqf on 2017/5/23.
 * 数据管理类--->方便调用RetrofitService 中定义的方法
 */
public class DataManager {
    private RetrofitService mRetrofitService;

    public DataManager(Context context) {
        mRetrofitService = RetrofitHelper.getInstance(context).getServer();
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
}
