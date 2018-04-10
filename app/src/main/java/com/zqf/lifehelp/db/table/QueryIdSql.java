package com.zqf.lifehelp.db.table;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

/**
 * class from
 * Created by zqf
 * Time 2018/4/9 15:37
 */
public class QueryIdSql extends RealmObject {
    //主键必须添加注解
    @PrimaryKey
    private String query_id;
    //注解设为Required代表必须项
    @Required
    private String queryidnum;

    public String getQuery_id() {
        return query_id;
    }

    public void setQuery_id(String query_id) {
        this.query_id = query_id;
    }

    public QueryIdSql() {
    }

    public String getQueryidnum() {
        return queryidnum;
    }

    public void setQueryidnum(String queryidnum) {
        this.queryidnum = queryidnum;
    }

    @Override
    public String toString() {
        return "QueryIdSql{" +
                "query_id='" + query_id + '\'' +
                ", queryidnum='" + queryidnum + '\'' +
                '}';
    }
}
