package com.aitrades.blockchain.trade.computation;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.aitrades.blockchain.trade.domain.TradeOrderMessage;

@Service
public class ITraillingStopComputation implements ITraillingStop{

	@Override
	public Map<String, Object> limitPrice(TradeOrderMessage orderMessage) {
		return null;
	}

	@Override
	public Map<String, Object> stopPrice(TradeOrderMessage orderMessage) {
		return null;
	}

	@Override
	public Map<String, Object> trailingStopComputation(TradeOrderMessage orderMessage) {
		return null;
	}


}
