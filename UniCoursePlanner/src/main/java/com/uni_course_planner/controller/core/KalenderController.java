package com.uni_course_planner.controller.core;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.uni_course_planner.constants.views.PageAddress;
import com.uni_course_planner.relation.user.LogInData;
import com.uni_course_planner.service.relation.user.UserService;

@Controller
public class KalenderController 
{
	private UserService userService;
	
	public KalenderController(UserService userService)
	{
		this.userService = userService;
	}
	
	@GetMapping(PageAddress.KALENDER_PAGE_ADDRESS)
	public String loadKalenderPage(Model model)
	{
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		LogInData user = userService.getUserByUsername(auth.getName());
		
		return PageAddress.KALENDER_PAGE_ADDRESS;
	}
}
