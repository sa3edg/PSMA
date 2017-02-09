package com.benchmark.psma.dao.model;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.data.domain.Persistable;
import javax.validation.constraints.Size;

public class User implements Persistable<String>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@NotEmpty 
	@Size(min=2, max=30)
	private String userName = "";
	
	@NotEmpty 
	@Size(min=2, max=30)
	private String password = "";
	
	private String role = "";
	
	private Boolean enabled; 
	private transient boolean persisted;

	
	@Override
	public String getId() {
		// TODO Auto-generated method stub
		return getUserName();
	}
	
	public void setId(String id){
		this.userName = id;
	}

	@Override
	public boolean isNew() {
		return !persisted;
	}

	

	public User withPersisted(boolean persisted) {
		this.persisted = persisted;
		return this;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}
	@Override
	public String toString() {
		return "Customer [userName=" + userName + ", password=" + password + "]";
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
}
