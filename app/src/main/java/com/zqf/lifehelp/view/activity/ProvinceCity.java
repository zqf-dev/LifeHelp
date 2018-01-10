package com.zqf.lifehelp.view.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.JsonObject;
import com.orhanobut.logger.Logger;
import com.zqf.lifehelp.R;
import com.zqf.lifehelp.app.App;
import com.zqf.lifehelp.model.entity.CityModel;
import com.zqf.lifehelp.utils.Constants;
import com.zqf.lifehelp.view.widget.LoadingDialog;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
    @Bind(R.id.city_swiperefresh)
    SwipeRefreshLayout citySwiperefresh;
    private LoadingDialog mDialog;
    private List<CityModel.ResultBean> mList = new ArrayList<>();
    private List<String> mList_Province = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.province_city_layout);
        ButterKnife.bind(this);
        requesdata();
    }

    private void requesdata() {
        mDialog = LoadingDialog.show(this, "", null);
        App.getInstance().getDataManager(this).getCityApi(Constants.MOBKEY).enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                mDialog.destorydialog();
                try {
                    JSONObject obj = new JSONObject(response.body() + "");
                    Logger.e("---\n" + obj.toString());

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                Logger.e("---onFailure");
                mDialog.destorydialog();
            }
        });
    }

    @OnClick(R.id.act_left_iv)
    public void onViewClicked() {
        finish();
    }
}
