package com.zqf.lifehelp.db.dao;

import android.content.Context;

import com.j256.ormlite.dao.Dao;
import com.zqf.lifehelp.db.help.DBOrmLiteHelper;
import com.zqf.lifehelp.db.table.QueryIdSql;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * class from 身份证历史查询的Dao
 * Created by zqf
 * Time 2018/4/9 15:56
 */

public class QueryIdDao {

    private Dao<QueryIdSql, Integer> queryIdSqlDao;
    private DBOrmLiteHelper dbHelper;
    private Context context;

    public QueryIdDao(Context context) {
        this.context = context;
        dbHelper = DBOrmLiteHelper.getInstance(context);
        try {
            queryIdSqlDao = dbHelper.getDao(QueryIdSql.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    /**
     * 添加一条记录
     *
     * @param queryIdSql
     */
    public void add(QueryIdSql queryIdSql) {
        try {
            queryIdSqlDao.createOrUpdate(queryIdSql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 删除一条记录
     *
     * @param queryIdSql
     */
    public void deleteSingle(QueryIdSql queryIdSql) {
        try {
            queryIdSqlDao.delete(queryIdSql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 删除所有历史搜索记录
     *
     * @param queryIdSqlList
     */
    public void deleteAll(List<QueryIdSql> queryIdSqlList) {
        try {
            queryIdSqlDao.delete(queryIdSqlList);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 查
     *
     * @return
     */
    public List<QueryIdSql> queryAll() {
        List<QueryIdSql> queryIdSqlArrayList = new ArrayList<>();
        try {
            queryIdSqlArrayList = queryIdSqlDao.queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return queryIdSqlArrayList;
    }
}
