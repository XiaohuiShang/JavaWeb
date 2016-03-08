/**
 * @(#)UserDao.java, 2015年10月8日. 
 * 
 * Copyright 2015 Yodao, Inc. All rights reserved.
 * YODAO PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package cn.itcast.bbs.dao;

import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import cn.itcast.bbs.domain.User;
import cn.itcast.bbs.util.JdbcUtil;

/**
 *
 * @author Administrator
 *
 */
public class UserDao {
    /*
     * 查找用户，验证用户登录
     */
    public User findUser(String username) throws SQLException{
        QueryRunner runner = new QueryRunner(JdbcUtil.getDataSource());
        String sql = "select *from user where username = ?";
        User userFromDB = runner.query(sql, new BeanHandler(User.class), new Object[]{username});
        return userFromDB;
    }
    /*
     * 保存用户注册信息
     */
    public void saveUser(User user) throws SQLException{
        QueryRunner runner = new QueryRunner(JdbcUtil.getDataSource());
        String sql = "insert into user(username,password,gender,email) values(?,?,?,?);";
        runner.update(sql, new Object[]{user.getUsername(),user.getPassword(),user.getGender(),user.getEmail()});
    }
}
