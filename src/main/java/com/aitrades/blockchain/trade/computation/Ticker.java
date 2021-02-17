package com.aitrades.blockchain.trade.computation;

import java.math.BigInteger;

public class Ticker {

	private String address;
	private BigInteger tickerCurrentBidPriceInEthValue;
	private BigInteger tickerCurrentBidPriceInUSDValue;
	
	private Double plannedTickerTraillingStopPricePoint;

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public BigInteger getTickerCurrentBidPriceInEthValue() {
		return tickerCurrentBidPriceInEthValue;
	}

	public void setTickerCurrentBidPriceInEthValue(BigInteger tickerCurrentBidPriceInEthValue) {
		this.tickerCurrentBidPriceInEthValue = tickerCurrentBidPriceInEthValue;
	}

	public BigInteger getTickerCurrentBidPriceInUSDValue() {
		return tickerCurrentBidPriceInUSDValue;
	}

	public void setTickerCurrentBidPriceInUSDValue(BigInteger tickerCurrentBidPriceInUSDValue) {
		this.tickerCurrentBidPriceInUSDValue = tickerCurrentBidPriceInUSDValue;
	}

	public Double getPlannedTickerTraillingStopPricePoint() {
		return plannedTickerTraillingStopPricePoint;
	}

	public void setPlannedTickerTraillingStopPricePoint(Double plannedTickerTraillingStopPricePoint) {
		this.plannedTickerTraillingStopPricePoint = plannedTickerTraillingStopPricePoint;
	}

	
	
}
