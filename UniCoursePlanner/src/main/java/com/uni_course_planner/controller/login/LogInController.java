package com.uni_course_planner.controller.login;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.uni_course_planner.constants.views.PageAddress;

@Controller
public class LogInController
{	
	@GetMapping("/login")
	public String loadLogInPage(Model model, @RequestParam(required = false) String error)
	{
		if(error != null)
		{
			model.addAttribute("errorMessage", "Benutzername oder Passwort falsch");
		}
		
		return PageAddress.LOGIN_PAGE_ADDRESS;
	}
}
