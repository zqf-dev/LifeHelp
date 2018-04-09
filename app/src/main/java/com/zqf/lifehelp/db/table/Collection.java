package com.zqf.lifehelp.db.table;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by zqf on 2018/3/16.
 * 数据库用户收藏表实体类
 */
@DatabaseTable(tableName = "collection")
public class Collection {

    @DatabaseField(generatedId = true)
    public int id;
    @DatabaseField(id = true)
    public Integer collection_id;
    @DatabaseField(columnName = "title_name")
    public String title_name;
    @DatabaseField(columnName = "content")
    public String content;
    @DatabaseField(columnName = "lastTime")
    private String lastTime;

}
