package com.zqf.lifehelp.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.blankj.utilcode.util.LogUtils;
import com.zqf.lifehelp.R;
import com.zqf.lifehelp.app.App;
import com.zqf.lifehelp.model.entity.LeftTagModel;
import com.zqf.lifehelp.view.activity.Recipes;
import com.zqf.lifehelp.view.activity.WeatherActivity;
import com.zqf.lifehelp.view.adapter.LeftSlideRecyAdapter;
import com.zqf.lifehelp.view.customview.recycler.SpacesItemDecoration;
import com.zqf.lifehelp.utils.Util;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * class from
 * Created by zqf
 * Time 2017/6/6 17:13
 */

public class FgLeft extends Fragment {

    @Bind(R.id.left_recylv)
    RecyclerView leftRecylv;
    private LeftSlideRecyAdapter mAdapter;
    private List<LeftTagModel> mStringList = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View left_view = inflater.inflate(R.layout.left_layout, container, false);
        ButterKnife.bind(this, left_view);
        init();
        refreshData();
        return left_view;
    }

    private void init() {
        int leftRight = (int) getResources().getDimension(R.dimen.size_5dp);
        int topBottom = (int) getResources().getDimension(R.dimen.size_0_5dp);
        int lineColor = ContextCompat.getColor(App.getCon(), R.color.tinge_gray);

        leftRecylv.addItemDecoration(new SpacesItemDecoration(leftRight, topBottom, lineColor));
        leftRecylv.setLayoutManager(new LinearLayoutManager(App.getCon()));

        mAdapter = new LeftSlideRecyAdapter(mStringList);
        leftRecylv.setAdapter(mAdapter);
        mAdapter.setListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int pos = (int) view.getTag();
                Log.e("Tag", pos + "");
                switch (mStringList.get(pos).getTag_name()) {
                    case "天气预报":
                        Util.NextActivity(getActivity(), WeatherActivity.class);
                        break;
                    case "菜谱大全":
                        Util.NextActivity(getActivity(), Recipes.class);
                        break;
                    case "周公解梦":
                        break;
                    case "八字算命":
                        break;
                    case "健康知识":
                        break;
                    case "今日油价":
                        break;
                    case "基站查询":
                        break;
                    default:
                        break;
                }

            }
        });
    }

    public void refreshData() {
        String[] left_tag = App.getSp().getString("tag").split(",");
        for (int i = 0; i < left_tag.length; i++) {
            LogUtils.e("LifeHelp", left_tag[i]);
            LeftTagModel model = new LeftTagModel();
            model.setTag_name(left_tag[i]);
            mStringList.add(model);
        }
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
