package com.uni_course_planner.controller.login;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.uni_course_planner.constants.views.PageAddress;
import com.uni_course_planner.service.relation.user.UserService;

@Controller
public class RegisterController 
{	
	private UserService userService;
	
	public RegisterController(UserService userService)
	{
		this.userService = userService;
	}

	@GetMapping(PageAddress.REGISTER_PAGE_ADDRESS)
	public String loadRegisterPage()
	{
		return PageAddress.REGISTER_PAGE_ADDRESS;
	}
	
	@PostMapping("/register")
	public String register(
			@RequestParam(name="user-field") String userField,
			@RequestParam(name="email-field") String emailField,
			@RequestParam(name="password-field") String passwordField)
	{
		userService.registerUser(emailField, userField, passwordField);		
		return PageAddress.LOGIN_PAGE_ADDRESS;
	}
}
