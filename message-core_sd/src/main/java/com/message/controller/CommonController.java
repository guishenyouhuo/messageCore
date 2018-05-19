package com.message.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/common")
public class CommonController {
	@RequestMapping("/toLogin1")
	public String showLogin1(){
		return "base/login1";
	}
	@RequestMapping("/toLogin2")
	public String showLogin2(){
		return "base/login2";
	}
	@RequestMapping("/importMeum")
	public String showMeun(){
		return "base/menu";
	}
	@RequestMapping("/importMeum2")
	public String showMeun2(){
		return "base/menu2";
	}
	@RequestMapping("/importWelcome")
	public String showWelcome(){
		return "base/welcome";
	}
}
