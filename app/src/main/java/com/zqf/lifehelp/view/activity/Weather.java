package com.zqf.lifehelp.view.activity;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.orhanobut.logger.Logger;
import com.zqf.lifehelp.R;
import com.zqf.lifehelp.app.App;
import com.zqf.lifehelp.model.entity.WeatherModel;
import com.zqf.lifehelp.service.manage.DataManager;
import com.zqf.lifehelp.utils.Constants;
import com.zqf.lifehelp.utils.MapLocationUtil;
import com.zqf.lifehelp.view.adapter.NewWeatherAdapter;
import com.zqf.lifehelp.view.customview.recycler.SpacesItemDecoration;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.weyye.hipermission.HiPermission;
import me.weyye.hipermission.PermissionCallback;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * class from 天气预报
 * Created by zqf
 * Time 2017/6/15 16:03
 */
public class Weather extends Activity implements AMapLocationListener, PermissionCallback {

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

    //高德地图
    private AMapLocationClient locationClient = null;
    private AMapLocationClientOption locationOption = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.weather_layout);
        ButterKnife.bind(this);
        mManager = new DataManager(this);
        initView();
        if (TextUtils.isEmpty(App.getSp().getString("province", ""))) {
            //申请定位位置权限
            HiPermission.create(this).checkSinglePermission(Manifest.permission.ACCESS_FINE_LOCATION, this);
        } else {
            requestdata(App.getSp().getString("province"), App.getSp().getString("city"));
        }
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
     *
     * @param province
     * @param city
     */
    private void requestdata(String province, String city) {
        if (city.contains("市")) {
            city = city.split("市")[0];
        }
        actRightTv.setText(city);
        mManager.getWeathApi(Constants.MOBKEY, city, province).enqueue(new Callback<WeatherModel>() {
            @Override
            public void onResponse(Call<WeatherModel> call, Response<WeatherModel> response) {
                Logger.e("请求数据--body-" + response.body());
                if (response.body().getRetCode().equals("200")) {
                    for (WeatherModel.ResultBean bean : response.body().getResult()) {
                        mList.addAll(bean.getFuture());
                    }
                    mAdapter.notifyDataSetChanged();
                }
                Logger.e("mList" + mList.size());
            }

            @Override
            public void onFailure(Call<WeatherModel> call, Throwable t) {
                Logger.e("请求数据--onError-");
            }
        });
    }

    /**
     * 销毁定位
     */
    private void destroyLocation() {
        if (null != locationClient) {
            locationClient.stopLocation();
            /**
             * 如果AMapLocationClient是在当前Activity实例化的，
             * 在Activity的onDestroy中一定要执行AMapLocationClient的onDestroy
             */
            locationClient.onDestroy();
            locationClient = null;
            locationOption = null;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mList != null) {
            mList.clear();
            mList = null;
        }
        destroyLocation();
    }

    /**
     * 初始化定位配置
     */
    private void initLocation() {
        Logger.e("开启定位");
        //初始化client
        locationClient = new AMapLocationClient(this.getApplicationContext());
        locationOption = MapLocationUtil.getDefaultOption();
        //设置定位参数
        locationClient.setLocationOption(locationOption);
        // 设置定位监听
        locationClient.setLocationListener(this);
        //开始
        locationClient.startLocation();
    }

    /**
     * 定位监听
     *
     * @param aMapLocation AMapLocation
     */
    @Override
    public void onLocationChanged(AMapLocation aMapLocation) {
        String info = MapLocationUtil.getLocationStr(aMapLocation);
        Logger.e("定位回调" + info);
        if (!TextUtils.isEmpty(info) && !info.equals("null")) {
            String province = info.split(",")[0];
            String city = info.split(",")[1];
            App.getSp().put("province", province);
            App.getSp().put("city", city);
            requestdata(province, city);
        } else {
            ToastUtils.showShort("定位失败,请添加城市");
        }
    }

    @OnClick({R.id.act_left_iv, R.id.act_right_tv})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.act_left_iv:
                finish();
                break;
            case R.id.act_right_tv:
                startActivity(new Intent(this, ProvinceCity.class));
                break;
        }
    }

    //申请定位位置权限
    @Override
    public void onClose() {

    }

    @Override
    public void onFinish() {

    }

    @Override
    public void onDeny(String permission, int position) {
        Logger.e("onDeny");
        ToastUtils.showShort("定位权限关闭,请添加城市");
        actRightTv.setText("定位失败");
    }

    @Override
    public void onGuarantee(String permission, int position) {
        Logger.e("onGuarantee");
        //权限申请完成 开启定位
        initLocation();
    }
}
