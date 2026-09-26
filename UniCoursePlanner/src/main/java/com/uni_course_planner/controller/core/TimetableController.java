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
	private TimetableTableService timetableTableService;
	
	public TimetableController(UserService userService, TimetableTableService timetableTableService) 
	{
		this.userService = userService;
		this.timetableTableService = timetableTableService;
	}

	@GetMapping(PageRoutes.TIMETABLE)
	public String showTimetablePage(Model model)
	{
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		LogInData user = userService.getUserByUsername(auth.getName());
		
		model.addAttribute("timetable", timetableTableService.fillTimetable(user));
		
		return PageRoutes.TIMETABLE;
	}
}
