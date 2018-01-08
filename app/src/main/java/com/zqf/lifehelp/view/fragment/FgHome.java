package com.zqf.lifehelp.view.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.orhanobut.logger.Logger;
import com.shizhefei.view.indicator.IndicatorViewPager;
import com.shizhefei.view.indicator.ScrollIndicatorView;
import com.shizhefei.view.indicator.slidebar.DrawableBar;
import com.shizhefei.view.indicator.slidebar.ScrollBar;
import com.shizhefei.view.indicator.transition.OnTransitionTextListener;
import com.zqf.lifehelp.R;
import com.zqf.lifehelp.app.App;
import com.zqf.lifehelp.model.entity.HomeTag;
import com.zqf.lifehelp.service.manage.DataManager;
import com.zqf.lifehelp.utils.Constants;

import butterknife.Bind;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by zqf on 2017/6/6.
 * 首页
 */

public class FgHome extends Fragment {

    @Bind(R.id.tab_indicator)
    ScrollIndicatorView tabIndicator;
    @Bind(R.id.hoem_vp)
    ViewPager hoemVp;
    IndicatorViewPager mIndicatorViewPager;
    private DataManager mDataManager;
    private Activity mActivity;
    private String[] mTags_name;
    private String[] mTags_cid;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity = getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View home_view = inflater.inflate(R.layout.home_layout, container, false);
        ButterKnife.bind(this, home_view);
        mDataManager = new DataManager(mActivity);
        hometag_data();
        return home_view;
    }

    private void init() {
        tabIndicator.setBackgroundColor(ContextCompat.getColor(mActivity, R.color.title_color));
        tabIndicator.setScrollBar(new DrawableBar(mActivity, R.drawable.round_border_white_selector, ScrollBar.Gravity.CENTENT_BACKGROUND) {
            @Override
            public int getWidth(int tabWidth) {
                return tabWidth - dipToPix(10);
            }

            @Override
            public int getHeight(int tabHeight) {
                return tabHeight - dipToPix(10);
            }
        });
        int SelectTextColor = ContextCompat.getColor(mActivity, R.color.title_color);//选中的字体颜色
        int unSelectTextColor = ContextCompat.getColor(mActivity, R.color.white);//未选中的字体颜色
        // 设置滚动监听
        tabIndicator.setOnTransitionListener(new OnTransitionTextListener().setColor(SelectTextColor, unSelectTextColor));
        hoemVp.setOffscreenPageLimit(1);
        mIndicatorViewPager = new IndicatorViewPager(tabIndicator, hoemVp);
        tabIndicator.setSplitAuto(true);//自动布局以便tag标签增加或者减少
        tabIndicator.setPinnedTabView(true);
        tabIndicator.setPinnedShadow(R.mipmap.tabshadow, dipToPix(4));//固定第一个Tab
        tabIndicator.setPinnedTabBgColor(ContextCompat.getColor(mActivity, R.color.title_color));
        mIndicatorViewPager.setAdapter(new FgHomeTabAdapter(getChildFragmentManager()));
    }

    /**
     * 根据dip值转化成px值
     */
    private int dipToPix(float dip) {
        int size = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dip, getResources().getDisplayMetrics());
        return size;
    }

    private void hometag_data() {
        String hometag = App.getSp().getString("tag_name", "0");
        String homecid = App.getSp().getString("tags_cid", "0");
        if (TextUtils.isEmpty(hometag)
                || homecid.equals("0")) {
            //没有首页标签tag网络请求保存
            mDataManager.getHomeTags(Constants.MOBKEY).enqueue(new Callback<HomeTag>() {
                @Override
                public void onResponse(Call<HomeTag> call, Response<HomeTag> response) {
                    Logger.i("LifeHelp", "Tag--" + response.body().toString());
                    if (response.isSuccessful()) {
                        HomeTag homeTag = response.body();
                        mTags_name = new String[homeTag.getResult().size()];
                        mTags_cid = new String[homeTag.getResult().size()];
                        StringBuilder sb1 = new StringBuilder();
                        StringBuilder sb2 = new StringBuilder();
                        for (int i = 0; i < homeTag.getResult().size(); i++) {
                            mTags_name[i] = homeTag.getResult().get(i).getName();
                            mTags_cid[i] = homeTag.getResult().get(i).getCid();
                            sb1.append(homeTag.getResult().get(i).getName());
                            sb1.append(",");
                            sb2.append(homeTag.getResult().get(i).getCid());
                            sb2.append(",");
                        }
                        App.getSp().put("tag_name", sb1.toString());
                        App.getSp().put("tags_cid", sb2.toString());
                        init();//初始化
                    }
                }

                @Override
                public void onFailure(Call<HomeTag> call, Throwable t) {

                }
            });
        } else {
            mTags_name = hometag.split(",");
            mTags_cid = homecid.split(",");
            Logger.i(mTags_name.length + "");
            init();//初始化
        }
    }

    private class FgHomeTabAdapter extends IndicatorViewPager.IndicatorFragmentPagerAdapter {

        public FgHomeTabAdapter(FragmentManager fragmentManager) {
            super(fragmentManager);
        }

        @Override
        public int getCount() {
            return mTags_name.length;
        }

        @Override
        public View getViewForTab(int position, View convertView, ViewGroup container) {
            if (convertView == null) {
                convertView = LayoutInflater.from(mActivity).inflate(R.layout.tab_top, container, false);
            }
            TextView textView = (TextView) convertView;
            textView.setText(mTags_name[position % mTags_name.length]);
            int padding = dipToPix(15);
            textView.setPadding(padding, 0, padding, 0);
            return convertView;
        }

        @Override
        public Fragment getFragmentForPage(int position) {
            FgHomeTab fragment = new FgHomeTab();
            Bundle bundle = new Bundle();
            bundle.putString("tags_cid", mTags_cid[position]);
            fragment.setArguments(bundle);
            return fragment;
        }

        @Override
        public int getItemPosition(Object object) {
            //这是ViewPager适配器的特点,有两个值 POSITION_NONE，POSITION_UNCHANGED，
            // 默认就是POSITION_UNCHANGED,
            // 表示数据没变化不用更新.notifyDataChange的时候重新调用getViewForPage
            return PagerAdapter.POSITION_NONE;
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
