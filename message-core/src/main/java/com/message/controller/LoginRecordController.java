package com.message.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
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

import com.alibaba.fastjson.JSONObject;
import com.message.domain.LoginRecord;
import com.message.domain.LoginRecordDetail;
import com.message.service.ILoginRecordService;
import com.message.utils.AddressUtils;

@Controller
@RequestMapping("/loginRecord")
public class LoginRecordController {

	@Resource
	private ILoginRecordService loginRecordService;
	
	private static final int PAGE_SIZE = 100;
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@RequestMapping("/showLoginRecords")
    public String showLoginRecords(HttpServletRequest request) {
		
		int pageNow=1;
		String offset = request.getParameter("offset");
		String n_pageNow = request.getParameter("pageNow");
		String userNum = request.getParameter("userNum");
		String loginTime = request.getParameter("loginTime");
		
		if(null != offset && !"".equals(offset.trim())){
			pageNow = Integer.parseInt(offset);
		}
		else if(n_pageNow!=null && !"".equals(n_pageNow.trim())){
			pageNow = Integer.parseInt(n_pageNow);
		}
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("loginNum", userNum);
		paramMap.put("loginTime", loginTime);
		int recordCount = loginRecordService.getLoginRecourCount(paramMap);
		int pageCount = (recordCount + PAGE_SIZE - 1) / PAGE_SIZE;
		int startNo = (pageNow - 1) * PAGE_SIZE;
		List<LoginRecord> recordList = loginRecordService.getLiginRecordByPage(startNo, PAGE_SIZE, paramMap);
		request.setAttribute("records", recordList);
		request.setAttribute("pageCount", pageCount);
		request.getSession().setAttribute("pageNow", String.valueOf(pageNow));
		request.setAttribute("recordCount", recordCount);
		
        return "jsp/loginrecord";
    }
	
	@RequestMapping("/lookIpDetail")
    public String lookIpDetail(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String ip = request.getParameter("loginIp");
		// json_result用于接收返回的json数据  
        String json_result = null;  
        response.setCharacterEncoding("utf-8");
        try {  
            json_result = AddressUtils.getAddresses("ip=" + ip, "utf-8");  
        } catch (UnsupportedEncodingException e) {  
            e.printStackTrace();
        }  
        try{
        	JSONObject json = (JSONObject)JSONObject.parse(json_result);  
            JSONObject dataJSON = (JSONObject)JSONObject.parse(json.get("data").toString());
          
            String country = dataJSON.get("country").toString();
            String region = dataJSON.get("region").toString();
            String city = dataJSON.get("city").toString();
            String county = dataJSON.get("county").toString();
            String isp = dataJSON.get("isp").toString();
            StringBuilder sb = new StringBuilder("IP地址" + ip + "信息如下：\n");
            sb.append("国家：").append(country).append("\n");
            sb.append("省份：").append(region).append("\n");
            sb.append("城市：").append(city).append("\n");
            sb.append("区/县：").append(county).append("\n");
            sb.append("运营商：").append(isp);
            
            response.getWriter().write(sb.toString());
        }catch(Exception e){
        	logger.warn("查询IP操作过于频繁！", e);
            response.getWriter().write("操作频繁！");
        }
		return null;
	}
	
	private List<LoginRecordDetail> buildLoginDetailList(List<LoginRecord> recordList){
		List<LoginRecordDetail> loginRecordDetailList = new ArrayList<LoginRecordDetail>(recordList.size());
		for(LoginRecord loginRecord : recordList){
			String loginIp = loginRecord.getLoginIp();
			// json_result用于接收返回的json数据  
	        String json_result = null;  
	        try {  
	            json_result = AddressUtils.getAddresses("ip=" + loginIp, "utf-8");  
	        } catch (UnsupportedEncodingException e) {  
	            e.printStackTrace();  
	        }  
	        JSONObject json = (JSONObject)JSONObject.parse(json_result);  
	        JSONObject dataJSON = (JSONObject)JSONObject.parse(json.get("data").toString());
	        
	        LoginRecordDetail recordDetail = new LoginRecordDetail();
	        recordDetail.setRecordId(loginRecord.getRecordId());
	        recordDetail.setLoginNum(loginRecord.getLoginNum());
	        recordDetail.setLoginIp(loginRecord.getLoginIp());
	        recordDetail.setLoginUser(loginRecord.getLoginUser());
	        recordDetail.setLoginTime(loginRecord.getLoginTime());
	        recordDetail.setLoginCountry(dataJSON.get("country").toString());
	        recordDetail.setLoginProvince(dataJSON.get("region").toString());
	        recordDetail.setLoginCity(dataJSON.get("city").toString());
	        recordDetail.setLoginCounty(dataJSON.get("county").toString());
	        recordDetail.setLoginIsp(dataJSON.get("isp").toString());
	        
	        loginRecordDetailList.add(recordDetail);  
		}
		return loginRecordDetailList;
	}
}
