package com.message.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/login")
public class LoginController {

	@RequestMapping("/admin")
	public String adminLogin(HttpServletRequest request) throws Exception{
		request.setCharacterEncoding("utf-8"); 
//		AdLoginForm alf = (AdLoginForm) form;
//		AdminManager am = new AdminManager();
//		if(am.checkLogin(alf))
//		{
//			request.getSession().setAttribute("username", alf.getAdminname());
//			return mapping.findForward("success");
//		}
//		else
//		{
//			request.setAttribute("erro", "用户名或密码错误");
//			return mapping.findForward("error");
//		}
		return "base/login1";
	}
	@RequestMapping("/toLogin2")
	public String showLogin2(){
		return "base/login2";
	}
}
