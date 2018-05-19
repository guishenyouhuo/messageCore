package com.message.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.message.domain.GsUser;
import com.message.service.IGsUserService;

@Controller
@RequestMapping("/user")
public class UserController {

	@Resource
	private IGsUserService gsUserService;
	
	@RequestMapping("/showUser")
    public String showUser(HttpServletRequest request, Model model) {
        List<GsUser> users = gsUserService.getAllGsUsers("1");
        model.addAttribute("users", users);
        return "user";
    }
}
