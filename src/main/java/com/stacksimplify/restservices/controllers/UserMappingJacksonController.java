package com.stacksimplify.restservices.controllers;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import javax.validation.constraints.Min;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.stacksimplify.restservices.entities.User;
import com.stacksimplify.restservices.exceptions.UserNotFoundException;
import com.stacksimplify.restservices.services.UserService;

@RestController
@RequestMapping(value = "/jacksonfilter/users")
@Validated
public class UserMappingJacksonController {

	@Autowired
	private UserService userService;

	@GetMapping("/{id}") // -- fields with HashSet
	public MappingJacksonValue getUserById(@PathVariable("id") @Min(1) Long id) {
		try {
			Optional<User> userOptional = userService.getUserById(id);
			User user = userOptional.get();

			/*
			 * TECH : Dynamic Filtering with MappingJacksonValue
			 * TODO : Add another filter to order entity
			 */
			Set<String> fields = new HashSet<>();
			fields.add("userid");
			fields.add("username");
			fields.add("ssn");
			fields.add("orders");
            //@JsonFilter(value = "userFilter") must be added to User entity
			FilterProvider filters = new SimpleFilterProvider().addFilter("userFilter",
					SimpleBeanPropertyFilter.filterOutAllExcept(fields));
			MappingJacksonValue mapper = new MappingJacksonValue(user);
			mapper.setFilters(filters);
            return mapper;
            
		} catch (UserNotFoundException ex) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());
		}
	}
	
	@GetMapping("/params/{id}") // -- fields with @RequestParam
	public MappingJacksonValue getUserById2(@PathVariable("id") @Min(1) Long id, @RequestParam Set<String> fields) {
		try {
			Optional<User> userOptional = userService.getUserById(id);
			User user = userOptional.get();

			/*
			 * TECH : Dynamic Filtering with MappingJacksonValue - @RequestParam
			 * TODO : Add another filter to order entity
			 */
            //@JsonFilter(value = "userFilter") must be added to User entity
			FilterProvider filters = new SimpleFilterProvider().addFilter("userFilter",
					SimpleBeanPropertyFilter.filterOutAllExcept(fields));
			MappingJacksonValue mapper = new MappingJacksonValue(user);
			mapper.setFilters(filters);
            return mapper;
            
		} catch (UserNotFoundException ex) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());
		}
	}

}
