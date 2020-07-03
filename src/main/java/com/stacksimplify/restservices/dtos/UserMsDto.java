package com.stacksimplify.restservices.dtos;

public class UserMsDto {

	private Long userid;
	private String username;
	private String emailaddress;
	private String roleName;
	
	public UserMsDto() {
	}

	public UserMsDto(Long userid, String username, String emailaddress, String roleName) {
		super();
		this.userid = userid;
		this.username = username;
		this.emailaddress = emailaddress;
		this.roleName = roleName;
	}

	public Long getUserid() {
		return userid;
	}

	public String getUsername() {
		return username;
	}

	public String getEmailaddress() {
		return emailaddress;
	}

	public void setUserid(Long userid) {
		this.userid = userid;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setEmailaddress(String emailaddress) {
		this.emailaddress = emailaddress;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	
}
