package com.uni_course_planner.service.email.text;

public class RegisterText extends EmailText
{
	public RegisterText(String username) 
	{
		this.text = "Hallo " + username + ",\n\n"
				+ "willkommen bei Uni-Course-Planner!\n\n"
				+ "Dein Uni-Course-Planner-Team";
		
		this.topic = "Registrierung";
	}
}
