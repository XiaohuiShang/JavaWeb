/**
 * @(#)BbsServiceTest.java, 2015年10月7日. 
 * 
 * Copyright 2015 Yodao, Inc. All rights reserved.
 * YODAO PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package cn.itcast.bbs.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.junit.Test;

import cn.itcast.bbs.domain.Address;
import cn.itcast.bbs.domain.Flow;
import cn.itcast.bbs.domain.Type;
import cn.itcast.bbs.service.BbsService;

/**
 *
 * @author Administrator
 *
 */
public class BbsServiceTest {

    @Test
    public void testfindAllType() {
        try {
            BbsService service = new BbsService();
            List<Type> typeList = service.findAllType();
            for(Type type : typeList){
                System.out.println(type.getTitle()+":"+type.getClick());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Test
    public void testfindAddressByIp() {
        try {
            BbsService service = new BbsService();
            Address address = service.findAddressByIp("127.0.0.1");
            System.out.println(address.getIp()+":"+address.getLocation());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Test
    public void testRandom() {
        Random random = new Random();
        int num = random.nextInt(10000); 
        while(num<1000){
            num = random.nextInt(10000); 
        }
        System.out.println(num);
        
        System.out.println(new Date());
    }
    @Test
    public void testupdateFlow() {
        try {
            Date date = new Date();
            BbsService service = new BbsService();
            Flow flow = new Flow();
            flow.setHistorydate(date);
            flow.setNum(2);
            service.updateFlow(flow);
            System.out.println(new Date());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
