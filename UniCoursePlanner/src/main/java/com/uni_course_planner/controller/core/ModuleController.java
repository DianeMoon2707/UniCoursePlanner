package com.uni_course_planner.controller.core;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.uni_course_planner.constants.views.PageRoutes;
import com.uni_course_planner.entity.user.LogInData;
import com.uni_course_planner.service.entity.module.ModuleTableService;
import com.uni_course_planner.service.entity.user.UserService;

@Controller
public class ModuleController 
{
	private UserService userService;
	private ModuleTableService moduleTableService;
	
	public ModuleController(UserService userService, ModuleTableService moduleTableService)
	{
		this.userService = userService;
		this.moduleTableService = moduleTableService;
	}
	
	@GetMapping(PageRoutes.MODULE)
	public String loadModulePage(Model model)
	{
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		LogInData user = userService.getUserByUsername(auth.getName());
		
		model.addAttribute("modules", moduleTableService.fillModuleTable(user.getId()));
		
		return PageRoutes.MODULE;
	}
}
