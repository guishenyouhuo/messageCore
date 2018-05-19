package com.message.service;

import java.util.List;
import java.util.Map;

public interface IMessageService {
	List<String> getMessageIdByUser(String userNum);
	
	int dividMessage(Map<String, List<String>> userMsgMap);
}
