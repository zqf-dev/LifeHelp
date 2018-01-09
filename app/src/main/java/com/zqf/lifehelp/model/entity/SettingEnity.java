package com.zqf.lifehelp.model.entity;

/**
 * class from
 * Created by zqf
 * Time 2018/1/9 14:47
 */

public class SettingEnity {

    String title_id;
    String title_name;
    String title_status;

    public SettingEnity(String title_id, String title_name, String title_status) {
        this.title_id = title_id;
        this.title_name = title_name;
        this.title_status = title_status;
    }

    public String getTitle_id() {
        return title_id;
    }

    public void setTitle_id(String title_id) {
        this.title_id = title_id;
    }

    public String getTitle_name() {
        return title_name;
    }

    public void setTitle_name(String title_name) {
        this.title_name = title_name;
    }

    public String getTitle_status() {
        return title_status;
    }

    public void setTitle_status(String title_status) {
        this.title_status = title_status;
    }

}
