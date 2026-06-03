package com.uni_course_planner.controller.popup;

import java.util.Map;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.uni_course_planner.constants.popup.InsertType;
import com.uni_course_planner.constants.views.PageAddress;
import com.uni_course_planner.relation.user.LogInData;
import com.uni_course_planner.service.popup.PopupServiceFactory;
import com.uni_course_planner.service.relation.user.UserService;

@Controller
public class InsertController 
{	
	private UserService userService;
	private PopupServiceFactory serviceFactory;
	
	private InsertType insertType;
	
	public InsertController(UserService userService, PopupServiceFactory serviceFactory) 
	{
		this.userService = userService;
		this.serviceFactory = serviceFactory;
	}

	@GetMapping(PageAddress.INSERT_POPUP_ADDRESS)
	public String showInsertPopup(@RequestParam InsertType type, Model model)
	{
		insertType = type;
		
		model.addAttribute("fragmentPath", InsertType.MODUL.getFragmentFile());
		return PageAddress.INSERT_POPUP_ADDRESS;
	}
	
	@PostMapping("/insert")
	public String insert(@RequestParam Map<String, String> insertMap)
	{
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		LogInData currentUser = userService.getUserByUsername(auth.getName());
		
		serviceFactory.getInsertService(insertType).save(insertMap, currentUser);
		return PageAddress.MODUL_PAGE_ADDRESS;
		
	}
}
