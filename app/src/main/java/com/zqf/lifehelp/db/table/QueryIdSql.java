package com.zqf.lifehelp.db.table;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * class from
 * Created by zqf
 * Time 2018/4/9 15:37
 */
@DatabaseTable(tableName = "queryidsql")
public class QueryIdSql {

    @DatabaseField(generatedId = true, columnName = "id", useGetSet = true)
    public int query_id;
    @DatabaseField(useGetSet = true, columnName = "queryidnum", canBeNull = true, defaultValue = "")
    public String queryidnum;

    public QueryIdSql(String queryidnum) {
        this.queryidnum = queryidnum;
    }

    public int getQuery_id() {
        return query_id;
    }

    public void setQuery_id(int query_id) {
        this.query_id = query_id;
    }

    public String getQueryidnum() {
        return queryidnum;
    }

    public void setQueryidnum(String queryidnum) {
        this.queryidnum = queryidnum;
    }
}
