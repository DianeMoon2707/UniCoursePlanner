package com.uni_course_planner.controller.core;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.uni_course_planner.constants.views.PageAddress;

@Controller
public class HomeController
{
	@GetMapping(PageAddress.HOME_PAGE_ADDRESS)
	public String loadHomePage()
	{		
		return PageAddress.HOME_PAGE_ADDRESS;
	}
}
