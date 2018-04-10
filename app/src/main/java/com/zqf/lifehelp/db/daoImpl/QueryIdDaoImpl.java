package com.zqf.lifehelp.db.daoImpl;

import com.orhanobut.logger.Logger;
import com.zqf.lifehelp.db.table.QueryIdSql;

import java.sql.SQLException;
import java.util.List;

import io.realm.Case;
import io.realm.Realm;
import io.realm.RealmResults;

/**
 * class from 身份证历史查询的Dao
 * Created by zqf
 * Time 2018/4/9 15:56
 */

public class QueryIdDaoImpl implements QueryIdDao {

    @Override
    public void insert(final QueryIdSql queryIdSql) throws SQLException {
        Realm mRealm = Realm.getDefaultInstance();
        mRealm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.copyToRealm(queryIdSql);
            }
        });
        mRealm.close();
    }

    @Override
    public List<QueryIdSql> getAllQueryID() throws SQLException {
        List<QueryIdSql> mList = null;
        Realm mRealm = Realm.getDefaultInstance();
        RealmResults<QueryIdSql> realmResults = mRealm.where(QueryIdSql.class).findAll();
        Logger.e("查询数据库所有搜索记录\n" + mRealm.copyFromRealm(realmResults).toString());
        mList = mRealm.copyFromRealm(realmResults);
        mRealm.close();
        return mList;
    }

    @Override
    public Integer insertQueryIdAsync(QueryIdSql queryIdSql, String queryid_key) throws SQLException {
        List<QueryIdSql> mList = null;
        Realm mRealm = Realm.getDefaultInstance();
        RealmResults<QueryIdSql> realmResults = mRealm.where(QueryIdSql.class).equalTo("query_id", queryid_key, Case.INSENSITIVE).findAll();
        Logger.e("条件查询数据库所有记录\n" + mRealm.copyFromRealm(realmResults).toString());
        mList = mRealm.copyFromRealm(realmResults);
        mRealm.close();
        if (mList != null && mList.size() > 0) {
            return -1;
        }
        return 0;
    }

    @Override
    public void deleteAll() throws SQLException {
        Realm mRealm = Realm.getDefaultInstance();
        mRealm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.delete(QueryIdSql.class);
                realm.deleteAll();
            }
        });
        mRealm.close();
    }
}
