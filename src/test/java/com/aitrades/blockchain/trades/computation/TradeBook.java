package com.aitrades.blockchain.trades.computation;

import org.apache.commons.lang.builder.ToStringBuilder;

public class TradeBook {

	private String fromContractAddress;
	private String toContractAddress;
	private Double buyLimit;
	private String buySide;
	private String orderLimitType;
	private String ensName;

	public String getFromContractAddress() {
		return fromContractAddress;
	}
	public void setFromContractAddress(String fromContractAddress) {
		this.fromContractAddress = fromContractAddress;
	}
	public String getToContractAddress() {
		return toContractAddress;
	}
	public void setToContractAddress(String toContractAddress) {
		this.toContractAddress = toContractAddress;
	}
	public Double getBuyLimit() {
		return buyLimit;
	}
	public void setBuyLimit(Double buyLimit) {
		this.buyLimit = buyLimit;
	}
	public String getBuySide() {
		return buySide;
	}
	public void setBuySide(String buySide) {
		this.buySide = buySide;
	}
	public String getOrderLimitType() {
		return orderLimitType;
	}
	public void setOrderLimitType(String orderLimitType) {
		this.orderLimitType = orderLimitType;
	}
	
	public String getEnsName() {
		return ensName;
	}
	public void setEnsName(String ensName) {
		this.ensName = ensName;
	}
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}
