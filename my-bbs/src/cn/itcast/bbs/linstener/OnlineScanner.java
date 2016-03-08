/**
 * @(#)OnlineScanner.java, 2015年10月10日. 
 * 
 * Copyright 2015 Yodao, Inc. All rights reserved.
 * YODAO PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package cn.itcast.bbs.linstener;

import javax.servlet.ServletContext;
import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import cn.itcast.bbs.domain.User;

/**
 *
 * @author Administrator
 *
 */
public class OnlineScanner implements HttpSessionAttributeListener{
    @Override
    public void attributeAdded(HttpSessionBindingEvent sbe) {
        String name = sbe.getName();
        //确定绑定的与对象名是否user
        if(name.equals("user")){
            Object value = sbe.getValue();
            //确定是否是User对象
            if(value instanceof User){
                HttpSession session = sbe.getSession();
                User user = (User) session.getAttribute("user");
                ServletContext context = session.getServletContext();
                Integer online = (Integer) context.getAttribute("online");
                if(online == null){
                    online = 1;
                }else{
                    online ++;
                }
                //在线人数
                context.setAttribute("online", online);
                Integer caller = (Integer) context.getAttribute("caller");
                if(caller == null){
                    caller = 1;
                }else{
                    caller ++;
                }
                //访问次序
                context.setAttribute("caller", caller);//可变
                session.setAttribute("caller", caller);//不变
            }
            
        }
    }
    @Override
    public void attributeRemoved(HttpSessionBindingEvent sbe) {
        String name = sbe.getName();
        //确定绑定的与对象名是否user
        if(name.equals("user")){
            Object value = sbe.getValue();
            //确定是否是User对象
            if(value instanceof User){
                HttpSession session = sbe.getSession();
                ServletContext context = session.getServletContext();
                Integer online = (Integer) context.getAttribute("online");
                online --;
                context.setAttribute("online", online);
            }
        }
    }
    @Override
    public void attributeReplaced(HttpSessionBindingEvent sbe) {
        
    }

}
