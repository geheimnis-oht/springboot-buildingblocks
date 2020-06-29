package com.stacksimplify.restservices.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import com.stacksimplify.restservices.entities.User;
import com.stacksimplify.restservices.exceptions.UserExistsExecption;
import com.stacksimplify.restservices.exceptions.UserNotFoundException;
import com.stacksimplify.restservices.services.UserService;

@RestController
public class UserController {
   
	@Autowired
	private UserService userService;
	
	@GetMapping("/users")
	List<User> getAllUsers(){
		
		return userService.getAllUsers();
	}
	
	@PostMapping("/users")
	public ResponseEntity<Void> createUser(@RequestBody User user, UriComponentsBuilder builder) {
		try {
			/*
			 * Create and insert user
			 */
			userService.createUser(user);
			
			/* 
			 * TECH: Define HttpHeader Location
			 * TECH: Return ResponseEntity
			 */
			HttpHeaders headers = new HttpHeaders();
			headers.setLocation(builder.path("/users/{id}").buildAndExpand(user.getId()).toUri());
			return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
					
		} catch (UserExistsExecption ex) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ex.getMessage());
		}
	}
	
	@GetMapping("/users/{id}")
	public Optional<User> getUserById (@PathVariable("id") Long id) {
	   try {
		return userService.getUserById(id);
		} catch (UserNotFoundException ex) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());
		}	
	}
	
	@GetMapping("/users/byusername/{username}")
	public User getUserByUsername (@PathVariable("username") String username) {
	   return userService.getUserByUsername(username);	
	}
	
	@PutMapping("/users/{id}")
	public User createUser(@PathVariable("id") Long id, @RequestBody User user) {
		try {
			return userService.updateUserById(id, user);
		} catch (UserNotFoundException ex) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ex.getMessage());
		}
	}
	 
	@DeleteMapping("/users/{id}")
	public String deleteUser(@PathVariable("id") Long id) {
	  userService.deleteUserById(id);
	  return "deletion completed !";
	}
	
	
	
}
