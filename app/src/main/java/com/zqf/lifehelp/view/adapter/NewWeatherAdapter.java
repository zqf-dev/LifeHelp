package com.zqf.lifehelp.view.adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.text.TextUtils;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zqf.lifehelp.R;
import com.zqf.lifehelp.model.WeatherModel;

import java.util.List;

/**
 * class from 天气适配器
 * Created by zqf
 * Time 2018/1/9 9:31
 */

public class NewWeatherAdapter extends BaseQuickAdapter<WeatherModel.ResultBean.FutureBean, BaseViewHolder> {
    public NewWeatherAdapter(@LayoutRes int layoutResId, @Nullable List<WeatherModel.ResultBean.FutureBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, WeatherModel.ResultBean.FutureBean item) {
        helper.setText(R.id.week_day_tv, item.getWeek());
        String split_date = item.getDate().split("-")[1];
        if (!TextUtils.isEmpty(split_date)) {
            helper.setText(R.id.date_tv, item.getDate());
        }
        helper.setText(R.id.temperature_tv, item.getTemperature());
        if (item.getDayTime().equals(item.getNight())) {
            helper.setText(R.id.weather_tv, item.getDayTime());
        } else {
            helper.setText(R.id.weather_tv, item.getDayTime() + "转" + item.getNight());
        }
        String wind1 = item.getWind().split(" ")[0] + "";
        String wind2 = item.getWind().split(" ")[1] + "";
        if (!TextUtils.isEmpty(wind1)) {
            helper.setText(R.id.wind_tv1, wind1);
        }
        if (!TextUtils.isEmpty(wind2)) {
            helper.setText(R.id.wind_tv2, wind2);
        }
    }
}
