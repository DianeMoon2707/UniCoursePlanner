package com.uni_course_planner.controller.popup;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.uni_course_planner.constants.views.*;
import com.uni_course_planner.dto.FieldDTO;
import com.uni_course_planner.relation.user.LogInData;
import com.uni_course_planner.service.popup.strategy.PopupServiceFactory;
import com.uni_course_planner.service.relation.user.UserService;

@Controller
public class EditController 
{
	private UserService userService;
	private PopupServiceFactory serviceFactory;
	
	public EditController(UserService userService, PopupServiceFactory serviceFactory) 
	{
		this.userService = userService;
		this.serviceFactory = serviceFactory;
	}
	
	@ModelAttribute("fieldDTO")
	public FieldDTO fieldDTO(@RequestParam PopupType editType, @RequestParam(required = false) String rowData) 
	{
		if(rowData == null)
		{
			return serviceFactory.getEditService(editType).createDTO();
		}
		
	    return serviceFactory.getEditService(editType).createDTO(rowData);
	}
	
	@GetMapping(PageAddress.EDIT_POPUP_ADDRESS)
	public String showEditPopup(@RequestParam PopupType editType, Model model)
	{		
		model.addAttribute("fragmentPath", editType.getFragmentFile());		
		model.addAttribute("editType", editType);
		
		return PageAddress.EDIT_POPUP_ADDRESS;
	}
	
	@PostMapping("/edit")
	public String edit(@RequestParam PopupType editType, @ModelAttribute FieldDTO fieldDTO)
	{
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		LogInData currentUser = userService.getUserByUsername(auth.getName());
		
		serviceFactory.getEditService(editType).edit(fieldDTO, currentUser);
		return PageAddress.MODUL_PAGE_ADDRESS;
	}
}
