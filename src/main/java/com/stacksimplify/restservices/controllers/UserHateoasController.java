package com.stacksimplify.restservices.controllers;

import java.util.List;
import java.util.Optional;

import javax.validation.constraints.Min;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.stacksimplify.restservices.entities.Order;
import com.stacksimplify.restservices.entities.User;
import com.stacksimplify.restservices.exceptions.UserNotFoundException;
import com.stacksimplify.restservices.repositories.UserRepository;
import com.stacksimplify.restservices.services.UserService;

@RestController
@RequestMapping(value = "/hateoas/users") 
@Validated
public class UserHateoasController {
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	UserService userService;
	
	@GetMapping
	CollectionModel<User> getAllUsers() throws UserNotFoundException{		
		/*
		 * TECH : Implement HATEOAS self link (getAllUsers)
		 */
		List<User> allusers = userService.getAllUsers();	
		for (User user : allusers) {
			Long userid = user.getUserid();
			Link selflink = WebMvcLinkBuilder.linkTo(this.getClass()).slash(userid).withSelfRel();
			user.add(selflink);
		    /*
		     * TECH : Build Relationship link with getAllOrders	
		     */
			CollectionModel<Order> orders = WebMvcLinkBuilder.methodOn(OrderHateoasController.class)
					                                         .getAllOrders(userid);
			Link orderslink = WebMvcLinkBuilder.linkTo(orders).withRel("all-orders");
			user.add(orderslink);
			
		}
		
		// TECH: Self link for getAllUsers
		Link selflinkAllUsers = WebMvcLinkBuilder.linkTo(this.getClass()).withSelfRel();
		
		
		@SuppressWarnings("deprecation")
		CollectionModel<User> finalCollection = new CollectionModel<User>(allusers, selflinkAllUsers);
		return finalCollection;
	}
	
	@GetMapping("/{id}")
	public EntityModel<User> getUserById (@PathVariable("id") @Min(1) Long id) {
	   try {
		 /*
		  * TECH : Implement HATEOAS self link (getUserById)
		  */
		   
		   User user = userService.getUserById(id).get();
		   Long userid = user.getUserid();
		   Link selflink = WebMvcLinkBuilder.linkTo(this.getClass()).slash(userid).withSelfRel();
		   user.add(selflink);
		   @SuppressWarnings("deprecation")
		   EntityModel<User> finalEntity = new EntityModel<User>(user); 
		   
		   return finalEntity;
		
		} catch (UserNotFoundException ex) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());
		}	
	}

}
