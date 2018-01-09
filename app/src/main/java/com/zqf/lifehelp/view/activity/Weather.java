package com.zqf.lifehelp.view.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.zqf.lifehelp.R;
import com.zqf.lifehelp.model.entity.WeatherModel;
import com.zqf.lifehelp.service.manage.DataManager;
import com.zqf.lifehelp.utils.Constants;
import com.zqf.lifehelp.utils.LogUtil;
import com.zqf.lifehelp.view.adapter.NewWeatherAdapter;
import com.zqf.lifehelp.view.customview.recycler.SpacesItemDecoration;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * class from 天气预报
 * Created by zqf
 * Time 2017/6/15 16:03
 */
public class Weather extends Activity {

    @Bind(R.id.weather_recycle)
    RecyclerView weatherRecycle;
    @Bind(R.id.act_left_iv)
    ImageView actLeftIv;
    @Bind(R.id.act_left_tv)
    TextView actLeftTv;
    @Bind(R.id.act_right_tv)
    TextView actRightTv;
    private NewWeatherAdapter mAdapter;
    private List<WeatherModel.ResultBean.FutureBean> mList = new ArrayList<>();
    private DataManager mManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.weather_layout);
        ButterKnife.bind(this);
        mManager = new DataManager(this);
        initView();
        requestdata();
    }

    private void initView() {
        actLeftTv.setText(R.string.fature_ten_wearther_str);
        //分割线
        int leftRight = (int) getResources().getDimension(R.dimen.size_10dp);
        int topBottom = (int) getResources().getDimension(R.dimen.size_0_5dp);
        int lineColor = ContextCompat.getColor(this, R.color.white);
        weatherRecycle.addItemDecoration(new SpacesItemDecoration(leftRight, topBottom, lineColor));
        //创建布局管理
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mAdapter = new NewWeatherAdapter(R.layout.new_weather_recy_item, mList);
        weatherRecycle.setLayoutManager(layoutManager);
        weatherRecycle.setAdapter(mAdapter);
        mAdapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_RIGHT);

    }

    /**
     * 请求数据
     */
    private void requestdata() {
        mManager.getWeathApi(Constants.MOBKEY, "贵阳", "贵州").enqueue(new Callback<WeatherModel>() {
            @Override
            public void onResponse(Call<WeatherModel> call, Response<WeatherModel> response) {
                LogUtil.logD("请求数据--body-" + response.body());
                if (response.body().getRetCode().equals("200")) {
                    for (WeatherModel.ResultBean bean : response.body().getResult()) {
                        mList.addAll(bean.getFuture());
                    }
                    mAdapter.notifyDataSetChanged();
                }
                LogUtil.logD("mList" + mList.size());
            }

            @Override
            public void onFailure(Call<WeatherModel> call, Throwable t) {
                LogUtil.logD("请求数据--onError-");
            }
        });
//        mManager.getWeathApi(Constants.MOBKEY, "贵阳", "贵州").subscribeOn(Schedulers.io())
//                .subscribeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Subscriber<WeatherModel>() {
//                    @Override
//                    public void onCompleted() {
//                        LogUtil.logD("请求数据--onCompleted-");
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        LogUtil.logD("请求数据--onError-");
//                    }
//
//                    @Override
//                    public void onNext(WeatherModel weatherModel) {
//                        LogUtil.logD("请求数据--body-" + weatherModel.toString());
//                        if (weatherModel.getRetCode().equals("200")) {
//                            for (WeatherModel.ResultBean bean : weatherModel.getResult()) {
//                                mList.addAll(bean.getFuture());
//                            }
//                            mAdapter.notifyDataSetChanged();
//                        }
//                        LogUtil.logD("mList" + mList.size());
//                    }
//                });
    }

    @OnClick(R.id.act_left_iv)
    public void onViewClicked() {
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mList != null) {
            mList.clear();
            mList = null;
        }
    }
}
