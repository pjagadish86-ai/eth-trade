package com.aitrades.blockchain.trade.domain;

import java.math.BigInteger;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.springframework.data.annotation.Id;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)
public class Order {
	
	@Id
	private String id; 
	private String privateKey;
	private String publicKey;
	
    private String from;
    private String to;
    private BigInteger gasEth;
    private BigInteger gasPriceEth;
    private BigInteger quatity;
    private String data;
    private BigInteger nonce;
    private BigInteger gasPremiumEth;
    private BigInteger feeCapEth;

    private BigInteger gasUSD;
    private BigInteger gasPriceUSD;
    private BigInteger gasPremiumUSD;
    private BigInteger feeCapUSD;
    
    private Double slippageTolerance;
    private String transactionDeadLine;
    private String side;
    private String orderType;
    private String limitPrice;
    private String stopPrice;
    private String traillingStopPercentage;
    
	@JsonCreator
	public Order(@JsonProperty("privateKey") String privateKey, 
			     @JsonProperty("publicKey") String publicKey,
				 @JsonProperty("from") String from, 
				 @JsonProperty("to") String to, 
				 @JsonProperty("gasEth") BigInteger gasEth, 
				 @JsonProperty("gasPriceEth") BigInteger gasPriceEth, 
				 @JsonProperty("quatity") BigInteger quatity, 
				 @JsonProperty("data") String data,
				 @JsonProperty("nonce") BigInteger nonce, 
				 @JsonProperty("gasPremiumEth") BigInteger gasPremiumEth, 
				 @JsonProperty("feeCapEth") BigInteger feeCapEth, 
				 @JsonProperty("gasUSD") BigInteger gasUSD, 
				 @JsonProperty("gasPriceUSD") BigInteger gasPriceUSD,
				 @JsonProperty("gasPremiumUSD") BigInteger gasPremiumUSD, 
				 @JsonProperty("feeCapUSD") BigInteger feeCapUSD,
				 @JsonProperty("slippageTolerance") Double slippageTolerance,
				 @JsonProperty("side") String side, 
				 @JsonProperty("orderType") String orderType, 
				 @JsonProperty("limitPrice") String limitPrice,
				 @JsonProperty("stopPrice") String stopPrice, 
				 @JsonProperty("transactionDeadLine") String transactionDeadLine, 
				 @JsonProperty("traillingStopPercentage") String traillingStopPercentage) {
		this.privateKey = privateKey;
		this.publicKey = publicKey;
		this.from = from;
		this.to = to;
		this.gasEth = gasEth;
		this.gasPriceEth = gasPriceEth;
		this.quatity = quatity;
		this.data = data;
		this.nonce = nonce;
		this.gasPremiumEth = gasPremiumEth;
		this.feeCapEth = feeCapEth;
		this.gasUSD = gasUSD;
		this.gasPriceUSD = gasPriceUSD;
		this.gasPremiumUSD = gasPremiumUSD;
		this.feeCapUSD = feeCapUSD;
		this.slippageTolerance = slippageTolerance;
		this.side = side;
		this.orderType = orderType;
		this.limitPrice = limitPrice;
		this.stopPrice = stopPrice;
		this.traillingStopPercentage = traillingStopPercentage;
		this.transactionDeadLine = transactionDeadLine;
	}
	public String getFrom() {
		return from;
	}
	public String getTo() {
		return to;
	}
	public BigInteger getGasEth() {
		return gasEth;
	}
	public BigInteger getGasPriceEth() {
		return gasPriceEth;
	}
	public BigInteger getQuatity() {
		return quatity;
	}
	public String getData() {
		return data;
	}
	public BigInteger getNonce() {
		return nonce;
	}
	public BigInteger getGasPremiumEth() {
		return gasPremiumEth;
	}
	public BigInteger getFeeCapEth() {
		return feeCapEth;
	}
	public BigInteger getGasUSD() {
		return gasUSD;
	}
	public BigInteger getGasPriceUSD() {
		return gasPriceUSD;
	}
	public BigInteger getGasPremiumUSD() {
		return gasPremiumUSD;
	}
	public BigInteger getFeeCapUSD() {
		return feeCapUSD;
	}
	public Double getSlippageTolerance() {
		return slippageTolerance;
	}
	
	public String getId() {
		return id;
	}
	
	public String getPrivateKey() {
		return privateKey;
	}
	public String getPublicKey() {
		return publicKey;
	}
	public String getSide() {
		return side;
	}
	public String getOrderType() {
		return orderType;
	}
	public String getLimitPrice() {
		return limitPrice;
	}
	public String getStopPrice() {
		return stopPrice;
	}
	public String getTraillingStopPercentage() {
		return traillingStopPercentage;
	}
	
	public String getTransactionDeadLine() {
		return transactionDeadLine;
	}
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

	@Override
	public boolean equals(Object obj) {
		return EqualsBuilder.reflectionEquals(this, obj);
	}

	@Override
	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this);
	}
    
    
}