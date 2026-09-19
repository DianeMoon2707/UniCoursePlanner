package com.uni_course_planner.controller.login;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.uni_course_planner.constants.views.PageAddress;
import com.uni_course_planner.relation.user.LogInData;
import com.uni_course_planner.service.relation.user.UserService;

@Controller
public class PasswortVergessenController 
{
	private UserService userService;
	
	public PasswortVergessenController(UserService userService)
	{
		this.userService = userService;
	}
	
	@GetMapping(PageAddress.PASSWORT_VERGESSEN_PAGE_ADDRESS)
	public String loadPasswortVergessenPage(Model model)
	{
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
		if(auth != null && auth.isAuthenticated() && !(auth instanceof AnonymousAuthenticationToken))
		{
			LogInData user = userService.getUserByUsername(auth.getName());
			
			model.addAttribute("loggedIn", true);
			model.addAttribute("username", user.getUsername());
		}
		else
		{
			model.addAttribute("loggedIn", false);
		}
		
		return PageAddress.PASSWORT_VERGESSEN_PAGE_ADDRESS;
	}
	
	@PostMapping("/changePasswort")
	public String editPasswort(@RequestParam(name="authentication-field") String userField, 
			@RequestParam(name="password-field") String passwordField)
	{

		return "redirect:/" + PageAddress.CODE_PAGE_ADDRESS;
	}
}
