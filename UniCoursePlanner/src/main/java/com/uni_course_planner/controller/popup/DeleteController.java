package com.uni_course_planner.controller.popup;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.uni_course_planner.constants.views.PageAddress;
import com.uni_course_planner.constants.views.PopupType;
import com.uni_course_planner.dto.FieldDTO;
import com.uni_course_planner.relation.user.LogInData;
import com.uni_course_planner.service.modal.strategy.PopupServiceFactory;
import com.uni_course_planner.service.relation.user.UserService;

@Controller
public class DeleteController 
{
	private UserService userService;
	private PopupServiceFactory serviceFactory;
	
	public DeleteController(UserService userService, PopupServiceFactory serviceFactory) 
	{
		this.userService = userService;
		this.serviceFactory = serviceFactory;
	}
	
	@ModelAttribute("fieldDTO")
	public FieldDTO fieldDTO(@RequestParam PopupType deleteType, @RequestParam(required = false) String rowData) 
	{
		if(rowData == null)
		{
			return serviceFactory.getDeleteService(deleteType).createDTO();
		}
		
	    return serviceFactory.getDeleteService(deleteType).createDTO(rowData);
	}
	
	@GetMapping(PageAddress.DELETE_MODAL_ADDRESS)
	public String showDeletePopup(@RequestParam PopupType deleteType, Model model)
	{		
		model.addAttribute("fragmentPath", deleteType.getFragmentFile());		
		model.addAttribute("deleteType", deleteType);
		
		return deleteType.getFragmentFile() + " :: delete-mask";
	}
	
	@PostMapping("/delete")
	public String delete(@RequestParam PopupType deleteType, @ModelAttribute FieldDTO fieldDTO)
	{
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		LogInData currentUser = userService.getUserByUsername(auth.getName());
		
		serviceFactory.getDeleteService(deleteType).delete(fieldDTO, currentUser);
		return PageAddress.MODUL_PAGE_ADDRESS;
	}
}
