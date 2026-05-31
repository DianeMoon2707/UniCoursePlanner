package com.uni_course_planner.controller.popup;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.uni_course_planner.constants.popup.InsertType;
import com.uni_course_planner.constants.views.PageAddress;
import com.uni_course_planner.service.field.FieldService;

@Controller
public class InsertController 
{
	private final ApplicationContext context;	
	
	public InsertController(ApplicationContext context) 
	{
		this.context = context;
	}

	@GetMapping(PageAddress.INSERT_POPUP_ADDRESS)
	public String showInsertPopup(@RequestParam InsertType type, Model model)
	{
		model.addAttribute("type", type);
		model.addAttribute("headline", type.getHeadline());
		
		FieldService service = context.getBean(type.getServiceClass());
		model.addAttribute("fields", service.createInsertMask());
		
		return PageAddress.INSERT_POPUP_ADDRESS;
	}
}
