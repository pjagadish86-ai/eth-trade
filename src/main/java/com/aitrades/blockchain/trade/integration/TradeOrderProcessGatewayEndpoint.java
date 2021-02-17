package com.aitrades.blockchain.trade.integration;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.annotation.ServiceActivator;

import com.aitrades.blockchain.trade.computation.ITraillingStopComputation;
import com.aitrades.blockchain.trade.domain.Order;
import com.aitrades.blockchain.trade.transformer.TradeOrderToTransactionFascade;

public class TradeOrderProcessGatewayEndpoint {
	
	@Autowired
	private ITraillingStopComputation traillingStopComputation;

	@Autowired
	private TradeOrderToTransactionFascade orderToTransactionFascade;
	
	
	@ServiceActivator(inputChannel = "validateInput", outputChannel = "tradeOrdercomputation")
	public Map<String, Object>  validateInput(List<Order> tradeOrderMap){
		System.out.println("in validator");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("orders", tradeOrderMap);
		map.put("id", "jay");
		return map;
	}
	
	@ServiceActivator(inputChannel = "tradeOrdercomputation", outputChannel = "transformInput")
	public  Map<String, Object>  tradeOrdercomputation(Map<String, Object> tradeOrderMap){
		System.out.println("in tradeOrdercomputation"+ tradeOrderMap.get("id"));
		return tradeOrderMap;
	}
	
	@ServiceActivator(inputChannel = "transformInput", outputChannel = "web3jApprove")
	public  Map<String, Object>  transformInput(Map<String, Object> tradeOrderMap){
		System.out.println("in transformInput"+ tradeOrderMap.get("id"));
		return tradeOrderMap;
	}
	
	@ServiceActivator(inputChannel = "web3jApprove", outputChannel = "web3jTransaction")
	public  Map<String, Object>  web3jApprove(Map<String, Object> tradeOrderMap){
		System.out.println("in web3jApprove"+ tradeOrderMap.get("id"));
		return tradeOrderMap;
	}
	
	@ServiceActivator(inputChannel = "web3jTransaction")
	public Map<String, Object> web3jTransaction(Map<String, Object> tradeOrderMap){
		System.out.println("in web3jTransaction"+ tradeOrderMap.get("id"));
		return tradeOrderMap;
	}
	
}
