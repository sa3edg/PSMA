package com.benchmark.psma.dao.model;

import java.util.Date;

public class SubscriberForm {

	private Integer id ;
	private String name = "";
	private String gender = "";
	private Integer age;
	private String address = "";
	private String mobileNumber = "";
	private String facebookAccount = "";
	private String emailAddress = "";
	private Date registerationDate;
	private String nationalIdNumber = "";
	private String cardNumber = "";

	@Override
	public String toString() {
		return "SubscriberForm [id=" + id + ", name=" + name + ", gender="
				+ gender + ", age=" + age + ", address=" + address
				+ ", mobileNumber=" + mobileNumber + ", facebookAccount="
				+ facebookAccount + ", emailAddress=" + emailAddress
				+ ", registerationDate=" + registerationDate
				+ ", nationalIdNumber=" + nationalIdNumber + ", cardNumber="
				+ cardNumber + "]";
	}

	public Integer getId() {
		// TODO Auto-generated method stub
		return id;
	}
	
	public void setId(Integer id){
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getFacebookAccount() {
		return facebookAccount;
	}

	public void setFacebookAccount(String facebookAccount) {
		this.facebookAccount = facebookAccount;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public Date getRegisterationDate() {
		return registerationDate;
	}

	public void setRegisterationDate(Date registerationDate) {
		this.registerationDate = registerationDate;
	}

	public String getNationalIdNumber() {
		return nationalIdNumber;
	}

	public void setNationalIdNumber(String nationalIdNumber) {
		this.nationalIdNumber = nationalIdNumber;
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

}
