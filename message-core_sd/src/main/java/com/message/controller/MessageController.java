package com.message.controller;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import com.message.domain.Admin;
import com.message.domain.Autoly;
import com.message.domain.GsUser;
import com.message.domain.KhmessageWithBLOBs;
import com.message.domain.MessageFilterDTO;
import com.message.domain.MsgTemplate;
import com.message.domain.TagMapping;
import com.message.service.IMessageService;
import com.message.service.IUserService;

@Controller
@RequestMapping("/message")
public class MessageController {
	
	@Resource
	private IUserService userService;
	@Resource
	private IMessageService messageService;
	private static final int PAGE_SIZE = 20;
	private static final String RECYCLE_NUM = "20";
	private static final String AUTO_MESSAGE = "autoly";
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	@RequestMapping("/toAuto")
	public String toAuto(HttpServletRequest request) throws IOException{
		Autoly autoLy = messageService.getAutoLyRecord("1");
		request.setAttribute("ab", autoLy);
		return "message/autoly";
	}
	@RequestMapping(value = "/autoLy", method = RequestMethod.POST)
	public String autoLy(HttpServletRequest request) throws IOException{
		Autoly autoLy = new Autoly();
		autoLy.setId(Long.parseLong(request.getParameter("id")));
		autoLy.setMaxuser(Integer.parseInt(request.getParameter("maxuser")));
		autoLy.setNownum(Integer.parseInt(request.getParameter("nownum")));
		messageService.updateAutoLyRecord(autoLy);
		request.setAttribute("ab", autoLy);
		return "message/autoly";
	}
	@RequestMapping("/showAllMessage")
	public String showAllMessage(HttpServletRequest request) throws IOException{
		MessageFilterDTO msgFilter = new MessageFilterDTO();
		this.showMessageList(request, msgFilter);
		return "message/listly";
	}
	
	@RequestMapping(value = "/changeUser", method = RequestMethod.POST)
	public String changeUser(HttpServletRequest request, HttpServletResponse response) throws IOException{
		String select = request.getParameter("select");
		String id = request.getParameter("id");
		String last_num = request.getParameter("last_num");
		if(select == null || last_num == null || id == null){
			response.getWriter().write("0");
			return null;
		}
		KhmessageWithBLOBs message = new KhmessageWithBLOBs();
		message.setId(Long.parseLong(id));
		message.setLastUser(Integer.parseInt(last_num));
		message.setFpUser(Integer.parseInt(select));
		if(!select.equals(last_num) && messageService.updateMessage(message) > 0){
			response.getWriter().write("1");
		} else {
			response.getWriter().write("0");
		}
		return null;
	}
	
	@RequestMapping(value = "/delMessage", method = RequestMethod.POST)
	public String delMessage(HttpServletRequest request, HttpServletResponse response) throws IOException{
		String id = request.getParameter("id");
		String last_num = request.getParameter("last_num");
		if(id == null || last_num == null){
			response.getWriter().write("0");
			return null;
		}
		String resultCode = "";
		if(RECYCLE_NUM.equals(last_num)){
			logger.info("删除回收站留言，永久删除！id：{}", id);
			if(messageService.deleteMessage(id) > 0){
				resultCode = "1";
			} else {
				resultCode = "0";
			}
		} else {
			logger.info("留言移入回收站！id：{}", id);
			KhmessageWithBLOBs message = messageService.getMessageById(id);
			message.setLastUser(message.getFpUser());
			message.setFpUser(Integer.parseInt(RECYCLE_NUM));
			if(messageService.updateMessage(message) > 0){
				resultCode = "1";
			} else {
				resultCode = "0";
			}
		}
		response.getWriter().write(resultCode);
		return null;
	}
	
	@RequestMapping("/toShowMsgByUser")
	public String toShowMsgByUser(HttpServletRequest request) throws IOException{
		List<GsUser> userList = userService.queryGsUsers(null);
		request.setAttribute("users", userList);
		return "message/lookren";
	}
	
