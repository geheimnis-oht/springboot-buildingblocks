package com.stacksimplify.restservices.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stacksimplify.restservices.entities.Order;
import com.stacksimplify.restservices.entities.User;
import com.stacksimplify.restservices.exceptions.UserNotFoundException;
import com.stacksimplify.restservices.repositories.OrderRepository;
import com.stacksimplify.restservices.services.UserService;

@RestController
@RequestMapping(value = "/users")
public class OrderController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private OrderRepository orderRepository;
	
	/*
	 * TECH: NoSuchElementException Handling
	 */
	
	@GetMapping("/{userid}/orders")
	public List<Order> getAllOrders(@PathVariable("userid") Long userid) throws UserNotFoundException {
		Optional<User> optionalUser = userService.getUserById(userid);
		
		/*
		 * FIXME: Exception Problem ...
		 */
		if (!optionalUser.isPresent()) {
		 throw new UserNotFoundException("User with id=" + userid +" has not been found in Users Repo. ");
		}
		
		return optionalUser.get().getOrders();
	}
	
	@PostMapping("/{userid}/orders")
	public Order createOrder(@PathVariable("userid") Long userid, @RequestBody Order order) throws UserNotFoundException {
		Optional<User> optionalUser = userService.getUserById(userid);
		
		
		if (!optionalUser.isPresent()) {
		 throw new UserNotFoundException("User with id=" + userid +" has not been found in Users Repo. ");
		}
		
		User user = optionalUser.get();
		order.setUser(user);
		
		return orderRepository.save(order);	
	}
	
	@GetMapping("/{userid}/orders/{orderid}")
	public Order getOrdersByOrderId(@PathVariable("userid") Long userid, @PathVariable("orderid") Long orderid) throws UserNotFoundException {
				
		Optional<Order> optionalOrder = orderRepository.findById(orderid);
		// FIXME : add new exceptions OrderNotFoundException | OrderMismatchException
		if (!optionalOrder.isPresent()) {
		  throw new UserNotFoundException("Order with id=" + orderid +" has not been found. "); 	
		} else {
			if (optionalOrder.get().getUser().getId() != userid) {
				throw new UserNotFoundException("The Order with id=" + orderid +" belong to another user."); 	
			}
		}
		
		return optionalOrder.get();		
	}
	
	
	
	
	
	

}
