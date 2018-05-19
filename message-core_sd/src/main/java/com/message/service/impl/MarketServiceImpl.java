package com.message.service.impl;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.message.dao.MarketMapper;
import com.message.domain.MarketWithBLOBs;
import com.message.domain.MessageFilterDTO;
import com.message.service.IMarketService;

@Service
public class MarketServiceImpl implements IMarketService {

	private Logger logger = LoggerFactory.getLogger(getClass());
	@Resource
	private MarketMapper marketMapper;
	
	@Override
	public List<MarketWithBLOBs> queryMarketByPage(String startNo,
			int pageSize, MessageFilterDTO msgFilter) {
		Map<String, Object> paramMap = new HashMap<String, Object>(); 
		paramMap.put("startNo", startNo);
		paramMap.put("pageSize", pageSize);
		
		putFieldInMap(paramMap, msgFilter);
		logger.info("分页查询市场客户留言，参数:{}", paramMap);
		List<MarketWithBLOBs> marketList = marketMapper.selectMarketByPage(paramMap);
		return marketList;
	}

	@Override
	public MarketWithBLOBs getMarketById(String id) {
		try{
			Short priKey = Short.parseShort(id);
			return marketMapper.selectByPrimaryKey(priKey);
		}catch(Exception e){
			logger.warn("Market ID类型转换错误！id:{}", id);
			return null;
		}
	}

	@Override
	public int updateMarket(MarketWithBLOBs market) {
		logger.info("修改market信息:id：{}，khTel: {}", market.getId(), market.getKhTel());
		return marketMapper.updateByPrimaryKeySelective(market);
	}

	@Override
	public int addMarket(MarketWithBLOBs market) {
		logger.info("新增market信息, khTel: {}", market.getKhTel());
		if(marketMapper.queryExistTel(market.getKhTel()) > 0){
			logger.info("market信息在数据库中已经存在, khTel: {}", market.getKhTel());
			return 0;
		}
		return marketMapper.insertSelective(market);
	}

	@Override
	public List<MarketWithBLOBs> searchMarket(Map<String, Object> conditions) {
		logger.info("搜索 market信息, conditions: {}", conditions);
		return marketMapper.searchMarketByCondition(conditions);
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

}
