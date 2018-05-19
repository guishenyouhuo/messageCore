package com.message.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.message.domain.GsUser;
import com.message.service.IGsUserService;
import com.message.utils.MysqlBackUp;

@Controller
@RequestMapping("/backup")
public class BackupController {

	@Value("${back.username}")
	private String userName;
	@Value("${back.password}")
	private String password;
	@Value("${back.dbname}")
	private String dbName;
	@Value("${back.path}")
	private String mysqlBackupPath;
	
	@Resource
	private IGsUserService gsUserService;
	
	@RequestMapping("/mysqlback")
	public String doBackup(HttpServletRequest request, Model model){
		String command = "mysqldump -u" + userName + " -p" + password + " " + dbName;
		if(MysqlBackUp.backUp(dbName, mysqlBackupPath, command)){
			//备份成功则进入资源分配页面（同时取出所有用户）
			List<GsUser> userList = gsUserService.getAllGsUsers(null);
			request.setAttribute("userList", userList);
			return "divide";
		}
		else{
			return "error";
		}
	}
}
