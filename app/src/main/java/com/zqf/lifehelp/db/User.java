package com.zqf.lifehelp.db;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by zqf on 2018/3/16.
 * 数据库表用户实体类
 */

@DatabaseTable(tableName = "user")
public class User {

    @DatabaseField(generatedId = true)
    public int user_id;
    @DatabaseField(columnName = "account", canBeNull = true, defaultValue = "")
    public String account;
    @DatabaseField(columnName = "password", canBeNull = true, defaultValue = "")
    public String password;
    @DatabaseField(columnName = "headimg", canBeNull = true, defaultValue = "")
    public String headimg;
    @DatabaseField(columnName = "nickname", canBeNull = true, defaultValue = "")
    public int nickname;

}
