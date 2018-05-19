package com.message.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.message.dao.AutolyMapper;
import com.message.dao.KhmessageMapper;
import com.message.dao.MsgTemplateMapper;
import com.message.dao.TagMappingMapper;
import com.message.domain.Autoly;
import com.message.domain.GsUser;
import com.message.domain.KhmessageWithBLOBs;
import com.message.domain.MessageFilterDTO;
import com.message.domain.MsgTemplate;
import com.message.domain.TagMapping;
import com.message.service.IMessageService;
import com.message.service.IUserService;

@Service
public class MessageServiceImpl implements IMessageService {

	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Resource
	private KhmessageMapper khmessageMapper;
	@Resource
	private TagMappingMapper tagMappingMapper;
	@Resource
	private MsgTemplateMapper msgTemplateMapper;
	@Resource
	private AutolyMapper autolyMapper;
	@Resource
	private IUserService userService;
	
	private static final String FIRST_LINE_FALSE = "0";
	private static final char COL_BASE_CHAR = 'a';
	private static final String FIRST_ZERO = "0";
	private static final int PAGE_SIZE = 1000;
	private static final String FP_USER_AUTO = "autoly";
	
	@Override
	public int queryMessageCount(MessageFilterDTO msgFilter) {
		Map<String, Object> paramMap = new HashMap<String, Object>(); 
		putFieldInMap(paramMap, msgFilter);
		return khmessageMapper.selectMessageCount(paramMap);
	}
	
	@Override
	public List<KhmessageWithBLOBs> queryMessageByPage(int startNo,
			int pageSize, MessageFilterDTO msgFilter) {
		Map<String, Object> paramMap = new HashMap<String, Object>(); 
		paramMap.put("startNo", startNo);
		paramMap.put("pageSize", pageSize);
		
		putFieldInMap(paramMap, msgFilter);
		logger.info("分页查询客户留言，参数:{}", paramMap);
		List<KhmessageWithBLOBs> khmessageList = khmessageMapper.selectMessageByPage(paramMap);
		return khmessageList;
	}

	@Override
	public KhmessageWithBLOBs getMessageById(String id) {
		try{
			Long priKey = Long.parseLong(id);
			return khmessageMapper.selectByPrimaryKey(priKey);
		}catch(Exception e){
			logger.warn("khmessage ID类型转换错误！id:{}", id);
			return null;
		}
	}

	@Override
	public int updateMessage(KhmessageWithBLOBs message) {
		logger.info("修改message信息:id：{}，khTel: {}", message.getId(), message.getKhTel());
		return khmessageMapper.updateByPrimaryKeySelective(message);
	}

	@Override
	public int addMessage(KhmessageWithBLOBs message) {
		logger.info("新增message信息, khTel: {}", message.getKhTel());
		return khmessageMapper.insertSelective(message);
	}

	@Override
	public int deleteMessage(String id) {
		try{
			Long priKey = Long.parseLong(id);
			return khmessageMapper.deleteByPrimaryKey(priKey);
		}catch(Exception e){
			logger.warn("khmessage ID类型转换错误！id:{}", id);
			return 0;
		}
	}
	
