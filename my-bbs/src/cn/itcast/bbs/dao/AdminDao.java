/**
 * @(#)AdminDao.java, 2015年10月11日. 
 * 
 * Copyright 2015 Yodao, Inc. All rights reserved.
 * YODAO PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package cn.itcast.bbs.dao;

import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import cn.itcast.bbs.domain.Admin;
import cn.itcast.bbs.util.JdbcUtil;

/**
 *
 * @author Administrator
 *
 */
public class AdminDao {
    /*
     * 根据版块名查找对应的版主
     */
    public Admin findAdminByType(String title) throws SQLException{
        QueryRunner runner = new QueryRunner(JdbcUtil.getDataSource());
        String sql = "select *from admin where title = ?";
        Admin admin = runner.query(sql, new BeanHandler(Admin.class), title);
        return admin;
    }
}
