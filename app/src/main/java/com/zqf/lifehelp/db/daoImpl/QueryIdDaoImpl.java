package com.zqf.lifehelp.db.daoImpl;

import com.zqf.lifehelp.app.App;
import com.zqf.lifehelp.db.table.QueryIdSql;

import java.sql.SQLException;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * class from 身份证历史查询的Dao
 * Created by zqf
 * Time 2018/4/9 15:56
 */

public class QueryIdDaoImpl implements QueryIdDao {
    private Realm mRealm;

    public QueryIdDaoImpl() {
        mRealm = App.getInstance().getRealm();
    }

    @Override
    public void insert(QueryIdSql queryIdSql) throws SQLException {
        mRealm.beginTransaction();//必须先开启事务
        mRealm.copyToRealm(queryIdSql);//把QueryIdSql对象复制到Realm
        mRealm.commitTransaction();//提交事务
        mRealm.close();//必须关闭，不然会造成内存泄漏
    }

    @Override
    public List<QueryIdSql> getAllQueryID() throws SQLException {
        List<QueryIdSql> mAllQueryIDList = null;
        RealmResults<QueryIdSql> realmResults = mRealm.where(QueryIdSql.class).findAll();
        mAllQueryIDList = realmResults;
        return mAllQueryIDList;
    }

    @Override
    public void insertQueryIdAsync(QueryIdSql queryIdSql) throws SQLException {

    }

    @Override
    public void deleteAll() throws SQLException {

    }

    @Override
    public void closeRealm() {
        mRealm.close();
    }
}
