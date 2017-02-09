package com.benchmark.psma.dao.model;

import java.util.Date;

import org.springframework.data.domain.Persistable;

public class SubscriberBills implements Persistable<String> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static final String VALID = "VALID";
	public static final String EXPIRED = "EXPIRED";
	public static final String ADDED = "ADDED";
	private transient boolean persisted;

	private String billNumber = "";
	private Integer subscriberId;
	private Integer amount;
	private Date billDate;
	private String status = "";

	@Override
	public String getId() {
		return getBillNumber();
	}

	@Override
	public boolean isNew() {
		return !persisted;
	}
	
	public SubscriberBills withPersisted(boolean persisted) {
		this.persisted = persisted;
		return this;
	}
	
	@Override
	public boolean equals(Object o) {

		SubscriberBills card = (SubscriberBills) o;

		if (billNumber != card.billNumber) return false;
		return billNumber.equals(card.billNumber);

	}

	@Override
	public int hashCode() {
		int result = billNumber != null ? billNumber.hashCode() : 0;
		return result;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getBillNumber() {
		return billNumber;
	}

	public void setBillNumber(String billNumber) {
		this.billNumber = billNumber;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	public Date getBillDate() {
		return billDate;
	}

	public void setBillDate(Date billDate) {
		this.billDate = billDate;
	}

	public Integer getSubscriberId() {
		return subscriberId;
	}

	public void setSubscriberId(Integer subscriberId) {
		this.subscriberId = subscriberId;
	}

}
