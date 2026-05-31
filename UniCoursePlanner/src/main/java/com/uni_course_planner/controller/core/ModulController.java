package com.uni_course_planner.controller.core;

import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.uni_course_planner.constants.views.PageAddress;
import com.uni_course_planner.relation.user.LogInData;
import com.uni_course_planner.service.relation.user.UserService;

@Controller
public class ModulController 
{
	private UserService userService;
	
	public ModulController(UserService userService)
	{
		this.userService = userService;
	}

	@GetMapping(PageAddress.MODUL_PAGE_ADDRESS)
	public String loadModulPage(Model model)
	{
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
		String username = auth.getName();
		LogInData currentUser = userService.getUserByUsername(username);
		
		/*List<String> headlines = List.of("W1", "W2", "W3");
		List<List<String>> tableContent = List.of(
			List.of("1", "w", "2"),
			List.of("2", "3", "d")
		);
		
		model.addAttribute("headlineTable", headlines);
		model.addAttribute("tableContent", tableContent);*/
		
		return PageAddress.MODUL_PAGE_ADDRESS;
	}
}
