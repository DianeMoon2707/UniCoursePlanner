package com.uni_course_planner.controller.core;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.uni_course_planner.constants.views.PageAddress;
import com.uni_course_planner.relation.user.LogInData;
import com.uni_course_planner.service.relation.modul.ModulTableService;
import com.uni_course_planner.service.relation.user.UserService;

@Controller
public class ModulController 
{
	private UserService userService;
	private ModulTableService modulTableService;
	
	public ModulController(UserService userService, ModulTableService modulTableService)
	{
		this.userService = userService;
		this.modulTableService = modulTableService;
	}

	@GetMapping(PageAddress.MODUL_PAGE_ADDRESS)
	public String loadModulPage(Model model)
	{
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		LogInData user = userService.getUserByUsername(auth.getName());
		
		model.addAttribute("module", modulTableService.fillModulTable(user.getId()));
		
		return PageAddress.MODUL_PAGE_ADDRESS;
	}
}
