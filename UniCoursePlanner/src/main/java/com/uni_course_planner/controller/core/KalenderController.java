package com.uni_course_planner.controller.core;

import java.time.LocalDate;
import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.uni_course_planner.constants.views.PageAddress;
import com.uni_course_planner.dto.calender.CalenderDTOWithID;
import com.uni_course_planner.relation.user.LogInData;
import com.uni_course_planner.service.relation.calender.CalenderTableService;
import com.uni_course_planner.service.relation.user.UserService;

@Controller
public class KalenderController 
{
	private UserService userService;
	private CalenderTableService tableService;
	
	public KalenderController(UserService userService, CalenderTableService tableService)
	{
		this.userService = userService;
		this.tableService = tableService;
	}

	@GetMapping(PageAddress.KALENDER_PAGE_ADDRESS)
	public String loadKalenderPage(Model model)
	{
		return PageAddress.KALENDER_PAGE_ADDRESS;
	}
	
	@GetMapping(PageAddress.KALENDER_EVENTS_ADDRESS)
	@ResponseBody
	public List<CalenderDTOWithID> loadEvents(@RequestParam LocalDate date)
	{
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		LogInData user = userService.getUserByUsername(auth.getName());
		
		return tableService.fillCalenderTable(user.getId(), date);
	}
}
