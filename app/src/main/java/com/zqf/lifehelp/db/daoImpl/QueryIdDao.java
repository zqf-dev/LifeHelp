package com.zqf.lifehelp.db.daoImpl;

import com.zqf.lifehelp.db.table.QueryIdSql;

import java.sql.SQLException;
import java.util.List;

/**
 * class from 身份证历史查询的Dao
 * Created by zqf
 * Time 2018/4/9 15:56
 */

public interface QueryIdDao {

    /**
     * 清楚所有
     *
     * @throws SQLException
     */
    void deleteAll() throws SQLException;

    /**
     * 插入一条搜索记录
     *
     * @throws SQLException
     */
    void insert(QueryIdSql queryIdSql) throws SQLException;


    /**
     * 获得所有的记录
     *
     * @throws SQLException
     */
    List<QueryIdSql> getAllQueryID() throws SQLException;

    /**
     * 按条件搜索
     *
     * @param queryIdSql
     * @param queryid_key
     * @throws SQLException
     */
    Integer insertQueryIdAsync(QueryIdSql queryIdSql, String queryid_key) throws SQLException;
//    /**
//     * 关闭事务
//     */
//    void closeRealm();
//
//    /**
//     * 更新一个用户
//     * @param user 需要更新的用户类
//     * @return      更新后的对象
//     * @throws SQLException
//     */
//    User updateUser(User user) throws SQLException;
//
//    /**
//     * 根据姓名修改新姓名
//     * @param name1 老名字
//     * @param name2 新名字
//     * @throws SQLException
//     */
//    void updateUser(String name1,String name2) throws SQLException;
//
//    /**
//     * 根据queryid删除
//     * @param id 用户主键
//     * @throws SQLException
//     */
//    void deleteUser(int id) throws SQLException;
//    /**
//     * 按名字或者年龄查找第一个User
//     */
//    User findByNameOrAge(String name1,int age1) throws SQLException;
}
