package com.uni_course_planner.controller;


import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.uni_course_planner.constants.views.PageAddress;
import com.uni_course_planner.relation.user.LogInData;
import com.uni_course_planner.service.relation.user.UserService;

@Controller
public class HomeController
{
	private UserService userService;
	
	public HomeController(UserService userService)
	{
		this.userService = userService;
	}


	@GetMapping(PageAddress.HOME_PAGE_ADDRESS)
	public String loadLogInPage()
	{
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
		String username = auth.getName();
		LogInData currentUser = userService.getUserByUsername(username);
		
		return PageAddress.HOME_PAGE_ADDRESS;
	}
}
