package com.stacksimplify.restservices.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.stacksimplify.restservices.entities.User;
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
	public User createUser(@RequestBody User user) {
		return userService.createUser(user);
	}
	
	@GetMapping("/user/{id}")
	public Optional<User> getUserById (@PathVariable("id") Long id) {
	   return userService.getUserById(id);	
	}
	
	@GetMapping("/users/byusername/{username}")
	public User getUserByUsername (@PathVariable("username") String username) {
	   return userService.getUserByUsername(username);	
	}
	
	@PutMapping("/user/{id}")
	public User createUser(@PathVariable("id") Long id, @RequestBody User user) {
		return userService.updateUserById(id, user);
	}
	 
	@DeleteMapping("/users/{id}")
	public String deleteUser(@PathVariable("id") Long id) {
	  userService.deleteUserById(id);
	  return "deletion completed !";
	}
	
	
	
}
