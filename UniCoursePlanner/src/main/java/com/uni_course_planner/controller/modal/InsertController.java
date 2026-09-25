package com.uni_course_planner.controller.modal;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.uni_course_planner.constants.views.ModalType;
import com.uni_course_planner.constants.views.PageAddress;
import com.uni_course_planner.dto.FieldDTO;
import com.uni_course_planner.relation.user.LogInData;
import com.uni_course_planner.service.modal.strategy.*;
import com.uni_course_planner.service.relation.user.UserService;

@Controller
public class InsertController 
{	
	private UserService userService;
	private ModalServiceFactory serviceFactory;
	
	public InsertController(UserService userService, ModalServiceFactory serviceFactory) 
	{
		this.userService = userService;
		this.serviceFactory = serviceFactory;
	}

	@ModelAttribute("fieldDTO")
	public FieldDTO fieldDTO(@RequestParam ModalType insertType) 
	{
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		LogInData currentUser = userService.getUserByUsername(auth.getName());
		
	    return serviceFactory.getInsertService(insertType).createDTO(currentUser);
	}
	
	@GetMapping(PageAddress.INSERT_MODAL_ADDRESS)
	public String showInsertModal(@RequestParam ModalType insertType, Model model)
	{		
		model.addAttribute("fragmentPath", insertType.getFragmentFile());		
		model.addAttribute("insertType", insertType);
		
		return insertType.getFragmentFile() + " :: insert-mask";
	}
	
	@PostMapping("/insert")
	@ResponseBody
	public ResponseEntity<?> insert(@RequestParam ModalType insertType, @ModelAttribute FieldDTO fieldDTO)
	{
		try
		{
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			LogInData currentUser = userService.getUserByUsername(auth.getName());
			
			serviceFactory.getInsertService(insertType).save(fieldDTO, currentUser);
			return ResponseEntity.ok().build();
		}
		catch(Exception e)
		{
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
}
