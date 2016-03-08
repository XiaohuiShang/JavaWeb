/**
 * @(#)TypeDaoTest.java, 2015年10月7日. 
 * 
 * Copyright 2015 Yodao, Inc. All rights reserved.
 * YODAO PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package cn.itcast.bbs.test;

import java.util.Date;
import java.util.List;

import org.junit.Test;

import cn.itcast.bbs.dao.FlowDao;
import cn.itcast.bbs.dao.TypeDao;
import cn.itcast.bbs.domain.Flow;
import cn.itcast.bbs.domain.Type;
import cn.itcast.bbs.service.BbsService;

/**
 *
 * @author Administrator
 *
 */
public class FlowDaoTest {
    @Test
    public void testupdateFlow() {
        try {
            Date date = new Date();
            FlowDao flowDao = new FlowDao();
            Flow flow = new Flow();
            flow.setHistorydate(date);
            flow.setNum(2);
            System.out.println(new Date());
            flowDao.updateFlow(flow);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
