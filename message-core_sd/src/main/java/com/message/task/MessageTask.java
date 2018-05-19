package com.message.task;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.message.service.IMessageService;

@Component
public class MessageTask {
	private Logger logger = LoggerFactory.getLogger(getClass());
	@Resource
	private IMessageService messageService;
	@Scheduled(cron="0 0/30 1-2 * * ?")
	public void autoUnVisited(){
		logger.info("未回访留言自动进入未回访分组！");
		messageService.autoUnVisit();
	}
}
