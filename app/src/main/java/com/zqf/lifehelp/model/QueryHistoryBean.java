package com.zqf.lifehelp.model;

/**
 * class from
 * Created by zqf
 * Time 2018/4/9 13:52
 */

public class QueryHistoryBean {

    private String idnum;
    private String area;
    private String birthday;
    private String sex;

    public String getIdnum() {
        return idnum;
    }

    public void setIdnum(String idnum) {
        this.idnum = idnum;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public QueryHistoryBean(String idnum, String area, String birthday, String sex) {
        this.idnum = idnum;
        this.area = area;
        this.birthday = birthday;
        this.sex = sex;
    }
}
