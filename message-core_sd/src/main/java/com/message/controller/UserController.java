package com.message.controller;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.message.domain.GsUser;
import com.message.service.IUserService;

@Controller
@RequestMapping("/user")
public class UserController {

	@Resource
	private IUserService userService;
	
	@RequestMapping("/toAddUser")
    public String toAddUser() {
        return "user/adduser";
    }
	@RequestMapping("/toModUser")
    public String toModUser() {
        return "user/modifyuser";
    }
	
	@RequestMapping("/toAdmin")
    public String toAdmin() {
        return "user/admin";
    }
	
	@RequestMapping("/checkUser")
    public String checkUser(HttpServletResponse response, String num) throws IOException {
		GsUser gsUser = userService.getUserByNum(num);
		if(gsUser != null){
			response.getWriter().write("1");
		} else {
			response.getWriter().write("0");
		}
        return null;
    }
	
	@RequestMapping(value = "/addUser", method = RequestMethod.POST)
    public String addUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
		GsUser gsUser = new GsUser();
		gsUser.setGsName(request.getParameter("gsName"));
		gsUser.setGsNum(Integer.parseInt(request.getParameter("gsNum")));
		gsUser.setPws(request.getParameter("pws"));
		gsUser.setTyFlag(1);
		if(userService.addUser(gsUser) > 0){
			response.getWriter().write("1");
		} else {
			response.getWriter().write("0");
		}
        return null;
    }
	
	@RequestMapping("/showUsers")
    public String showUser(HttpServletRequest request) {
        List<GsUser> users = userService.queryGsUsers(null);
        request.setAttribute("users", users);
        return "user/userlist";
    }
	
	@RequestMapping("/toModify")
    public String toModifyUser(HttpServletRequest request) {
        String id = request.getParameter("id");
		GsUser gsUser = userService.getUserById(id);
        request.setAttribute("user", gsUser);
        return "user/modifyuser";
    }
	
	@RequestMapping(value = "/modifyUser", method = RequestMethod.POST)
    public String modifyUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
		GsUser gsUser = new GsUser();
		gsUser.setId(Long.parseLong(request.getParameter("id")));
		gsUser.setGsName(request.getParameter("gsName"));
		gsUser.setGsNum(Integer.parseInt(request.getParameter("gsNum")));
		gsUser.setPws(request.getParameter("pws"));
		gsUser.setTyFlag(Integer.parseInt(request.getParameter("gsType")));
		if(userService.modifyUser(gsUser) > 0){
			response.getWriter().write("1");
		} else {
			response.getWriter().write("0");
		}
        return null;
    }
	
	@RequestMapping(value = "/openCloseUser", method = RequestMethod.POST)
    public String openCloseUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String id = request.getParameter("id");
		GsUser gsUser = userService.getUserById(id);
		if(gsUser.getTyFlag() == 1){
			gsUser.setTyFlag(0);
		} else if(gsUser.getTyFlag() == 0){
			gsUser.setTyFlag(1);
		}
		if(userService.modifyUser(gsUser) > 0){
			response.getWriter().write("1");
		} else {
			response.getWriter().write("0");
		}
        return null;
    }
	
	@RequestMapping(value = "/delUser", method = RequestMethod.POST)
    public String delUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String id = request.getParameter("id");
		if(userService.delUserById(id) > 0){
			response.getWriter().write("1");
		} else {
			response.getWriter().write("0");
		}
        return null;
    }
	
	@RequestMapping("/toModifyGsUser")
    public String toModifyGsUser(HttpServletRequest request) {
		String userNum = (String)request.getSession().getAttribute("usernum");
		GsUser gsUser = userService.getUserByNum(userNum);
		request.setAttribute("ub", gsUser);
        return "user/modify_zs_pass";
    }
	
	@RequestMapping("/modifyGsUser")
    public String modifyGsUser(HttpServletRequest request) {
		String id = request.getParameter("mid");
		String pws = request.getParameter("pws");
		GsUser gsUser = new GsUser();
		gsUser.setId(Long.parseLong(id));
		gsUser.setPws(pws);
		if(userService.modifyUser(gsUser) > 0){
			return "redirect:../user/toModifyGsUser";
		}
		return null;
    }
}
