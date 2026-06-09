package com.uni_course_planner.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.uni_course_planner.constants.views.PageAddress;

@Controller
public class RootController 
{
	@GetMapping("/")
	public String root() 
	{
	    return "redirect:" + PageAddress.HOME_PAGE_ADDRESS;
	}
}
