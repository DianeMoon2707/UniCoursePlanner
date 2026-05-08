package com.uni_course_planner.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.uni_course_planner.service.relation.user.UserService;

@Controller
public class RegisterController 
{
	private static final String REGISTER_PAGE_ADRESS = "page/logIn/register";
	
	private UserService userService;
	
	public RegisterController(UserService userService)
	{
		this.userService = userService;
	}

	@GetMapping(REGISTER_PAGE_ADRESS)
	public String loadRegisterPage()
	{
		return REGISTER_PAGE_ADRESS;
	}
	
	@PostMapping("/register")
	public String register(
			@RequestParam(name="user-field") String userField,
			@RequestParam(name="email-field") String emailField,
			@RequestParam(name="password-field") String passwordField)
	{
		System.out.println("Email: " + emailField);
		userService.registerUser(emailField);
		
		return REGISTER_PAGE_ADRESS;
	}
}
