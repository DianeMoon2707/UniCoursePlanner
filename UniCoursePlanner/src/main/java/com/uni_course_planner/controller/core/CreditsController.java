package com.uni_course_planner.controller.core;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.uni_course_planner.constants.views.PageRoutes;
import com.uni_course_planner.entity.user.LogInData;
import com.uni_course_planner.service.entity.grade.GradeTableService;
import com.uni_course_planner.service.entity.user.UserService;

@Controller
public class CreditsController 
{
	private UserService userService;
	private GradeTableService gradeTableService;
	
	public CreditsController(UserService userService, GradeTableService gradeTableService) 
	{
		this.userService = userService;
		this.gradeTableService = gradeTableService;
	}
	
	@GetMapping(PageRoutes.CREDITS)
	public String loadCreditsPage(Model model)
	{
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		LogInData user = userService.getUserByUsername(auth.getName());
		
		model.addAttribute("grades", gradeTableService.fillCreditsTable(user.getId()));
		model.addAttribute("totalCredits", gradeTableService.getTotalCreditsByUserId(user.getId()));
		
		return PageRoutes.CREDITS;
	}
}
