package com.uni_course_planner.controller.login;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.uni_course_planner.constants.views.PageRoutes;
import com.uni_course_planner.entity.user.LogInData;
import com.uni_course_planner.service.email.EmailService;
import com.uni_course_planner.service.email.text.ForgotPasswordText;
import com.uni_course_planner.service.relation.user.UserService;
import com.uni_course_planner.service.security.CodeGenerator;

import jakarta.servlet.http.HttpSession;

@Controller
public class ForgotPasswordController 
{
	private UserService userService;
	private EmailService emailService;
	private CodeGenerator codeGenerator;
	
	public ForgotPasswordController(UserService userService, EmailService emailService,
			CodeGenerator codeGenerator) 
	{
		this.userService = userService;
		this.emailService = emailService;
		this.codeGenerator = codeGenerator;
	}

	@GetMapping(PageRoutes.PASSWORT_VERGESSEN_PAGE_ADDRESS)
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
		
		return PageRoutes.PASSWORT_VERGESSEN_PAGE_ADDRESS;
	}
	
	@PostMapping("/changePasswort")
	public String editPasswort(Model model,
			@RequestParam(name="authentication-field") String authenticationField, 
			@RequestParam(name="password-field") String passwordField, HttpSession session)
	{	
		String username = userService.getUsernameFromAuthenticationField(authenticationField);
		String email = userService.getEmailFromAuthenticationField(authenticationField);
		
		if(!userService.userExistsByUsername(username))
		{
			model.addAttribute("errorMessage", "Es existiert kein Nutzer zu dieser Email-Adresse oder diesem Benutzernamen.");
			return PageRoutes.PASSWORT_VERGESSEN_PAGE_ADDRESS;
		}
		else
		{
			String code = codeGenerator.generateCode();
			this.setSessionAttributes(session, username, email, passwordField, code);
			emailService.sendEmail(email, new ForgotPasswordText(username, passwordField, code));
			return "redirect:/" + PageRoutes.CODE_PAGE_ADDRESS;
		}
	}
	
	private void setSessionAttributes(HttpSession session, String username, String email, String password, String code)
	{
		session.setAttribute("username", username);
		session.setAttribute("email", email);
		session.setAttribute("password", password);
		session.setAttribute("code", code);
	}
}
