package com.uni_course_planner.controller.core;

import java.time.LocalDate;
import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.uni_course_planner.constants.views.PageRoutes;
import com.uni_course_planner.dto.calendar.CalendarDTOWithID;
import com.uni_course_planner.entity.user.LogInData;
import com.uni_course_planner.service.relation.calendar.CalendarTableService;
import com.uni_course_planner.service.relation.user.UserService;

@Controller
public class CalendarController 
{
	private UserService userService;
	private CalendarTableService tableService;
	
	public CalendarController(UserService userService, CalendarTableService tableService)
	{
		this.userService = userService;
		this.tableService = tableService;
	}

	@GetMapping(PageRoutes.CALENDAR)
	public String loadCalendarPage(Model model)
	{
		return PageRoutes.CALENDAR;
	}
	
	@GetMapping(PageRoutes.CALENDAR_EVENTS)
	@ResponseBody
	public List<CalendarDTOWithID> loadEvents(@RequestParam LocalDate date)
	{
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		LogInData user = userService.getUserByUsername(auth.getName());
		
		return tableService.fillCalendarTable(user.getId(), date);
	}
	
	@GetMapping(PageRoutes.CALENDAR_DATES)
	@ResponseBody
	public List<LocalDate> loadEventDates(@RequestParam int year, @RequestParam int month)
	{
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		LogInData user = userService.getUserByUsername(auth.getName());
		
		return tableService.getEventDays(user.getId(), year, month);
	}
}
