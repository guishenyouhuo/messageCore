package com.message.service;

import java.util.List;
import java.util.Map;

import com.message.domain.MarketWithBLOBs;
import com.message.domain.MessageFilterDTO;

public interface IMarketService {
	/**
	 * 分页查询，支持id和type过滤
	 * @param startNo
	 * @param pageSize
	 * @param id
	 * @param type
	 * @return
	 */
	List<MarketWithBLOBs> queryMarketByPage(String startNo, int pageSize, MessageFilterDTO msgFilter);
	
	/**
	 * 根据id获取market
	 * @param id
	 * @return
	 */
	MarketWithBLOBs getMarketById(String id);
	
	/**
	 * 修改market
	 * @param market
	 * @return
	 */
	int updateMarket(MarketWithBLOBs market);
	
	/**
	 * 添加market
	 * @param market
	 * @return
	 */
	int addMarket(MarketWithBLOBs market);
	
	/**
	 * 根据条件搜索market
	 * @param conditions
	 * @return
	 */
	List<MarketWithBLOBs> searchMarket(Map<String, Object> conditions);
}
