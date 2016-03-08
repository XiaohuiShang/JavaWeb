/**
 * @(#)AddressDao.java, 2015年10月7日. 
 * 
 * Copyright 2015 Yodao, Inc. All rights reserved.
 * YODAO PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package cn.itcast.bbs.dao;

import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import cn.itcast.bbs.domain.Address;
import cn.itcast.bbs.util.JdbcUtil;

/**
 *
 * @author Administrator
 *
 */
public class AddressDao {
    /*
     * 根据ip查询用户归属地
     */
    public Address findAddressByIp(String ip) throws SQLException{
        QueryRunner runner = new QueryRunner(JdbcUtil.getDataSource());
        String sql = "select *from address;";
        Address address = runner.query(sql, new BeanHandler(Address.class));
        return address;
    }
}
