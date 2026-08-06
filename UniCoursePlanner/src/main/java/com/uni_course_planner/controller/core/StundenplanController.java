package com.uni_course_planner.controller.core;

import java.time.LocalDate;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.uni_course_planner.constants.views.PageAddress;
import com.uni_course_planner.relation.user.LogInData;
import com.uni_course_planner.service.relation.timetable.TimetableTableService;
import com.uni_course_planner.service.relation.user.UserService;
import com.uni_course_planner.service.relation.zeitraum.ZeitraumService;

@Controller
public class StundenplanController 
{
	private static final String STANDARD_FRAGMENT = "standard";
	private static final String TIMETABLE_FRAGMENT = "timetable";
	
	private UserService userService;
	private ZeitraumService zeitraumService;
	private TimetableTableService timetableService;
	
	public StundenplanController(UserService userService, ZeitraumService zeitraumService, TimetableTableService timetableService) 
	{
		this.userService = userService;
		
		this.zeitraumService = zeitraumService;
		this.timetableService = timetableService;
	}

	@GetMapping(PageAddress.STUNDENPLAN_PAGE_ADDRESS)
	public String showStundenplanPage(Model model)
	{
		this.renderStundenplanPage(model);
		return PageAddress.STUNDENPLAN_PAGE_ADDRESS;
	}
	
	@PostMapping("/saveDate")
	public String saveZeitraum(@RequestParam String validFrom,
			@RequestParam String validTo, Model model)
	{
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		LogInData user = userService.getUserByUsername(auth.getName());
		
		zeitraumService.saveZeitraum(
			LocalDate.parse(validFrom), 
			LocalDate.parse(validTo), 
			user
		);
		
		return "redirect:" + PageAddress.STUNDENPLAN_PAGE_ADDRESS;
	}
	
	private void renderStundenplanPage(Model model)
	{
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		LogInData user = userService.getUserByUsername(auth.getName());
		
		if(zeitraumService.actuellZeitraumExistsByUser(user))
		{
			model.addAttribute("fragmentName", TIMETABLE_FRAGMENT);
			model.addAttribute("timetable", timetableService.fillTimetable(user));
		}
		else
		{
			model.addAttribute("fragmentName", STANDARD_FRAGMENT);
			
			LocalDate today = LocalDate.now();
			model.addAttribute("today", today);
			model.addAttribute("later", today.plusMonths(3));
		}
	}
}
