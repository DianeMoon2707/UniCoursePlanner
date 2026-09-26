package com.uni_course_planner.controller.modal;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.uni_course_planner.constants.views.ModalType;
import com.uni_course_planner.constants.views.PageRoutes;
import com.uni_course_planner.dto.FieldDTO;
import com.uni_course_planner.entity.user.LogInData;
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
	public FieldDTO fieldDTO(@RequestParam ModalType deleteType, @RequestParam(required = false) String data) 
	{
		if(data == null)
		{
			return serviceFactory.getDeleteService(deleteType).createDTO();
		}
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		LogInData currentUser = userService.getUserByUsername(auth.getName());
		
	    return serviceFactory.getDeleteService(deleteType).createDTO(data, currentUser);
	}
	
	@GetMapping(PageRoutes.DELETE_MODAL)
	public String showDeleteModal(@RequestParam ModalType deleteType, Model model)
	{		
		model.addAttribute("fragmentPath", deleteType.getFragmentFile());		
		model.addAttribute("deleteType", deleteType);
		
		return deleteType.getFragmentFile() + " :: delete-mask";
	}
	
	@PostMapping("/delete")
	@ResponseBody
	public ResponseEntity<?> delete(@RequestParam ModalType deleteType, @ModelAttribute FieldDTO fieldDTO)
	{
		try
		{
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			LogInData currentUser = userService.getUserByUsername(auth.getName());
			
			serviceFactory.getDeleteService(deleteType).delete(fieldDTO, currentUser);
			return ResponseEntity.ok().build();
		}
		catch(Exception e)
		{
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
}
