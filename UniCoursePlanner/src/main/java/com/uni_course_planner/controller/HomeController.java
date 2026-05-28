package com.uni_course_planner.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController
{
	private static final String HOME_PAGE_ADDRESS = "page/core/home";
	
	@GetMapping(HOME_PAGE_ADDRESS)
	public String loadLogInPage()
	{
		return HOME_PAGE_ADDRESS;
	}
}
