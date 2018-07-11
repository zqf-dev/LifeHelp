package com.zqf.lifehelp.db.daoImpl;

import com.blankj.utilcode.util.ToastUtils;
import com.orhanobut.logger.Logger;
import com.zqf.lifehelp.db.table.QueryIdSql;
import com.zqf.lifehelp.db.table.User;

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

public class UserDaoImpl implements UserDao {

    //增加用户
    @Override
    public void insert(final User userIdSql) throws SQLException {
        Realm mRealm = Realm.getDefaultInstance();
        mRealm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.copyToRealm(userIdSql);
            }
        });
        mRealm.close();
    }

    //获取所有用户
    @Override
    public List<User> getAllUser() throws SQLException {
        List<User> mList = null;
        Realm mRealm = Realm.getDefaultInstance();
        RealmResults<User> realmResults = mRealm.where(User.class).findAll();
        Logger.e("查询数据库所有搜索记录\n" + mRealm.copyFromRealm(realmResults).toString());
        mList = mRealm.copyFromRealm(realmResults);
        mRealm.close();
        return mList;
    }

    //根据用户ID查询用户信息
    @Override
    public Integer queryUserIdInfo(User IdSql, String userId_key) throws SQLException {
        List<User> mList = null;
        Realm mRealm = Realm.getDefaultInstance();
        RealmResults<User> realmResults = mRealm.where(User.class).equalTo("user_id", userId_key, Case.INSENSITIVE).findAll();
        Logger.e("条件查询数据库所有记录\n" + mRealm.copyFromRealm(realmResults).toString());
        mList = mRealm.copyFromRealm(realmResults);
        mRealm.close();
        if (mList != null && mList.size() > 0) {
            return -1;
        }
        return 0;
    }

    //删除所有
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

    //更新一个用户信息
    @Override
    public void updateUser(final String old_name, final String new_name) throws SQLException {
        final Realm mRealm = Realm.getDefaultInstance();
        mRealm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                User info = realm.where(User.class).equalTo("account", old_name).findFirst();
                info.setAccount(new_name);
            }
        }, new Realm.Transaction.OnSuccess() {
            @Override
            public void onSuccess() {
                ToastUtils.showShort("修改成功!");
            }
        }, new Realm.Transaction.OnError() {
            @Override
            public void onError(Throwable error) {
                ToastUtils.showShort("修改失败!");
            }
        });
        mRealm.close();
    }

    //更新一个用户头像
    @Override
    public void updateUserHeadimg(final String old_headimg, final String new_headimg) throws SQLException {
        final Realm mRealm = Realm.getDefaultInstance();
        mRealm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                User info = realm.where(User.class).equalTo("headimg", old_headimg).findFirst();
                info.setHeadimg(new_headimg);
            }
        }, new Realm.Transaction.OnSuccess() {
            @Override
            public void onSuccess() {
                ToastUtils.showShort("修改成功!");
            }
        }, new Realm.Transaction.OnError() {
            @Override
            public void onError(Throwable error) {
                ToastUtils.showShort("修改失败!");
            }
        });
        mRealm.close();
    }
}
