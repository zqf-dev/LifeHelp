package com.zqf.lifehelp.view.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;

import com.zqf.lifehelp.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * class from
 * Created by zqf
 * Time 2017/6/7 9:51
 */

public class ContentFg extends Fragment {
    @Bind(R.id.container)
    FrameLayout container;
    @Bind(R.id.controller_hall)
    RadioButton controllerHall;
    @Bind(R.id.controller_bgsyy)
    RadioButton controllerBgsyy;
    @Bind(R.id.controller_me)
    RadioButton controllerMe;
    @Bind(R.id.radiogroup)
    RadioGroup radiogroup;
    @Bind(R.id.content_main)
    RelativeLayout contentMain;
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    private FgHome mFgHome;
    private FgDisc mFgDisc;
    private FgMes mFgMes;
    private Activity mActivity;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity = getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View conten_view = inflater.inflate(R.layout.activi_main_bar, container, false);
        ButterKnife.bind(this, conten_view);
        DrawerLayout mDrawerLayout = (DrawerLayout) mActivity.findViewById(R.id.main_drawer);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                mActivity, mDrawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        radiogroup.check(R.id.controller_hall);//模拟选中首页效果
        select(1);
        return conten_view;
    }

    /**
     * 选择fragment
     */
    private void select(int i) {
        FragmentManager manager = getChildFragmentManager();
        FragmentTransaction ft = manager.beginTransaction();
        hidtFragment(ft);
        switch (i) {
            case 1:
                if (mFgHome == null) {
                    mFgHome = new FgHome();
                    ft.add(R.id.container, mFgHome);
                } else {
                    ft.show(mFgHome);
                }
                toolbar.setTitle("首页");
                break;
            case 2:
                if (mFgDisc == null) {
                    mFgDisc = new FgDisc();
                    ft.add(R.id.container, mFgDisc);
                } else {
                    ft.show(mFgDisc);
                }
                toolbar.setTitle("发现");
                break;
            case 3:
                if (mFgMes == null) {
                    mFgMes = new FgMes();
                    ft.add(R.id.container, mFgMes);
                } else {
                    ft.show(mFgMes);
                }
                toolbar.setTitle("个人");
                break;
        }
        ft.commitAllowingStateLoss();
    }

    /**
     * hidtFragment
     * 隐藏fragmnet
     * fragmentTransaction
     */
    private void hidtFragment(FragmentTransaction ft) {
        if (mFgHome != null) {
            ft.hide(mFgHome);
        }
        if (mFgDisc != null) {
            ft.hide(mFgDisc);
        }
        if (mFgMes != null) {
            ft.hide(mFgMes);
        }
    }

    @OnClick({R.id.controller_hall, R.id.controller_bgsyy, R.id.controller_me})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.controller_hall:
                select(1);
                break;
            case R.id.controller_bgsyy:
                select(2);
                break;
            case R.id.controller_me:
                select(3);
                break;
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
