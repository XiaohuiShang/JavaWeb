/**
 * @(#)JdbcUtil.java, 2015年10月7日. 
 * 
 * Copyright 2015 Yodao, Inc. All rights reserved.
 * YODAO PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package cn.itcast.bbs.util;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.SQLException;

import com.mchange.v2.c3p0.ComboPooledDataSource;

/**
 *
 * @author Administrator
 *
 */
public final class JdbcUtil {
    //数据源
    private static ComboPooledDataSource dataSource;
    private static ThreadLocal<Connection> tl = new ThreadLocal<Connection>();
    static{
        dataSource = new ComboPooledDataSource();
        /*try {
            dataSource.setDriverClass("com.mysql.jdbc.Driver");
            dataSource.setJdbcUrl("jdbc:mysql://127.0.0.1:3306/bbs");
            dataSource.setUser("root");
            dataSource.setPassword("411421");
        } catch (PropertyVetoException e) {
            e.printStackTrace();
        }*/
    }
    /*
     *  取得数据源
     */
    public static ComboPooledDataSource getDataSource(){
        return dataSource;
    }
    /*
     * 取得连接
     */
    public static Connection getConnection(){
        java.sql.Connection conn = tl.get();
        if(conn == null){
            try {
                conn = dataSource.getConnection();
                tl.set(conn);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return conn;
    }
    /*
     * 事务开始
     */
    public static void begin() throws SQLException {
        getConnection().setAutoCommit(false);
        
    }
    /*
     * 事务提交
     */
    public static void commit() throws SQLException {
        getConnection().commit();
    }
    /*
     * 事务回滚
     */
    public static void rollback() throws SQLException {
        getConnection().rollback();
    }
    /*
     * 关闭连接
     */
    public static void close() throws SQLException {
        getConnection().close();
        tl.remove();
    }
}
