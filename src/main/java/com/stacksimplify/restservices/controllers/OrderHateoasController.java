package com.stacksimplify.restservices.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stacksimplify.restservices.entities.Order;
import com.stacksimplify.restservices.entities.User;
import com.stacksimplify.restservices.entities.Order;
import com.stacksimplify.restservices.exceptions.UserNotFoundException;
import com.stacksimplify.restservices.repositories.OrderRepository;
import com.stacksimplify.restservices.services.UserService;

@RestController
@RequestMapping(value = "/hateoas/users") 
@Validated
public class OrderHateoasController {
	
	@Autowired
    UserService userService;
	
	@Autowired
	OrderRepository orderRepository;
	
	@GetMapping("/{userid}/orders")
	public CollectionModel<Order> getAllOrders(@PathVariable("userid") Long userid) throws UserNotFoundException {
		Optional<User> optionalUser = userService.getUserById(userid);
		
		/*
		 * FIXME: Exception Problem ...
		 */
		if (!optionalUser.isPresent()) {
		 throw new UserNotFoundException("User with id=" + userid +" has not been found in Users Repo. ");
		}
		
		List<Order> allorders = optionalUser.get().getOrders();	
				
		@SuppressWarnings("deprecation")
		CollectionModel<Order> finalCollection = new CollectionModel<Order>(allorders);
		return finalCollection;
			
	}
}
