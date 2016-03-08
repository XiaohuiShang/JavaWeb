/**
 * @(#)Topic.java, 2015年10月11日. 
 * 
 * Copyright 2015 Yodao, Inc. All rights reserved.
 * YODAO PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package cn.itcast.bbs.domain;

import java.util.Date;

/**
 *
 * @author Administrator
 *
 */
public class Topic {
    private int id;
    private String title;//主题名
    private String name;//作者
    private String content;//内容
    private java.sql.Timestamp time;//最后更新时间
    private int replyCnt;//回帖数
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
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public java.sql.Timestamp getTime() {
        return time;
    }
    public void setTime(java.sql.Timestamp time) {
        this.time = time;
    }
    public int getReplyCnt() {
        return replyCnt;
    }
    public void setReplyCnt(int replyCnt) {
        this.replyCnt = replyCnt;
    }
    
}
