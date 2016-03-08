/**
 * @(#)Flow.java, 2015年10月11日. 
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
public class Flow {
    private int id;
    private Date historydate;
    private int num;
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public Date getHistorydate() {
        return historydate;
    }
    public void setHistorydate(Date historydate) {
        this.historydate = historydate;
    }
    public int getNum() {
        return num;
    }
    public void setNum(int num) {
        this.num = num;
    }
    
}
