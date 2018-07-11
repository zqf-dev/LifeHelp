package com.zqf.lifehelp.db.table;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

/**
 * Created by zqf on 2018/3/16.
 * 数据库表用户实体类
 */

public class User extends RealmObject{

    @PrimaryKey
    public String user_id;//随机生成
    @Required
    public String account;//昵称
    @Required
    public String password;//密码
    public String headimg;//头像
    public String phonennum;//手机号

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getHeadimg() {
        return headimg;
    }

    public void setHeadimg(String headimg) {
        this.headimg = headimg;
    }

    public String getPhonennum() {
        return phonennum;
    }

    public void setPhonennum(String phonennum) {
        this.phonennum = phonennum;
    }
}
