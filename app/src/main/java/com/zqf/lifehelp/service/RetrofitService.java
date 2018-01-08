package com.zqf.lifehelp.service;

import com.zqf.lifehelp.model.entity.HomeTag;
import com.zqf.lifehelp.model.entity.TabModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by zqf on 2017/5/22.
 * 接口
 */

public interface RetrofitService {
    /**
     * 首页标签tag
     *
     * @param key-->Mob申请的Key
     * @return HomeTag标签对象
     */
    @GET("/wx/article/category/query")
    Call<HomeTag> getHomeTag(@Query("key") String key);

    /**
     * 首页对应每个标签下的数据
     *
     * @param key-->Mob申请的Key
     * @param cid-->对应标签的Cid
     * @param page-->页数
     * @param size-->大小
     * @return TabModel对象
     */
    @GET("/wx/article/search")
    Call<TabModel> getTabData(@Query("key") String key,
                              @Query("cid") String cid,
                              @Query("page") int page,
                              @Query("size") int size);

    /**
     * 菜谱大全分类标签
     *
     * @param key-->Mob申请的Key
     * @return MenuSort对象
     */
    @GET("/v1/cook/category/query")
    Call<Object> getMenuData(@Query("key") String key);
}
