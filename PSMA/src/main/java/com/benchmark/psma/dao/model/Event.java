package com.benchmark.psma.dao.model;

import java.util.Arrays;
import java.util.Date;

import org.springframework.data.domain.Persistable;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Event implements Persistable<Integer>{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6109453099377681629L;
	private Integer id ;
	private String title = "";
	private String details = "";
	private byte[] image;
	private Date startDate;
	private Date endDate;

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
	

	/**
	 * @return the image
	 */
	@JsonIgnore
	public byte[] getImage() {
		return image;
	}

	/**
	 * @param image the image to set
	 */
	public void setImage(byte[] image) {
		this.image = image;
	}
	

	@Override
	public String toString() {
		return "Event [id=" + id + ", title=" + title + ", details=" + details
				+ ", image=" + Arrays.toString(image) + ", startDate="
				+ startDate + ", endDate=" + endDate + "]";
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

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

}