	@RequestMapping("/showCompleteMessage")
	public String showCompleteMessage(HttpServletRequest request) throws IOException{
		MessageFilterDTO msgFilter = new MessageFilterDTO();
		msgFilter.setMsgType("3");
		this.showMessageList(request, msgFilter);
		return "message/complete";
	}
	
	@RequestMapping("/toShowCompleteMsgByUser")
	public String toShowCompleteMsgByUser(HttpServletRequest request) throws IOException{
		List<GsUser> userList = userService.queryGsUsers(null);
		request.setAttribute("users", userList);
		return "message/completebyuser";
	}
	
	private void showMessageList(HttpServletRequest request, MessageFilterDTO msgFilter){
		int pageNow=1;
		String offset = request.getParameter("offset");
		String userNum = request.getParameter("id");
		String flag = request.getParameter("flag");
		if(userNum != null){
			logger.info("根据客户查询。userNum: {}", userNum);
			request.getSession().setAttribute("userNum", userNum);
		} 
		if(flag != null && "all".equals(flag)){
			request.getSession().removeAttribute("userNum");
		}
		String byNum = (String)request.getSession().getAttribute("userNum");
		if(byNum != null){
			msgFilter.setGsNum(byNum);
		}
		String n_pageNow=request.getParameter("pageNow");
		if(null != offset && !"".equals(offset.trim())){
			pageNow = Integer.parseInt(offset.trim());
			if(pageNow < 1){
				pageNow = 1;
			}
		} else if(null != n_pageNow){
			pageNow=Integer.parseInt(n_pageNow);
		}
		int messageCount = messageService.queryMessageCount(msgFilter);
		int pageCount = (messageCount + PAGE_SIZE - 1) / PAGE_SIZE;
		int startNo = (pageNow - 1) * PAGE_SIZE;
		List<KhmessageWithBLOBs> messageList = messageService.queryMessageByPage(startNo, PAGE_SIZE, msgFilter);
		
		this.getUsersMapAndSet(request);
		request.setAttribute("messages", messageList);
		request.setAttribute("pageCount", pageCount);
		request.getSession().setAttribute("pageNow", String.valueOf(pageNow));
		request.setAttribute("messageCount", messageCount);
	}
	
	public void getUsersMapAndSet(HttpServletRequest request){
		List<GsUser> userList = userService.queryGsUsers(null);
		Map<Integer, GsUser> numUserMap = new HashMap<Integer, GsUser>(userList.size());
		for(GsUser gsUser : userList){
			numUserMap.put(gsUser.getGsNum(), gsUser);
		}
		request.setAttribute("numUserMap", numUserMap);
		request.setAttribute("userList", userList);
	}
	
	@RequestMapping("/toAddMessage")
	public String toAddMessage(HttpServletRequest request) throws IOException{
		List<GsUser> userList = userService.queryGsUsers(null);
		request.setAttribute("users", userList);
		return "message/addly";
	}
	
	@RequestMapping(value = "/checkMessage", method = RequestMethod.POST)
	public String checkMessage(HttpServletRequest request, HttpServletResponse response) throws IOException{
		String tel = request.getParameter("tel");
		if(messageService.checkTelExist(tel)){
			response.getWriter().write("1");
		}
		else {
			response.getWriter().write("0");
		}
		return null;
	}
	
