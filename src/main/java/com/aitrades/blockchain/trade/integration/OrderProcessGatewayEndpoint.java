package com.aitrades.blockchain.trade.integration;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.annotation.Transformer;
import org.web3j.crypto.Credentials;

import com.aitrades.blockchain.trade.computation.OrderTypeComputation;
import com.aitrades.blockchain.trade.domain.Order;
import com.aitrades.blockchain.trade.domain.OrderDecision;
import com.aitrades.blockchain.trade.domain.TransactionRequest;
import com.aitrades.blockchain.trade.domain.side.OrderState;
import com.aitrades.blockchain.trade.domain.side.OrderType;
import com.aitrades.blockchain.trade.repository.PersistProcessedOrderToMongo;
import com.aitrades.blockchain.trade.service.mq.RabbitMqOrderPublisher;
import com.google.common.collect.Lists;
import com.jsoniter.JsonIterator;
public class OrderProcessGatewayEndpoint {
	
	private static final String ORDER = "ORDER";
	private static final String TRANSACTION_REQUEST = "TRANSACTION_REQUEST";
	private static final String ORDER_DECISION = "ORDER_DECISION";

	@Autowired
	private OrderTypeComputation orderComputation;
	
	@Autowired
	private PersistProcessedOrderToMongo persistProcessedOrder;
	
	@Autowired
	private RabbitMqOrderPublisher rabbitMqOrderPublisher;
	
	@Transformer(inputChannel = "rabbitMqProcessOrderConsumer", outputChannel = "tradeOrdercomputationChannel")
	public Map<String, Object> rabbitMqProcessOrderConsumer(byte[] message){
		String orderstr = new String(message);
		Order order  = JsonIterator.deserialize(orderstr, Order.class);
		Map<String, Object> aitradesMap = new ConcurrentHashMap<String, Object>();
		aitradesMap.put(ORDER, order);
		return aitradesMap;
	}

	@ServiceActivator(inputChannel = "tradeOrdercomputationChannel", outputChannel = "transformInputChannel")
	public Map<String, Object> tradeOrdercomputationChannel(Map<String, Object> tradeOrderMap){
		Order order = (Order)tradeOrderMap.get(ORDER);
			if(order.getOrderCode() != null && orderStateCheck(order)) {
				if(StringUtils.equalsIgnoreCase(OrderType.LIMIT.name(), order.getOrderEntity().getOrderType())) {
					 return orderComputation.limitOrder(order, tradeOrderMap);
				}
				if(StringUtils.equalsIgnoreCase(OrderType.STOPLOSS.name(), order.getOrderEntity().getOrderType())) {
					 return orderComputation.stopLossOrder(order, tradeOrderMap);
				}
				if(StringUtils.equalsIgnoreCase(OrderType.STOPLIMIT.name(), order.getOrderEntity().getOrderType())) {
					 return orderComputation.stopLimitOrder(order, tradeOrderMap);
				}
				if(StringUtils.equalsIgnoreCase(OrderType.TRAILLING_STOP.name(), order.getOrderEntity().getOrderType())) {
					 return orderComputation.trailingStopOrder(order, tradeOrderMap);
				}
				if(StringUtils.equalsIgnoreCase(OrderType.LIMIT_TRAILLING_STOP.name(), order.getOrderEntity().getOrderType())) {
					 return orderComputation.limitTrailingStopOrder(order, tradeOrderMap);
				}
				if(StringUtils.equalsIgnoreCase(OrderType.MARKET.name(), order.getOrderEntity().getOrderType())) {
					 return orderComputation.marketOrder(order, tradeOrderMap);
				}
				if(StringUtils.equalsIgnoreCase(OrderType.SNIPE.name(), order.getOrderEntity().getOrderType())) {
					  return orderComputation.snipeOrder(order, tradeOrderMap);
				}
			}else {
				order.setOrderCode(86);
			}
		return tradeOrderMap;
	}

	private boolean orderStateCheck(Order order) {
		return OrderState.WORKING.name().equalsIgnoreCase(order.getOrderEntity().getOrderState()) 
				|| OrderState.PARTIAL_FILLED.name().equalsIgnoreCase(order.getOrderEntity().getOrderState()) ;
	}
	
	@ServiceActivator(inputChannel = "transformInputChannel", outputChannel = "saveToMongoBasedOnOrderTypeChannel")
	public Map<String, Object>  transformInputChannel(Map<String, Object> tradeOrderMap){
		Order order = (Order)tradeOrderMap.get(ORDER);
		if(order.getOrderCode() != null && tradeOrderMap.get(ORDER_DECISION) != null) {
			TransactionRequest transactionRequest = new TransactionRequest();
			
			transactionRequest.setRoute(order.getRoute());
			
			transactionRequest.setCredentials(Credentials.create(order.getWalletInfo().getPrivateKey()));
			transactionRequest.setHasFee(false);
			
			transactionRequest.setFromAddress(order.getFrom().getTicker().getAddress());
			transactionRequest.setToAddress(order.getTo().getTicker().getAddress());
			
			transactionRequest.setPairAddress(order.getPairData().getPairAddress().getAddress());
			
			transactionRequest.setInputTokenValueAmountAsBigDecimal(order.getFrom().getAmountAsBigDecimal());

			if(OrderDecision.BUY.equals(tradeOrderMap.get(ORDER_DECISION))) {
				transactionRequest.setMemoryPath(Lists.newArrayList(order.getTo().getTicker().getAddress(), order.getFrom().getTicker().getAddress()));
			}else {
				transactionRequest.setMemoryPath(Lists.newArrayList(order.getFrom().getTicker().getAddress(), order.getTo().getTicker().getAddress()));
			}
			
			transactionRequest.setGasMode(order.getGasMode());
			transactionRequest.setGasPrice(order.getGasPrice().getValueBigInteger());
			transactionRequest.setGasLimit(order.getGasLimit().getValueBigInteger());
			
			transactionRequest.setSlipage(order.getSlippage().getSlipageInBips());
			
			tradeOrderMap.put(TRANSACTION_REQUEST, transactionRequest);
			order.setOrderCode(84);
		}else {
			 order.setOrderCode(83);
		}
		return tradeOrderMap;
	}
	
	@ServiceActivator(inputChannel = "saveToMongoBasedOnOrderTypeChannel", outputChannel = "sendToOrderSubmitQueueChannel")
	public Map<String, Object> saveToMongoBasedOnOrderTypeChannel(Map<String, Object> tradeOrderMap){
		Order order = (Order)tradeOrderMap.get(ORDER);
		if(order.getOrderCode() != null && (order.getOrderCode().equals(83) || order.getOrderCode().equals(84))) {
			persistProcessedOrder.updateOrSaveProcessedOrder(order);
		}
		return tradeOrderMap;
	}
	
	@ServiceActivator(inputChannel = "sendToOrderSubmitQueueChannel")
	public Map<String, Object> sendToOrderSubmitQueueChannel(Map<String, Object> tradeOrderMap){
		Order order = (Order)tradeOrderMap.get(ORDER);
		TransactionRequest transactionRequest = (TransactionRequest)tradeOrderMap.get(TRANSACTION_REQUEST);
		if(order.getOrderCode() != null && order.getOrderCode().equals(84)) {
			rabbitMqOrderPublisher.sendOrder((OrderDecision)tradeOrderMap.get(ORDER_DECISION), transactionRequest);
		}
		return tradeOrderMap;
	}
	
	
}
