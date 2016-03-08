/**
 * @(#)TypeDao.java, 2015年10月7日. 
 * 
 * Copyright 2015 Yodao, Inc. All rights reserved.
 * YODAO PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package cn.itcast.bbs.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import cn.itcast.bbs.domain.Type;
import cn.itcast.bbs.util.JdbcUtil;

/**
 * 版块Dao
 * @author Administrator
 *
 */
public class TypeDao {
    /*
     * 查询所有的版块
     */
    public List<Type> findAllType() throws SQLException{
        List<Type> typeList = null;
        QueryRunner runner = new QueryRunner(JdbcUtil.getDataSource());
        String sql = "select *from type;";
        typeList = runner.query(sql, new BeanListHandler(Type.class));
        return typeList;
    }
    /*
     * 更新点击数
     */
    public void updateTypeClick(int id) throws SQLException{
        QueryRunner runner = new QueryRunner(JdbcUtil.getDataSource());
        String sql = "update type set click = click +1 where id = ?;";
        runner.update(sql, id);
    }
    /*
     * 根据id查询版块
     */
    public Type findTypeById(int id) throws SQLException{
        QueryRunner runner = new QueryRunner(JdbcUtil.getDataSource());
        String sql = "select *from type where id=?;";
        Type type = runner.query(sql, new BeanHandler(Type.class), id);
        return type;
    }
    /*
     * 根据id删除版块
     */
    public void deleteTypeById(int typeId) throws SQLException{
        QueryRunner runner = new QueryRunner();
        String sql = "delete from type where id = ?;";
        runner.update(JdbcUtil.getConnection(), sql, typeId);
    }
} 