	@RequestMapping(value = "/addMessage", method = RequestMethod.POST)
	public String addMessage(HttpServletRequest request) throws IOException{
		String user_num = "";
		String numFromAd = request.getParameter("select");
		String numFromUser = (String)request.getSession().getAttribute(("usernum"));
		String fromtag = request.getParameter("fromtag");
		Autoly autoLy = null;
		// 自动分配
		if(numFromAd != null && AUTO_MESSAGE.equals(numFromAd)){
			logger.info("管理员添加，自动分配。");
			autoLy = messageService.getAutoLyRecord("1");
			user_num = autoLy.getNownum().toString();
		} else if(numFromAd != null){
			logger.info("管理员添加，指定分配:{}。", numFromAd);
			user_num = numFromAd;
		} else {
			logger.info("招商用户添加，userNum：{}。", numFromUser);
			user_num = numFromUser;
		}
		KhmessageWithBLOBs message = new KhmessageWithBLOBs();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String timeNow=df.format(new Date());
		message.setKhName(request.getParameter("kh_name"));
		message.setKhTel(request.getParameter("kh_tel"));
		message.setKhAddress(request.getParameter("kh_address"));
		message.setKhLy(request.getParameter("kh_ly"));
		message.setFpUser(Integer.parseInt(user_num));
		message.setIntime(timeNow);
		if(fromtag != null){
			message.setTag(fromtag);
		}
		if(messageService.addMessage(message) > 0){
			// 自动分配需更新自动分配记录表
			if(AUTO_MESSAGE.equals(numFromAd)){
				String nowNum = user_num;
				List<GsUser> userList = userService.getAllGsUsers("1");
				int index = 0;
				while(!nowNum.equals(userList.get(index).getGsNum().toString())){
					index++;
					if(index == userList.size()){
						logger.info("没有找到当前配置userNum,从第一个开始。");
						index = -1;
						break;
					}
				}
				index = (index == userList.size() - 1) ? 0 : index + 1;
				if(autoLy == null){
					autoLy = messageService.getAutoLyRecord("1");
				}
				autoLy.setNownum(userList.get(index).getGsNum());
				messageService.updateAutoLyRecord(autoLy);
			}
			if(numFromAd != null){
				logger.info("返回管理员查看留言页面。");
				return "../message/showAllMessage?flag=all";
			} else if(numFromUser != null){
				logger.info("返回招商人员查看留言页面。");
				return "redirect:../message/showUserMssage";
			} else {
				logger.info("出现错误。");
				return null;
			}
		} else {
			logger.info("出现错误。");
			return null;
		}
	}
	
	@RequestMapping("/toImport")
	public String toImport(HttpServletRequest request, HttpServletResponse response) throws IOException{
		List<GsUser> gsUserList = userService.queryGsUsers(null);
		request.setAttribute("users", gsUserList);
		List<TagMapping> tags = messageService.getAllTagMapping();
		request.setAttribute("tags", tags);
		return "message/import";
	}
	
	@RequestMapping(value = "/importMessage", method = RequestMethod.POST)
	public String importMessage(HttpServletRequest request, HttpServletResponse response, MultipartFile excel) throws IOException{
		String fromtag = request.getParameter("fromtag");
		String selectUser = request.getParameter("select");
		logger.info("导入留言，from：{}", fromtag);
		InputStream fis = excel.getInputStream();
		List<String> failTelList = messageService.checkAndImportMsgByTag(fromtag, selectUser, fis);
		if(failTelList != null && failTelList.size() > 0){
			request.setAttribute("telList", failTelList);
			return "message/imoprtFail";
		}
		return "../message/showAllMessage?flag=all";
	}
	
	@RequestMapping("/toAddTag")
	public String toAddTag(HttpServletRequest request) throws IOException{
		List<MsgTemplate> templates = messageService.getAllTemplate();
		request.setAttribute("templates", templates);
		return "message/addtag";
	}
	
	@RequestMapping("/toAddTemplate")
	public String toAddTemplate(HttpServletRequest request) throws IOException{
		List<MsgTemplate> templates = messageService.getAllTemplate();
		request.setAttribute("templates", templates);
		return "message/addtemplate";
	}
	
