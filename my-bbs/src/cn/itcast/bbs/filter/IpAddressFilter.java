/**
 * @(#)IpAddressFilter.java, 2015年10月7日. 
 * 
 * Copyright 2015 Yodao, Inc. All rights reserved.
 * YODAO PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package cn.itcast.bbs.filter;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.itcast.bbs.domain.Address;
import cn.itcast.bbs.domain.Flow;
import cn.itcast.bbs.service.BbsService;

/**
 *
 * @author Administrator
 *
 */
public class IpAddressFilter implements Filter{
    @Override
    public void destroy() { 
        
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res,
            FilterChain chain) throws IOException, ServletException {
        //强转接口
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        String ip = request.getRemoteAddr();
        HttpSession session = request.getSession();
        //// 判段当前用户HttpSession中是否存在IP归属地信息
        Address address = (Address)session.getAttribute("address");
        try {
            if (address == null) {
                BbsService service = new BbsService();
                address = service.findAddressByIp(ip);
                session.setAttribute("address", address);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        //取得今日流量昨日流量
        ServletContext context = session.getServletContext();
        Integer yesterdayFlow = (Integer) context.getAttribute("yesterdayFlow");
        try {
            if (yesterdayFlow == null) {
                BbsService service = new BbsService();
                // 创建日期
                Calendar c = Calendar.getInstance();
                c.add(Calendar.DATE, -1);
                Date date = c.getTime();
                Flow flow = service.showFlow(date);
                context.setAttribute("yesterdayFlow", flow.getNum());
            }
            //放行资源
            chain.doFilter(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Override
    public void init(FilterConfig arg0) throws ServletException {
        
    }
    
}
