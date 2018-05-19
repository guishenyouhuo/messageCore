package com.message.service.impl;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.message.dao.KhmessageMapper;
import com.message.service.IMessageService;

@Service("messageService")
public class MessageServiceImpl implements IMessageService {

	@Autowired
	private KhmessageMapper khmessageMapper;
	Logger logger = LoggerFactory.getLogger(getClass());
	@Override
	public List<String> getMessageIdByUser(String userNum) {
		return khmessageMapper.selectMsgIdByUser(userNum);
	}
	@Override
	public int dividMessage(Map<String, List<String>> userMsgMap) {
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
}
