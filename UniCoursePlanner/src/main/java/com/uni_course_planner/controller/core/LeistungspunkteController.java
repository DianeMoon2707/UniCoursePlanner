package com.uni_course_planner.controller.core;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.uni_course_planner.constants.views.PageAddress;
import com.uni_course_planner.relation.user.LogInData;
import com.uni_course_planner.service.relation.modulnote.ModulnoteTableService;
import com.uni_course_planner.service.relation.user.UserService;

@Controller
public class LeistungspunkteController 
{
	private UserService userService;
	private ModulnoteTableService modulnotenTableService;
	
	public LeistungspunkteController(UserService userService, ModulnoteTableService modulnotenTableService) 
	{
		this.userService = userService;
		this.modulnotenTableService = modulnotenTableService;
	}
	
	@GetMapping(PageAddress.LEISTUNGSPUNKTE_PAGE_ADDRESS)
	public String loadLeistungspunktePage(Model model)
	{
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		LogInData user = userService.getUserByUsername(auth.getName());
		
		model.addAttribute("modulnoten", modulnotenTableService.fillLeistungspunkteTable(user.getId()));
		model.addAttribute("lp_gesamt", modulnotenTableService.sumByUserId(user.getId()));
		
		return PageAddress.LEISTUNGSPUNKTE_PAGE_ADDRESS;
	}
}
