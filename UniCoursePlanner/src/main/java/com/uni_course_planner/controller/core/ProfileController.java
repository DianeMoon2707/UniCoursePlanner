package com.uni_course_planner.controller.core;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.uni_course_planner.constants.views.PageRoutes;
import com.uni_course_planner.entity.user.LogInData;
import com.uni_course_planner.service.relation.user.UserService;

import jakarta.servlet.http.*;

@Controller
public class ProfileController
{
	private UserService userService;
	
	public ProfileController(UserService userService)
	{
		this.userService = userService;
	}
	
	@GetMapping(PageRoutes.PROFILE)
	public String loadProfilePage(Model model)
	{
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		LogInData user = userService.getUserByUsername(auth.getName());
		
		model.addAttribute("username", user.getUsername());
		model.addAttribute("email", userService.getEmailFromUser(user.getId()));
		
		return PageRoutes.PROFILE;
	}
	
	//Edit Username and/or Email
	@PostMapping("/changeUserData")
	public String editUserData(Model model,
			@RequestParam(name="user-field") String userField, 
			@RequestParam(name="email-field") String emailField,
			HttpServletRequest request, HttpServletResponse response)
	{
		try
		{
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			LogInData currentUser = userService.getUserByUsername(auth.getName());
		
			userService.changeUserData(currentUser, userField, emailField);
		
			//Log out the user after changing their account data
			new SecurityContextLogoutHandler().logout(request, response, auth);

			return "redirect:/" + PageRoutes.LOGIN;
		}
		catch(Exception e)
		{
			model.addAttribute("errorMessage", e.getMessage());
			return PageRoutes.PROFILE;
		}
	}
}
