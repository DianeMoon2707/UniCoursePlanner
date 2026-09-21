package com.uni_course_planner.controller.login;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.uni_course_planner.constants.views.PageAddress;
import com.uni_course_planner.service.email.EmailService;
import com.uni_course_planner.service.email.text.RegisterText;
import com.uni_course_planner.service.relation.user.UserService;

@Controller
public class RegisterController 
{	
	private UserService userService;
	private EmailService emailService;

	public RegisterController(UserService userService, EmailService emailService)
	{
		this.userService = userService;
		this.emailService = emailService;
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
		emailService.sendEmail(emailField, new RegisterText(userField));
		
		return PageAddress.LOGIN_PAGE_ADDRESS;
	}
}
