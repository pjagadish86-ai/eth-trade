package com.aitrades.blockchain.trade.computation;

import java.util.Map;

import com.aitrades.blockchain.trade.domain.TradeOrderMessage;

public interface ITraillingStop {

	public Map<String, Object> limitPrice(TradeOrderMessage orderMessage);
	public Map<String, Object> stopPrice(TradeOrderMessage orderMessage);
	public Map<String, Object> trailingStopComputation(TradeOrderMessage orderMessage);
	
}
