package com.zqf.lifehelp.db.help;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import com.zqf.lifehelp.db.table.QueryIdSql;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * class from 数据库工具类
 * Created by zqf
 * Time 2018/4/9 15:47
 */

public class DBOrmLiteHelper extends OrmLiteSqliteOpenHelper {
    private static final String DB_NAME = "lifehelp.db";
    private static final int DB_VERSOIN = 1;
    // 用来存放 Dao 的键值对集合
    private Map<String, Dao> daos = new HashMap<>();


    private static DBOrmLiteHelper instance;

    public static DBOrmLiteHelper getInstance(Context context) {
        if (instance == null) {
            synchronized (DBOrmLiteHelper.class) {
                if (instance == null) {
                    instance = new DBOrmLiteHelper(context);
                }
            }
        }
        return instance;
    }

    private DBOrmLiteHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSOIN);
    }

    /**
     * 创建表的操作
     *
     * @param sqLiteDatabase
     * @param connectionSource
     */
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase, ConnectionSource connectionSource) {
        try {
            //创建历史搜索的表
            TableUtils.createTableIfNotExists(connectionSource, QueryIdSql.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 这里进行更新表操作
     *
     * @param sqLiteDatabase
     * @param connectionSource
     * @param oldVersion
     * @param newVersion
     */
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, ConnectionSource connectionSource, int oldVersion, int newVersion) {

    }

    /**
     * 通过类来获得指定的 dao
     *
     * @param clazz
     * @return
     * @throws SQLException
     */
    public synchronized Dao getDao(Class clazz) throws SQLException {
        Dao dao = null;
        String className = clazz.getSimpleName();
        if (daos.containsKey(className)) {
            dao = daos.get(className);
        }
        if (dao == null) {
            dao = super.getDao(clazz);
            daos.put(className, dao);
        }
        return dao;
    }

    /**
     * 释放资源
     */
    @Override
    public void close() {
        super.close();
        for (String key : daos.keySet()) {
            Dao dao = daos.get(key);
            dao = null;
        }
    }
}
