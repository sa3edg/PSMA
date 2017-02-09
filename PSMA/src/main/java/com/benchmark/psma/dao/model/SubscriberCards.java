package com.benchmark.psma.dao.model;

import java.util.Date;

import org.springframework.data.domain.Persistable;

public class SubscriberCards implements Persistable<String> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static final String ACTIVE = "ACTIVE";
	public static final String SUSPENDED = "SUSPENDED";
	private transient boolean persisted;

	private String cardNumber = "";
	private Integer subscriberId;
	private Integer points;
	private Date registerationDate;
	private Date lastUpdateDate;
	private String status = "";

	@Override
	public String getId() {
		return getCardNumber();
	}

	@Override
	public boolean isNew() {
		return !persisted;
	}
	
	public SubscriberCards withPersisted(boolean persisted) {
		this.persisted = persisted;
		return this;
	}
	
	@Override
	public boolean equals(Object o) {

		SubscriberCards card = (SubscriberCards) o;

		if (cardNumber != card.cardNumber) return false;
		return cardNumber.equals(card.cardNumber);

	}

	@Override
	public int hashCode() {
		int result = cardNumber != null ? cardNumber.hashCode() : 0;
		return result;
	}

	public String getCardNumber() {
		return this.cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public Integer getPoints() {
		return points;
	}

	public void setPoints(Integer points) {
		this.points = points;
	}

	public Date getRegisterationDate() {
		return registerationDate;
	}

	public void setRegisterationDate(Date registerationDate) {
		this.registerationDate = registerationDate;
	}

	public Date getLastUpdateDate() {
		return lastUpdateDate;
	}

	public void setLastUpdateDate(Date lastUpdateDate) {
		this.lastUpdateDate = lastUpdateDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Integer getSubscriberId() {
		return subscriberId;
	}

	public void setSubscriberId(Integer subscriberId) {
		this.subscriberId = subscriberId;
	}

}
