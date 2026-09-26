package com.uni_course_planner.controller.core;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.uni_course_planner.constants.views.PageRoutes;
import com.uni_course_planner.entity.user.LogInData;
import com.uni_course_planner.service.relation.timetable.TimetableTableService;
import com.uni_course_planner.service.relation.user.UserService;

@Controller
public class TimetableController 
{	
	private UserService userService;
	private TimetableTableService timetableService;
	
	public TimetableController(UserService userService, TimetableTableService timetableService) 
	{
		this.userService = userService;
		this.timetableService = timetableService;
	}

	@GetMapping(PageRoutes.TIMETABLE)
	public String showTimetablePage(Model model)
	{
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		LogInData user = userService.getUserByUsername(auth.getName());
		
		model.addAttribute("timetable", timetableService.fillTimetable(user));
		
		return PageRoutes.TIMETABLE;
	}
}
