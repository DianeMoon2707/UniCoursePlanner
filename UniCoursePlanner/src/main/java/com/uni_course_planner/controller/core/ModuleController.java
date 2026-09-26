package com.uni_course_planner.controller.core;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.uni_course_planner.constants.views.PageRoutes;
import com.uni_course_planner.entity.user.LogInData;
import com.uni_course_planner.service.relation.module.ModuleTableService;
import com.uni_course_planner.service.relation.user.UserService;

@Controller
public class ModuleController 
{
	private UserService userService;
	private ModuleTableService modulTableService;
	
	public ModuleController(UserService userService, ModuleTableService modulTableService)
	{
		this.userService = userService;
		this.modulTableService = modulTableService;
	}
	
	@GetMapping(PageRoutes.MODUL_PAGE_ADDRESS)
	public String loadModulPage(Model model)
	{
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		LogInData user = userService.getUserByUsername(auth.getName());
		
		model.addAttribute("module", modulTableService.fillModulTable(user.getId()));
		
		return PageRoutes.MODUL_PAGE_ADDRESS;
	}
}