	@RequestMapping(value = "/addTemplate", method = RequestMethod.POST)
	public String addTemplate(HttpServletRequest request) throws IOException{
		String tmpname = request.getParameter("tmpname");
		String hasfirst = request.getParameter("hasfirst");
		String name = request.getParameter("name");
		String tel = request.getParameter("tel");
		String address = request.getParameter("address");
		String message = request.getParameter("message");
		String firstline = "1";
		if(hasfirst == null){
			firstline = "0";
		}
		MsgTemplate template = new MsgTemplate();
		template.setTmpKey(tmpname);
		template.setHasFirstline(firstline);
		template.setColName(name);
		template.setColTel(tel);
		template.setColAddr(address);
		template.setColMsg(message);
		
		if(messageService.addTemplate(template) > 0){
			logger.info("添加模板成功。");
			List<MsgTemplate> templates = messageService.getAllTemplate();
			request.setAttribute("templates", templates);
			return "message/addtag";
		} else {
			return null;
		}
	}
	
	@RequestMapping(value = "/addTag", method = RequestMethod.POST)
	public String addTag(HttpServletRequest request) throws IOException{
		String tag = request.getParameter("tag");
		String template = request.getParameter("template");
		TagMapping tagMapping = new TagMapping();
		tagMapping.setTagId(tag);
		tagMapping.setTmpKey(template);
		if(messageService.addTag(tagMapping) > 0){
			logger.info("添加来源成功。");
			List<GsUser> gsUserList = userService.queryGsUsers(null);
			request.setAttribute("users", gsUserList);
			List<TagMapping> tags = messageService.getAllTagMapping();
			request.setAttribute("tags", tags);
			return "message/import";
		}
		return null;
	}
	
	@RequestMapping("/toSearchMessage")
	public String toSearchMessage(HttpServletRequest request) throws IOException{
		return "message/sousoly";
	}
	
	@RequestMapping("/toModifyAdmin")
	public String toModifyAdmin(HttpServletRequest request) throws IOException{
		List<Admin> admins = userService.getAllAdmins();
		request.setAttribute("admins", admins);
		return "message/admin";
	}
	
	@RequestMapping("/searchMessage")
	public String searchMessage(HttpServletRequest request) throws IOException{
		String tel = request.getParameter("tel");
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		Map<String, Object> searchCondition = new HashMap<String, Object>();
		searchCondition.put("msgId", id);
		searchCondition.put("custTel", tel);
		searchCondition.put("custName", name);
		List<KhmessageWithBLOBs> msgList = messageService.searchMessage(searchCondition);
		this.getUsersMapAndSet(request);
		request.setAttribute("messages", msgList);
		return "message/adminresult";
	}
	
	@RequestMapping("/modifyAdmin")
	public String modifyAdmin(HttpServletRequest request) throws IOException{
		String id = request.getParameter("id");
		Admin ab = new Admin();
		ab.setId(Long.parseLong(id));
		ab.setAdminname(request.getParameter("adminname"));
		ab.setAdminpass(request.getParameter("adminpass"));
		ab.setVarGs(request.getParameter("var_gs"));
		if(userService.modifyAdmin(ab) > 0){
			List<Admin> admins = userService.getAllAdmins();
			request.setAttribute("admins", admins);
			return "message/admin";
		}
		return null;
	}
	
	@RequestMapping("/showUserMssage")
	public String showUserMssage(HttpServletRequest request) throws IOException{
		String type = null;
		String flag = request.getParameter("flag");
		if("mine".equals(flag)){
			request.getSession().setAttribute("m_type", "1");
		}else if("intent".equals(flag)){
			request.getSession().setAttribute("m_type", "2");
		}
		else if("unvisited".equals(flag)){
			request.getSession().setAttribute("m_type", "0");
		}
		type = (String)request.getSession().getAttribute("m_type");
		String  u_num = (String)request.getSession().getAttribute("usernum");
		MessageFilterDTO msgFilter = new MessageFilterDTO();
		msgFilter.setGsNum(u_num);
		msgFilter.setMsgType(type);
		this.handleUserMessage(request, msgFilter, flag);
		return "message/zslookly";
	}
	
