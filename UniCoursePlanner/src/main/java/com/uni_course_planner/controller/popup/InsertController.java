package com.uni_course_planner.controller.popup;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.uni_course_planner.constants.popup.InsertType;
import com.uni_course_planner.constants.views.PageAddress;

@Controller
public class InsertController 
{
	@GetMapping(PageAddress.INSERT_POPUP_ADDRESS)
	public String showInsertPopup(@RequestParam InsertType type, Model model)
	{
		model.addAttribute("type", type);
		model.addAttribute("headline", type.getHeadline());
		
		return PageAddress.INSERT_POPUP_ADDRESS;
	}
}
