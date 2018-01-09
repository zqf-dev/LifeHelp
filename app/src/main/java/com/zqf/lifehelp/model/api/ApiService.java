package com.zqf.lifehelp.model.api;

import com.zqf.lifehelp.model.entity.WeatherModel;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * class from Request_服务接口
 * Created by zqf
 * Time 2018/1/2 11:11
 */

public interface ApiService {

    /**
     * 根据城市名查询天气
     *
     * @param appkey   key
     * @param city     城市名
     * @param province 省份名
     * @return
     */
    @GET("weather/query")
    Observable<WeatherModel.ResultBean> getWeathApi(@Query("key") String appkey,
                                         @Query("city") String city,
                                         @Query("province") String province);

}
