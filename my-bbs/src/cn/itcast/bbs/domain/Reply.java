/**
 * @(#)Reply.java, 2015年10月11日. 
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
public class Reply {
    private int id;
    private String title;//回复的主题名
    private String name;//回复者的名字
    private String content;//回复的内容
    private java.sql.Timestamp time;//最后更新的时间
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
}
