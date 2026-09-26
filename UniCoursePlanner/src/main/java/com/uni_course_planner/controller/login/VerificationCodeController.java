package com.uni_course_planner.controller.login;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.uni_course_planner.constants.views.PageRoutes;
import com.uni_course_planner.service.email.EmailService;
import com.uni_course_planner.service.email.text.CodeText;
import com.uni_course_planner.service.relation.user.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
public class VerificationCodeController 
{
	private UserService userService;
	private EmailService emailService;
	
	public VerificationCodeController(UserService userService, EmailService emailService)
	{
		this.userService = userService;
		this.emailService = emailService;
	}
	
	@GetMapping(PageRoutes.VERIFICATION_CODE)
	public String loadCodePage(Model model)
	{
		return PageRoutes.VERIFICATION_CODE;
	}
	
	@PostMapping("/verifyCode")
	public String verifyCode(Model model, @RequestParam(name = "code-field") String codeField, HttpSession session)
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
			
			return PageRoutes.LOGIN;
		}
		else
		{
			model.addAttribute("errorMessage", "Falscher Code!");
			return PageRoutes.VERIFICATION_CODE;
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
