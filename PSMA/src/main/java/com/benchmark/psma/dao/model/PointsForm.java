package com.benchmark.psma.dao.model;

public class PointsForm{
	private String mobileNumber;
	private String cardNumber;
	private String resetNumber;
	private String resetCount;
	public String getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	public String getCardNumber() {
		return cardNumber;
	}
	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}
	public String getResetNumber() {
		return resetNumber;
	}
	public void setResetNumber(String resetNumber) {
		this.resetNumber = resetNumber;
	}
	public String getResetCount() {
		return resetCount;
	}
	public void setResetCount(String resetCount) {
		this.resetCount = resetCount;
	}
}
