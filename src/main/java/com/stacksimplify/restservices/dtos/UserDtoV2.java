package com.stacksimplify.restservices.dtos;

import java.util.List;

import com.stacksimplify.restservices.entities.Order;

public class UserDtoV2 {

	private Long userid;
	private String username;
	private String firstname;
	private String lastname;
	private String email;
	private String role;
	private String ssn;
	private List<Order> orders;
	private String address;
	
	//constructor
	public UserDtoV2() {
	}

	//constructor with fields ...
	public UserDtoV2(Long userid, String username, String firstname, String lastname, String email, String role,
			String ssn, List<Order> orders, String address) {
		super();
		this.userid = userid;
		this.username = username;
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.role = role;
		this.ssn = ssn;
		this.orders = orders;
		this.address = address;
	}

	// getter & setter
	public Long getUserid() {
		return userid;
	}

	public String getUsername() {
		return username;
	}

	public String getFirstname() {
		return firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public String getEmail() {
		return email;
	}

	public String getRole() {
		return role;
	}

	public String getSsn() {
		return ssn;
	}

	public List<Order> getOrders() {
		return orders;
	}

	public String getAddress() {
		return address;
	}

	public void setUserid(Long userid) {
		this.userid = userid;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public void setSsn(String ssn) {
		this.ssn = ssn;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	
}
