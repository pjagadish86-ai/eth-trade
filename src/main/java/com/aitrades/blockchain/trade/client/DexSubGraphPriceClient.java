package com.aitrades.blockchain.trade.client;

import java.math.BigDecimal;

import com.aitrades.blockchain.trade.domain.price.EthPrice;
import com.aitrades.blockchain.trade.domain.price.PairPrice;

import io.reactivex.Flowable;

public interface DexSubGraphPriceClient {
	
	public String getResourceUrl(String route);
	
	public BigDecimal getPriceOfTicker(String pairAddress);
	
	public BigDecimal calculateTickerPrice(PairPrice pairPrice, EthPrice ethPrice);
	
	public Flowable<PairPrice> getPairData(final String pairAddress);
	
	public Flowable<EthPrice> getEthPrice();
	
}
