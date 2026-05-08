package com.uni_course_planner.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class LogInController {

	private static final String LOGIN_PAGE_ADRESS = "page/logIn/index";
	
	@GetMapping("/")
	public String loadLogInPage()
	{
		return LOGIN_PAGE_ADRESS;
	}
	
	@PostMapping("/login")
	public String proofLogInData()
	{
		return "login";
	}
}
