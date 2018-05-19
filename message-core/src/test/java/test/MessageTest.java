package test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.message.domain.KhmessageWithBLOBs;
import com.message.domain.MessageFilterDTO;
import com.message.service.IMessageService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:application.xml",
		"classpath:spring-mybatis.xml" })
public class MessageTest {
	
	@Resource
	private IMessageService messageService;
	@Test
	public void testQueryMessage(){
		MessageFilterDTO msgFilter = new MessageFilterDTO();
		msgFilter.setGsNum("9");
		msgFilter.setMsgType("1");
		msgFilter.setNextVisitTime("2016-10-14");
		List<KhmessageWithBLOBs> messageList = messageService.queryMessageByPage(11, 10, msgFilter);
		for(KhmessageWithBLOBs message : messageList){
			System.out.println(message.getId() + "-" + message.getKhName() + "-" + message.getKhTel() + "-" + message.getKhLy());
		}
	}
	
	@Test
	public void testSearchMessage(){
		Map<String, Object> condition = new HashMap<String, Object>();
		//condition.put("custTel", "13071755385");
		condition.put("custName", "韩彦彬");
		List<KhmessageWithBLOBs> messageList = messageService.searchMessage(condition);
		for(KhmessageWithBLOBs message : messageList){
			System.out.println(message.getId() + "-" + message.getKhName() + "-" + message.getKhTel() + "-" + message.getKhLy());
		}
	}
	
	@Test
	public void testGetLinkData(){
		List<String> messageList = messageService.getLinkDataList("130", null);
		for(String message : messageList){
			System.out.println(message);
		}
	}
	
	@Test
	public void testImportExcel(){
		String fileName = "C:\\Users\\wangfei\\Desktop\\test1.xls";
		File excleFile = new File(fileName);
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(excleFile);
			List<String> failList = messageService.checkAndImportMsgByTag("23网", "4", fis);
			for(String failTel : failList){
				System.out.println(failTel);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
