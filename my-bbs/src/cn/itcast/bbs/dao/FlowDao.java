/**
 * @(#)UserDao.java, 2015年10月8日. 
 * 
 * Copyright 2015 Yodao, Inc. All rights reserved.
 * YODAO PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package cn.itcast.bbs.dao;

import java.sql.SQLException;
import java.util.Date;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import cn.itcast.bbs.domain.Flow;
import cn.itcast.bbs.domain.User;
import cn.itcast.bbs.util.JdbcUtil;

/**
 *
 * @author Administrator
 *
 */
public class FlowDao {
    /*
     * 查询昨日流量
     */
    public Flow showFlow(Date date) throws SQLException{
        QueryRunner runner = new QueryRunner(JdbcUtil.getDataSource());
        String sql = "select *from flow where historydate = ?";
        Flow flow = runner.query(sql, new BeanHandler(Flow.class), new java.sql.Date(date.getTime()));
        return flow;
    }
    /*
     * 到零点时自动更新数据库
     */
    public void updateFlow(Flow flow) throws SQLException{
        QueryRunner runner = new QueryRunner(JdbcUtil.getDataSource());
        String sql = "update flow set num = ?,historydate = ? where id = ? ";
        System.out.println(flow.getNum()+":"+new java.sql.Date(flow.getHistorydate().getTime()));
        runner.update(sql, new Object[]{flow.getNum(),new java.sql.Date(flow.getHistorydate().getTime()),1});
    }
}
