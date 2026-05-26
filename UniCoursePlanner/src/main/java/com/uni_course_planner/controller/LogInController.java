package com.uni_course_planner.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.uni_course_planner.service.relation.user.UserService;

@Controller
public class LogInController
{
	private static final String LOGIN_PAGE_ADDRESS = "page/logIn/index";
	
	private UserService userService;
	
	public LogInController(UserService userService)
	{
		this.userService = userService;
	}
	
	@GetMapping("/")
	public String loadLogInPage()
	{
		return LOGIN_PAGE_ADDRESS;
	}
	
	@PostMapping("/login")
	public String proofLogInData(
			@RequestParam(name="user-field") String userField,
			@RequestParam(name="password-field") String passwordField)
	{
		if(userService.checkLogInData(userField, passwordField) == true)
		{
			System.out.println("Existiert!");
		}
		else
		{
			System.out.println("Nicht existend!");
		}
		
		return "login";
	}
}
