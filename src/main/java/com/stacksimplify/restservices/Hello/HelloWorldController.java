package com.stacksimplify.restservices.Hello;

import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {
	
	@Autowired
	private ResourceBundleMessageSource messageSource;
	
	/*
	 * Simple Method
	 * URI : /helloworld
	 * GET
	 */
	//@RequestMapping(method = RequestMethod.GET, path = "/helloworld")
	@GetMapping("/helloworld1")
	public String helloWorld() {
		return "Hello World !";
	}
	
	@GetMapping("/helloworld-bean")
	public UserDetails helloWorldBean() {
		return new UserDetails("mohamed", "laidani", "Médéa");
	}
	
	@GetMapping("/helloworld-list")
	public Set<UserDetails> helloWorldBeanSet() {
		//TODO : Try other Java Collection type
		//XXX : normal Tag 
		//TECH : HelloWorld Set<> and HashSet
		Set<UserDetails> userList = new HashSet<>();
		userList.add(new UserDetails("mohamed", "laidani", "Médéa"));
		userList.add(new UserDetails("tamim", "laidani", "Blida"));
		userList.add(new UserDetails("athyl", "laidani", "Médéa"));
		userList.add(new UserDetails("tim", "Oliver", "New York"));
		userList.add(new UserDetails("Ashton", "ville", "London"));
		userList.add(new UserDetails("lilly", "tominet", "Bruxelle"));
				
		return userList;
		//return new UserDetails("mohamed", "laidani", "Médéa");
	}
	
	/*
	 * TECH : Test Internationalization
	 */
	
	@GetMapping("/hello-i18n")
	public String getMessageInI18nFormat(@RequestHeader(name="Accept-Language", required = false) String locale) {
		return messageSource.getMessage("label.hello", null, new Locale(locale));
	}

}
