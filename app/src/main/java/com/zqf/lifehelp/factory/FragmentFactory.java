package com.zqf.lifehelp.factory;


import com.zqf.lifehelp.view.fragment.CityProvinceFg;

/**
 * Created by zqf on 2017/6/6.
 * fragment工厂
 */

public class FragmentFactory {

    public static com.zqf.lifehelp.view.base.BaseFragment createMainFragment(String type) {
        com.zqf.lifehelp.view.base.BaseFragment fragment = null;
        switch (type) {
            case "CityProvince":
                fragment = new CityProvinceFg();
                break;
        }
        return fragment;
    }
}
