package com.benchmark.psma.dao.model;

public class RedeemPointsForm{
	private String mobileNumber;
	private String cardNumber;
	private String pointsCount;
	private String reddemAllPoints;
	private String showPoint = "false";
	public String getCardNumber() {
		return cardNumber;
	}
	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}
	public String getPointsCount() {
		return pointsCount;
	}
	public void setPointsCount(String pointsCount) {
		this.pointsCount = pointsCount;
	}
	public String getReddemAllPoints() {
		return reddemAllPoints;
	}
	public void setReddemAllPoints(String reddemAllPoints) {
		this.reddemAllPoints = reddemAllPoints;
	}
	public String getShowPoint() {
		return showPoint;
	}
	public void setShowPoint(String showPoint) {
		this.showPoint = showPoint;
	}
	public String getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
}