	private void handleUserMessage(HttpServletRequest request, MessageFilterDTO msgFilter, String flag){
		int pageNow=1;
		String offset = request.getParameter("offset");
		String n_pageNow = request.getParameter("pageNow");
		
		if(null != offset && !"".equals(offset.trim())){
			pageNow = Integer.parseInt(offset);
		}
		else if(n_pageNow!=null && !"".equals(n_pageNow.trim())){
			pageNow = Integer.parseInt(n_pageNow);
		}
		int messageCount = messageService.queryMessageCount(msgFilter);
		int pageCount = (messageCount + PAGE_SIZE - 1) / PAGE_SIZE;
		int startNo = (pageNow - 1) * PAGE_SIZE;
		List<KhmessageWithBLOBs> messageList = messageService.queryMessageByPage(startNo, PAGE_SIZE, msgFilter);
		this.getUsersMapAndSet(request);
		request.setAttribute("messages", messageList);
		request.setAttribute("pageCount", pageCount);
		request.getSession().setAttribute("pageNow", String.valueOf(pageNow));
		request.setAttribute("messageCount", messageCount);
		request.setAttribute("userid", msgFilter.getGsNum());
		request.setAttribute("flag", flag);
	}
	
	@RequestMapping("/gsToAddMessage")
	public String gsToAddMessage(){
		return "message/addly";
	}
	
	@RequestMapping("/toChangeByUser")
	public String toChangeByUser(HttpServletRequest request) throws IOException{
		String num = request.getParameter("num");
		String id = request.getParameter("id");
		String flag = request.getParameter("flag");
		String type = request.getParameter("type");
		List<GsUser> userList = userService.getAllGsUsers(null);
		request.setAttribute("users", userList);
		request.setAttribute("num", num);
		request.setAttribute("id", id);
		request.setAttribute("flag", flag);
		request.setAttribute("type", type);
		return "message/modifyren1";
	}
	
	@RequestMapping("/mssageIntent")
	public String mssageIntent(HttpServletRequest request, HttpServletResponse response) throws IOException{
		logger.info("移入意向组，user:{}", request.getParameter("usernum"));
		String resultCode = this.modifyType(request, 2);
		response.getWriter().write(resultCode);
		return null;
	}
	@RequestMapping("/mssageOutIntent")
	public String mssageOutIntent(HttpServletRequest request, HttpServletResponse response) throws IOException{
		logger.info("移出意向组，user:{}", request.getParameter("usernum"));
		String resultCode = this.modifyType(request, 1);
		response.getWriter().write(resultCode);
		return null;
	}
	private String modifyType(HttpServletRequest request, Integer type){
		String messageid = request.getParameter("messageid");
		KhmessageWithBLOBs message = new KhmessageWithBLOBs();
		message.setId(Long.parseLong(messageid));
		message.setType(type);
		if(messageService.updateMessage(message) > 0){
			return "1";
		}
		return "0";
	}
	
	@RequestMapping("/toModifyHf")
	public String toModifyHf(HttpServletRequest request) throws IOException{
		String id = request.getParameter("id");
		String type = request.getParameter("type");
		String pageNow = request.getParameter("pageNow");
		KhmessageWithBLOBs message = messageService.getMessageById(id);
		if(message.getLasthf() != null && !"".equals(message.getLasthf())){
			message.setLasthf(message.getLasthf().substring(0, 10));
		}
		request.setAttribute("message", message);
		request.setAttribute("type", type);
		request.setAttribute("pageNow", pageNow);
		return "message/modify_ly";
	}
	
	@RequestMapping("/modifyHf")
	public String modifyHf(HttpServletRequest request) throws IOException{
		String id = request.getParameter("mid");
		String hf = request.getParameter("textarea");
		String lasthf = request.getParameter("lasthg");
		String type = request.getParameter("type");
		String flag = request.getParameter("flag");
		String pageNow = request.getParameter("pageNow");
		String forwardUrl = "..";
		if(null != type && "task".equals(type)){
			forwardUrl += "/message/showUserTask?pageNow=" + pageNow;
		} else {
			forwardUrl += "/message/showUserMssage?pageNow=" + pageNow;
		}
		KhmessageWithBLOBs message = new KhmessageWithBLOBs();
		message.setId(Long.parseLong(id));
		message.setFpHf(hf);
		message.setLasthf(lasthf);
		if("0".equals(flag)){
			message.setType(1);
		}
		if(messageService.updateMessage(message) > 0){
			return "redirect:" + forwardUrl;
		}
		return null;
	}
	
