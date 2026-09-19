package com.uni_course_planner.controller.login;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.uni_course_planner.constants.views.PageAddress;
import com.uni_course_planner.service.relation.user.UserService;

@Controller
public class CodePasswortController 
{
	private UserService userService;
	
	public CodePasswortController(UserService userService)
	{
		this.userService = userService;
	}
	
	@GetMapping(PageAddress.CODE_PAGE_ADDRESS)
	public String loadCodePage(Model model)
	{
		return PageAddress.CODE_PAGE_ADDRESS;
	}
	
	@PostMapping("/confirmEmail")
	public String confirmEmail(@RequestParam(name="user-field") String userField)
	{
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();

		return "redirect:/" + PageAddress.LOGIN_PAGE_ADDRESS;
	}
}
