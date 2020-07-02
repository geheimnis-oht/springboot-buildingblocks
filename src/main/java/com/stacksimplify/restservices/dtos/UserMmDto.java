package com.stacksimplify.restservices.dtos;

import java.util.List;

import com.stacksimplify.restservices.entities.Order;

public class UserMmDto {

	private Long userid;
	private String username;
	private String firstname;
	private List<Order> orders;


	public Long getUserid() {
		return userid;
	}

	public String getUsername() {
		return username;
	}

	public String getFirstname() {
		return firstname;
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

	public List<Order> getOrders() {
		return orders;
	}
	
	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}
}
