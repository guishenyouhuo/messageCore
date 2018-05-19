package com.message.controller;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.message.domain.Admin;
import com.message.domain.GsUser;
import com.message.domain.LoginRecord;
import com.message.service.ILoginRecordService;
import com.message.service.IUserService;

@Controller
@RequestMapping("/loginout")
public class LoginOutController {
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	@Resource
	private IUserService userService;
	@Resource
	private ILoginRecordService loginRecordService;
    /** 
     * 管理员用户登录
     * @param session  HttpSession 
     * @param username 用户名 
     * @param password 密码 
     * @return 
     */  
    @RequestMapping("/adLogin")  
    public String doAdLogin(HttpServletRequest request, String adminname, String adminpass) throws Exception{        
    	Admin adminUser = userService.getAdminByName(adminname);
    	if(adminUser != null && adminUser.getAdminpass().equals(adminpass)){
    		String loginIp = this.getIpAddr(request);
    		logger.info("登陆成功！adminname:{}, ip:{}", adminname, loginIp);
	    	//在Session里保存信息  
	        request.getSession().setAttribute("username", adminname);  
	        
	        // 记录登录日志
	        try{
	        	this.recordLogin(request, "admin", adminname);
	        } catch(Exception e) {
	        	logger.warn("添加登录日志记录出现异常！", e);
	        }
	        
	        return "redirect:../ad_index.jsp"; 
    	}
    	return "redirect:../index.jsp";
    }  
	/** 
     * 招商用户登录
     * @param session  HttpSession 
     * @param username 用户名 
     * @param password 密码 
     * @return 
     */  
    @RequestMapping("/gsLogin")  
    public String doGgsLogin(HttpServletRequest request, String gs_name, String password) throws Exception{        
    	GsUser gsUser = userService.getUserByName(gs_name);
    	if(gsUser != null && gsUser.getPws().equals(password)){
    		String loginIp = this.getIpAddr(request);
    		logger.info("登陆成功！userName:{}, ip:{}", gs_name, loginIp);
	    	//在Session里保存信息  
    		request.getSession().setAttribute("username", gs_name);  
    		request.getSession().setAttribute("usernum", gsUser.getGsNum().toString());  
    		
    		// 记录登录日志
	        try{
	        	this.recordLogin(request, gsUser.getGsNum().toString(), gs_name);
	        } catch(Exception e) {
	        	logger.warn("添加登录日志记录出现异常！", e);
	        }
	        
	        return "redirect:../index2.jsp"; 
    	}
    	return "redirect:../index.jsp";
    } 
      
    /** 
     * 退出系统 
     * @param session Session 
     * @return 
     * @throws Exception 
     */  
    @RequestMapping(value="/logout")  
    public String logout(HttpServletRequest request) throws Exception{  
        //清除Session  
    	request.getSession().invalidate();  
        return "redirect:../index.jsp";  
    }  
    
    private void recordLogin(HttpServletRequest request, String userNum, String userName){
    	String loginIp = this.getIpAddr(request);
        LoginRecord record = new LoginRecord();
        record.setLoginIp(loginIp);
        record.setLoginNum(userNum);
        record.setLoginUser(userName);
        record.setLoginTime(new Date());
        loginRecordService.inserLoginRecord(record);
    }
    
    /** 获取登录用户的ip地址 **/
    public String getIpAddr(HttpServletRequest request) {   
        String ip = request.getHeader("x-forwarded-for");   
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {   
            ip = request.getHeader("Proxy-Client-IP");   
        }   
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {   
            ip = request.getHeader("WL-Proxy-Client-IP");   
        }   
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {   
            ip = request.getRemoteAddr();   
            if(ip.equals("127.0.0.1")){     
                //根据网卡取本机配置的IP     
                InetAddress inet=null;     
                try {     
                    inet = InetAddress.getLocalHost();     
                } catch (UnknownHostException e) {     
                    e.printStackTrace();     
                }     
                ip= inet.getHostAddress();     
            }  
        }   
        // 对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割  
        if(ip != null && ip.length() > 15){    
            if(ip.indexOf(",")>0){     
                ip = ip.substring(0,ip.indexOf(","));     
            }     
        }     
        return ip;   
    }  
}
