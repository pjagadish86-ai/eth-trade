package com.aitrades.blockchain.trade.service.mq;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aitrades.blockchain.trade.domain.OrderDecision;
import com.aitrades.blockchain.trade.domain.TransactionRequest;

@Service
public class RabbitMqOrderPublisher {

	@Autowired
	public RabbitMqBuyOrderPublisher buyOrderPublisher;
	
	@Autowired
	public RabbitMqSellOrderPublisher sellOrderPublisher;
	
	@Autowired
	public RabbitMqSnipeOrderPublisher snipeOrderPublisher;
	
	public void sendOrder(OrderDecision orderDecision, TransactionRequest transactionRequest) {
		if(orderDecision.equals(OrderDecision.BUY)) {
			buyOrderPublisher.send(transactionRequest);
		}else if(orderDecision.equals(OrderDecision.SELL)) {
			sellOrderPublisher.send(transactionRequest);
		}else if(orderDecision.equals(OrderDecision.SNIPE)) {
			snipeOrderPublisher.send(transactionRequest);
		}
	}

}
