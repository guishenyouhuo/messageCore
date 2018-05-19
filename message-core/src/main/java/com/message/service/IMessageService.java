package com.message.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

import com.message.domain.Autoly;
import com.message.domain.KhmessageWithBLOBs;
import com.message.domain.MessageFilterDTO;
import com.message.domain.MsgTemplate;
import com.message.domain.TagMapping;

public interface IMessageService {
	
	/**
	 * 分页查询，支持id和type过滤
	 * @param id
	 * @param type
	 * @return
	 */
	int queryMessageCount(MessageFilterDTO msgFilter);
	
	/**
	 * 分页查询，支持id和type过滤
	 * @param startNo
	 * @param pageSize
	 * @param id
	 * @param type
	 * @return
	 */
	List<KhmessageWithBLOBs> queryMessageByPage(int startNo, int pageSize, MessageFilterDTO msgFilter);
	
	/**
	 * 根据id获取message
	 * @param id
	 * @return
	 */
	KhmessageWithBLOBs getMessageById(String id);
	
	/**
	 * 修改message
	 * @param message
	 * @return
	 */
	int updateMessage(KhmessageWithBLOBs message);
	
	/**
	 * 添加message
	 * @param message
	 * @return
	 */
	int addMessage(KhmessageWithBLOBs message);
	
	/**
	 * 删除message
	 * @param id
	 * @return
	 */
	int deleteMessage(String id);
	
	/**
	 * 根据条件搜索message
	 * @param conditions
	 * @return
	 */
	List<KhmessageWithBLOBs> searchMessage(Map<String, Object> conditions);
	
	/**
	 * 根据电话号码模糊查询
	 * @param telNo
	 * @param gsNum
	 * @return
	 */
	List<String> getLinkDataList(String telNo, String gsNum);
	
	/**
	 * 根据来源导入message并返回导入失败的记录电话号码
	 * @param fromTag
	 * @param is 传入的文件流
	 * @return
	 */
	List<String> checkAndImportMsgByTag(String fromTag, String selectUser, InputStream is) throws IOException;
	
	/**
	 * 获取自动分配留言记录
	 * @param pkNo
	 * @return
	 */
	Autoly getAutoLyRecord(String pkNo);
	
	/**
	 * 更新自动分配留言记录
	 * @param autoLy
	 * @return
	 */
	int updateAutoLyRecord(Autoly autoLy);
	
	/**
	 * 根据用户号获取留言id
	 * @param userNum
	 * @return
	 */
	List<String> getMessageIdByUser(String userNum);
	
	/**
	 * 人工批量分配留言
	 * @param userMsgMap
	 * @return
	 */
	int dividMessage(Map<String, List<String>> userMsgMap);
	
	/**
	 * 检查电话号码是否存在
	 * @param tel
	 * @return
	 */
	boolean checkTelExist(String tel);
	
	/**
	 * 获取所有来源列表
	 * @return
	 */
	List<TagMapping> getAllTagMapping();
	
	/**
	 * 获取所有模板列表
	 * @return
	 */
	List<MsgTemplate> getAllTemplate();
	
	/**
	 * 添加模板
	 * @param template
	 * @return
	 */
	int addTemplate(MsgTemplate template);
	
	/**
	 * 添加来源
	 * @param tag
	 * @return
	 */
	int addTag(TagMapping tag);
	
	/**
	 * 自动未回访
	 * @return
	 */
	int autoUnVisit();
}
