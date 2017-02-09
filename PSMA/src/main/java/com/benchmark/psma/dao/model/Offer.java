package com.benchmark.psma.dao.model;

import java.util.Arrays;
import java.util.Date;

import org.springframework.data.domain.Persistable;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Offer implements Persistable<Integer>{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6152753539947257822L;
	private Integer id ;
	private String details = "";
	private String title = "";
	private String item = "";
	private byte[] image ;
	private float oldPrice;
	private float newPrice;
	private float discount;
	private Date startDate;
	private Date endDate;
	private boolean sale;
	@Override
	public Integer getId() {
		// TODO Auto-generated method stub
		return id;
	}
	
	public void setId(Integer id){
		this.id = id;
	}

	@Override
	@JsonIgnore
	public boolean isNew() {
		return id == null;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getItem() {
		return item;
	}
	public void setItem(String item) {
		this.item = item;
	}

	@JsonIgnore
	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	@Override
	public String toString() {
		return "Offer [id=" + id + ", details=" + details + ", title=" + title
				+ ", item=" + item + ", image=" + Arrays.toString(image)
				+ ", oldPrice=" + getOldPrice() + ", newPrice=" + getNewPrice()
				+ ", discount=" + getDiscount() + ", startDate=" + startDate
				+ ", endDate=" + endDate + ", isSale=" + isSale() + "]";
	}

	public float getOldPrice() {
		return oldPrice;
	}

	public void setOldPrice(float oldPrice) {
		this.oldPrice = oldPrice;
	}

	public float getNewPrice() {
		return newPrice;
	}

	public void setNewPrice(float newPrice) {
		this.newPrice = newPrice;
	}

	public float getDiscount() {
		return discount;
	}

	public void setDiscount(float discount) {
		this.discount = discount;
	}

	public boolean isSale() {
		return sale;
	}

	public void setSale(boolean sale) {
		this.sale = sale;
	}

}
