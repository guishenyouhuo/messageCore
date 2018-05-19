package com.message.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DivideMessage {

	public static Map<String, List<String>> dividMessageByUsers(
			List<String> userNums, List<String> msgIds) {
		Map<String, List<String>> userMsgMap = new HashMap<String, List<String>>();
		for (int i = 0, j = 0; i < msgIds.size(); ++i, ++j) {
			String curUseNum = userNums.get(j);
			List<String> ids = null;
			if (!userMsgMap.containsKey(curUseNum)) {
				ids = new ArrayList<String>();
			} else {
				ids = userMsgMap.get(curUseNum);
			}
			ids.add(msgIds.get(i));
			userMsgMap.put(curUseNum, ids);
			if (j == userNums.size() - 1) {
				j = -1;
			}
		}
		return userMsgMap;
	}
}
