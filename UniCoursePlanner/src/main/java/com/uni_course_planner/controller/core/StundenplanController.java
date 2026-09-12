package com.uni_course_planner.controller.core;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.uni_course_planner.constants.views.PageAddress;
import com.uni_course_planner.relation.user.LogInData;
import com.uni_course_planner.service.relation.timetable.TimetableTableService;
import com.uni_course_planner.service.relation.user.UserService;

@Controller
public class StundenplanController 
{	
	private UserService userService;
	private TimetableTableService timetableService;
	
	public StundenplanController(UserService userService, TimetableTableService timetableService) 
	{
		this.userService = userService;
		this.timetableService = timetableService;
	}

	@GetMapping(PageAddress.STUNDENPLAN_PAGE_ADDRESS)
	public String showStundenplanPage(Model model)
	{
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		LogInData user = userService.getUserByUsername(auth.getName());
		
		model.addAttribute("timetable", timetableService.fillTimetable(user));
		
		return PageAddress.STUNDENPLAN_PAGE_ADDRESS;
	}
}
