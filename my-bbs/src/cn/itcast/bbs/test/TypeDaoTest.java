/**
 * @(#)TypeDaoTest.java, 2015年10月7日. 
 * 
 * Copyright 2015 Yodao, Inc. All rights reserved.
 * YODAO PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package cn.itcast.bbs.test;

import java.util.List;

import org.junit.Test;

import cn.itcast.bbs.dao.TypeDao;
import cn.itcast.bbs.domain.Type;

/**
 *
 * @author Administrator
 *
 */
public class TypeDaoTest {
    @Test
    public void test() {
        try {
            TypeDao typeDao = new TypeDao();
            List<Type> typeList = typeDao.findAllType();
            for(Type type : typeList){
                System.out.println(type.getTitle()+":"+type.getClick());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
