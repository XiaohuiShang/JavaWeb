/**
 * @(#)SessionScanner.java, 2015年10月10日. 
 * 
 * Copyright 2015 Yodao, Inc. All rights reserved.
 * YODAO PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package cn.itcast.bbs.linstener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.ListIterator;
import java.util.Timer;
import java.util.TimerTask;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import cn.itcast.bbs.domain.Flow;
import cn.itcast.bbs.domain.User;
import cn.itcast.bbs.service.BbsService;

/**
 *
 * @author Administrator
 *
 */
public class SessionScanner implements HttpSessionListener,ServletContextListener{
    private Timer timer = new Timer();;
    private  List<HttpSession> sessionList = new ArrayList<HttpSession>();
    //转为线程安全的list
    public SessionScanner(){
        sessionList = Collections.synchronizedList(sessionList);
    }
    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        
    }
    @Override
    public void contextInitialized(ServletContextEvent sce) {          
        timer.schedule(new MyTimerTask(sessionList), 0, 60*1000);
        //设定指定时间
        Calendar c= Calendar.getInstance();
        c.set(c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH), 23, 59, 59);
        ServletContext context = sce.getServletContext();
        System.out.println("date "+c.getTime());
        timer.schedule(new MyTimerTask2(context,c.getTime()), c.getTime());
    }
    @Override
    public void sessionCreated(HttpSessionEvent se) {
        HttpSession session = se.getSession();
        ServletContext context = session.getServletContext();
        /*List<HttpSession> sessionList = (List<HttpSession>) context.getAttribute("sessionList");
        if(sessionList == null){
            sessionList = new ArrayList<HttpSession>();
         }*/
        synchronized (sessionList) {
            sessionList.add(session);
        }
        //打开一个新的session，今日流量就记录一次
        if(session.isNew()){
            Integer todayFlow = (Integer) context.getAttribute("todayFlow");
            if(todayFlow == null){
                todayFlow = 1;
            }else{   
                todayFlow ++;
            }
            //今日流量
            context.setAttribute("todayFlow", todayFlow);
        }
    }
    @Override
    public void sessionDestroyed(HttpSessionEvent se) {        
    }
}
class MyTimerTask extends TimerTask{
    private List<HttpSession> sessionList;
    public MyTimerTask(List<HttpSession> sessionList) {
        this.sessionList = sessionList;
    }
    @Override
    public void run() {
        synchronized (sessionList) {
            ListIterator<HttpSession> it = sessionList.listIterator();
            while (it.hasNext()) {
                HttpSession session = it.next();
                User user = (User) session.getAttribute("user");
                //用户未按"安全退出"
                if (user != null) {
                    int middle = (int) ((System.currentTimeMillis() - session.getLastAccessedTime()) / 1000);
                    if (middle > 60*5) {
                        sessionList.remove(session);
                        session.invalidate();
                    }
                } 
            }
        }
    }
}
class MyTimerTask2 extends TimerTask{
    private ServletContext context;
    private Date date;
    public MyTimerTask2(ServletContext context,Date date){
        this.context = context;
        this.date = date;
    }
    @Override
    public void run() {
        try {
            Flow flow = new Flow();
            Integer todayFlow = (Integer) context.getAttribute("todayFlow");
            flow.setNum(todayFlow);
            flow.setHistorydate(date);
            BbsService service = new BbsService();
            System.out.println("自动更新数据库");
            service.updateFlow(flow); 
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}