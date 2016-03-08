/**
 * @(#)TopicDao.java, 2015年10月11日. 
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
import org.apache.commons.dbutils.handlers.ScalarHandler;

import cn.itcast.bbs.domain.Topic;
import cn.itcast.bbs.util.JdbcUtil;

/**
 *
 * @author Administrator
 *
 */
public class TopicDao {
    /*
     * 查询某版块下的所有主题
     */
    public List<Topic> showAllTopicByTypeId(int id) throws SQLException{
        List<Topic> topicList = null;
        QueryRunner runner = new QueryRunner(JdbcUtil.getDataSource());
        String sql = "select *from topic where type_id = ? order by time desc;";
        topicList = runner.query(sql, new BeanListHandler(Topic.class), id);
        return topicList;
    }
    /*
     * 查询某版块下的主题数
     */
    public int countTopicByType(int id) throws SQLException{
        QueryRunner runner = new QueryRunner(JdbcUtil.getDataSource());
        String sql = "select count(*) from topic where type_id = ?;";
        Long cnt = runner.query(sql, new ScalarHandler(), id);
        return cnt.intValue();
    }
    /*
     * 添加新话题
     */
    public void addTopic(Topic topic,int id) throws SQLException{
        QueryRunner runner = new QueryRunner(JdbcUtil.getDataSource());
        String sql = "insert into topic(title,name,content,type_id) values(?,?,?,?);";
        runner.update(sql, new Object[]{topic.getTitle(),topic.getName(),topic.getContent(),id});
    }
    /*
     * 根据id查找话题
     */
    public Topic findTopicById(int id) throws SQLException{
        QueryRunner runner = new QueryRunner(JdbcUtil.getDataSource());
        String sql = "select *from topic where id = ?;";
        Topic topic = runner.query(sql, new BeanHandler(Topic.class), id);
        return topic;
    }
    /*
     * 编辑主题
     */
    public void updateTopic(Topic topic) throws SQLException{
        QueryRunner runner = new QueryRunner(JdbcUtil.getDataSource());
        String sql = "update topic set title=?,content=? where id=?;";
        runner.update(sql, new Object[]{topic.getTitle(),topic.getContent(),topic.getId()});
    }
    /*
     * 根据版块id删除主题
     */
    public void deleteTopicByTypeId(int typeId) throws SQLException{
        QueryRunner runner = new QueryRunner();
        String sql = "delete from topic where type_id = ?;";
        runner.update(JdbcUtil.getConnection(), sql, typeId);
    }
}
