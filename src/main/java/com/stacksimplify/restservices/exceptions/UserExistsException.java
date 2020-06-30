package com.stacksimplify.restservices.exceptions;

public class UserExistsException extends Exception {

	private static final long serialVersionUID = 6141213190030433514L;

	public UserExistsException(String message) {
		super(message);
	}


}
