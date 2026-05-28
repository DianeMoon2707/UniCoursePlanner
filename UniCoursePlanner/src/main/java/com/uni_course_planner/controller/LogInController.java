package com.uni_course_planner.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.uni_course_planner.service.relation.user.UserService;

@Controller
public class LogInController
{
	private static final String LOGIN_PAGE_ADDRESS = "page/logIn/index";
	private static final String HOME_PAGE_ADDRESS = "page/core/home";
	
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
			@RequestParam(name="password-field") String passwordField,
			Model model)
	{
		boolean loginSuccess = userService.checkLogInData(userField, passwordField);
		
		if(loginSuccess == true)
		{
			return "redirect:" + HOME_PAGE_ADDRESS;
		}
		else
		{
			model.addAttribute("error", "Benutzername oder Passwort falsch"); 
			return LOGIN_PAGE_ADDRESS;
		}
	}
}
