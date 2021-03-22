package com.aitrades.blockchain.trade.repository;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.stereotype.Repository;

import com.aitrades.blockchain.trade.domain.Order;

@Repository
public class PersistProcessedOrderToMongo {
	
	@Resource(name = "buyOrderMongoTemplate")
	public ReactiveMongoTemplate buyOrderMongoTemplate;
	
	@Resource(name = "sellOrderMongoTemplate")
	public ReactiveMongoTemplate sellOrderMongoTemplate;
	
	@Resource(name = "snipeOrderMongoTemplate")
	public ReactiveMongoTemplate snipeOrderMongoTemplate;

	public void updateOrSaveProcessedOrder(Order order) {
		if(StringUtils.equalsIgnoreCase("", "buy")) {
//			Class orde;
//			buyOrderMongoTemplate.update(orde)
		}else if(StringUtils.equalsIgnoreCase("", "buy")) {
//			Class orde;
//			sellOrderMongoTemplate.update(orde)
		}else if(StringUtils.equalsIgnoreCase("", "SNIPE")) {// come back
//			Class orde;
//			snipeOrderMongoTemplate.update(orde)
		}
	}
	
	
}
