package com.zqf.lifehelp.model;

import java.util.List;

/**
 * class from
 * Created by zqf
 * Time 2018/1/11 12:29
 */

public class LocalCityModel {

    private String title;
    private List<ChildCityBean> mCityBeanList;

    public LocalCityModel(String title, List<ChildCityBean> cityBeanList) {
        this.title = title;
        mCityBeanList = cityBeanList;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<ChildCityBean> getCityBeanList() {
        return mCityBeanList;
    }

    public void setCityBeanList(List<ChildCityBean> cityBeanList) {
        mCityBeanList = cityBeanList;
    }

    public static class ChildCityBean {
        public ChildCityBean(String district) {
            this.district = district;
        }

        /**
         * district : 北京
         */

        private String district;

        public String getDistrict() {
            return district;
        }

        public void setDistrict(String district) {
            this.district = district;
        }
    }
}
