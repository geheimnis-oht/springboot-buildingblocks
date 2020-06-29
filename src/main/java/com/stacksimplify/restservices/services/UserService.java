package com.stacksimplify.restservices.services;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.stacksimplify.restservices.entities.User;
import com.stacksimplify.restservices.exceptions.UserExistsExecption;
import com.stacksimplify.restservices.exceptions.UserNotFoundException;
import com.stacksimplify.restservices.repositories.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	

	public List<User> getAllUsers(){
		return userRepository.findAll();
	}

	public User createUser(User user) throws UserExistsExecption {
		User existingUser = userRepository.findByUsername(user.getUsername());
		if(existingUser != null) {
		   throw new UserExistsExecption("User already exists ! Insertion abort.");	
		}	
		return userRepository.save(user);
		
	
	}
	
	public Optional<User> getUserById(Long id) throws UserNotFoundException {
		Optional<User> user = userRepository.findById(id);
		if(!user.isPresent()) {
		   throw new UserNotFoundException("User not found in Users Repository");	
		}
		return user;
	}
	
	public User updateUserById(Long id, User user) throws UserNotFoundException {
		
		Optional<User> optionalUser = userRepository.findById(id);
		
		if(!optionalUser.isPresent()) {
			throw new UserNotFoundException("User not found in Users Repository, Please provide correct id");
		}
		user.setId(id);
		return userRepository.save(user);
	}
	
	public void deleteUserById(Long id) {
     /**
      * TECH: Handle Exceptions directly in the Service Layer
      */
		Optional<User> optionalUser = userRepository.findById(id);
		
		if(!optionalUser.isPresent()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User not found in Users Repository, Deletion failed");
		}
		
		userRepository.deleteById(id);
	}
	
	public User getUserByUsername(String username) {
		return userRepository.findByUsername(username);
	}
}
