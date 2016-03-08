/**
 * @(#)BbsServlet.java, 2015年10月7日. 
 * 
 * Copyright 2015 Yodao, Inc. All rights reserved.
 * YODAO PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package cn.itcast.bbs.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;

import cn.itcast.bbs.domain.Reply;
import cn.itcast.bbs.domain.Topic;
import cn.itcast.bbs.domain.Type;
import cn.itcast.bbs.domain.User;
import cn.itcast.bbs.form.LoginForm;
import cn.itcast.bbs.form.RegisterForm;
import cn.itcast.bbs.service.BbsService;

/**
 *
 * @author Administrator
 *
 */
public class BbsServlet extends HttpServlet {
    private BbsService service = new BbsService();
    
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String method = request.getParameter("method");
        if(method.equals("showAllType")){
            this.showAllType(request,response);
        }else if(method.equals("toLoginJsp")){
            this.toLoginJsp(request,response);
        }else if(method.equals("toRegisterJsp")){
            this.toRegisterJsp(request,response);
        }else if(method.equals("exit")){
            this.exit(request,response);
        }else if(method.equals("showAllTopic")){
            this.showAllTopic(request,response);
        }else if(method.equals("toNewTopicJsp")){
            this.toNewTopicJsp(request,response);
        }else if(method.equals("showReply")){
            this.showReply(request,response);
        }else if(method.equals("toReplyJsp")){
            this.toReplyJsp(request, response);
        } else if(method.equals("toeditTopicJsp")){
            this.toeditTopicJsp(request, response);
        } else if(method.equals("deleteType")){
            this.deleteType(request, response);
        }
    }
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String method = request.getParameter("method");
        if(method.equals("login")){
            this.login(request,response);
        }else if(method.equals("register")){
            this.register(request,response);
        }else if(method.equals("addTopic")){
            this.addTopic(request,response);
        }else if(method.equals("addReply")){
            this.addReply(request,response);
        } else if(method.equals("editTopic")){
            this.editTopic(request,response);
        }
    }
    /*
     * 显示所有的版块
     */
    private void showAllType(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException { 
        try {
            List<Type> typeList = service.findAllType();
            List<Type> typeListOrder = service.findAllTypeByClick();
            request.setAttribute("typeList", typeList);
            request.setAttribute("typeListOrder", typeListOrder);
            request.getRequestDispatcher("/WEB-INF/bbs/listAllType.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("message", "查找论坛版块失败");
            request.getRequestDispatcher("/WEB-INF/bbs/message.jsp").forward(request, response);
        }
    }
    /*
     * 显示某个版块下的所有主题
     */
    private void showAllTopic(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException{
        try {
            int id = Integer.parseInt(request.getParameter("typeId"));
            //显示该板块下的主题
            List<Topic>  topicList = service.showAllTopicByTypeId(id);
            request.setAttribute("topicList", topicList);
            request.setAttribute("typeId", id);
            //更新点击数
            HttpSession session = request.getSession();
            if(!session.isNew()){
                List<Integer> typeIdList = (List<Integer>) session.getAttribute("typeIdList");
                if(typeIdList == null){
                    typeIdList = new ArrayList<Integer>();
                    session.setAttribute("typeIdList", typeIdList);
                }
                boolean flag = service.isClicked(id,typeIdList);
                if(!flag){
                    service.updateTypeClick(id);              
                }
            } 
            request.getRequestDispatcher("/WEB-INF/bbs/listAllTopic.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("message", "查找版块主题失败");
            request.getRequestDispatcher("/WEB-INF/bbs/message.jsp").forward(request, response);
        }
    }
    /*
     * 删除主题
     */
    private void deleteType(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException{
        int typeId = Integer.parseInt(request.getParameter("typeId"));
        try {
            service.deleteType(typeId);
            response.sendRedirect(request.getContextPath());
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("message", "删除版块失败");
            request.getRequestDispatcher("/WEB-INF/bbs/message.jsp").forward(request, response);
        }
    }
    /*
     * 转到新话题录入界面
     */
    private void toNewTopicJsp(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        String typeId = request.getParameter("typeId");
        try {
            Type type = service.findTypeById(Integer.parseInt(typeId));
            request.setAttribute("type", type);
            request.getRequestDispatcher("/WEB-INF/bbs/addNewTopic.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /*
     * 添加新话题
     */
    private void addTopic(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            String title = request.getParameter("title");
            String content = request.getParameter("content");
            User user = (User) request.getSession().getAttribute("user");
            Topic t = new Topic();
            t.setTitle(title);
            t.setName(user.getUsername());
            t.setContent(content);
            t.setReplyCnt(0);
            service.addTopic(t,id);
            response.sendRedirect(request.getContextPath()+"/BbsServlet?method=showAllTopic&typeId="+id);
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("message", "添加版块主题失败");
            request.getRequestDispatcher("/WEB-INF/bbs/message.jsp").forward(request, response);
        }
    }
    /*
     * 转到编辑话题页面
     */
    private void toeditTopicJsp(HttpServletRequest request,
            HttpServletResponse response){
        try {
            int typeId = Integer.parseInt(request.getParameter("typeId"));
            int topicId = Integer.parseInt(request.getParameter("topicId"));
            Topic topic = service.findTopicById(topicId);
            request.setAttribute("typeId", typeId);
            request.setAttribute("topic", topic);
            request.getRequestDispatcher("/WEB-INF/bbs/editTopic.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
    /*
     * 编辑话题
     */
    private void editTopic(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException{
        try {
            Topic topic = new Topic();
            Enumeration<String> enums = request.getParameterNames();
            while(enums.hasMoreElements()){
                String key = enums.nextElement();
                if(key.equals("method") || key.equals("typeId")){
                    continue;
                }
                String[] value = request.getParameterValues(key);
                BeanUtils.setProperty(topic, key, value);
            }     
            service.updateTopic(topic);
            int typeId = Integer.parseInt(request.getParameter("typeId"));
            response.sendRedirect(request.getContextPath()+"/BbsServlet?method=showAllTopic&typeId="+typeId);
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("message", "编辑主题失败");
            request.getRequestDispatcher("/WEB-INF/bbs/message.jsp").forward(request, response);
        }
        
    }
    /*
     * 显示主题下的所有回复
     */
    private void showReply(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException{
        try {
            int typeId = Integer.parseInt(request.getParameter("typeId"));
            int topicId = Integer.parseInt(request.getParameter("topicId"));
            List<Reply> replyList = service.findAllReply(topicId);
            Topic topic = service.findTopicById(topicId);
            request.setAttribute("typeId",typeId);
            request.setAttribute("replyList",replyList);
            request.setAttribute("topic",topic);
            request.getRequestDispatcher("/WEB-INF/bbs/listAllReply.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("message", "查询主题下的所有回复失败");
            request.getRequestDispatcher("/WEB-INF/bbs/message.jsp").forward(request, response);
        }
    }
    /*
     * 转到主题回复界面
     */
    private void toReplyJsp(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        try {
            int topicId = 0;
            if(request.getParameter("topicId") != null){
                topicId = Integer.parseInt(request.getParameter("topicId"));
            }
            request.setAttribute("topicId",topicId);
            request.getRequestDispatcher("/WEB-INF/bbs/addNewReply.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /*
     * 主题回复
     */
    private void addReply(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException{
        try {
            int topicId = Integer.parseInt(request.getParameter("topicId"));
            Reply reply = new Reply();
            reply.setTitle(request.getParameter("title"));
            reply.setContent(request.getParameter("content"));
            User user = (User) request.getSession().getAttribute("user");
            reply.setName(user.getUsername());
            request.setAttribute("topicId",topicId);
            request.setAttribute("reply",reply);
            service.addReply(reply,topicId);
            response.sendRedirect(request.getContextPath()+"/BbsServlet?method=showReply&topicId="+topicId);
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("message", "回复失败");
            request.getRequestDispatcher("/WEB-INF/bbs/message.jsp").forward(request, response);
        }
    }
    /*
     * 转到用户登录界面
     */
    private void toLoginJsp(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/bbs/login.jsp").forward(request, response);
    }
    /*
     * 转到用户注册界面
     */
    private void toRegisterJsp(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/bbs/register.jsp").forward(request, response);
    }
    /*
     * 用户退出
     */
    private void exit(HttpServletRequest request,HttpServletResponse response) throws IOException {
        //取得在线用户列表
        List<String> usernameList = (List<String>) request.getServletContext().getAttribute("usernameList");
        for(String str:usernameList){
            System.out.println(str);
        }
        //取得session中的user对象
        User user = (User) request.getSession().getAttribute("user");
        //从在线用户列表中移除用户
        usernameList.remove(user.getUsername());
        //从域对象中移除用户
        request.getSession().removeAttribute("user");
        request.getSession().invalidate();  
        response.sendRedirect(request.getContextPath());
    }
    /*
     * 处理用户登录
     */
    private void login(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        User user = new User();
        String username = request.getParameter("username");
        user.setUsername(username);
        String password = request.getParameter("password");
        user.setPassword(password);
        String checkCode = request.getParameter("checkCode");
        HttpSession session = request.getSession();
        String ideacheckCode = (String) session.getAttribute("checkCode");
        try {
            //判断验证码是否正确
            if (checkCode.equals(ideacheckCode)) {
                LoginForm loginForm = new LoginForm();
                //判断用户名和密码格式是否正确
                if (loginForm.validate(user)) {
                    //判断用户否已注册
                    if (service.findUser(username) != null) {
                        // 判断用户名和密码是否正确
                        if (service.findUser(username).getPassword().equals(password)) {
                            // 再判断用户是否在线
                            List<String> usernameList = (List<String>) this.getServletContext().getAttribute("usernameList");
                            if (usernameList == null) {
                                usernameList = new ArrayList<String>();
                                this.getServletContext().setAttribute("usernameList", usernameList);
                            }
                            if (service.checkOnline(user.getUsername(),
                                    usernameList)) {
                                // 用户在线
                                request.setAttribute("message", "用户已在线");
                                request.getRequestDispatcher("/WEB-INF/bbs/message.jsp").forward(request, response);
                            } else {
                                // 用户不在线，判断用户名和密码是否正确
                                session.setAttribute("user", user);
                                response.sendRedirect(request.getContextPath());
                            }
                        } else {
                            // 密码不正确
                            request.setAttribute("message","<font color='red'>用户名或密码错误</font>");
                            request.getRequestDispatcher("/WEB-INF/bbs/login.jsp").forward(request,response);
                        }
                    } else {
                         //用户不存在
                            request.setAttribute("message", "<font color='red'>用户不存在</font>");
                            request.getRequestDispatcher("/WEB-INF/bbs/login.jsp").forward(request, response);
                        }
                } else {
                    //用户名或密码格式不正确
                    request.setAttribute("loginForm", loginForm);
                    request.getRequestDispatcher("/WEB-INF/bbs/login.jsp").forward(request, response);
                }
            } else {
                //验证码输入不正确
                request.setAttribute("checkmessage", "<font color='red'>验证码不正确，请重新输入</font>");
                request.getRequestDispatcher("/WEB-INF/bbs/login.jsp").forward(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("message", "用户登录失败");
            request.getRequestDispatcher("/WEB-INF/bbs/message.jsp").forward(request, response);
        }
    }
    /*
     * 处理用户注册
     */
    private void register(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        User user = new User();
        Enumeration<String> enums = request.getParameterNames();
        try {
            while (enums.hasMoreElements()) {
                String name = enums.nextElement();
                if (name.equals("method") || name.equals("checkCode")) {
                    continue;
                }
                String[] value = request.getParameterValues(name);
                //System.out.println(name+":"+value[0]);
                BeanUtils.setProperty(user, name, value);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        String checkCode = request.getParameter("checkCode");
        HttpSession session = request.getSession();
        String ideacheckCode = (String) session.getAttribute("checkCode");
        try {
            //判断验证码是否正确
            if (checkCode.equals(ideacheckCode)) {
                RegisterForm registerForm = new RegisterForm();
                //判断用户名、密码和邮箱格式是否正确
                if (registerForm.validate(user)) {
                    if(service.findUser(user.getUsername()) == null){
                        if(registerForm.validate2Password(user)){
                            session.setAttribute("user", user);
                            List<String> usernameList = (List<String>) this.getServletContext().getAttribute("usernameList");
                            usernameList.add(user.getUsername());
                            service.saveUser(user);
                            request.setAttribute("message", "<font color='red'>恭喜您，注册成功</font>");
                            response.setHeader("Refresh", "2;/my-bbs/");
                            request.getRequestDispatcher("/WEB-INF/bbs/register.jsp").forward(request, response);
                        }else{
                            //两次密码输入不一致
                            request.setAttribute("passmessage", "<font color='red'>两次密码输入不一致，请重新输入</font>");
                            request.getRequestDispatcher("/WEB-INF/bbs/register.jsp").forward(request, response);
                        }  
                    }else{
                         //用户名已被注册
                        request.setAttribute("usermessage", "<font color='red'>该用户名已被注册</font>");
                        request.getRequestDispatcher("/WEB-INF/bbs/register.jsp").forward(request, response);
                    }
                }else{
                    //用户名或密码格式不正确
                    request.setAttribute("registerForm", registerForm);
                    request.getRequestDispatcher("/WEB-INF/bbs/register.jsp").forward(request, response);
                }
            } else {
                //验证码输入不正确
                request.setAttribute("checkmessage", "<font color='red'>验证码不正确，请重新输入</font>");
                request.getRequestDispatcher("/WEB-INF/bbs/register.jsp").forward(request, response);
            }
            request.setAttribute("user", user);
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("message", "用户登录失败");
            request.getRequestDispatcher("/WEB-INF/bbs/message.jsp").forward(request, response);
        }
    }
}
