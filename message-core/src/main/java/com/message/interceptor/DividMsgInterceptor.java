package com.message.interceptor;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.message.domain.Admin;
import com.message.service.IUserService;

public class DividMsgInterceptor implements HandlerInterceptor {

	private Logger logger = LoggerFactory.getLogger(getClass());
	@Resource
	private IUserService userService;
	@Override
	public void afterCompletion(HttpServletRequest arg0,
		HttpServletResponse arg1, Object arg2, Exception arg3)
		throws Exception {
	}
	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1,
		Object arg2, ModelAndView arg3) throws Exception {
	}
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
			Object arg2) throws Exception {
        
        //获取Session  
        HttpSession session = request.getSession();  
        String username = (String)session.getAttribute("username");  
        // 手工分留言拦截 器，检查是否 管理员登陆
        Admin adminUser = userService.getAdminByName(username);
        if(adminUser != null){  
        	logger.info("是管理员登陆，通过！");
            return true;  
        }  
        logger.info("没有登陆管理员账号，操作拒绝！");
        //不符合条件的，跳转到登录界面  
        request.getRequestDispatcher("/index.jsp").forward(request, response);  
		return false;
	}
}
