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
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import cn.itcast.bbs.domain.Reply;
import cn.itcast.bbs.domain.Topic;
import cn.itcast.bbs.util.JdbcUtil;

/**
 *
 * @author Administrator
 *
 */
public class ReplyDao {
    /*
     * 查询某主题下的所有回帖
     */
    public List<Reply> showAllReplyByTopicId(int id) throws SQLException{
        List<Reply> replyList = null;
        QueryRunner runner = new QueryRunner(JdbcUtil.getDataSource());
        String sql = "select *from reply where topic_id = ? order by time;";
        replyList = runner.query(sql, new BeanListHandler(Reply.class), id);
        return replyList;
    }
    /*
     * 查询某主题下的回帖数
     */
    public int countReplyByTopic(int id) throws SQLException{
        QueryRunner runner = new QueryRunner(JdbcUtil.getDataSource());
        String sql = "select count(*) from reply where topic_id = ?;";
        Long cnt = runner.query(sql, new ScalarHandler(), id);
        return cnt.intValue();
    }
    /*
     * 主题回复
     */
    public void addReply(Reply reply,int topicId) throws Exception{
        QueryRunner runner = new QueryRunner(JdbcUtil.getDataSource());
        String sql = "insert into reply(title,name,content,topic_id) values(?,?,?,?);";
        runner.update(sql, new Object[]{reply.getTitle(),reply.getName(),reply.getContent(),topicId});
    }
    /*
     * 删除某主题下的所有回复
     */
    public void deleteReplyByTopicId(int topicId) throws SQLException{
        QueryRunner runner = new QueryRunner();
        String sql = "delete from reply where topic_id = ?;";
        runner.update(JdbcUtil.getConnection(), sql, topicId);
    }
}
