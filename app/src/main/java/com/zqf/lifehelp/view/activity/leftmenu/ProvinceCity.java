package com.zqf.lifehelp.view.activity.leftmenu;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;

import com.orhanobut.logger.Logger;
import com.zqf.lifehelp.R;
import com.zqf.lifehelp.model.LocalCityModel;
import com.zqf.lifehelp.utils.Constants;
import com.zqf.lifehelp.utils.Util;
import com.zqf.lifehelp.utils.widget.LoadingDialog;
import com.zqf.lifehelp.view.adapter.CityAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * class from
 * Created by zqf
 * Time 2018/1/10 15:02
 */

public class ProvinceCity extends Activity {

    @Bind(R.id.act_left_iv)
    ImageView actLeftIv;
    @Bind(R.id.act_left_tv)
    TextView actLeftTv;
    @Bind(R.id.act_right_tv)
    TextView actRightTv;
    @Bind(R.id.city_expanlistview)
    ExpandableListView cityExpanlistview;
    //    @Bind(R.id.city_swiperefresh)
//    SwipeRefreshLayout citySwiperefresh;
    private LoadingDialog mDialog;
    //private List<CityModel.ResultBean> mList = new ArrayList<>();
    //private List<String> mList_Province = new ArrayList<>();
    private List<LocalCityModel> mList_Province = new ArrayList<>();
    private List<LocalCityModel.ChildCityBean> mList_city;
    private String mJson;
    private CityAdapter mCityAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.province_city_layout);
        ButterKnife.bind(this);
        actLeftTv.setText("添加城市");
        mJson = Util.ReadDayDayString(this, Constants.CityAssets);
        LocalJson();
        cityExpanlistview.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView expandableListView, View view, int i, int i1, long l) {
                String ss = mList_Province.get(i).getCityBeanList().get(i1).getDistrict();
                Logger.e(ss);
                return true;
            }
        });
    }

    //本地解析
    private void LocalJson() {
        if (!TextUtils.isEmpty(mJson)) {
            try {
                JSONObject object = new JSONObject(mJson);
                JSONArray array = object.optJSONArray("result").optJSONObject(0).optJSONArray("city");
                for (int i = 0; i < array.length(); i++) {
                    JSONObject json1 = array.optJSONObject(i);
                    String city = json1.optString("city");
                    JSONArray array1 = json1.optJSONArray("district");
                    mList_city = new ArrayList<>();
                    for (int j = 0; j < array1.length(); j++) {
                        String district = array1.optJSONObject(j).optString("district");
                        mList_city.add(new LocalCityModel.ChildCityBean(district));
                    }
                    mList_Province.add(new LocalCityModel(city, mList_city));
                }
                mCityAdapter = new CityAdapter(this, mList_Province);
                cityExpanlistview.setAdapter(mCityAdapter);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
//    private void requesdata() {
//        mDialog = LoadingDialog.show(this, "", null);
//        App.getInstance().getDataManager(this).getCityApi(Constants.MOBKEY).enqueue(new Callback<JsonObject>() {
//            @Override
//            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
//                mDialog.destorydialog();
//                try {
//                    JSONObject obj = new JSONObject(response.body() + "");
//                    Logger.e("---\n" + obj.toString());
//
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//            }
//
//            @Override
//            public void onFailure(Call<JsonObject> call, Throwable t) {
//                Logger.e("---onFailure");
//                mDialog.destorydialog();
//            }
//        });
//    }

    @OnClick(R.id.act_left_iv)
    public void onViewClicked() {
        finish();
    }
}
