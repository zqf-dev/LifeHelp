package com.zqf.lifehelp.view.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import com.zqf.lifehelp.R;
import com.zqf.lifehelp.view.adapter.WeatherAdapter;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * class from 天气预报
 * Created by zqf
 * Time 2017/6/15 16:03
 */
public class WeatherActivity extends Activity {

    @Bind(R.id.weather_recycle)
    RecyclerView weatherRecycle;
    private WeatherAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.weather_layout);
        ButterKnife.bind(this);
    }
}
