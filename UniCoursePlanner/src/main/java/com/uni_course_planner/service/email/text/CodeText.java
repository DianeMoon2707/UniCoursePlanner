package com.uni_course_planner.service.email.text;

public class CodeText extends EmailText
{
	public CodeText(String username) 
	{
		this.text = "Hallo " + username + ", \n\n"
				+ "dein neues Passwort wurde aktiviert.\n\n"
				+ "Dein Uni-Course-Planner-Team";
		
		this.topic = "Passwort aktiviert";
	}
}
