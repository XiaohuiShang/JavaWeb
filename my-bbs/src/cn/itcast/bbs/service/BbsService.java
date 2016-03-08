/**
 * @(#)BbsService.java, 2015年10月7日. 
 * 
 * Copyright 2015 Yodao, Inc. All rights reserved.
 * YODAO PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package cn.itcast.bbs.service;

import java.sql.SQLException;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import cn.itcast.bbs.dao.AddressDao;
import cn.itcast.bbs.dao.AdminDao;
import cn.itcast.bbs.dao.FlowDao;
import cn.itcast.bbs.dao.ReplyDao;
import cn.itcast.bbs.dao.TopicDao;
import cn.itcast.bbs.dao.TypeDao;
import cn.itcast.bbs.dao.UserDao;
import cn.itcast.bbs.domain.Address;
import cn.itcast.bbs.domain.Admin;
import cn.itcast.bbs.domain.Flow;
import cn.itcast.bbs.domain.Reply;
import cn.itcast.bbs.domain.Topic;
import cn.itcast.bbs.domain.Type;
import cn.itcast.bbs.domain.User;
import cn.itcast.bbs.util.JdbcUtil;

/**
 *
 * @author Administrator
 */
public class BbsService {
    
    private TypeDao typeDao = new TypeDao();
    private AddressDao addressDao = new AddressDao();
    private UserDao userDao = new UserDao();
    private AdminDao adminDao = new AdminDao();
    private TopicDao topicDao = new TopicDao();
    private ReplyDao replyDao = new ReplyDao();
    private FlowDao flowDao = new FlowDao();
    /*
     * 查询所有的版块
     */
    public List<Type> findAllType() throws Exception {
        try {
            List<Type> typeList = typeDao.findAllType();
            for(Type type :typeList){
                Admin admin = adminDao.findAdminByType(type.getTitle());
                type.setAdmin(admin);
                int cnt = topicDao.countTopicByType(type.getId());
                type.setTopicCnt(cnt);
                Topic topic = showAllTopicByTypeId(type.getId()).get(0);
                type.setTopic(topic);
            }
            return typeList;
        } catch (SQLException e) {
            throw new Exception();
        }
    }
    /*
     * 根据点击数查询版块，找出最火的三个版块
     */
    public List<Type> findAllTypeByClick() throws Exception {
        try {
            List<Type>  typeListOrder = typeDao.findAllType();
            Collections.sort(typeListOrder);
            return typeListOrder;
        } catch (SQLException e) {
            throw new Exception();
        }
    }
    /*
     * 根据id查询版块
     */
    public Type findTypeById(int id) throws Exception{
        try {
            return typeDao.findTypeById(id);
        } catch (SQLException e) {
            throw new Exception();
        }
    }
    /*
     * 更新点击数
     */
    public void updateTypeClick(int id) throws Exception{
        try {
            typeDao.updateTypeClick(id);
        } catch (SQLException e) {
            throw new Exception();
        }
    }
    /*
     * 删除主题
     */
    public void deleteType(int typeId) throws Exception{
        try {
            List<Topic> topicList = topicDao.showAllTopicByTypeId(typeId);
            //事务开始
            JdbcUtil.begin();
            for(Topic topic : topicList){
                replyDao.deleteReplyByTopicId(topic.getId());
            }
            topicDao.deleteTopicByTypeId(typeId);
            typeDao.deleteTypeById(typeId);
            JdbcUtil.commit();
        } catch (Exception e) {
            throw new Exception();
        } finally{
            JdbcUtil.rollback();
            JdbcUtil.commit();
            JdbcUtil.close();
        }
    }
    /*
     * 查询某版块下的所有主题
     */
    public List<Topic> showAllTopicByTypeId(int id) throws Exception{
        try {
            List<Topic>  topicList = topicDao.showAllTopicByTypeId(id);
            for(Topic topic : topicList){
                int cnt = replyDao.countReplyByTopic(topic.getId());  
                topic.setReplyCnt(cnt);
            }
            return topicList;
        } catch (SQLException e) {
            throw new Exception();
        }
    }
    /*
     * 根据id查找话题
     */
    public Topic findTopicById(int id) throws Exception{
        try {
            return topicDao.findTopicById(id);
        } catch (SQLException e) {
            throw new Exception();
        }
    }
    /*
     * 添加新话题
     */
    public void addTopic(Topic topic,int id) throws Exception{
        try {
            topicDao.addTopic(topic, id);
        } catch (SQLException e) {
            throw new Exception();
        }
    }
    /*
     * 编辑主题
     */
    public void updateTopic(Topic topic) throws Exception{
        try {
            topicDao.updateTopic(topic);
        } catch (SQLException e) {
            throw new Exception();
        }
    }
    /*
     * 查找主题下的所有回复
     */
    public List<Reply> findAllReply(int topicId) throws Exception{
        try {
            return replyDao.showAllReplyByTopicId(topicId);
        } catch (SQLException e) {
            throw new Exception();
        }
    }
    /*
     * 主题回复
     */
    public void addReply(Reply reply,int topicId) throws Exception{
        try {
            replyDao.addReply(reply, topicId);
        } catch (Exception e) {
            throw new Exception();
        }
    }
    /*
     * 根据ip查询用户归属地
     */
    public Address findAddressByIp(String ip) throws Exception{
        try {
            return addressDao.findAddressByIp(ip);
        } catch (SQLException e) {
            throw new Exception();
        }
    }
    /*
     * 查询昨日流量
     */
    public Flow showFlow(Date date) throws Exception {
        try {
            return flowDao.showFlow(date);
        } catch (SQLException e) {
            throw new Exception();
        }
    }
    /*
     * 到零点时自动更新数据库
     */
    public void updateFlow(Flow flow) throws Exception{
        try {
            flowDao.updateFlow(flow);
        } catch (SQLException e) {
            throw new Exception();
        }
    }
    /*
     * 核实用户是否在线
     */
    public boolean checkOnline(String username, List<String> usernameList){
        boolean flag = false;
        if(usernameList == null && usernameList.size()==0){
            //不在线
            flag = false;
            usernameList.add(username);
        }else{
            if(usernameList.contains(username)){
                flag = true;
            }else{
                flag = false;
                usernameList.add(username);
            }
        }
        return flag;
    }
    /*
     * 查找用户，验证用户名和密码是否正确和用户是否存在
     */
    public User findUser(String username) throws Exception{
        try {
            User user = userDao.findUser(username);
            return user;
        } catch (SQLException e) {
            throw new Exception();
        }
    }
    /*
     * 保存用户注册信息
     */
    public void saveUser(User user) throws Exception{
        try {
            userDao.saveUser(user);
        } catch (SQLException e) {
            throw new Exception();
        }
    }
    /*
     * 判断该模块是否点击过
     */
    public boolean isClicked(int id,List<Integer> typeIdList) {
        boolean flag = false;
        if(typeIdList == null && typeIdList.size() == 0){
            flag = false;
            typeIdList.add(id);
        }else{
            if(typeIdList.contains(id)){
                flag = true;
                return flag;
            }else{
                typeIdList.add(id);
                flag = false;
            }
        }       
        return flag;
    }
}
