package com.stacksimplify.restservices.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.hateoas.RepresentationModel;

import com.fasterxml.jackson.annotation.JsonView;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiModelProperty.AccessMode;

@Entity
@Table(name = "user")
//@JsonIgnoreProperties({"firstname","lastname"})  - Static Filtering
//@JsonFilter(value = "userFilter") // Dynamic Filtering
@ApiModel(description = "this model is to create a user")
public class User extends RepresentationModel{
	@ApiModelProperty(notes="Auto generated Unique ID", required = true, position = 1)
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@JsonView(Views.External.class)
	private Long userid;

	@ApiModelProperty(notes="Username should be unique", 
			          required = true, 
			          position = 2,
			          example = "'MLAIDANI'",
			          accessMode = AccessMode.READ_WRITE,
			          allowableValues = "[A-Za-z0-9_]" )
	@NotBlank(message = "[[--> Username is Mandatory. please provide a valid username --]]")
	@Column(name = "USER_NAME", length = 50, nullable = false, unique = true)
	@JsonView(Views.External.class)
	private String username;

	@Size(min = 3, message = "[[--> First name should have at least 3 characters --]]")
	@Column(name = "FIRST_NAME", length = 50, nullable = false)
	@JsonView(Views.External.class)
	private String firstname;
	@Column(name = "LAST_NAME", length = 50, nullable = false)
	@JsonView(Views.External.class)
	private String lastname;
	@Column(name = "EMAIL_ADDRESS", length = 50, nullable = false)
	@JsonView(Views.External.class)
	private String email;
	@JsonView(Views.Internal.class)
	@Column(name = "ROLE", length = 50, nullable = false)
	private String role;
	@Column(name = "SSN", length = 50, nullable = false, unique = true)
	//@JsonIgnore - Static Filtering
	@JsonView(Views.Internal.class)
	private String ssn;
	
	@Column (name = "ADDRESS")
	private String address;

	@OneToMany (mappedBy = "user")
	@JsonView(Views.Internal.class)
	private List<Order> orders;

	public User() {
	}

	public User(Long userid,
			@NotBlank(message = "[[--> Username is Mandatory. please provide a valid username --]]") String username,
			@Size(min = 3, message = "[[--> First name should have at least 3 characters --]]") String firstname,
			String lastname, String email, String role, String ssn, String address, List<Order> orders) {
		super();
		this.userid = userid;
		this.username = username;
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.role = role;
		this.ssn = ssn;
		this.address = address;
		this.orders = orders;
	}

	public Long getUserid() {
		return userid;
	}

	public void setUserid(Long userid) {
		this.userid = userid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getSsn() {
		return ssn;
	}

	public void setSsn(String ssn) {
		this.ssn = ssn;
	}


	public List<Order> getOrders() {
		return orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}
	
	
	
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "User [userid=" + userid + ", username=" + username + ", firstname=" + firstname + ", lastname="
				+ lastname + ", email=" + email + ", role=" + role + ", ssn=" + ssn + ", address=" + address
				+ ", orders=" + orders + "]";
	}

}
