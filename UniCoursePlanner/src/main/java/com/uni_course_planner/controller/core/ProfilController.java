package com.uni_course_planner.controller.core;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.uni_course_planner.constants.views.PageAddress;
import com.uni_course_planner.relation.user.LogInData;
import com.uni_course_planner.service.relation.user.UserService;

import jakarta.servlet.http.*;

@Controller
public class ProfilController
{
	private UserService userService;
	
	public ProfilController(UserService userService)
	{
		this.userService = userService;
	}
	
	@GetMapping(PageAddress.PROFIL_PAGE_ADDRESS)
	public String loadProfilPage(Model model)
	{
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		LogInData user = userService.getUserByUsername(auth.getName());
		
		model.addAttribute("username", user.getUsername());
		model.addAttribute("email", userService.getEmailFromUser(user.getId()));
		
		return PageAddress.PROFIL_PAGE_ADDRESS;
	}
	
	@PostMapping("/changeUserData")
	public String editUserData(@RequestParam(name="user-field") String userField, 
			@RequestParam(name="email-field") String emailField,
			HttpServletRequest request, HttpServletResponse response)
	{
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		LogInData currentUser = userService.getUserByUsername(auth.getName());
		
		userService.changeUserData(currentUser, userField, emailField);
		
		//Benutzer ausloggen
		new SecurityContextLogoutHandler().logout(request, response, auth);

		return "redirect:/" + PageAddress.LOGIN_PAGE_ADDRESS;
	}
}
