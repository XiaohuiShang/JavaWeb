/**
 * @(#)LoginForm.java, 2015年10月8日. 
 * 
 * Copyright 2015 Yodao, Inc. All rights reserved.
 * YODAO PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package cn.itcast.bbs.form;

import java.util.LinkedHashMap;
import java.util.Map;

import cn.itcast.bbs.domain.User;


/**
 *
 * @author Administrator
 *
 */
public class LoginForm {
    private Map<String,String> errors = new LinkedHashMap<String,String>();
    private String username;
    private String password;
    public boolean validate(User user){
        boolean flag = false;
        if(isValidateUsername(user.getUsername()) && isValidatePassword(user.getPassword())){
            flag=true;
        }
        return flag;
    }   
    /*
     * 验证用户名
     */
    private boolean isValidateUsername(String username){
        boolean flag = false;
        if(username != null && username.trim().length()>0){
            if(username.matches("[\u4E00-\uFA29]+")){
                flag = true;
            }else{
                errors.put("username", "<font color='red'>用户名必须为中文</font>");
            }
        }else{
            errors.put("username", "<font color='red'>用户名不能为空</font>");
        }
        return flag;
    }
    /*
     * 验证用户名
     */
    private boolean isValidatePassword(String password){
        boolean flag = false;
        if(password != null && password.trim().length()>0){
            if(password.matches("\\w{6}")){
                flag=true;
            }else{
                errors.put("password", "<font color='red'>密码必须是6位数字或大小写字母</font>");
            }
        }else{
            errors.put("password", "<font color='red'>密码不能为空</font>");
        }
        return flag;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public Map<String, String> getErrors() {
        return errors;
    }
    public void setErrors(Map<String, String> errors) {
        this.errors = errors;
    }
    
}