	@RequestMapping("/completeMsg")
	public String completeMsg(HttpServletRequest request) throws IOException{
		return this.togleComplete(request, 3);
	}
	
	@RequestMapping("/unCompleteMsg")
	public String unCompleteMsg(HttpServletRequest request) throws IOException{
		return this.togleComplete(request, 1);
	}
	private String togleComplete(HttpServletRequest request, int type){
		String messageid = request.getParameter("id");
		String pageNow = request.getParameter("pageNow");
		KhmessageWithBLOBs message = new KhmessageWithBLOBs();
		message.setId(Long.parseLong(messageid));
		message.setType(type);
		if(messageService.updateMessage(message) > 0){
			return "redirect:../message/showUserMssage?pageNow=" + pageNow;
		}
		return null;
	}
	
	@RequestMapping("/showUserCompleteMessage")
	public String showUserCompleteMessage(HttpServletRequest request) throws IOException{
		String flag = request.getParameter("flag");
		String  u_num = (String)request.getSession().getAttribute("usernum");
		MessageFilterDTO msgFilter = new MessageFilterDTO();
		msgFilter.setGsNum(u_num);
		msgFilter.setMsgType("3");
		this.handleUserMessage(request, msgFilter, flag);
		return "message/usercomplete";
	}
	
	@RequestMapping("/toUserSearchMessage")
	public String toUserSearchMessage(HttpServletRequest request) throws IOException{
		return "message/sousoly2";
	}
	
	@RequestMapping("/searchUserMessage")
	public String searchUserMessage(HttpServletRequest request) throws IOException{
		String tel = request.getParameter("tel");
		String usernum = (String)request.getSession().getAttribute("usernum");
		Map<String, Object> searchCondition = new HashMap<String, Object>();
		searchCondition.put("custTel", tel);
		List<KhmessageWithBLOBs> msgList = messageService.searchMessage(searchCondition);
		this.getUsersMapAndSet(request);
		request.setAttribute("messages", msgList);
		request.setAttribute("nowUser", usernum);
		return "message/userresult";
	}
	
	@RequestMapping("/showUserTask")
	public String showUserTask(HttpServletRequest request) throws IOException{
		String flag = request.getParameter("flag");//tomorrow  today
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Date date=new Date();
		String timeNow = df.format(date);
	    Calendar calendar = new GregorianCalendar();
	    calendar.setTime(date);
	    calendar.add(Calendar.DATE,1);//明天
	    date=calendar.getTime();
	    String timeTomorrow = df.format(date);
	    calendar.add(Calendar.DATE,1);//后天
	    date=calendar.getTime();
	    String afterTomorrow = df.format(date);
		String time = "";
		MessageFilterDTO msgFilter = new MessageFilterDTO();
		if("today".equals(flag)){
			request.getSession().setAttribute("time", timeNow);
		} else if ("tomorrow".equals(flag)){
			request.getSession().setAttribute("time", timeTomorrow);
		} else if ("aftertomorrow".equals(flag)){
			request.getSession().setAttribute("time", afterTomorrow);
		} else if ("history".equals(flag)){
			request.getSession().setAttribute("time", flag);
			msgFilter.setMsgType("1");
		}
		time = (String)request.getSession().getAttribute("time");
		String user_num = (String)request.getSession().getAttribute("usernum");
		msgFilter.setGsNum(user_num);
		msgFilter.setNextVisitTime(time);
		this.handleUserMessage(request, msgFilter, flag);
		request.setAttribute("tasktime", time);
		return "message/usertask";
	}
}
