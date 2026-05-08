package com.uni_course_planner.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class RegisterController {

	private static final String REGISTER_PAGE_ADRESS = "page/logIn/register";
	
	@GetMapping(REGISTER_PAGE_ADRESS)
	public String loadRegisterPage()
	{
		return REGISTER_PAGE_ADRESS;
	}
	
	@PostMapping("/register")
	public String register()
	{
		return "login";
	}
}
