package com.zqf.lifehelp.db.daoImpl;

import com.zqf.lifehelp.db.table.User;

import java.sql.SQLException;
import java.util.List;

/**
 * class from 身份证历史查询的Dao
 * Created by zqf
 * Time 2018/4/9 15:56
 */

public interface UserDao {

    /**
     * 清楚所有用户
     *
     * @throws SQLException
     */
    void deleteAll() throws SQLException;

    /**
     * 插入一条新用户记录
     *
     * @throws SQLException
     */
    void insert(User user) throws SQLException;

    /**
     * 获得所有的用户记录
     *
     * @throws SQLException
     */
    List<User> getAllUser() throws SQLException;

    /**
     * 按条件搜索
     *
     * @param queryIdSql
     * @param queryid_key
     * @throws SQLException
     */
    Integer queryUserIdInfo(User queryIdSql, String queryid_key) throws SQLException;


    /**
     * 根据用户名（昵称）修改新用户名
     *
     * @param old_name 老用户名
     * @param new_name 新用户名
     * @throws SQLException
     */
    void updateUser(String old_name, String new_name) throws SQLException;
    /**
     * 根据用户名（昵称）修改新用户名
     *
     * @param old_headimg 老用户名
     * @param new_headimg 新用户名
     * @throws SQLException
     */
    void updateUserHeadimg(String old_headimg, String new_headimg) throws SQLException;

}
