package com.uni_course_planner.controller.popup;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.uni_course_planner.constants.popup.InsertType;
import com.uni_course_planner.constants.views.PageAddress;
import com.uni_course_planner.dto.FieldDTO;
import com.uni_course_planner.relation.user.LogInData;
import com.uni_course_planner.service.popup.strategy.*;
import com.uni_course_planner.service.relation.user.UserService;

@Controller
public class InsertController 
{	
	private UserService userService;
	private PopupServiceFactory serviceFactory;
	
	public InsertController(UserService userService, PopupServiceFactory serviceFactory) 
	{
		this.userService = userService;
		this.serviceFactory = serviceFactory;
	}

	@ModelAttribute("fieldDTO")
	public FieldDTO fieldDTO(@RequestParam InsertType insertType) 
	{
	    return serviceFactory.get(insertType).createDTO();
	}
	
	@GetMapping(PageAddress.INSERT_POPUP_ADDRESS)
	public String showInsertPopup(@RequestParam InsertType insertType, Model model)
	{		
		model.addAttribute("fragmentPath", insertType.getFragmentFile());		
		model.addAttribute("insertType", insertType);
		
		return PageAddress.INSERT_POPUP_ADDRESS;
	}
	
	@PostMapping("/insert")
	public String insert(@RequestParam InsertType insertType, @ModelAttribute FieldDTO fieldDTO)
	{
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		LogInData currentUser = userService.getUserByUsername(auth.getName());
		
		serviceFactory.get(insertType).save(fieldDTO, currentUser);
		return PageAddress.MODUL_PAGE_ADDRESS;
	}
}
