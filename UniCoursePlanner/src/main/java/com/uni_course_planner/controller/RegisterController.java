package com.uni_course_planner.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.uni_course_planner.service.relation.user.UserService;

@Controller
public class RegisterController 
{
	private static final String REGISTER_PAGE_ADDRESS = "page/logIn/register";
	private static final String LOGIN_PAGE_ADDRESS = "page/logIn/index";
	
	private UserService userService;
	
	public RegisterController(UserService userService)
	{
		this.userService = userService;
	}

	@GetMapping(REGISTER_PAGE_ADDRESS)
	public String loadRegisterPage()
	{
		return REGISTER_PAGE_ADDRESS;
	}
	
	@PostMapping("/register")
	public String register(
			@RequestParam(name="user-field") String userField,
			@RequestParam(name="email-field") String emailField,
			@RequestParam(name="password-field") String passwordField)
	{
		userService.registerUser(emailField, userField, passwordField);		
		return LOGIN_PAGE_ADDRESS;
	}
}
