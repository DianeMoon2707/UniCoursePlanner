package com.uni_course_planner.controller.login;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.uni_course_planner.constants.views.PageAddress;
import com.uni_course_planner.service.email.EmailService;
import com.uni_course_planner.service.email.text.CodeText;
import com.uni_course_planner.service.relation.user.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
public class CodePasswortController 
{
	private UserService userService;
	private EmailService emailService;
	
	public CodePasswortController(UserService userService, EmailService emailService)
	{
		this.userService = userService;
		this.emailService = emailService;
	}
	
	@GetMapping(PageAddress.CODE_PAGE_ADDRESS)
	public String loadCodePage(Model model)
	{
		return PageAddress.CODE_PAGE_ADDRESS;
	}
	
	@PostMapping("/verifyCode")
	public String confirmEmail(Model model, @RequestParam(name = "code-field") String codeField, HttpSession session)
	{
		String username = (String)session.getAttribute("username");
		String email = (String)session.getAttribute("email");
		String password = (String)session.getAttribute("password");
		String code = (String)session.getAttribute("code");
		
		if(codeField != null && code.equals(codeField))
		{
			userService.changePassword(username, password);
			emailService.sendEmail(email, new CodeText(username));
			
			this.removeSessionAttribute(session);
			
			return PageAddress.LOGIN_PAGE_ADDRESS;
		}
		else
		{
			model.addAttribute("errorMessage", "Falscher Code!");
			return PageAddress.CODE_PAGE_ADDRESS;
		}		
	}
	
	private void removeSessionAttribute(HttpSession session)
	{
		session.removeAttribute("username");
		session.removeAttribute("email");
		session.removeAttribute("password");
		session.removeAttribute("code");
	}
}
