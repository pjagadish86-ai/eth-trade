package com.aitrades.blockchain.trade.transformer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TradeOrderToTransactionFascade {

	@Autowired
	private TradeOrderToTransactionMapStructMapper mapper;
	

}
