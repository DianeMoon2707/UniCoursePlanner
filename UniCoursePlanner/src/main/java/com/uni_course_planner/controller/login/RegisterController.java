package com.uni_course_planner.controller.login;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.uni_course_planner.constants.views.PageRoutes;
import com.uni_course_planner.service.email.EmailService;
import com.uni_course_planner.service.email.text.RegisterText;
import com.uni_course_planner.service.entity.user.UserService;

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

	@GetMapping(PageRoutes.REGISTER)
	public String loadRegisterPage()
	{
		return PageRoutes.REGISTER;
	}
	
	@PostMapping("/register")
	public String register(Model model,
			@RequestParam(name="user-field") String userField,
			@RequestParam(name="email-field") String emailField,
			@RequestParam(name="password-field") String passwordField)
	{		
		try
		{
			userService.registerUser(emailField, userField, passwordField);	
			emailService.sendEmail(emailField, new RegisterText(userField));
			return "redirect:/" + PageRoutes.LOGIN;
		}
		catch(Exception e)
		{
			model.addAttribute("errorMessage", e.getMessage());
			return PageRoutes.REGISTER;
		}
	}
}
