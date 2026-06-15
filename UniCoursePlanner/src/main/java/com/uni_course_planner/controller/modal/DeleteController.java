package com.uni_course_planner.controller.modal;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.uni_course_planner.constants.views.ModalType;
import com.uni_course_planner.constants.views.PageAddress;
import com.uni_course_planner.dto.FieldDTO;
import com.uni_course_planner.relation.user.LogInData;
import com.uni_course_planner.service.modal.strategy.ModalServiceFactory;
import com.uni_course_planner.service.relation.user.UserService;

@Controller
public class DeleteController 
{
	private UserService userService;
	private ModalServiceFactory serviceFactory;
	
	public DeleteController(UserService userService, ModalServiceFactory serviceFactory) 
	{
		this.userService = userService;
		this.serviceFactory = serviceFactory;
	}
	
	@ModelAttribute("fieldDTO")
	public FieldDTO fieldDTO(@RequestParam ModalType deleteType, @RequestParam(required = false) String rowData) 
	{
		if(rowData == null)
		{
			return serviceFactory.getDeleteService(deleteType).createDTO();
		}
		
	    return serviceFactory.getDeleteService(deleteType).createDTO(rowData);
	}
	
	@GetMapping(PageAddress.DELETE_MODAL_ADDRESS)
	public String showDeleteModal(@RequestParam ModalType deleteType, Model model)
	{		
		model.addAttribute("fragmentPath", deleteType.getFragmentFile());		
		model.addAttribute("deleteType", deleteType);
		
		return deleteType.getFragmentFile() + " :: delete-mask";
	}
	
	@PostMapping("/delete")
	public String delete(@RequestParam ModalType deleteType, @ModelAttribute FieldDTO fieldDTO)
	{
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		LogInData currentUser = userService.getUserByUsername(auth.getName());
		
		serviceFactory.getDeleteService(deleteType).delete(fieldDTO, currentUser);
		return PageAddress.MODUL_PAGE_ADDRESS;
	}
}
