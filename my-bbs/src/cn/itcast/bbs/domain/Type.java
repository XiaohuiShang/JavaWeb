/**
 * @(#)Type.java, 2015年10月7日. 
 * 
 * Copyright 2015 Yodao, Inc. All rights reserved.
 * YODAO PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package cn.itcast.bbs.domain;

/**
 * 论坛版块表
 * @author Administrator
 *
 */
public class Type implements Comparable<Type>{
    private int id; //主键
    private String title;//版块名称
    private String image;//版块图标
    private int click;//点击数
    private Admin admin;//版主
    private int topicCnt;//主题数
    private Topic topic;
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getImage() {
        return image;
    }
    public void setImage(String image) {
        this.image = image;
    }
    public int getClick() {
        return click;
    }
    public void setClick(int click) {
        this.click = click;
    }
    public Admin getAdmin() {
        return admin;
    }
    public void setAdmin(Admin admin) {
        this.admin = admin;
    }
    public int getTopicCnt() {
        return topicCnt;
    }
    public void setTopicCnt(int topicCnt) {
        this.topicCnt = topicCnt;
    }
    public Topic getTopic() {
        return topic;
    }
    public void setTopic(Topic topic) {
        this.topic = topic;
    }
    @Override
    public int compareTo(Type o) {
        if(this.click > o.click){
            return -1;
        }else if(this.click < o.click){
            return 1;
        }else{
            return 0;
        }
    }  
    
}