	private void putFieldInMap(Map<String, Object> paramMap, MessageFilterDTO msgFilterDTO){
		Class<?> cls = msgFilterDTO.getClass();
		Field[] dtoFields = cls.getDeclaredFields();
		Field.setAccessible(dtoFields,   true); 
		try {
			for(Field field : dtoFields){
				String fieldName = field.getName();
				Object fieldVal = field.get(msgFilterDTO);
				if(null != fieldVal){
					paramMap.put(fieldName, fieldVal);
				}
			}
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<KhmessageWithBLOBs> searchMessage(Map<String, Object> conditions) {
		logger.info("搜索 message信息, conditions: {}", conditions);
		return khmessageMapper.searchMessageByCondition(conditions);
	}

	@Override
	public List<String> getLinkDataList(String telNo, String gsNum) {
		return khmessageMapper.queryLinkedTel(telNo, gsNum);
	}

	@Override
	public List<String> checkAndImportMsgByTag(String fromTag, String selectUser, InputStream is) throws IOException {
		TagMapping tagMap = tagMappingMapper.selectByPrimaryKey(fromTag);
		logger.info("来源：{}, 模板ID：{}", tagMap.getTagId(), tagMap.getTmpKey());
		MsgTemplate template = msgTemplateMapper.selectByPrimaryKey(tagMap.getTmpKey());
		int firstRow = 1;
		// 判断是否存在第一行
		if(FIRST_LINE_FALSE.equals(template.getHasFirstline())){
			logger.info("第一行无用，从第二行开始解析！");
			firstRow = 2;
		}
		List<String> failTelList = null;
		List<String> telList = new ArrayList<String>(PAGE_SIZE);
		Map<String, KhmessageWithBLOBs> telMsgMap = new HashMap<String, KhmessageWithBLOBs>(PAGE_SIZE);
		HSSFWorkbook hssfWorkbook = null;
		try {
			hssfWorkbook = new HSSFWorkbook(is);
		} catch (IOException e) {
			logger.error("读取文件出现错误！", e);
			throw e;
		}
		// 循环工作表Sheet
        for (int numSheet = 0; numSheet < hssfWorkbook.getNumberOfSheets(); numSheet++) {
            HSSFSheet hssfSheet = hssfWorkbook.getSheetAt(numSheet);
            if (hssfSheet == null) {
                continue;
            }
            // 循环行Row
            for (int rowNum = firstRow; rowNum <= hssfSheet.getLastRowNum(); rowNum++) {
                HSSFRow hssfRow = hssfSheet.getRow(rowNum);
                if (hssfRow != null) {
                	try{
	                	KhmessageWithBLOBs message = this.buildmsgDTO(hssfRow, template, fromTag);
	                	telList.add(message.getKhTel());
	                	telMsgMap.put(message.getKhTel(), message);
                	} catch(Exception e){
                		logger.error("第{}行对象组装失败，跳过！", rowNum, e);
                	}
                }
                //达到一页大小，进行处理
                if(telMsgMap.size() >= PAGE_SIZE){
                	failTelList = this.checkAndBatchInsert(telMsgMap, telList, selectUser);
                	telMsgMap.clear();
                	telList.clear();
                }
            }
            // 处理为处理完的记录
            if(telMsgMap != null && telMsgMap.size() > 0){
            	failTelList = this.checkAndBatchInsert(telMsgMap, telList, selectUser);
            	telMsgMap.clear();
            	telList.clear();
            }
        }
        // 释放文件流资源
        if(is != null){
        	is.close();
        	is = null;
        }
		return failTelList;
	}
	
	/**
	 * 检查存在号码即批量插入
	 * @param messageList
	 * @param telList
	 * @return
	 */
	private List<String> checkAndBatchInsert(Map<String, KhmessageWithBLOBs> telMsgMap, List<String> telList, String selectUser){
		// 首先检查这一页中在数据库中有号码的记录
		List<String> existTelList = khmessageMapper.queryExistTel(telList);
		if(existTelList != null && !existTelList.isEmpty()){
			for(String existTel : existTelList){
				if(telMsgMap.containsKey(existTel)){
					telMsgMap.remove(existTel);
				}
			}
		}
		if(telMsgMap.isEmpty()){
			logger.info("没有可以插入的数据。");
			return existTelList;
		}
		// 转成 List
		List<KhmessageWithBLOBs> messageList = new ArrayList<KhmessageWithBLOBs>(telMsgMap.size());
		for(String keyTel : telMsgMap.keySet()){
			messageList.add(telMsgMap.get(keyTel));
		}
		if(FP_USER_AUTO.equals(selectUser)){
			// 自动分配
			Autoly autoLy = this.getAutoLyRecord("1");
			Integer nowNum = autoLy.getNownum();
			logger.info("自动分配，从{}开始分配", nowNum);
			// 查询所有生效的客户(type 为 1)
			List<GsUser> gsUserList = userService.queryGsUsers("1");
			int j = 0;
			for(int i = 0; i < messageList.size(); i++, j++){
				// 第一个要分配给nowNum
				while(i == 0 && gsUserList.get(j).getGsNum() != nowNum){
					if(j == gsUserList.size()){
						logger.warn("没有找到配置的nowNum, 按gsNum排序后从头分配。");
						break;
					}
					j++;
				}
				//如果j值达到最后一个则跳到最开始0
				if(j == gsUserList.size()){
					j = 0;
				}
				// 设置分配的用户
				messageList.get(i).setFpUser(gsUserList.get(j).getGsNum());
			}
			// 记录下一次分配的第一个用户, 最后一个是j，如果是列表中最后一个则下一个是0
			int nextIndex = (j == gsUserList.size()) ? 0 : j;
			autoLy.setNownum(gsUserList.get(nextIndex).getGsNum());
			// 更新自动分配留言记录表
			this.updateAutoLyRecord(autoLy);
		} else {
			logger.info("指定分配，分配给:{}", selectUser);
			for(KhmessageWithBLOBs message : messageList){
				message.setFpUser(Integer.parseInt(selectUser));
			}
		}
		// 进行批量插入
		if(messageList.size() > 0){
			khmessageMapper.bactchInsert(messageList);
		}
		logger.info("插入条数: {}", messageList.size());
		return existTelList;
	}
	
	/**
	 * 组建DTO
	 * @param hssfRow
	 * @param template
	 * @return
	 */
	private KhmessageWithBLOBs buildmsgDTO(HSSFRow hssfRow, MsgTemplate template, String fromTag){
		KhmessageWithBLOBs message = new KhmessageWithBLOBs();
		int telIndex = template.getColTel().toLowerCase().charAt(0) - COL_BASE_CHAR;
        HSSFCell tel = hssfRow.getCell(telIndex);
        String newtel = getValue(tel);
        newtel = newtel.replaceAll("'", "");// 去引号
        if(FIRST_ZERO.equals(newtel.substring(0, 1))){// 去首字母0
        	newtel = newtel.substring(1);
        }
        // 替换非数字字符
        message.setKhTel(newtel.replaceAll("[^0-9]", ""));
        
        if(template.getColName() == null || "".equals(template.getColName().trim())){
        	message.setKhName("");
        } else {
        	int nameIndex = template.getColName().toLowerCase().charAt(0) - COL_BASE_CHAR;
        	HSSFCell name = hssfRow.getCell(nameIndex);
        	message.setKhName(getValue(name));
        }
        if(template.getColAddr() == null || "".equals(template.getColAddr().trim())){
        	message.setKhAddress("");
        } else {
        	 int addrIndex = template.getColAddr().toLowerCase().charAt(0) - COL_BASE_CHAR;
        	 HSSFCell address = hssfRow.getCell(addrIndex);
        	 message.setKhAddress(getValue(address));
        }
        if(template.getColMsg() == null || "".equals(template.getColMsg().trim())){
        	message.setKhLy("");
        } else {
        	 int msgIndex = template.getColMsg().toLowerCase().charAt(0) - COL_BASE_CHAR;
        	 HSSFCell msg = hssfRow.getCell(msgIndex);
        	 message.setKhLy(getValue(msg));
        }
        message.setTag(fromTag);
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date=new Date();
		String timeNow=df.format(date);
		message.setIntime(timeNow);
		message.setType(1);
		return message;
	}
	
	private String getValue(HSSFCell hssfCell) {
		if(hssfCell == null){
			return "";
		}
        if (hssfCell.getCellType() == HSSFCell.CELL_TYPE_BOOLEAN) {
            // 返回布尔类型的值
            return String.valueOf(hssfCell.getBooleanCellValue());
        } else if (hssfCell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
            // 返回数值类型的值
        	DecimalFormat df = new DecimalFormat("0");  
            String cellValue = df.format(hssfCell.getNumericCellValue()); 
            return String.valueOf(cellValue);
        } else {
            // 返回字符串类型的值
            return String.valueOf(hssfCell.getStringCellValue());
        }
    }

	@Override
	public Autoly getAutoLyRecord(String pkNo) {
		logger.info("获取自动分配留言记录。");
		return autolyMapper.selectByPrimaryKey(1L);
	}

	@Override
	public int updateAutoLyRecord(Autoly autoLy) {
		logger.info("更新自动分配留言记录。");
		return autolyMapper.updateByPrimaryKeySelective(autoLy);
	}

	@Override
	public List<String> getMessageIdByUser(String userNum) {
		logger.info("获取留言ID列表。");
		return khmessageMapper.selectMsgIdByUser(userNum);
	}

	@Override
	public int dividMessage(Map<String, List<String>> userMsgMap) {
		logger.info("具体分配操作:{}。", userMsgMap);
		Set<String> keys = userMsgMap.keySet();
		int count = 0;
		for(String key : keys){
			List<String> msgs = userMsgMap.get(key);
			String fpUser = key;
			logger.info("开始转移：user:{}, messages:{}", fpUser, msgs);
			count += khmessageMapper.batchUpdateByUser(fpUser, msgs);
		}
		return count;
	}

	@Override
	public boolean checkTelExist(String tel) {
		logger.info("检查电话:{}是否存在。", tel);
		List<String> telList = new ArrayList<String>();
		telList.add(tel);
		List<String> existTelList = khmessageMapper.queryExistTel(telList);
		if(existTelList != null && !existTelList.isEmpty()){
			logger.info("tel在数据库中已经存在, khTel: {}", tel);
			return true;
		}
		return false;
	}

	@Override
	public List<TagMapping> getAllTagMapping() {
		logger.info("获取来源列表");
		return tagMappingMapper.selectAllTags();
	}

	@Override
	public List<MsgTemplate> getAllTemplate() {
		logger.info("获取模板列表");
		return msgTemplateMapper.selectAllTemplates();
	}

	@Override
	public int addTemplate(MsgTemplate template) {
		logger.info("添加模板");
		return msgTemplateMapper.insertSelective(template);
	}

	@Override
	public int addTag(TagMapping tag) {
		logger.info("添加来源");
		return tagMappingMapper.insertSelective(tag);
	}

	@Override
	public int autoUnVisit() {
		logger.info("更新未回访记录，time:{}", new Date());
		return khmessageMapper.updateToUnVisit();
	}
}
