package com.zqf.lifehelp.api;

import com.google.gson.JsonObject;
import com.zqf.lifehelp.model.HomeTag;
import com.zqf.lifehelp.model.TabModel;
import com.zqf.lifehelp.model.WeatherModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by zqf on 2017/5/22.
 * 接口
 */

public interface ApiService {
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
     * 首页对应每个标签下的数据
     *
     * @param key-->Mob申请的Key
     * @param cid-->对应标签的Cid
     * @param page-->页数
     * @param size-->大小
     * @return TabModel对象
     */
    @GET("/wx/article/search")
    Observable<TabModel> getCommTabData(@Query("key") String key,
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

    /**
     * 根据城市名查询天气
     *
     * @param appkey   key
     * @param city     城市名
     * @param province 省份名
     * @return
     */
    @GET("/v1/weather/query")
    Call<WeatherModel> getWeathApi(@Query("key") String appkey,
                                   @Query("city") String city,
                                   @Query("province") String province);

    /**
     * 获取城市名列表
     *
     * @param appkey key
     */
    @GET("/v1/weather/citys")
    Call<JsonObject> getCityApi(@Query("key") String appkey);

}
