package com.benchmark.psma.dao.model;

import org.springframework.data.domain.Persistable;

public class UserRole implements Persistable<Integer>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final String ADMIN_ROLE = "ROLE_ADMIN";
	public static final String MERCHANT_ROLE = "ROLE_MERCHANT";
	public static final String DATA_ENTRY_ROLE = "ROLE_DATAENTRY";
    
	private Integer id;
	private String userName = "";
	private String role = "";
	
	@Override
	public Integer getId() {
		// TODO Auto-generated method stub
		return this.id;
	}

	public void setId(Integer id){
		this.id = id;
	}
	@Override
	public boolean isNew() {
		// TODO Auto-generated method stub
		return false;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
}
