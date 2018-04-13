package com.ait.tech;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Basket {
	private int id;
	private int userId;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	
}
