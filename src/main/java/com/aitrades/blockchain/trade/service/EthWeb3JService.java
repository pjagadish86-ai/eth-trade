package com.aitrades.blockchain.trade.service;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.rx.Web3jRx;

@Service
public class EthWeb3JService{
	
	@Resource(name = "web3jClient")
	private Web3j web3j;

	@Autowired
	private Web3jRx web3jRx;
	
	//private Web3jService web3jService;
	
}
