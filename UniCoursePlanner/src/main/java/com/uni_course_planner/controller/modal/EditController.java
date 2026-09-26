package com.uni_course_planner.controller.modal;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.uni_course_planner.constants.views.*;
import com.uni_course_planner.dto.FieldDTO;
import com.uni_course_planner.entity.user.LogInData;
import com.uni_course_planner.service.modal.strategy.ModalServiceFactory;
import com.uni_course_planner.service.relation.user.UserService;

@Controller
public class EditController 
{
	private UserService userService;
	private ModalServiceFactory serviceFactory;
	
	public EditController(UserService userService, ModalServiceFactory serviceFactory) 
	{
		this.userService = userService;
		this.serviceFactory = serviceFactory;
	}
	
	@ModelAttribute("fieldDTO")
	public FieldDTO fieldDTO(@RequestParam ModalType editType, @RequestParam(required = false) String data) 
	{
		if(data == null)
		{
			return serviceFactory.getEditService(editType).createDTO();
		}
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		LogInData currentUser = userService.getUserByUsername(auth.getName());
		
	    return serviceFactory.getEditService(editType).createDTO(data, currentUser);
	}
	
	@GetMapping(PageRoutes.EDIT_MODAL_ADDRESS)
	public String showEditModal(@RequestParam ModalType editType, Model model)
	{		
		model.addAttribute("fragmentPath", editType.getFragmentFile());		
		model.addAttribute("editType", editType);
		
		return editType.getFragmentFile() + " :: edit-mask";
	}
	
	@PostMapping("/edit")
	@ResponseBody
	public ResponseEntity<?> edit(@RequestParam ModalType editType, @ModelAttribute FieldDTO fieldDTO)
	{
		try
		{
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			LogInData currentUser = userService.getUserByUsername(auth.getName());
			
			serviceFactory.getEditService(editType).edit(fieldDTO, currentUser);
			return ResponseEntity.ok().build();
		}
		catch(Exception e)
		{
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
}
