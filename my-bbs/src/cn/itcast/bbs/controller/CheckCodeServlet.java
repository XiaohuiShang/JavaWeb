/**
 * @(#)CheckCodeServlet.java, 2015年10月8日. 
 * 
 * Copyright 2015 Yodao, Inc. All rights reserved.
 * YODAO PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package cn.itcast.bbs.controller;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Administrator
 *
 */
public class CheckCodeServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //设置浏览器不缓存
        response.setHeader("expires", "-1");
        response.setHeader("cache-control", "no-cache");
        response.setHeader("pragma", "no-cache");
        //绑定验证码到域对象
        String checkCode = Token.getNum();
        HttpSession session = request.getSession();
        //将验证码放入session域对象
        session.setAttribute("checkCode", checkCode);
        //在内存中构造一副图画
        BufferedImage image = new BufferedImage(50, 25, BufferedImage.TYPE_INT_RGB);
        //取得画笔
        Graphics graphics = image.getGraphics();
        //画图
        graphics.drawString(checkCode,15,17);
        graphics.setColor(Color.red);
        //画干扰线
        for(int i=0;i<8;i++){
            Random random = new Random();
            int x1 = random.nextInt(50);
            int y1 = random.nextInt(25);
            int x2 = random.nextInt(50);
            int y2 = random.nextInt(25);          
            graphics.drawLine(x1,y1, x2, y2);
        }
        ImageIO.write(image, "jpg", response.getOutputStream());
    }
}
class Token{
    public static String getNum(){
        Random random = new Random();
        int num = random.nextInt(10000); 
        while(num<1000){
            num = random.nextInt(10000); 
        }
        return num+"";
    }
}