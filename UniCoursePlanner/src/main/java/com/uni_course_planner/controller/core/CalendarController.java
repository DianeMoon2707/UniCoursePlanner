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
import com.uni_course_planner.service.entity.calendar.CalendarTableService;
import com.uni_course_planner.service.entity.user.UserService;

@Controller
public class CalendarController 
{
	private UserService userService;
	private CalendarTableService calendarTableService;
	
	public CalendarController(UserService userService, CalendarTableService calendarTableService)
	{
		this.userService = userService;
		this.calendarTableService = calendarTableService;
	}

	@GetMapping(PageRoutes.CALENDAR)
	public String loadCalendarPage(Model model)
	{
		return PageRoutes.CALENDAR;
	}
	
	//Load calendar-events for the currently authenticated user
	@GetMapping(PageRoutes.CALENDAR_EVENTS)
	@ResponseBody
	public List<CalendarDTOWithID> loadEvents(@RequestParam LocalDate date)
	{
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		LogInData user = userService.getUserByUsername(auth.getName());
		
		return calendarTableService.fillCalendarTable(user.getId(), date);
	}
	
	//Load all dates with events for the currently authenticated user
	@GetMapping(PageRoutes.CALENDAR_DATES)
	@ResponseBody
	public List<LocalDate> loadEventDates(@RequestParam int year, @RequestParam int month)
	{
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		LogInData user = userService.getUserByUsername(auth.getName());
		
		return calendarTableService.getEventDays(user.getId(), year, month);
	}
}
