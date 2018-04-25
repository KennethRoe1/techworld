package com.ait.tech;

import java.sql.Date;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class User {
	private int id;
	private String email;
	private String name;
	private String pass;
	private String address;
	private Date dob;
	private String role;
	
	public User() {
		
	}
	
	public User(int id, String email, String name, String pass, String address, Date dob, String role) {
		
		if (id<=0)
			throw new IllegalArgumentException("ID has to be a positive number");
		
		if (name==null)
			throw new IllegalArgumentException("Name cannot be null");
		
		if (email==null)
			throw new IllegalArgumentException("email cannot be null");
		
		if (pass==null)
			throw new IllegalArgumentException("pass cannot be null");
		
		if (address==null)
			throw new IllegalArgumentException("address cannot be null");
		
		if (role==null)
			throw new IllegalArgumentException("role cannot be null");
		
		if (name.trim().length()==0)
			throw new IllegalArgumentException("Name cannot be empty");
		
		if (email.trim().length()==0)
			throw new IllegalArgumentException("email cannot be empty");
		
		if (pass.trim().length()==0)
			throw new IllegalArgumentException("pass cannot be empty");
		
		if (address.trim().length()==0)
			throw new IllegalArgumentException("address cannot be empty");
		
		if (role.trim().length()==0)
			throw new IllegalArgumentException("role cannot be empty");
		
		this.id = id;
		this.name = name;
		this.email = email;
		this.pass = pass;
		this.address = address;
		this.dob = dob;
		this.role = role;
		
		
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Date getDob() {
		return dob;
	}
	public void setDob(Date dob) {
		this.dob = dob;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
}
