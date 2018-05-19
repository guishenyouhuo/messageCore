package com.message.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.message.domain.GsUser;
import com.message.service.IGsUserService;
import com.message.service.IMessageService;
import com.message.utils.DivideMessage;

@Controller
@RequestMapping("/dividmsg")
public class DividMessageController {
	
	@Resource
	private IGsUserService gsUserService;
	
	@Resource
	private IMessageService messageService;
	
	@RequestMapping("/showDestUsers")
	public void showDestUsers(HttpServletRequest request, HttpServletResponse response) throws IOException{
		String srcUser = request.getParameter("srcUser");
		List<GsUser> destUsers = gsUserService.getAllGsUsers("1");
		StringBuilder destUserTag= new StringBuilder("<div id='appendDest'> 接收资源的用户：<br/>");
		//排除来源用户
		int count = 0;
		for(GsUser user : destUsers){
			if(user.getGsNum().toString().equals(srcUser)){
				continue;
			}
			count++;
			destUserTag.append(user.getGsName());
			String checkBox = "<input type='checkbox' name='acceptUser' value='" + user.getGsNum() + "'>&nbsp;&nbsp;";
			destUserTag.append(checkBox);
			if(count % 5 == 0){
				destUserTag.append("<br/>");
			}
		}
		destUserTag.append("</div>");
		response.setCharacterEncoding("utf-8");
		request.setAttribute("destUsers", destUsers);
		
		response.getWriter().write(destUserTag.toString());
	}
	
	@RequestMapping("/dividMessage")
	public String divideSrcToDestUser(HttpServletRequest request, Model model){
		String srcUser = request.getParameter("srcUser");
		String destUsers = request.getParameter("destUsers");
		String[] dests = destUsers.split(",");
		List<String> destList = new ArrayList<String>(dests.length);
		for(String dest : dests){
			destList.add(dest);
		}
		
		List<String> srcMsgIds = messageService.getMessageIdByUser(srcUser);
		Map<String, List<String>> msgDividMap = DivideMessage.dividMessageByUsers(destList, srcMsgIds);
		int result = messageService.dividMessage(msgDividMap);
		if(result > 0)
			return "succ";
		else
			return "error";
	}
}
